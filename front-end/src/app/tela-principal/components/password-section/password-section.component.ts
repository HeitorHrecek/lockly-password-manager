import { Component } from '@angular/core';
import { CreateButtonComponent } from '../create-button-pasta/create-button.component';
import { PasswordComponent } from "./password/password.component";
import { LocalStorageService } from '../../local-storage.service';

@Component({
  selector: 'app-password-section',
  standalone: true,
  imports: [CreateButtonComponent, PasswordComponent],
  templateUrl: './password-section.component.html',
  styleUrl: './password-section.component.css'
})
export class PasswordSectionComponent {

  constructor(
    private service:SenhaService,
    private localStorageService:LocalStorageService
  ){}

  senhas: { nome: string; isEditing: boolean }[] = [];

  criarPasta() {
    this.senhas.push({ nome: '', isEditing: true });
  }

  salvarNome(index: number, nome: string) {
    this.senhas[index].nome = nome;
    this.senhas[index].isEditing = false;

    const usuario = this.localStorageService.getItem<{id: number, name: string, email: string, password: string}>('usuario')

    if(usuario != null) {
      this.service.criar(new Folder(0, this.pastas[index].nome, new Usuario(usuario.id, '', '', ''))).subscribe(() =>{});
    }

  }
}
