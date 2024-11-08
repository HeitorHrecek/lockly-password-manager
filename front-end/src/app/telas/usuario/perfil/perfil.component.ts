import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Usuario } from '../usuario';
import { UsuarioService } from '../usuario.service';
import { take } from 'rxjs';
import { LocalStorageService } from '../../local-storage.service';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent {


  constructor(
    private service: UsuarioService,
    private localStorageService: LocalStorageService
  ){}

  ngOnInit(): void {
    const usuario = this.localStorageService.getItem<{id: number, name: string, email: string, password: string}>('usuario');

    if(usuario) {
      this.usuario = usuario;
    }
  }

  usuario: Usuario = {
    id: 0,
    name: '',
    email: '',
    password: ''
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
    this.service.editar(this.usuario).pipe(take(1)).subscribe((novoUsuario) => {
      this.showButtons = false;
      this.isEditable = false;
      if(novoUsuario) {
        this.localStorageService.setItem('usuario', novoUsuario);
      }
    })
  }
}
