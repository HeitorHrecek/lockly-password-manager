import { Usuario } from './../usuario';
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class PerfilService {
    private readonly API = 'http://localhost:8080/users/change/';

    constructor(private http: HttpClient){}

 
    editar(usuario: Usuario): Observable<Usuario> {
        return this.http.put<Usuario>(this.API + usuario.id, usuario);
    }
}