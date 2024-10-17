import { Component } from '@angular/core';
import { CreateButtonComponent } from '../create-button/create-button.component';
import { PasswordComponent } from "./password/password.component";

@Component({
  selector: 'app-password-section',
  standalone: true,
  imports: [CreateButtonComponent, PasswordComponent],
  templateUrl: './password-section.component.html',
  styleUrl: './password-section.component.css'
})
export class PasswordSectionComponent {

}
