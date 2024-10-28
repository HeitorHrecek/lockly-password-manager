import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FolderSectionComponent } from './folder-section.component';

describe('FolderSectionComponent', () => {
  let component: FolderSectionComponent;
  let fixture: ComponentFixture<FolderSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FolderSectionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FolderSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
