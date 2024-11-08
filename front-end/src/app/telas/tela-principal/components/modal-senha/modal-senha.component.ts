import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { SenhaService } from '../password-section/senha.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FolderService } from '../folder-section/folder.service';
import { switchMap, take, tap } from 'rxjs';
import { ModalService } from '../../modal.senha.service';
import { LocalStorageService } from '../../local-storage.service';
import { ModalPastaService } from '../../modal.pasta.service';
import { SenhaComPasta } from '../../senhaComPasta';
import { SenhaSemPasta } from '../../senhaSemPasta';

@Component({
  selector: 'app-modal-senha',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './modal-senha.component.html',
  styleUrl: './modal-senha.component.css'
})
export class ModalSenhaComponent implements OnInit {
  @Output() fechar = new EventEmitter<void>();

  constructor(
    private modalService: ModalService,
    private senhaService: SenhaService,
    private localStorageService: LocalStorageService,
    private modalPastaService: ModalPastaService,
    private folderService: FolderService
  ) { }

  buttonDeletar: Boolean = false;
  criarSenhaComPasta: Boolean = false;
  inputSenha: boolean = false;

  ngOnInit() {
    this.modalService.modalSatateFolder$.pipe(
      take(1),
      switchMap((aberto) => {
        if (aberto) {
          return this.modalService.senhaComPastaData$.pipe(tap(data => {
            if (data) {
              this.senhaComPasta.name = data.nome;
              this.senhaComPasta.content = data.conteudo;
            }
          }));
        } else {
          return this.modalService.senhaData$.pipe(tap(data => {
            if (data) {
              this.senha.name = data.nome;
              this.senha.content = data.conteudo;
            }
          }));
        }
      }),
      take(1)
    ).subscribe();

    this.modalService.modalPassword$.pipe(take(1)).subscribe((aberto) => {
      this.buttonDeletar = aberto;
      if(this.buttonDeletar)
        this.inputSenha = true;
    });

    this.modalPastaService.modalStateParaSenhas$.pipe(
      take(1),
      switchMap((aberto) => {
        if (aberto) {
          return this.folderService.consultarSenhaPorNome(this.senhaComPasta.name).pipe(
            tap(senha => {
              if (senha) {
                this.senhaComPasta.id = senha.id;
                this.senhaComPasta.userDto = senha.userDto;
                this.senhaComPasta.folderDto = senha.folderDto;
              }
            })
          );
        } else {
          return this.senhaService.consultarPorNome(this.senha.name).pipe(
            tap(senha => {
              if (senha) {
                this.senha.id = senha.id;
              }
            })
          );
        }
      })
    ).subscribe();

    this.modalService.modalSatateFolder$.pipe(take(1)).subscribe(verdadeiro => {
      this.criarSenhaComPasta = verdadeiro;
    });
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
    this.modalPastaService.modalStateParaSenhas$.subscribe(aberto => {
      if (aberto) {
        this.modalPastaService.fecharModalStateParaSenhas();
        this.modalService.closeModal();
      } else {
        this.modalService.closeModal();
      }
    })

  }



  salvar() {
    if (this.buttonDeletar) {
      if (this.senha.id != null && this.senha.id != 0) {
        this.senhaService.alterar(this.senha.id, this.senha);
        this.fecharModal();
      } else if (this.senhaComPasta.id != null && this.senhaComPasta.id != 0) {
        console.log('teste');
        this.folderService.alterarSenha(this.senhaComPasta.id, this.senhaComPasta).pipe(take(1)).subscribe();
        this.fecharModal();
      }
    } else if (this.criarSenhaComPasta) {
      let pasta = this.localStorageService.getItem<{ id: number, nome: string }>('pasta');

      this.senhaComPasta.folderDto.id = pasta?.id;

      if (pasta != null) {
        this.folderService.cadastrarSenhaComPasta(this.senhaComPasta).pipe(take(1)).subscribe(() => { });
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
    if (this.senha.id != null && this.senha.id != 0) {
      this.senhaService.deletar(this.senha.id);
    } else if (this.senhaComPasta.id != null && this.senhaComPasta.id != 0) {
      this.folderService.deletar(this.senhaComPasta.id);
    }
    this.buttonDeletar = false;
    this.modalService.closeModal()
  }
}
