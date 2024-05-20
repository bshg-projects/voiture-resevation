import {Component, inject} from '@angular/core';
import {NavigationEnd, Router, RouterOutlet} from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {Title} from "@angular/platform-browser";
import {IconSetService} from "@coreui/icons-angular";
import {iconSubset} from "./icons/icon-subset";
import {TokenService} from "./controller/auth/services/token.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: '<router-outlet/>'
})
export class AppComponent {
  title = 'voiture-resevation';

  http = inject(HttpClient)

  constructor(
    private router: Router,
    private titleService: Title,
    private iconSetService: IconSetService,
    private tokenService: TokenService
  ) {
    this.titleService.setTitle(this.title);
    this.iconSetService.icons = {...iconSubset};
  }

  ngOnInit(): void {
    if (!this.tokenService.isLoggedIn()) this.router.navigate(["login"]).then()

    this.router.events.subscribe((evt) => {
      if (!(evt instanceof NavigationEnd)) {
        return;
      }
    });
  }
}
