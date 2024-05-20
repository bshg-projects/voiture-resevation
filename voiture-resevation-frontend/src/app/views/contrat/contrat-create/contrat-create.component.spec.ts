import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ContratCreateComponent} from './user-create.component';

describe('ContratCreateComponent', () => {
  let component: ContratCreateComponent;
  let fixture: ComponentFixture<ContratCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContratCreateComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ContratCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
