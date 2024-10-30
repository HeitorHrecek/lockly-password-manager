import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-password',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './password.component.html',
  styleUrl: './password.component.css'
})
export class PasswordComponent {
salvarConteudo() {
throw new Error('Method not implemented.');
}
  @Input() nome: string = '';  // Propriedade de entrada
  @Output() nomeChange = new EventEmitter<string>();  // Evento de saída
  @Input() content: string = '';
  @Output() contentChange = new EventEmitter<string>();
  @Input() isEditing: boolean = false;


  editarNome() {
    this.isEditing = true;
  }

  salvarNome() {
    this.isEditing = false;
    this.nomeChange.emit(this.nome);  // Emite o novo valor
  }
}
