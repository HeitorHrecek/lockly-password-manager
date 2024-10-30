import { Component } from '@angular/core';
import { PasswordComponent } from "./password/password.component";
import { LocalStorageService } from '../../local-storage.service';
import { SenhaService } from './senha.service';
import { PasswordWithoutFolder } from './passwords/password-without-folder';
import { CreateButtonSenhaComponent } from '../create-button-senhas/create-button.component';
import { Usuario } from '../folder-section/usuario';

@Component({
  selector: 'app-password-section',
  standalone: true,
  imports: [CreateButtonSenhaComponent, PasswordComponent],
  templateUrl: './password-section.component.html',
  styleUrl: './password-section.component.css'
})
export class PasswordSectionComponent {

  constructor(
    private service:SenhaService,
    private localStorageService:LocalStorageService
  ){}

  senhas: { nome: string; conteudo:string; isEditing: boolean }[] = [];

  criarPasta() {
    this.senhas.push({ nome: '', conteudo: '' , isEditing: true });
  }

  salvarNome(index: number, nome: string) {
    this.senhas[index].nome = nome;
    this.senhas[index].isEditing = false;

    const usuario = this.localStorageService.getItem<{id: number, name: string, email: string, password: string}>('usuario')

    if(usuario != null) {
      // this.service.criar(new PasswordWithoutFolder(0, this.senhas[index].nome, '' , new Usuario(usuario.id, '', '', ''))).subscribe(() =>{});
    }

  }
}
