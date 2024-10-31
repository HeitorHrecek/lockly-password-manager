import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from "./components/header/header.component";
import { FolderSectionComponent } from "./components/folder-section/folder-section.component";
import { PasswordSectionComponent } from "./components/password-section/password-section.component";
import { TelaPrincipalService } from './tela-principal.service';
import { LocalStorageService } from './local-storage.service';
import { Usuario } from './components/folder-section/usuario';
import { ModalService } from './modal.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ModalSenhaComponent } from './components/modal-senha/modal-senha.component';


@Component({
  selector: 'app-tela-principal',
  standalone: true,
  imports: [HeaderComponent, FolderSectionComponent, PasswordSectionComponent, CommonModule, FormsModule, ModalSenhaComponent],
  templateUrl: './tela-principal.component.html',
  styleUrl: './tela-principal.component.css'
})
export class TelaPrincipalComponent implements OnInit {

  constructor(
    private service: TelaPrincipalService,
    private localStorageService: LocalStorageService,
    private modalService: ModalService
  ) {}

  modalAberto = false;

  ngOnInit(): void {
    const emailUsuario = this.localStorageService.getItem<{ email: string }>('email-usuario');
    if (emailUsuario != null) {
      const usuario = this.service.consultarUsuario(emailUsuario.email).subscribe(() => { });
      this.localStorageService.setItem('usuario', usuario);
    } else {
      console.log('Usuario é null');
    }

    this.modalService.modalState$.subscribe((aberto) => {
      if(aberto) {
        this.modalAberto = true;
      }else {
        this.modalAberto = false;
      }
    });
  }
}
