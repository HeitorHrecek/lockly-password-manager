import { Injectable } from '@angular/core';
import { BehaviorSubject, forkJoin, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map, tap, switchMap, take } from 'rxjs/operators';
import { Usuario } from '../folder-section/usuario';
import { LocalStorageService } from '../../local-storage.service';
import { SenhaSemPasta } from '../../senhaSemPasta';

@Injectable({
  providedIn: 'root'
})
export class SenhaService {

  private readonly API = 'http://localhost:8080/passwords';
  private senhas = new BehaviorSubject<{ id: number, nome: string; conteudo: string; isEditing: boolean }[]>([]);
  senhas$ = this.senhas.asObservable();

  constructor(
    private http: HttpClient,
    private localStorageService: LocalStorageService
  ) { }

  criar(senha: SenhaSemPasta): Observable<SenhaSemPasta> {
    return this.http.post<SenhaSemPasta>(this.API + '/register', senha);
  }

  criarSenha(id: number, nome: string, conteudo: string) {
    const senhasAtual = this.senhas.getValue();
    const novaSenha = { id, nome, conteudo, isEditing: true };
    this.senhas.next([...senhasAtual, novaSenha]);
  }

  salvarNome(index: number, nome: string, conteudo: string) {
    const senhasAtualizadas = [...this.senhas.getValue()];
    senhasAtualizadas[index] = { ...senhasAtualizadas[index], nome, conteudo, isEditing: false };
    this.senhas.next(senhasAtualizadas);

    const usuario = this.localStorageService.getItem<{ id: number, name: string, email: string, password: string }>('usuario');
    if (usuario != null) {
      this.criar(new SenhaSemPasta(0, nome, '', new Usuario(usuario.id, '', '', '')))
        .pipe(take(1))
        .subscribe();
    }
  }

  deletar(id: number) {
    this.http.delete(`${this.API}/delete/${id}`).pipe(
      tap(() => {
        const senhasAtualizadas = this.senhas.getValue().filter(senha => senha.id !== id);
        this.senhas.next(senhasAtualizadas);
      }),
      take(1)
    ).subscribe();
  }

  alterar(id: number, senha: SenhaSemPasta) {
    this.http.put<SenhaSemPasta>(`${this.API}/change-name`, { name: senha.name, id })
      .pipe(
        tap(senhaAtualizada => {
          const senhasAtualizadas = this.senhas.getValue().map(s => s.id === id ? { ...s, nome: senhaAtualizada.name } : s);
          this.senhas.next(senhasAtualizadas);
        }),
        take(1)
      ).subscribe();
  }

  consultarSenhas() {
    const usuario = { id: 4 };
    
    if (usuario != null) {
      this.http.get<{ id: number, name: string; content: string }[]>(`${this.API}/consult/all-by-user/${usuario.id}`)
        .pipe(
          map(senhasBack => senhasBack.map(senha => ({
            id: senha.id,
            nome: senha.name,
            conteudo: senha.content,
            isEditing: false
          }))),
          tap(senhasMapeadas => this.senhas.next(senhasMapeadas)),
          switchMap(senhasMapeadas =>
            forkJoin(
              senhasMapeadas.map(senha => 
                this.http.get<SenhaSemPasta>(`http://localhost:8080/password/decrypt/${senha.id}`)
                  .pipe(
                    map(decryptedSenha => ({
                      ...senha,
                      conteudo: decryptedSenha.content
                    }))
                  )
              )
            )
          ),
          tap(updatedSenhas => this.senhas.next(updatedSenhas)),
          take(1)
        ).subscribe(() => {});
    }
  }  

  consultarPorNome(nome: string): Observable<{id: number, name: string, content: string}> {
    return this.http.get<{id: number, name: string, content: string}>(`${this.API}/consult/by-name/${nome}`);
  }

  decriptografarSenha(id: number): Observable<{id: number, name: string, content: string}> {
    return this.http.get<{id: number, name: string, content: string}>(`http://localhost:8080/password/decrypt/${id}`);
  }
}
