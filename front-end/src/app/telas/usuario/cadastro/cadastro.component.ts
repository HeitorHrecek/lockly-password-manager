import { Component } from '@angular/core';
import { Usuario } from '../usuario';
import { CadastroUsuarioService } from '../cadastro.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
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

