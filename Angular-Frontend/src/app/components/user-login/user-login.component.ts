import { Component, OnInit } from '@angular/core'
import { FormControl, FormGroup, Validators } from '@angular/forms'
import { Title } from '@angular/platform-browser'
import { EMPTY } from 'rxjs'
import { catchError } from 'rxjs/operators'
import { NavigateRouteService } from 'src/app/service/navigate-route.service'
import { LoginData } from '../../model/login-data.model'
import { ApiCallService } from '../../service/apiServices/api-call.service'

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss'],
})
export class UserLoginComponent implements OnInit {
  readonly PASSWORD_PATTERN: string = '^(?=.*[A-Z].*[A-z])(?=.*[!@#$&*])(?=.*[a-z].*[a-z].*[a-z]).{5,}'
  errorMessage!: string
  error: boolean = false
  loginData = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  })

  userLoginData: LoginData = new LoginData('', '')
  constructor(private apiCallService: ApiCallService, private navigateRoute: NavigateRouteService, private titleService: Title) {}

  ngOnInit(): void {
    this.titleService.setTitle('Login - Return order managment')
  }

  onLogin = () => {
    this.userLoginData = new LoginData(this.loginData.value.username, this.loginData.value.password)
    this.apiCallService
      .loginService(this.userLoginData)
      .pipe(
        catchError(error => {
          this.error = true
          this.errorMessage = error.error.message
          return EMPTY
        })
      )
      .subscribe(() => this.navigateRoute.navigateToHome())
  }
}
