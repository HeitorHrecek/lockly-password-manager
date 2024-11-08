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

    // private senhas = new BehaviorSubject<{ id: number, nome: string; conteudo: string; isEditing: boolean }[]>([]);
    // senhas$ = this.senhas.asObservable();

    private pastas = new BehaviorSubject<{ id: number, nome: string; isEditing: boolean }[]>([]);
    pastas$ = this.pastas.asObservable();

    // getSenhasPorIdPasta(id: number): Array<{ id: number, nome: string; conteudo: string; isEditing: boolean }> {
    //     return this.senhas.getValue().filter(senha => senha.)
    // }

    salvarPasta(index: number, nome: string) {
        const pastasAtualizadas = [...this.pastas.getValue()];
        pastasAtualizadas[index] = { ...pastasAtualizadas[index], nome, isEditing: false };
        this.pastas.next(pastasAtualizadas);

        // const usuario = this.localStorageService.getItem<{ id: number, name: string, email: string, password: string }>('usuario');
        const usuario = { id: 4 };

        if (usuario != null) {
            this.http.post<Folder>(this.API + '/register', new Folder(0, nome, new Usuario(usuario.id, '', '', '')))
                .pipe(take(1)).subscribe(novaPasta => {
                    this.localStorageService.setItem('pasta', { id: novaPasta.id, nome: novaPasta.name });
                });
        }
    }

    editar(nome: string, id: number) {
        this.http.put<Folder>(`${this.API}/change-name/${id}`, {id: 0, name: nome, userDto: {id: 0, name: '', email: '', password: ''}})
            .pipe(take(1)).subscribe(novaPasta => {
                this.pastas.getValue().map(pasta => {
                    if(pasta.id == id) {
                        pasta.nome = nome;
                    }
                });
            });
    }


    consultarSenhasPorPasta(id: number): Observable<{id: number, name: string, content: string}[]> {
        return this.http.get<{ id: number, name: string; content: string }[]>(`${this.API_PASSWORDS}/consult/all/folder/${id}`);
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

    consultarPastaPorNome(nome: string): Observable<Folder> {
        return this.http.get<Folder>(`${this.API}/name/${nome}`);
    }

    alterarSenha(id: number, senhaComPasta: SenhaComPasta): Observable<SenhaComPasta> {
        return this.http.put<SenhaComPasta>(`${this.API_PASSWORDS}/change-name`, { name: senhaComPasta.name, id });
    }

    deletar(id: number) {
        this.http.delete(`${this.API_PASSWORDS}/delete/${id}`).pipe(take(1)).subscribe();
    }

    decriptografarSenha(id: number): Observable<{ id: number, name: string, content: string }> {
        return this.http.get<{ id: number, name: string, content: string }>(`http://localhost:8080/password/decrypt/folder/${id}`);
    }


    deletarPasta(id: number) {
        this.http.delete(`${this.API}/${id}`).pipe(
            tap(() => {
                const pastasAtualizadas = this.pastas.getValue().filter(pasta => pasta.id !== id);
                this.pastas.next(pastasAtualizadas);    
            }),
            take(1)
        ).subscribe();
    }
}