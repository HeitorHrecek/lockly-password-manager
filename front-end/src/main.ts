import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { provideRouter, Route } from '@angular/router';
import { PerfilComponent } from './app/telas/usuario/perfil/perfil.component';
import { importProvidersFrom } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';


const routes: Route[] = [
  { path: 'perfil', component: PerfilComponent },
];

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    importProvidersFrom(HttpClientModule) 
  ]
})
  .catch((err) => console.error(err));
