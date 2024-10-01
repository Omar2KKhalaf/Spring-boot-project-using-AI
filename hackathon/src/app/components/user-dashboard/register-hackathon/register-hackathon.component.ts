import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormArray, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { DatasourceService } from 'src/app/services/datasource/datasource.service';

@Component({
  selector: 'app-register-hackathon',
  templateUrl: './register-hackathon.component.html',
  styleUrls: ['./register-hackathon.component.css']
})
export class RegisterHackathonComponent implements OnInit {
  registrationForm: FormGroup;
  maxTeamSize = 0;
  hackathon;
  challengeList: string[];
  challenge;

  constructor(private fb: FormBuilder, private router: Router,private dataSourceService:DatasourceService) {
    if (router.getCurrentNavigation().extras.state && router.getCurrentNavigation().extras.state) {
      this.hackathon = router.getCurrentNavigation().extras.state.hackathon;
      localStorage.setItem('hackathon', JSON.stringify(router.getCurrentNavigation().extras.state.hackathon));
    } else {
      this.hackathon = JSON.parse( localStorage.getItem('hackathon'));
    }
    console.log(this.hackathon);
    console.log(this.hackathon.challengeTitles);
    this.maxTeamSize=this.hackathon.maxTeamSize;
    this.challengeList=this.hackathon.challengeTitles;
    console.log(this.challengeList);
  }

  ngOnInit(): void {
    this.registrationForm = this.fb.group({
      teamName: ['', Validators.required],
      selectedChallenge: ['', Validators.required],
      teamMembers: this.fb.array([])
    });
    this.addTeamMember();

  }

  createTeamMember(): FormGroup {
    return this.fb.group({
      title: ['', Validators.required],
      name: ['', Validators.required],
      id: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      mobile: ['', Validators.required]
    });
  }

  teamMembers(): FormArray {
    return this.registrationForm.get('teamMembers') as FormArray;
  }

  addTeamMember(): void {
    if (this.teamMembers().length === this.maxTeamSize) {
      return;
    }
    this.teamMembers().push(this.createTeamMember());
  }

  removeTeamMember(index: number): void {
    this.teamMembers().removeAt(index);
  }

  isMaxTeamSizeReached(): boolean {
    return this.teamMembers().length >= this.maxTeamSize;
  }

  // Add validation logic to check for blank fields
  onSubmit() {
    if (this.registrationForm.invalid || this.teamMembers().invalid) {
      this.registrationForm.markAsTouched();
      this.teamMembers().markAsTouched();
      return;
    }

    // Proceed with form submission
    console.log(this.registrationForm.value);
    let competitors=[];
    console.log
    for(let i=0;i<this.teamMembers().controls.length;i++){
      competitors.push({
        name:this.registrationForm.get('teamMembers').value[i].name,
        title:this.registrationForm.get('teamMembers').value[i].title,
        personalId:this.registrationForm.get('teamMembers').value[i].id,
        mobile:this.registrationForm.get('teamMembers').value[i].mobile,
        email:this.registrationForm.get('teamMembers').value[i].email,
      })
    }
    console.log(competitors);
    this.dataSourceService.makeRequest('POST', 'hackathons/'+this.hackathon.hackathonId+"/teams/register", {
      teamName:this.registrationForm.get('teamName').value,
      selectedChallenge:this.registrationForm.get('selectedChallenge').value,
      competitors:competitors
    }).subscribe(data => {
      this.router.navigate(['/user-dashboard']);
    });
  }
}