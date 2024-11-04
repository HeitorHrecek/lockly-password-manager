import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
    providedIn: 'root',
})
export class ModalPastaService {

    private modalState = new BehaviorSubject<boolean>(false);
    modalState$ = this.modalState.asObservable();

    private pastaData = new BehaviorSubject<{ id: number, nome: string;} | null>(null);
    pastaData$ = this.pastaData.asObservable();


    openModal(id: number, nome: string) {
        this.modalState.next(true);
        this.pastaData.next({id, nome});

    }

    closeModal() {
        this.modalState.next(false);
        this.pastaData.next(null);
    }
}