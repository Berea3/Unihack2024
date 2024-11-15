import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WefrComponent } from './wefr.component';

describe('WefrComponent', () => {
  let component: WefrComponent;
  let fixture: ComponentFixture<WefrComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WefrComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WefrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
