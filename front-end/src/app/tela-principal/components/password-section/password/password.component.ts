import { ModalService } from '../../../modal.senha.service';
import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';
import { SenhaSemPasta } from 'src/app/tela-principal/senhaSemPasta';
import { SenhaService } from '../senha.service';
import { LocalStorageService } from 'src/app/tela-principal/local-storage.service';
import { ModalPastaComponent } from '../../modal-pasta/modal-pasta.component';
import { ModalPastaService } from 'src/app/tela-principal/modal.pasta.service';

@Component({
  selector: 'app-password',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './password.component.html',
  styleUrl: './password.component.css'
})
export class PasswordComponent {

  constructor(
    private modalService: ModalService,
    private modalPastaService: ModalPastaService,
    private localStorageService: LocalStorageService
  ) { }

  ngOnInit() {
    // let senha = this.localStorageService.getItem<{id: number, nome: string, conteudo: string}>('senha');

    // if(senha != undefined) {
    //   this.id = senha.id;
    // }
  }

  id?: number;
  @Input() nome: string = '';
  @Output() nomeChange = new EventEmitter<string>();
  @Input() content: string = '';
  @Output() contentChange = new EventEmitter<string>();
  @Input() isEditing: boolean = false;


  editarNome() {
    this.isEditing = true;
  }

  salvarNome() {
    this.isEditing = false;
    this.nomeChange.emit(this.nome);
  }

  abrirModal() {
    this.modalPastaService.modalState$.subscribe(aberto => {
      if(aberto) { 
        this.modalService.openModalComDados(this.nome, this.content, true);
        this.modalPastaService.closeModal();
      } else {
        this.modalService.openModalComDados(this.nome, this.content, false); 
      }
    })
  }
}
