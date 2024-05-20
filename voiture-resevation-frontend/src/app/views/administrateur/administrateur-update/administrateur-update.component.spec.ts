import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AdministrateurCreateComponent} from './user-create.component';

describe('AdministrateurCreateComponent', () => {
  let component: AdministrateurCreateComponent;
  let fixture: ComponentFixture<AdministrateurCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdministrateurCreateComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(AdministrateurCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
