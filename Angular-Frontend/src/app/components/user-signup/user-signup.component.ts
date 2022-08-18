import { Component, OnInit } from '@angular/core'
import { FormBuilder, Validators } from '@angular/forms'
import { Title } from '@angular/platform-browser'
import { catchError, EMPTY } from 'rxjs'
import { ApiCallService } from 'src/app/service/apiServices/api-call.service'
import { NavigateRouteService } from 'src/app/service/navigate-route.service'
import { CustomValidator } from 'src/app/validators/customValidator.validator'

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.scss'],
})
export class UserSignupComponent implements OnInit {
  error: boolean = false
  errorMessage: string = ''
  isRegistered: boolean = false

  readonly PASSWORD_REGEX: string = '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}'

  constructor(
    private apiCallService: ApiCallService,
    private navigateRoute: NavigateRouteService,
    private formBuilder: FormBuilder,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('Sign up - Return order management ')
  }

  signUpFormData = this.formBuilder.group(
    {
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.pattern(this.PASSWORD_REGEX)]],
      confirmPassword: ['', Validators.required],
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.minLength(10)]],
    },
    {
      validators: [CustomValidator.matchPassword('password', 'confirmPassword')],
    }
  )

  signUp = () => {
    this.apiCallService
      .signUpService(this.signUpFormData.value)
      .pipe(
        catchError(error => {
          this.error = true
          this.errorMessage = error.error.message
          return EMPTY
        })
      )
      .subscribe(() => {
        this.isRegistered = true
        this.errorMessage = ''
        this.error = false
        setTimeout(() => {
          this.navigateRoute.navigateToLogin()
        }, 1500)
      })
  }

  clearServerErrorMsg = (): void => {
    this.errorMessage = ''
  }
}
