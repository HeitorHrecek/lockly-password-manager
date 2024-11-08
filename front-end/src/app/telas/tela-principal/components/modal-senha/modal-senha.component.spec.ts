import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalSenhaComponent } from './modal-senha.component';

describe('ModalSenhaComponent', () => {
  let component: ModalSenhaComponent;
  let fixture: ComponentFixture<ModalSenhaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalSenhaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalSenhaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
