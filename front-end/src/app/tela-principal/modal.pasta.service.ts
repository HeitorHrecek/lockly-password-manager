import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
    providedIn: 'root',
})
export class ModalPastaService {

    private modalState = new BehaviorSubject<boolean>(false);
    modalState$ = this.modalState.asObservable();


    openModal() {
        this.modalState.next(true);
    }

    openModalComDados(id: number, nome: string, conteudo: string) {
        
    }

    closeModal() {
        this.modalState.next(false);
    }
}