
import { Component, OnInit } from '@angular/core';
import { PasswordComponent } from "./password/password.component";
import { SenhaService } from './senha.service';
import { CreateButtonSenhaComponent } from '../create-button-senhas/create-button.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-password-section',
  standalone: true,
  imports: [CreateButtonSenhaComponent, PasswordComponent, FormsModule, CommonModule],
  templateUrl: './password-section.component.html',
  styleUrls: ['./password-section.component.css']
})
export class PasswordSectionComponent implements OnInit{

  constructor(
    private service:SenhaService
  ){}

  senhas: {id:number, nome: string; conteudo: string; isEditing: boolean }[] = [];

  ngOnInit(): void {
    this.service.consultarSenhas();
    this.service.senhas$.subscribe((snehasBack) => {
      this.senhas = snehasBack;
    });
  }

  salvarNome(index: number, nome: string, conteudo: string) {
    this.service.salvarNome(index, nome, conteudo);
  }
}
