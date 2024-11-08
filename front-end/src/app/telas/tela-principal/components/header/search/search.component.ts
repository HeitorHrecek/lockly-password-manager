import { CommonModule } from '@angular/common';
import { SenhaService } from './../../password-section/senha.service';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FolderService } from '../../folder-section/folder.service';
import { catchError, of, take } from 'rxjs';
import { ModalService } from '../../../modal.senha.service';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {

  constructor(
    private senhaService: SenhaService,
    private pastaService: FolderService,
    private modalSenhaService: ModalService
  ) { }

  nomeSenha?: string;

  queryByName() {
    if (this.nomeSenha != null) {
      this.senhaService.consultarPorNome(this.nomeSenha).pipe(
        take(1),
        catchError(error => {
          this.queryByNameFolder();
          return of(null);
        })
      ).subscribe(senha => {
        if(senha != null) {
          this.senhaService.decriptografarSenha(senha.id)
            .pipe(take(1))
            .subscribe(senhaDecriptografada => {
              this.modalSenhaService.openModalComDados(senhaDecriptografada.name, senhaDecriptografada.content, false);
            });
        }
      })
    }
  }

  queryByNameFolder() {
    if(this.nomeSenha != null){
      this.pastaService.consultarSenhaPorNome(this.nomeSenha).pipe(take(1)).subscribe(senha => {
        if(senha != null) {
          if(senha.id != null){
            this.pastaService.decriptografarSenha(senha.id).pipe(take(1)).subscribe(senhaDecriptografada => {
                this.modalSenhaService.openModalComDados(senhaDecriptografada.name, senhaDecriptografada.content, true);
            })
          }
        }
      })

    }
  }

}
