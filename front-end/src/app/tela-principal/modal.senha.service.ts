import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ModalService {
  private modalState = new BehaviorSubject<boolean>(false);
  modalState$ = this.modalState.asObservable();

  private senhaData = new BehaviorSubject<{nome: string; conteudo: string } | null>(null);
  senhaData$ = this.senhaData.asObservable();

  private modalPassword = new BehaviorSubject<Boolean>(false);
  modalPassword$ = this.modalPassword.asObservable();

  private modalSatateFolder = new BehaviorSubject<Boolean>(false);
  modalSatateFolder$ = this.modalSatateFolder.asObservable();


  openModal() {
    this.modalState.next(true);
  }

  openModalComDados(nome: string, conteudo: string) {
    this.modalPassword.next(true);
    this.senhaData.next({nome, conteudo });
    this.modalState.next(true);
  }

  closeModal() {
    this.modalState.next(false);
    this.senhaData.next(null);
    this.modalPassword.next(false);
  }

  openModalComPasta() {
    this.openModal();
    this.modalSatateFolder.next(true);
  }
}
