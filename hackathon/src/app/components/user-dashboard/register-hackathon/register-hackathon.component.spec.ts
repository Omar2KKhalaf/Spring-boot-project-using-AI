import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterHackathonComponent } from './register-hackathon.component';

describe('RegisterHackathonComponent', () => {
  let component: RegisterHackathonComponent;
  let fixture: ComponentFixture<RegisterHackathonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterHackathonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterHackathonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
