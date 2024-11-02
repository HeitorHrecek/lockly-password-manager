import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ModalService {
  private modalState = new BehaviorSubject<boolean>(false);
  modalState$ = this.modalState.asObservable();

  private senhaData = new BehaviorSubject<{id: number, nome: string; conteudo: string } | null>(null);
  senhaData$ = this.senhaData.asObservable();

  private modalPassword = new BehaviorSubject<Boolean>(false);
  modalPassword$ = this.modalPassword.asObservable();

  openModal() {
    this.modalState.next(true);
  }

  openModalComDados(id: number, nome: string, conteudo: string) {
    this.modalPassword.next(true);
    this.senhaData.next({id, nome, conteudo });
    this.modalState.next(true);
  }

  closeModal() {
    this.modalState.next(false);
    this.senhaData.next(null);
    this.modalPassword.next(false);
  }
}
