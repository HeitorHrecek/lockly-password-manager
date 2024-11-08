import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginUsuarioService } from '../login.service';
import { Login } from '../login';
import { LocalStorageService } from '../../local-storage.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  usuario: Login = {
    email: '',
    password: ''
  }

  constructor(
      private service: LoginUsuarioService,
      private localStorageService: LocalStorageService,
      private router: Router
  ){}  

  entrar() {
    this.service.logar(this.usuario).subscribe(() => {
      this.router.navigate(['/home']);
      this.localStorageService.setItem('email-usuario', {email: this.usuario.email});
    });
  }
}
