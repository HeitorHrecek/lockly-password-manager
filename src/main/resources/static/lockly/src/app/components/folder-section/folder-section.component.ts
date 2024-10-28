import { Component } from '@angular/core';
import { CreateButtonComponent } from '../create-button/create-button.component';
import { FolderComponent } from './folder/folder.component';

@Component({
  selector: 'app-folder-section',
  standalone: true,
  imports: [CreateButtonComponent, FolderComponent],
  templateUrl: './folder-section.component.html',
  styleUrl: './folder-section.component.css'
})
export class FolderSectionComponent {

}
