import { Component, Input} from '@angular/core';

@Component({
  selector: 'app-folder',
  standalone: true,
  imports: [],
  templateUrl: './folder.component.html',
  styleUrl: './folder.component.css'
})
export class FolderComponent {
  @Input() nome: string = 'nova pasta';
}