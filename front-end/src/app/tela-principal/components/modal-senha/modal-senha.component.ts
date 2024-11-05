import { PasswordSectionComponent } from './../password-section/password-section.component';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ModalService } from '../../modal.senha.service';
import { SenhaService } from '../password-section/senha.service';
import { SenhaSemPasta } from '../../senhaSemPasta';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LocalStorageService } from '../../local-storage.service';
import { FolderService } from '../folder-section/folder.service';
import { SenhaComPasta } from '../../senhaComPasta';
import { Usuario } from '../folder-section/usuario';

@Component({
  selector: 'app-modal-senha',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './modal-senha.component.html',
  styleUrl: './modal-senha.component.css'
})
export class ModalSenhaComponent implements OnInit{
  @Output() fechar = new EventEmitter<void>();

  constructor(
    private modalService: ModalService,
    private senhaService: SenhaService,
    private localStorageService: LocalStorageService,
    private folderService: FolderService
  ) { }

  buttonDeletar = false;
  criarSenhaComPasta = false;

  ngOnInit() {
    console.log("123");

    this.modalService.senhaData$.subscribe((data) => {
      if (data) {
        this.senha.name = data.nome;
        this.senha.content = data.conteudo;
      }
    });

    this.modalService.modalPassword$.subscribe((aberto) => {
      if (aberto) {
        this.buttonDeletar = true;
      }
    })


    this.senhaService.consultarPorNome(this.senha.name).subscribe(senha => {
      if (senha != null){
        this.senha.id = senha.id;
      }
    })

    this.modalService.modalSatateFolder$.subscribe(verdadeiro => {
      if(verdadeiro) {
        this.criarSenhaComPasta = true;
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

  
  senhaComPasta: SenhaComPasta = {
    id: 0,
    name: '',
    content: '',
    userDto: {
      id: 4,
      name: '',
      email: '',
      password: ''
    },
    folderDto: {
      id: 0,
      name: '',
      userDto: {
        id: 4,
        name: '',
        email: '',
        password: ''
      }
    },
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
    } else if (this.criarSenhaComPasta) {
      let pasta = this.localStorageService.getItem<{id: number, nome: string}>('pasta');

      this.senhaComPasta.folderDto.id = pasta?.id;

      if(pasta != null) {
        this.folderService.cadastrarSenhaComPasta(this.senhaComPasta).subscribe(() => {});
        this.fecharModal();
      }

    } else {
      this.senhaService.criar(this.senha).subscribe((novaSenha) => {
        if (novaSenha.id != undefined) {
          this.senhaService.criarSenha(novaSenha.id, this.senha.name, this.senha.content);
          this.fecharModal();
        }
      });
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
