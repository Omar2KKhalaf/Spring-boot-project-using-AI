// user.component.ts
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Hackathon } from 'src/app/model/hackathon.model';
import { DatasourceService } from 'src/app/services/datasource/datasource.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  hackathons = []; // Populate this list from a service
  showRegistrationForm = false;
  displayedColumns: string[] = ['hackathonId', 'name', 'theme', 'registrationStartDate', 'registrationEndDate', 'eventDate', 'maxTeamSize', 'maxTeams', 'challengeTitles', 'register'];

  constructor(private dataSourceService: DatasourceService,private router:Router) { }

  ngOnInit(): void {
    // Load hackathons list (fetch from service)
    this.dataSourceService.makeRequest('GET', 'hackathons', {}).subscribe(data => {
      console.log(data);
      this.hackathons = data.hackathons;
    });
  }

  registerHackathon(element: Hackathon): void {
    this.router.navigate(['/register-hackathon',element.hackathonId], {
      state: { hackathon: element}
    });
  }

  registerForHackathon(hackathonId: number) {
    this.showRegistrationForm = true; // Show the registration form for the selected hackathon
  }

  submitRegistration() {
    // Submit registration form logic
  }
}

