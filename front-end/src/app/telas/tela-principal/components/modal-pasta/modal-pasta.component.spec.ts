import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalPastaComponent } from './modal-pasta.component';

describe('ModalPastaComponent', () => {
  let component: ModalPastaComponent;
  let fixture: ComponentFixture<ModalPastaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalPastaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalPastaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
