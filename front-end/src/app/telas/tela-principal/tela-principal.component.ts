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
import { Subject, takeUntil } from 'rxjs';
import { Route, Router } from '@angular/router';


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
    private router: Router
  ) { }

  modalAbertoSenha: boolean = false;
  modalAbertoPasta: boolean = false;



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

  // private unsubscribe$ = new Subject<void>();

  // ngOnInit(): void {
  //   this.modalService.modalState$.pipe(takeUntil(this.unsubscribe$)).subscribe((aberto) => {
  //     this.modalAbertoSenha = aberto;
  //   });

  //   this.modalPastaService.modalState$.pipe(takeUntil(this.unsubscribe$)).subscribe((aberto) => {
  //     this.modalAbertoPasta = aberto;
  //   });
  // }
}
