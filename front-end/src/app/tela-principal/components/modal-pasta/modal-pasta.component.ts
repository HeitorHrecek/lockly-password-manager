import { LocalStorageService } from './../../local-storage.service';
import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Folder } from '../folder-section/folder';
import { ModalPastaService } from '../../modal.pasta.service';
import { SenhaService } from '../password-section/senha.service';
import { FolderComponent } from "../folder-section/folder/folder.component";
import { PasswordComponent } from '../password-section/password/password.component';
import { FolderService } from '../folder-section/folder.service';
import { ModalService } from '../../modal.senha.service';
import { BehaviorSubject, forkJoin, map, switchMap, take } from 'rxjs';

@Component({
  selector: 'app-modal-pasta',
  standalone: true,
  imports: [FormsModule, CommonModule, FolderComponent, PasswordComponent],
  templateUrl: './modal-pasta.component.html',
  styleUrl: './modal-pasta.component.css'
})
export class ModalPastaComponent {


  constructor(
    private modalPastaService: ModalPastaService,
    private folderService: FolderService,
    private modalSenhaService: ModalService,
    private localStorageService: LocalStorageService
  ) { }

  senhas: { id: number, nome: string; conteudo: string; isEditing: boolean }[] = [];

  ngOnInit() {
    this.modalPastaService.pastaData$.subscribe(pasta => {
      if (pasta != null) {
        this.pasta.id = pasta.id;
        this.pasta.name = pasta.nome;
      }
    })

    if (this.pasta.id != null) {
      this.folderService.consultarSenhasPorPasta(this.pasta.id).pipe(
        map(senhasBack => senhasBack.map(senha => ({
          id: senha.id,
          nome: senha.name,
          conteudo: senha.content,
          isEditing: false
        }))),
        switchMap(senhasMapeadas =>
          forkJoin(senhasMapeadas.map(senha =>
            this.folderService.decriptografarSenha(senha.id)
              .pipe(
                map(decryptedPassword => ({
                  ...senha,
                  conteudo: decryptedPassword.content
                }))
              )
          ))
        ),
        take(1)
      ).subscribe((senhas) => {
        this.senhas = senhas;
      });
    }
  }
  
  pasta: Folder = {
    id: 0,
    name: '',
    userDto: {
      id: 4,
      name: '',
      email: '',
      password: ''
    }
  }

  // nome?: string

  @Input() isEditing: boolean = false;


  fecharModal() {
    this.modalPastaService.closeModal();
  }

  abrirModalSenha() {
    this.modalPastaService.closeModal();
    this.modalSenhaService.openModalComPasta();
    this.localStorageService.setItem('pasta', { id: this.pasta.id, nome: this.pasta.name });
  }

  salvarNome() {
    if (this.pasta.id) {
      this.folderService.editar(this.pasta.name, this.pasta.id);
    }

    this.isEditing = false;
  }

  editarNome() {
    this.isEditing = true;
  }

  deletarPasta() {
    if (this.pasta.id) {
      this.senhas.map(senha => {
        this.folderService.deletar(senha.id);
      })
      this.folderService.deletarPasta(this.pasta.id);
    }
    this.modalPastaService.closeModal();
  }
}
