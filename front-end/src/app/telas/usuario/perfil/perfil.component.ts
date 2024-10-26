import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Usuario } from '../usuario';
import { PerfilService } from './perfil.service';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent {


  constructor(
    private service: PerfilService,
  ){}

  usuario: Usuario = {
    id: 4,
    name: 'Pedro',
    email: 'pedroteste@gmail.com',
    password: '123456jvwnfownfnwf!'
  }

  showButtons = false;
  isEditable = false;

  editar() {
    this.showButtons = true;
    this.isEditable = true;
  }

  cancelar() {
    this.showButtons = false;
    this.isEditable = false;
  }

  confirmar() {
    this.service.editar(this.usuario).subscribe(() => {
      this.showButtons = false;
      this.isEditable = false;
    })
  }
}
