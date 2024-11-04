import { Folder } from "./components/folder-section/folder";
import { Usuario } from "./components/folder-section/usuario";

export class SenhaComPasta {

    constructor(id: number, name: string, content: string, userDto: Usuario, folderDto: Folder) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.userDto = userDto;
        this.folderDto = folderDto;
    }

    id?: number;
    name: string;
    content: string;
    userDto: Usuario;
    folderDto: Folder;
}