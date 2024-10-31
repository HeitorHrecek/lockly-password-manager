import { PasswordSectionComponent } from './../password-section/password-section.component';
import { Component, EventEmitter, Output } from '@angular/core';
import { ModalService } from '../../modal.service';
import { SenhaService } from '../password-section/senha.service';
import { SenhaSemPasta } from '../../senhaSemPasta';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-modal-senha',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './modal-senha.component.html',
  styleUrl: './modal-senha.component.css'
})
export class ModalSenhaComponent {
  @Output() fechar = new EventEmitter<void>();

  constructor(
    private modalService: ModalService,
    private senhaService: SenhaService,
    private passwordSection: PasswordSectionComponent
  ){}

  senha: SenhaSemPasta = {
    name: '',
    content: '',
    userDto: {
      id: 4,
      name: '',
      email: '',
      password: ''
    }
  }

  fecharModal() {
    this.modalService.closeModal();
  }

  salvar(){
    this.senhaService.criar(this.senha).subscribe(() => {
      this.fecharModal();
    })
  }
}
