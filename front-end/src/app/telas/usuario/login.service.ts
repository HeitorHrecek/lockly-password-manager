import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Usuario } from "./usuario";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class LoginUsuarioService{
    private readonly API = 'http://localhost:8080/users/login';
    constructor(
        private http: HttpClient
    ){}

    logar(usuario: Usuario): Observable<Usuario> {
        return this.http.post<Usuario>(this.API, usuario);
    }
}