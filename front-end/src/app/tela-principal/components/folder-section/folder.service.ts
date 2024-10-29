import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Folder } from "./folder";

@Injectable({
    providedIn: 'root'
})
export class FolderService {

    private readonly API = 'http://localhost:8080/folders'

    constructor(private http: HttpClient){}
    
    criar(folder: Folder): Observable<Folder> {
        console.log('request criar' + folder);
        return this.http.post<Folder>(this.API + '/register', folder);
    }
}