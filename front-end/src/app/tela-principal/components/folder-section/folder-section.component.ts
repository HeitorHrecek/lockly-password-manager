import { Component } from '@angular/core';
import { CreateButtonComponent } from '../create-button/create-button.component';
import { FolderComponent } from './folder/folder.component';
import { Folder } from './folder';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-folder-section',
  standalone: true,
  imports: [CommonModule, CreateButtonComponent, FolderComponent],
  templateUrl: './folder-section.component.html',
  styleUrl: './folder-section.component.css'
})
export class FolderSectionComponent {
  
  pastas: Array<FolderComponent>;

  criarPasta() {
    this.pastas.push({ nome: '', isEditing: true });
  }

  salvarNome(index: number, nome: string) {
    this.pastas[index].nome = nome;
    this.pastas[index].isEditing = false;
  }
}