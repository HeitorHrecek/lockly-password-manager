import { Usuario } from './usuario';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class UsuarioService {
    private readonly API = 'http://localhost:8080/users';

    constructor(private http: HttpClient){}

 
    editar(usuario: Usuario): Observable<Usuario> {
        return this.http.put<Usuario>(`${this.API}/change/` + usuario.id, usuario);
    }

    buscarPorEmail(email: string): Observable<Usuario> {
        return this.http.get<Usuario>(`${this.API}/consult/email/${email}`);
    }
}