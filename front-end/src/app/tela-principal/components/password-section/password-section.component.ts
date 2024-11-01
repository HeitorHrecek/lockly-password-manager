import { LocalStorageService } from './../../local-storage.service';
import { Component } from '@angular/core';
import { PasswordComponent } from "./password/password.component";
import { LocalStorageService } from '../../local-storage.service';
import { SenhaService } from './senha.service';
import { PasswordWithoutFolder } from './passwords/password-without-folder';
import { CreateButtonSenhaComponent } from '../create-button-senhas/create-button.component';
import { Usuario } from '../folder-section/usuario';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-password-section',
  standalone: true,
  imports: [CreateButtonSenhaComponent, PasswordComponent, FormsModule, CommonModule],
  templateUrl: './password-section.component.html',
  styleUrls: ['./password-section.component.css']
})
export class PasswordSectionComponent {

  constructor(
    private service:SenhaService
  ){}

  senhas: { nome: string; conteudo: string; isEditing: boolean }[] = [];

  ngOnInit(): void {
    this.service.senhas$.subscribe((aberto) => {
      this.senhas = this.service.consultarSenhas();
    });
  }

  salvarNome(index: number, nome: string, conteudo: string) {
    this.service.salvarNome(index, nome, conteudo);
  }
}
