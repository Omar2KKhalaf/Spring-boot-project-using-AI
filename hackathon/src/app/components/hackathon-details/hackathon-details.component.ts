import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DatasourceService } from 'src/app/services/datasource/datasource.service';


@Component({
  selector: 'app-hackathon-details',
  templateUrl: './hackathon-details.component.html',
  styleUrls: ['./hackathon-details.component.css']
})
export class HackathonDetailsComponent implements OnInit {
  hackathonId: string;
  hackathon={};

  constructor(private route: ActivatedRoute,private dataSourceService:DatasourceService) { }

  ngOnInit(): void {
    //TODO: Fetch the hackathon details from the backend
    // Get the hackathon ID from the route parameters
    this.hackathonId = this.route.snapshot.paramMap.get('id');
    // You can then use this hackathonId to fetch the specific hackathon details from your backend or service
    // Assign the hackathon details to the component properties
    this.dataSourceService.makeRequest('GET', 'hackathons/'+this.hackathonId, {}).subscribe(data => {
      console.log(data);
      this.hackathon=data;
    });
  }
}