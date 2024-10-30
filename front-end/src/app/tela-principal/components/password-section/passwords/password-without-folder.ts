import { Usuario } from "../../folder-section/usuario";

export class PasswordWithoutFolder {

    constructor(
        id: number,
        name: string,
        content: string,
        userDto: Usuario
    ){
        this.id = id;
        this.name = name;
        this.content = content;
        this.userDto = userDto;
    }
    
    id?: number;
    name: string;
    content: string;
    userDto: Usuario
}