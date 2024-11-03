import { LocalStorageService } from './../../local-storage.service';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, take, map, tap, switchMap, } from "rxjs";
import { Folder } from "./folder";
import { SenhaSemPasta } from "../../senhaSemPasta";
import { Usuario } from './usuario';

@Injectable({
    providedIn: 'root'
})
export class FolderService {
    salvarNome(index: number, nome: string) {
      throw new Error('Method not implemented.');
    }

    private readonly API = 'http://localhost:8080/folders'

    constructor(
        private http: HttpClient,
        private localStorageService: LocalStorageService
    ) { }

    private senhas = new BehaviorSubject<{ id: number, nome: string; conteudo: string; isEditing: boolean }[]>([]);
    senhas$ = this.senhas.asObservable();

    private pastas = new BehaviorSubject<{ id: number, nome: string; isEditing: boolean }[]>([]);
    pastas$ = this.pastas.asObservable();

    criar(folder: Folder): Observable<Folder> {
        return this.http.post<Folder>(this.API + '/register', folder);
    }

    salvarPasta(index: number, nome: string) {
        const pastasAtualizadas = [...this.pastas.getValue()];
        pastasAtualizadas[index] = { ...pastasAtualizadas[index], nome, isEditing: false };
        this.pastas.next(pastasAtualizadas);

        const usuario = this.localStorageService.getItem<{ id: number, name: string, email: string, password: string }>('usuario');

        if (usuario != null) {
            this.criar(new Folder(0, nome, new Usuario(usuario.id, '', '', '')))
                .pipe(take(1))
                .subscribe();
        }
    }

    consultarSenhasPorPasta(id: number) {

        this.http.get<{ id: number, name: string; content: string }[]>(`${this.API}/consult/all/folder/${id}`)
            .pipe(
                map(senhasBack => senhasBack.map(senha => ({
                    id: senha.id,
                    nome: senha.name,
                    conteudo: senha.content,
                    isEditing: false
                }))),
                tap(senhasMapeadas => this.senhas.next(senhasMapeadas)),
                switchMap(senhasMapeadas =>
                    this.http.get<SenhaSemPasta[]>('http://localhost:8080/password/decrypt/folder')
                        .pipe(
                            map(decryptedPasswords => {
                                const updatedSenhas = senhasMapeadas.map((senha, index) => ({
                                    ...senha,
                                    conteudo: decryptedPasswords[index].content
                                }));
                                this.senhas.next(updatedSenhas);
                            })
                        )
                ),
                take(1)
            ).subscribe();
    }

    consultarPastas() {
        // const usuario = this.localStorageService.getItem<{ id: number, name: string, email: string, password: string }>('usuario');
        const usuario = {id: 4};

        this.http.get<{id: number, name: string}[]>(`${this.API}/consult/all/${usuario.id}`)
            .pipe(
                map(pastasBack => pastasBack.map(pasta => ({
                    id: pasta.id,
                    nome: pasta.name,
                    isEditing: false,
                }))),
                tap(pastasMapeadas => this.pastas.next(pastasMapeadas))
            );
    }
}