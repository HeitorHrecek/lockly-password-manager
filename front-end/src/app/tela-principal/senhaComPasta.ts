import { Folder } from "./components/folder-section/folder";
import { Usuario } from "./components/folder-section/usuario";

export class SenhaSemPasta {

    constructor(id: number, name: string, content: string, userDto: Usuario) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.userDto = userDto;
    }

    id?: number;
    name: string;
    content: string;
    userDto?: Usuario;
    folderDto?: Folder;
}