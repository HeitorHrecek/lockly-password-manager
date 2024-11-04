import { Component, OnInit } from '@angular/core';
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
export class FolderSectionComponent implements OnInit {

  constructor(
    private service:FolderService,
    private localStorageService: LocalStorageService
  ){}

  pastas: {id: number; nome: string; isEditing: boolean }[] = [];

  ngOnInit(){
    this.service.consultarPastas();
    this.service.pastas$.subscribe((pastasBack) => {
      this.pastas = pastasBack;
    })
  }

  //this.localStorageService.setItem('senha', {id: novaSenha.id, nome: novaSenha.name, conteudo: novaSenha.content});

  salvarNome(index: number, nome: string) {
    this.service.salvarPasta(index, nome);
  }

  criarPasta() {
    this.pastas.push({id: 0, nome: '', isEditing: true});
  }
}