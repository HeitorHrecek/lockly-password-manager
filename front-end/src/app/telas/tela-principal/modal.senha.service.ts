import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ModalService {
  private modalState = new BehaviorSubject<boolean>(false);
  modalState$ = this.modalState.asObservable();

  private senhaData = new BehaviorSubject<{ nome: string; conteudo: string } | null>(null);
  senhaData$ = this.senhaData.asObservable();

  private modalPassword = new BehaviorSubject<Boolean>(false);
  modalPassword$ = this.modalPassword.asObservable();

  private modalSatateFolder = new BehaviorSubject<Boolean>(false);
  modalSatateFolder$ = this.modalSatateFolder.asObservable();

  private senhaComPastaData = new BehaviorSubject<{ nome: string; conteudo: string } | null>(null);
  senhaComPastaData$ = this.senhaComPastaData.asObservable();


  openModal() {
    this.modalState.next(true);
  }

  openModalComDados(nome: string, conteudo: string, comPasta: boolean) {
    console.log(nome);
    if (comPasta) {
      this.senhaComPastaData.next({ nome, conteudo });
      this.modalSatateFolder.next(true);
      this.modalPassword.next(true);
      this.modalState.next(true);
    } else {
      this.senhaData.next({ nome, conteudo });
      this.modalPassword.next(true);
      this.modalState.next(true);
    }
  }

  closeModal() {
    this.modalState.next(false);
    this.senhaData.next(null);
    this.senhaComPastaData.next(null);
    this.modalPassword.next(false);
    this.modalSatateFolder.next(false);
    window.location.reload();
  }

  openModalComPasta() {
    this.openModal();
    this.modalSatateFolder.next(true);
  }
}
