import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-password',
  standalone: true,
  imports: [],
  templateUrl: './password.component.html',
  styleUrl: './password.component.css'
})
export class PasswordComponent {
  @Input() nome: string = 'nova senha';
}
