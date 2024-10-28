import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter, Route } from '@angular/router';
import { CadastroComponent } from './app/telas/usuario/cadastro/cadastro.component';
import { HttpClientModule } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';
import { TelaInicialComponent } from './app/telas/tela-inicial/tela-inicial.component';

const routes: Route[] = [
  { path: 'cadastro', component: CadastroComponent },
  { path: '', component: TelaInicialComponent}
];

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    importProvidersFrom(HttpClientModule) 
  ]
})
  .catch((err) => console.error(err));
