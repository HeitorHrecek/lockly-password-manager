import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter, Route } from '@angular/router';
import { PerfilComponent } from './app/telas/usuario/perfil/perfil.component';
import { HttpClientModule } from '@angular/common/http';
import { CadastroComponent } from './app/telas/usuario/cadastro/cadastro.component';
import { importProvidersFrom } from '@angular/core';
import { TelaInicialComponent } from './app/telas/tela-inicial/tela-inicial.component';
import { LoginComponent } from './app/telas/usuario/login/login.component';
import { TelaPrincipalComponent } from './app/telas/tela-principal/tela-principal.component';

const routes: Route[] = [
  { path: 'cadastro', component: CadastroComponent },
  { path: '', component: TelaInicialComponent},
  { path: 'perfil', component: PerfilComponent },
  { path: 'login', component: LoginComponent},
  { path: 'home', component: TelaPrincipalComponent}
];

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    importProvidersFrom(HttpClientModule) 
  ]
})
  .catch((err) => console.error(err));
