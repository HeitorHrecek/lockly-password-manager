import { LocalStorageService } from './../../local-storage.service';
import { Injectable } from "@angular/core";
import { SenhaSemPasta } from "../../senhaSemPasta";
import { HttpClient } from "@angular/common/http";
import { BehaviorSubject, Observable, retry } from "rxjs";
import { PasswordWithoutFolder } from './passwords/password-without-folder';
import { Usuario } from '../folder-section/usuario';

@Injectable({
    providedIn: 'root'
})
export class SenhaService {

    private readonly API = 'http://localhost:8080/passwords';

    constructor(
        private http: HttpClient,
        private localStorageService: LocalStorageService
    ) { }

    private senhas = new BehaviorSubject<{ nome: string; conteudo: string; isEditing: boolean }[]>([]);
    senhas$ = this.senhas.asObservable();


    criar(senha: SenhaSemPasta): Observable<SenhaSemPasta> {
        let resposta;
        resposta = this.http.post<SenhaSemPasta>(this.API + '/register', senha);
        resposta.subscribe((data) => {
            console.log(data);
        })
        return resposta;
    }

    criarSenha(id: number, nome: string, conteudo: string) {
        const senhasAtual = this.senhas.getValue();

        const novaSenha = { id: id, nome: nome, conteudo: conteudo, isEditing: true };
        this.senhas.next([...senhasAtual, novaSenha]);
    }

    salvarNome(index: number, nome: string, conteudo: string) {
        this.senhas$.subscribe((senhas) => {
            senhas[index].nome = nome;
            senhas[index].conteudo = conteudo;
            senhas[index].isEditing = false;

            const usuario = this.localStorageService.getItem<{ id: number, name: string, email: string, password: string }>('usuario')

            if (usuario != null) {
                this.criar(new PasswordWithoutFolder(0, senhas[index].nome, '', new Usuario(usuario.id, '', '', ''))).subscribe(() => { });
            }
        })
    }

    deletar(id: number) {
        this.http.delete(this.API + `/delete/${id}`);
    }

    alterar(id: number, senha: SenhaSemPasta) {
        this.http.put<SenhaSemPasta>(this.API + `/change-name/${id}`, senha);
    }

    consultarSenhas(): Observable<any | undefined> {
        const usuario = this.localStorageService.getItem<{ id: number, name: string, email: string, password: string }>('usuario');

        if(usuario != null){
            return this.http.get(this.API + `/consult/all-by-user/${usuario.id}`);
        }

        return undefined;
    }

}