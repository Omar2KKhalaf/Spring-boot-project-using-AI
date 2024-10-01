import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddHackathonDialogComponent } from './add-hackathon-dialog.component';

describe('AddHackathonDialogComponent', () => {
  let component: AddHackathonDialogComponent;
  let fixture: ComponentFixture<AddHackathonDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddHackathonDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddHackathonDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
