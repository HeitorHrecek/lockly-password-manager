import { LocalStorageService } from './../../local-storage.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Folder } from '../folder-section/folder';
import { ModalPastaService } from '../../modal.pasta.service';
import { SenhaService } from '../password-section/senha.service';
import { FolderComponent } from "../folder-section/folder/folder.component";
import { PasswordComponent } from '../password-section/password/password.component';
import { FolderService } from '../folder-section/folder.service';
import { ModalService } from '../../modal.senha.service';

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
      this.folderService.consultarSenhasPorPasta(this.pasta.id);
      this.folderService.senhas$.subscribe(senhasBack => {
        this.senhas = senhasBack;
      })
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


  fecharModal() {
    this.modalPastaService.closeModal();
  }

  abrirModalSenha() {
    this.modalPastaService.closeModal();
    this.modalSenhaService.openModalComPasta();
    this.localStorageService.setItem('pasta', {id: this.pasta.id, nome: this.pasta.name});
  }
}
