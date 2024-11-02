import { Component } from '@angular/core';
import { CreateButtonPastaComponent } from '../create-button-pasta/create-button.component';
import { FolderComponent } from './folder/folder.component';
import { Folder } from './folder';
import { CommonModule } from '@angular/common';
import { FolderService } from './folder.service';
import { Usuario } from './usuario';
import { LocalStorageService } from '../../local-storage.service';


@Component({
  selector: 'app-folder-section',
  standalone: true,
  imports: [CommonModule, CreateButtonPastaComponent, FolderComponent],
  templateUrl: './folder-section.component.html',
  styleUrl: './folder-section.component.css'
})
export class FolderSectionComponent {

  constructor(
    private service:FolderService,
    private localStorageService:LocalStorageService
  ){}

  pastas: { nome: string; isEditing: boolean }[] = [];

  criarPasta() {
    this.pastas.push({ nome: '', isEditing: true });
  }

  salvarNome(index: number, nome: string) {
    this.pastas[index].nome = nome;
    this.pastas[index].isEditing = false;
    // const usuario = this.localStorageService.getItem<{id: number, name: string, email: string, password: string}>('usuario')
    const usuario = {id: 4}

    if(usuario != null) {
      this.service.criar(new Folder(0, this.pastas[index].nome, new Usuario(usuario.id, '', '', ''))).subscribe(() => {});
    }
  }
}