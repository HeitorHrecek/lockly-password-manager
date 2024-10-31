import { Component, EventEmitter, Output } from '@angular/core';
import { ModalSenhaComponent } from '../modal-senha/modal-senha.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ModalService } from '../../modal.service';

@Component({
  selector: 'app-create-button-senhas',
  standalone: true,
  imports: [ModalSenhaComponent, CommonModule, FormsModule],
  templateUrl: './create-button.component.html',
  styleUrl: './create-button.component.css'
})
export class CreateButtonSenhaComponent {

  constructor(private modalService: ModalService) { }

  mostrarModal = false;

  abrirModal() {
    this.modalService.openModal();
  }
}
