import { PasswordSectionComponent } from './../password-section/password-section.component';
import { Component, EventEmitter, Output } from '@angular/core';
import { ModalService } from '../../modal.service';
import { SenhaService } from '../password-section/senha.service';
import { SenhaSemPasta } from '../../senhaSemPasta';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LocalStorageService } from '../../local-storage.service';

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
    private localStorageService: LocalStorageService
  ) { }

  buttonDeletar = false;

  ngOnInit() {
    this.modalService.senhaData$.subscribe((data) => {
      if (data) {
        this.senha.name = data.nome;
        this.senha.content = data.conteudo;
        this.senha.id = data.id;
        console.log(data.id);
      }
    });

    this.modalService.modalPassword$.subscribe((aberto) => {
      if (aberto) {
        this.buttonDeletar = true;
      }
    })
  }

  senha: SenhaSemPasta = {
    id: 0,
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

  salvar() {
    if (this.buttonDeletar) {
      if (this.senha.id != undefined) {
        this.senhaService.alterar(this.senha.id, this.senha);
        this.fecharModal();
      }
    } else {
      this.senhaService.criar(this.senha).subscribe((novaSenha) => {
        this.localStorageService.setItem('senha', {id: novaSenha.id, nome: novaSenha.name, conteudo: novaSenha.content});
        if (novaSenha.id != undefined) {
          this.senhaService.criarSenha(novaSenha.id, this.senha.name, this.senha.content);
          this.fecharModal();
        }
      })
    }
  }

  deletar() {
    if (this.senha.id != undefined) {
      this.senhaService.deletar(this.senha.id);
    }
    this.buttonDeletar = false;
    this.modalService.closeModal()
  }
}
