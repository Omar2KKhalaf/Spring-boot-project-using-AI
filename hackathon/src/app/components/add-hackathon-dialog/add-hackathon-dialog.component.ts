import { Component, Inject, ViewChild } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Hackathon } from "../../model/hackathon.model";
import { TranslateService } from "@ngx-translate/core";
import { LanguageService } from "../../services/language/language.service";
import { FormArray, FormBuilder, FormControl, FormGroup, NgForm, Validators } from "@angular/forms";
import { DatasourceService } from "src/app/services/datasource/datasource.service";
import { MatChipInputEvent } from "@angular/material";
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import * as moment from "moment";


@Component({
  selector: "app-add-hackathon-dialog",
  templateUrl: "./add-hackathon-dialog.component.html",
  styleUrls: ["./add-hackathon-dialog.component.css"],
})
export class AddHackathonDialogComponent {
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  hackathonForm: FormGroup;
  isEdit: boolean = false;

  hackathon: Hackathon = {
    hackathonId: null,
    name: "",
    theme: "",
    registrationEndDate: "",
    registrationStartDate: "",
    eventDate: "",
    maxTeamSize: null,
    maxTeams: null,
    challengeTitles: []
  };

  constructor(
    public dialogRef: MatDialogRef<AddHackathonDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Hackathon,
    private formBuilder: FormBuilder,
    private dataSourceService: DatasourceService,
  ) {
    this.isEdit=localStorage.getItem('isEdit') === "true";
    console.log(this.isEdit);
    console.log(data);
  }

  ngOnInit(): void {
    this.initializeForm();
    this.prefillFormData();
  }

  initializeForm(): void {
    this.hackathonForm = this.formBuilder.group({
      name: [this.hackathon.name, Validators.required],
      startDate: [this.hackathon.registrationStartDate, Validators.required],
      endDate: [this.hackathon.registrationEndDate, Validators.required],
      eventDate: [this.hackathon.eventDate, Validators.required],
      maxTeams: [this.hackathon.maxTeams, Validators.required],
      maxTeamSize: [this.hackathon.maxTeamSize, Validators.required],
      description: [this.hackathon.theme, Validators.required],
      challengeLists: [this.hackathon.challengeTitles] // Initialize challengeList control
    });
  }

  prefillFormData(): void {
    if (this.data) {
      console.log(this.data);
      this.hackathonForm.patchValue({
        name: this.data.name,
        startDate: this.data.registrationStartDate,
        endDate: this.data.registrationEndDate,
        eventDate: this.data.eventDate,
        maxTeams: this.data.maxTeams,
        maxTeamSize: this.data.maxTeamSize,
        description: this.data.theme,
        challengeList: this.data.challengeTitles // Pre-fill challengeList if available
      });
      this.hackathon.hackathonId = this.data.hackathonId;
      this.hackathon.challengeTitles = this.data.challengeTitles;
    }
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our fruit
    if ((value || '').trim()) {
      if(!this.hackathon.challengeTitles){
        this.hackathon.challengeTitles=[];
      }
      this.hackathon.challengeTitles.push(value.trim());
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  remove(i): void {
    const index = this.hackathon.challengeTitles.indexOf(i);

    if (index >= 0) {
      this.hackathon.challengeTitles.splice(index, 1);
    }
  }

  onSubmit(): void {
    console.log(this.hackathonForm.value);
    if (this.hackathonForm.valid) {
      const formData = this.hackathonForm.value;
      const body = {
        name: this.hackathonForm.value.name,
        registrationStartDate: moment(this.hackathonForm.value.startDate).format("YYYY-MM-DD"),
        registrationEndDate: moment(this.hackathonForm.value.endDate).format("YYYY-MM-DD"),
        eventDate: moment(this.hackathonForm.value.eventDate).format("YYYY-MM-DD"),
        maxTeams: this.hackathonForm.value.maxTeams,
        maxTeamSize: this.hackathonForm.value.maxTeamSize,
        theme: this.hackathonForm.value.description,
        challengeTitles: this.hackathon.challengeTitles,
      }
      console.log(body);

      if(this.isEdit){
        this.dataSourceService.makeRequest('PUT', 'hackathons/'+this.hackathon.hackathonId, body)
        .subscribe(data => {
          console.log(data); // Handle response data
          this.dialogRef.close();
        });
      }
      else{
    
      // Send formData to the service
      this.dataSourceService.makeRequest('POST', 'hackathons', body)
        .subscribe(data => {
          console.log(data); // Handle response data
          this.dialogRef.close();
        });
      }
    }
  }
}
