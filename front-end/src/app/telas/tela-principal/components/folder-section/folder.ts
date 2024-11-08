import { Usuario } from "./usuario";

export class Folder {
    constructor(id:number, name: string, usuario: Usuario) {
        this.id = id;
        this.name = name;
        this.userDto = usuario;
    }


    id?: number;
    name: string;
    userDto: Usuario
}