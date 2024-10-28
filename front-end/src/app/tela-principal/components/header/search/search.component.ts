import { Component } from '@angular/core';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {

  public queryByName(){
    const searchForm = document.getElementById("searchForm");
    const name = document.getElementsByTagName("input")[0].value;
    const firstRoute = `/consult/by-name/${name}`;
    const secondRoute = `/consult/name/${name}`;

    
  }
}
