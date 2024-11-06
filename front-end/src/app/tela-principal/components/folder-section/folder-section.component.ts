import { Component, Input, OnInit } from '@angular/core';
import { CreateButtonPastaComponent } from '../create-button-pasta/create-button.component';
import { FolderComponent } from './folder/folder.component';
import { Folder } from './folder';
import { CommonModule } from '@angular/common';
import { FolderService } from './folder.service';
import { Usuario } from './usuario';
import { LocalStorageService } from '../../local-storage.service';
import { BehaviorSubject, take } from 'rxjs';


@Component({
  selector: 'app-folder-section',
  standalone: true,
  imports: [CommonModule, CreateButtonPastaComponent, FolderComponent],
  templateUrl: './folder-section.component.html',
  styleUrl: './folder-section.component.css'
})
export class FolderSectionComponent implements OnInit {

  constructor(
    private service:FolderService,
    private localStorageService: LocalStorageService
  ){}

  pastas: {id: number; nome: string; isEditing: boolean }[] = [];
  
  private isEditingCount = new BehaviorSubject<number>(0);
  isEditingCount$ = this.isEditingCount.asObservable();

  ngOnInit(){
    this.service.consultarPastas();
    this.service.pastas$.subscribe((pastasBack) => {
      this.pastas = pastasBack;
    })
  }

  //this.localStorageService.setItem('senha', {id: novaSenha.id, nome: novaSenha.name, conteudo: novaSenha.content});

  salvarNome(index: number, nome: string) {

    this.isEditingCount.pipe(take(1)).subscribe(valor => {
      console.log(valor);
      if(valor == 0) {
        console.log('oi');
        this.service.salvarPasta(index, nome);
        this.isEditingCount.next(1);
      } else {
        console.log('oi 2');
        const pasta = this.localStorageService.getItem<{id: number, nome: string}>('pasta');
        console.log(pasta);
        if(pasta != null){
          this.service.editar(index, nome, pasta.id);
        }
      }
      console.log(this.isEditingCount.getValue());
    });
  }

  criarPasta() {
    this.pastas.push({id: 0, nome: '', isEditing: true});
  }
}