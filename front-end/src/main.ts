import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter, Route } from '@angular/router';
import { importProvidersFrom } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { TelaPrincipalComponent } from './app/tela-principal/tela-principal.component';

const routes: Route[] = [
  { path: 'home', component: TelaPrincipalComponent}
];

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    importProvidersFrom(HttpClientModule) 
  ]
})
  .catch((err) => console.error(err));
