import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AdministrateurListComponent} from './administrateur-list.Component';

describe('AdministrateurListComponent', () => {
  let component: AdministrateurListComponent;
  let fixture: ComponentFixture<AdministrateurListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdministrateurListComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(AdministrateurListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
