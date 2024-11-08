import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Usuario } from "../tela-principal/components/folder-section/usuario";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class TelaPrincipalService {
    
    constructor(private http: HttpClient){}

    private readonly API = 'http://localhost:8080/users/consult/email'

    consultarUsuario(email: string): Observable<Usuario> {
        return this.http.get<Usuario>(this.API + `/${email}`);
    }
}