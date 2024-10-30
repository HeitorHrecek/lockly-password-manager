import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-folder',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './folder.component.html',
  styleUrl: './folder.component.css'
})
export class FolderComponent {

  @Input() nome: string = '';  
  @Output() nomeChange = new EventEmitter<string>();  
  @Input() isEditing: boolean = false;


  editarNome() {
    this.isEditing = true;
  }

  salvarNome() {
    this.isEditing = false;
    this.nomeChange.emit(this.nome);  
  }
}