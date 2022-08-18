import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { map } from 'rxjs/operators'
import { SignUpData } from 'src/app/model/sign-up-data.model'
import { LoginData } from '../../model/login-data.model'
import { OrderComponent } from '../../model/order-component.model'
import { DataService } from '../data.service'
import { NavigateRouteService } from '../navigate-route.service'
import { ApiUriService } from './api-uri.service'

@Injectable({
  providedIn: 'root',
})
export class ApiCallService {
  constructor(
    private http: HttpClient,
    private dataService: DataService,
    private apiUri: ApiUriService,
    private navigateRoute: NavigateRouteService
  ) {}

  signUpService = (signUpData: SignUpData) => this.http.post(this.apiUri.SIGNUP_API_URI, signUpData)

  loginService = (loginData: LoginData) =>
    this.http.post(this.apiUri.LOGIN_API_URI, loginData).pipe(
      map(jwt => {
        this.dataService.setInLocalStorage('Authorization', Object.values(jwt)[0])
        this.dataService.setInLocalStorage('loggedIn', 'TRUE')
      })
    )

  processComponentService = (orderComponent: OrderComponent) => {
    const JSON_TEXT = JSON.stringify(orderComponent)

    const params = new HttpParams().set('processRequest', JSON_TEXT)

    return this.http.get(this.apiUri.PROCESS_COMPONENT_API_URI, {
      headers: this.getAuthorizationHeaders(),
      params: params,
    })
  }

  confirmOrder = () =>
    this.http.post(this.apiUri.CONFIRM_ORDER_API_URI, null, {
      headers: this.getAuthorizationHeaders(),
    })

  getAuthorizationHeaders = () =>
    new HttpHeaders({
      Authorization: this.dataService.getItemFromLocalStorage('Authorization')!,
    })
}
