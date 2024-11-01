import { ModalService } from './../../../modal.service';
import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-password',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './password.component.html',
  styleUrl: './password.component.css'
})
export class PasswordComponent {

  constructor(
    private modalService: ModalService
  ) { }

  // private senhaData = new BehaviorSubject<{ nome: string; conteudo: string } | null>(null);
  // senhaData$ = this.senhaData.asObservable();

  @Input() nome: string = '';
  @Output() nomeChange = new EventEmitter<string>();
  @Input() content: string = '';
  @Output() contentChange = new EventEmitter<string>();
  @Input() isEditing: boolean = false;


  editarNome() {
    this.isEditing = true;
  }

  salvarNome() {
    this.isEditing = false;
    this.nomeChange.emit(this.nome);
  }

  abrirModal() {
    this.modalService.openModalComDados(0, this.nome, this.content);
  }
}
