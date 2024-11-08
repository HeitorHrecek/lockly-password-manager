import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalService } from '../../../modal.senha.service';
import { ModalPastaService } from '../../../modal.pasta.service';
import { LocalStorageService } from '../../../../local-storage.service';


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

  ngOnInit() {}

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
