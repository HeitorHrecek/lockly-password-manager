import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ModalService } from '../../modal.senha.service';

@Component({
  selector: 'app-create-button-senhas',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-button.component.html',
  styleUrls: ['./create-button.component.css']
})
export class CreateButtonSenhaComponent {

  constructor(private modalService: ModalService) { }

  mostrarModal = false;

  abrirModal() {
    this.modalService.openModal();
  }
}
