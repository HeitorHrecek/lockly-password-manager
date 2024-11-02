import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from "./components/header/header.component";
import { FolderSectionComponent } from "./components/folder-section/folder-section.component";
import { PasswordSectionComponent } from "./components/password-section/password-section.component";
import { TelaPrincipalService } from './tela-principal.service';
import { LocalStorageService } from './local-storage.service';
import { ModalService } from './modal.senha.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ModalSenhaComponent } from './components/modal-senha/modal-senha.component';
import { ModalPastaComponent } from "./components/modal-pasta/modal-pasta.component";
import { ModalPastaService } from './modal.pasta.service';


@Component({
  selector: 'app-tela-principal',
  standalone: true,
  imports: [HeaderComponent, FolderSectionComponent, PasswordSectionComponent, CommonModule, FormsModule, ModalSenhaComponent, ModalPastaComponent],
  templateUrl: './tela-principal.component.html',
  styleUrls: ['./tela-principal.component.css']
})
export class TelaPrincipalComponent {

  constructor(
    private service: TelaPrincipalService,
    private localStorageService: LocalStorageService,
    private modalService: ModalService,
    private modalPastaService: ModalPastaService
  ) { }

  modalAbertoSenha = false;
  modalAbertoPasta = false;



  ngOnInit(): void {
    // const emailUsuario = this.localStorageService.getItem<{ email: string }>('email-usuario');
    // if (emailUsuario != null) {
    //   const usuario = this.service.consultarUsuario(emailUsuario.email).subscribe(() => {});
    //   this.localStorageService.setItem('usuario', usuario);
    // } else {
    //   console.log('Usuario é null');
    // }

    this.modalService.modalState$.subscribe((aberto) => {
      if (aberto) {
        this.modalAbertoSenha = true;
      } else {
        this.modalAbertoSenha = false;
      }
    });

    this.modalPastaService.modalState$.subscribe((aberto) => {
      if (aberto) {
        this.modalAbertoPasta = true;
      } else {
        this.modalAbertoPasta = false;
      }
    })
  }
}
