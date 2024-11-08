import { LocalStorageService } from 'src/app/telas/local-storage.service';
import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from "../tela-principal/components/header/header.component";
import { FolderSectionComponent } from "../tela-principal/components/folder-section/folder-section.component";
import { PasswordSectionComponent } from "../tela-principal/components/password-section/password-section.component";
import { ModalService } from './modal.senha.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ModalSenhaComponent } from '../tela-principal/components/modal-senha/modal-senha.component';
import { ModalPastaComponent } from "../tela-principal/components/modal-pasta/modal-pasta.component";
import { ModalPastaService } from './modal.pasta.service';
import { Subject, take, takeUntil } from 'rxjs';
import { Route, Router } from '@angular/router';
import { UsuarioService } from '../usuario/usuario.service';


@Component({
  selector: 'app-tela-principal',
  standalone: true,
  imports: [HeaderComponent, FolderSectionComponent, PasswordSectionComponent, CommonModule, FormsModule, ModalSenhaComponent, ModalPastaComponent],
  templateUrl: './tela-principal.component.html',
  styleUrls: ['./tela-principal.component.css']
})
export class TelaPrincipalComponent implements OnInit{

  constructor(
    private modalService: ModalService,
    private modalPastaService: ModalPastaService,
    private localStorageService: LocalStorageService,
    private usuarioService: UsuarioService,
    private router: Router
  ) { }

  modalAbertoSenha: boolean = false;
  modalAbertoPasta: boolean = false;



  ngOnInit(): void {
    const emailUsuario = this.localStorageService.getItem<{ email: string }>('email-usuario');
    if (emailUsuario != null) {
      this.usuarioService.buscarPorEmail(emailUsuario.email).pipe(take(1)).subscribe((usuario) => {
        this.localStorageService.setItem('usuario', usuario);
      });
    } else {
      console.log('Usuario é null');
    }

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
