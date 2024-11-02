import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalPastaComponent } from '../../modal-pasta/modal-pasta.component';
import { ModalPastaService } from 'src/app/tela-principal/modal.pasta.service';

@Component({
  selector: 'app-folder',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './folder.component.html',
  styleUrl: './folder.component.css'
})
export class FolderComponent {

  constructor(
    private modalPastaService:ModalPastaService
  ){}


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

  abrirModal() {
    this.modalPastaService.openModal();
  } 
}