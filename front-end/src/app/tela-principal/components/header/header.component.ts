import { Component } from '@angular/core';
import { PerfilComponent } from './perfil/perfil.component'
import { SearchComponent } from './search/search.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [PerfilComponent, SearchComponent],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  
}
