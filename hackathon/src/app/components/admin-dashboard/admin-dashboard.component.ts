import { Component } from '@angular/core';
import { Hackathon } from '../../model/hackathon.model';
import { MatDialog } from '@angular/material/dialog';
import { AddHackathonDialogComponent } from '../add-hackathon-dialog/add-hackathon-dialog.component';
import { Router } from '@angular/router';
import { DatasourceService } from 'src/app/services/datasource/datasource.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {
  hackathon: Hackathon = {};

  hackathons: Hackathon[] = [];
  filteredHackathons: Hackathon[] = [];
  displayedColumns: string[] = ['hackathonId', 'name', 'theme', 'registrationStartDate', 'registrationEndDate', 'eventDate', 'maxTeamSize', 'maxTeams', 'challengeTitles', 'actions'];

  loadHackathons(): void {
    // Temporary hackathon data for demonstration
    this.dataSourceService.makeRequest('GET', 'hackathons', {}).subscribe(data => {
      console.log(data);
      this.hackathons=data.hackathons;
      this.filteredHackathons = data.hackathons;
    });
  }

  applyFilter(filterValue: string): void {
    this.filteredHackathons = this.hackathons.filter(hackathon =>{
      console.log(hackathon.name);
      return hackathon.name.toLowerCase().includes(filterValue.trim().toLowerCase())
    }
    );
  }

  editHackathon(id: string): void {
    // Navigation logic to edit page or dialog
    console.log('Editing hackathon', id);
  }

  deleteHackathon(id: string): void {
    // Perform delete operation
    this.dataSourceService.makeRequest('DELETE', 'hackathons/'+id, {}).subscribe(data => {
      this.loadHackathons();
    });
  }

  constructor(public dialog: MatDialog, private router: Router, private dataSourceService: DatasourceService) {
    this.loadHackathons();
  }

  // In your admin-dashboard.component.ts

  openAddHackathonDialog(hackathonToEdit?: Hackathon) { // Optional parameter for editing
    if(hackathonToEdit)
      localStorage.setItem('isEdit', "true");
    else
      localStorage.setItem('isEdit',null);

    const dialogRef = this.dialog.open(AddHackathonDialogComponent, {
      width: '50%',
      data: hackathonToEdit ? hackathonToEdit : {} // Pass the data to the dialog
    });

    dialogRef.afterClosed().subscribe(result => {
      // If the dialog was closed after editing, you might want to update the list or do something with the result
      console.log('The dialog was closed', result);
      if (result) {
        // update your hackathons list or refresh it
      }
    });
  }

  navigateToHackathonDetail(row): void {
    console.log(row);
    this.router.navigate(['/hackathon-details', row.hackathonId]);
  }
}