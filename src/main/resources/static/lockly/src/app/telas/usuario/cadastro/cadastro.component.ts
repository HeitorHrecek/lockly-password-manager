import { Component } from '@angular/core';
import { CadastroUsuarioService } from '../cadastroUsuario.service';
import { Route, Router } from '@angular/router';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.css'
})
export class CadastroComponent {

  usuario: Usuario = {
    name: '',
    email: '',
    password: ''
  }

  constructor(
    private service: CadastroUsuarioService,
    private router: Router
  ){}  

  cadastrar() {
    this.service.criar(this.usuario).subscribe(() => {
      this.router.navigate(['/'])
    });
  }
}
