import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from "./tela-principal/components/header/header.component";
import { FolderSectionComponent } from "./tela-principal/components/folder-section/folder-section.component";
import { PasswordComponent } from './tela-principal/components/password-section/password/password.component';
import { PasswordSectionComponent } from "./tela-principal/components/password-section/password-section.component";


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, FolderSectionComponent, PasswordComponent, PasswordSectionComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  
}
