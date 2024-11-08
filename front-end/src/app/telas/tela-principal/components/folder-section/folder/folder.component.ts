import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FolderService } from '../folder.service';
import { take } from 'rxjs';
import { ModalPastaService } from '../../../modal.pasta.service';

@Component({
  selector: 'app-folder',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './folder.component.html',
  styleUrl: './folder.component.css'
})
export class FolderComponent {

  constructor(
    private modalPastaService:ModalPastaService,
    private folderService: FolderService
  ){}

  ngOnInit() {
    this.folderService.consultarPastaPorNome(this.nome).pipe(take(1)).subscribe(pasta => this.id = pasta.id);
  }

  id?: number;
  @Input() nome: string = '';
  @Output() nomeChange = new EventEmitter<string>();
  @Input() isEditing: boolean = false;

  salvarNome() {
    this.isEditing = false;
    this.nomeChange.emit(this.nome);
  }

  abrirModal() {
    if(this.id != null) {
      this.modalPastaService.openModal(this.id, this.nome);
    }
  } 
}