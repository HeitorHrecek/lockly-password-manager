import { Injectable } from "@angular/core";
import { SenhaSemPasta } from "../../senhaSemPasta";
import { HttpClient } from "@angular/common/http";
import { Observable, retry } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class SenhaService {

    private readonly API = 'http://localhost:8080/passwords/register';

    constructor(
        private http: HttpClient
    ){}

    criar(senha: SenhaSemPasta):Observable<SenhaSemPasta> {
        return this.http.post<SenhaSemPasta>(this.API, senha);
    }

}