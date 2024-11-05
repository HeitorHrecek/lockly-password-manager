import { SenhaSemPasta } from 'src/app/tela-principal/senhaSemPasta';
import { LocalStorageService } from './../../local-storage.service';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, take, map, tap, switchMap, forkJoin, } from "rxjs";
import { Folder } from "./folder";
import { Usuario } from './usuario';
import { SenhaComPasta } from '../../senhaComPasta';

@Injectable({
    providedIn: 'root'
})
export class FolderService {

    private readonly API = 'http://localhost:8080/folders'
    private readonly API_PASSWORDS = 'http://localhost:8080/passwords/folders'

    constructor(
        private http: HttpClient,
        private localStorageService: LocalStorageService
    ) { }

    private senhas = new BehaviorSubject<{ id: number, nome: string; conteudo: string; isEditing: boolean }[]>([]);
    senhas$ = this.senhas.asObservable();

    private pastas = new BehaviorSubject<{ id: number, nome: string; isEditing: boolean }[]>([]);
    pastas$ = this.pastas.asObservable();

    salvarPasta(index: number, nome: string) {
        const pastasAtualizadas = [...this.pastas.getValue()];
        pastasAtualizadas[index] = { ...pastasAtualizadas[index], nome, isEditing: false };
        this.pastas.next(pastasAtualizadas);

        // const usuario = this.localStorageService.getItem<{ id: number, name: string, email: string, password: string }>('usuario');
        const usuario = { id: 4 };

        if (usuario != null) {
            this.http.post<Folder>(this.API + '/register', new Folder(0, nome, new Usuario(usuario.id, '', '', '')))
                .subscribe(novaPasta => {
                    this.localStorageService.setItem('pasta', { id: novaPasta.id, nome: novaPasta.name });
                });
        }
    }

    consultarSenhasPorPasta(id: number) {
        this.http.get<{ id: number, name: string; content: string }[]>(`${this.API_PASSWORDS}/consult/all/folder/${id}`)
            .pipe(
                map(senhasBack => senhasBack.map(senha => ({
                    id: senha.id,
                    nome: senha.name,
                    conteudo: senha.content,
                    isEditing: false
                }))),
                tap(senhasMapeadas => this.senhas.next(senhasMapeadas)),
                switchMap(senhasMapeadas =>
                    forkJoin(senhasMapeadas.map(senha =>
                        this.http.get<SenhaSemPasta>(`http://localhost:8080/password/decrypt/folder/${senha.id}`)
                            .pipe(
                                map(decryptedPassword => ({
                                    ...senha,
                                    conteudo: decryptedPassword.content
                                }))
                            )
                    ))
                ),
                tap(updatedSenhas => this.senhas.next(updatedSenhas)),
                take(1)
            ).subscribe();

    }

    consultarPastas() {
        // const usuario = this.localStorageService.getItem<{ id: number, name: string, email: string, password: string }>('usuario');
        const usuario = { id: 4 };

        this.http.get<{ id: number, name: string }[]>(`${this.API}/${usuario.id}`)
            .pipe(
                map(pastasBack => pastasBack.map(pasta => ({
                    id: pasta.id,
                    nome: pasta.name,
                    isEditing: false,
                }))),
                tap(pastasMapeadas => this.pastas.next(pastasMapeadas))
            )
            .subscribe(pastasBack => {
                this.pastas.next(pastasBack);
            })
    }

    cadastrarSenhaComPasta(senha: SenhaComPasta): Observable<SenhaComPasta> {
        return this.http.post<SenhaComPasta>(this.API_PASSWORDS + '/register', senha);
    }

    consultarSenhaPorNome(nome: string): Observable<SenhaComPasta> {
        return this.http.get<{ id: number, name: string, content: string, userDto: Usuario, folderDto: Folder }>(`${this.API_PASSWORDS}/consult/name/${nome}`);
    }

    alterarSenha(id: number, senhaComPasta: SenhaComPasta) {
        this.http.put<SenhaComPasta>(`${this.API_PASSWORDS}/change-name`, { name: senhaComPasta.name, id })
            .pipe(
                tap(senhaAtualizada => {
                    const senhasAtualizadas = this.senhas.getValue().map(s => s.id === id ? { ...s, nome: senhaAtualizada.name } : s);
                    this.senhas.next(senhasAtualizadas);
                }),
                take(1)
            ).subscribe();

        console.log(this.senhas.getValue());
    }

    deletar(id: number) {
        this.http.delete(`${this.API_PASSWORDS}/delete/${id}`).pipe(
            tap(() => {
                const senhasAtualizadas = this.senhas.getValue().filter(senha => senha.id !== id);
                this.senhas.next(senhasAtualizadas);
            }),
            take(1)
        ).subscribe();
    }
}