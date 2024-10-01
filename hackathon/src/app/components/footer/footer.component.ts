import { Component } from '@angular/core';
import "@fortawesome/fontawesome-free/css/all.css";
import { TranslateService } from '@ngx-translate/core';
import { LanguageService } from '../../services/language/language.service';



@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  currentYear: number = new Date().getFullYear();
}