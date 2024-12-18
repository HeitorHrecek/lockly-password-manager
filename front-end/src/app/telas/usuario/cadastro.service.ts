import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { Usuario } from "./usuario";

@Injectable({
    providedIn: 'root'
})
export class CadastroUsuarioService {

    private readonly API = 'http://localhost:8080/users/register';
    constructor(private http: HttpClient){}

    criar(usuario: Usuario): Observable<Usuario> {
        return this.http.post<Usuario>(this.API, usuario);
    }
}