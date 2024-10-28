import { Component } from '@angular/core';
import { HeaderComponent } from "./components/header/header.component";
import { FolderSectionComponent } from "./components/folder-section/folder-section.component";
import { PasswordSectionComponent } from "./components/password-section/password-section.component";


@Component({
  selector: 'app-tela-principal',
  standalone: true,
  imports: [HeaderComponent, FolderSectionComponent, PasswordSectionComponent],
  templateUrl: './tela-principal.component.html',
  styleUrl: './tela-principal.component.css'
})
export class TelaPrincipalComponent {

}
