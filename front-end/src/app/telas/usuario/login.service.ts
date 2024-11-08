import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Login } from "./login";

@Injectable({
    providedIn: 'root'
})
export class LoginUsuarioService{
    private readonly API = 'http://localhost:8080/users/login';
    constructor(
        private http: HttpClient
    ){}

    logar(usuario: Login): Observable<Login> {
        return this.http.post<Login>(this.API, usuario);
    }
}