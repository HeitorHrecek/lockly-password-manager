// src/app/app.module.ts

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { CadastroComponent } from './telas/cadastro/cadastro.component';
import { AppRoutingModule } from './app.routes';

@NgModule({
  declarations: [
    AppComponent,
    CadastroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule // Adicionar AppRoutingModule aos imports
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
