import {Component, inject} from '@angular/core';
import {NgStyle} from '@angular/common';
import {IconDirective} from '@coreui/icons-angular';
import {
  ButtonDirective,
  CardBodyComponent,
  CardComponent,
  CardGroupComponent,
  ColComponent,
  ContainerComponent,
  FormControlDirective,
  FormDirective,
  FormFeedbackComponent,
  InputGroupComponent,
  InputGroupTextDirective,
  RowComponent,
  SpinnerComponent,
  TextColorDirective
} from '@coreui/angular';
import {AuthService} from "src/app/controller/auth/services/auth.service";
import {Router, RouterLink} from "@angular/router";
import {TokenService} from "src/app/controller/auth/services/token.service";
import {JwtRequestValidator} from "src/app/controller/auth/validators/jwt-request.validator";
import {FormsModule} from "@angular/forms";
import {JwtRequest} from "src/app/controller/auth/entities/jwt-request";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  standalone: true,
  imports: [
    ContainerComponent, RowComponent, ColComponent, CardGroupComponent, TextColorDirective, CardComponent,
    CardBodyComponent, FormDirective, InputGroupComponent, InputGroupTextDirective, IconDirective, FormControlDirective,
    ButtonDirective, NgStyle, FormsModule, SpinnerComponent, RouterLink, FormFeedbackComponent
  ]
})
export class LoginComponent {

  loading = false

  private authService = inject(AuthService)
  private router = inject(Router)
  private tokenService = inject(TokenService)
  protected validator = JwtRequestValidator.init(() => this.item)

  get item(): JwtRequest {
    return this.authService.item;
  }

  set item(value: JwtRequest | undefined) {
    this.authService.item = value;
  }

  login() {
    if (!this.validator.validate()) return;
    this.loading = true;
    this.authService.login().subscribe({
      next: data => {
        console.log(data)
        this.tokenService.setToken(data.accessToken)
        this.validator.reset()
        this.item = undefined
        this.router.navigate(["dashboard"]).then()
        this.loading = false
      },
      error: err => {
        console.log(err)
        this.loading = false;
      }
    })
  }
}
