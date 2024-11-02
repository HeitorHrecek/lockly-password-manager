import { Component } from '@angular/core';
import { FolderService } from '../folder-section/folder.service';

@Component({
  selector: 'app-create-button-pastas',
  standalone: true,
  imports: [],
  templateUrl: './create-button.component.html',
  styleUrl: './create-button.component.css'
})
export class CreateButtonPastaComponent {

  constructor(
    private folderService:FolderService
  ){}

  criarPasta() {
    
  }
}
