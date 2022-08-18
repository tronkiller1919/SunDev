import { HttpClientModule } from '@angular/common/http'
import { NgModule } from '@angular/core'
import { ReactiveFormsModule } from '@angular/forms'
import { BrowserModule } from '@angular/platform-browser'
import { RouterModule } from '@angular/router'

import { AppComponent } from './app.component'
import { UserLoginComponent } from './components/user-login/user-login.component'
import { HomeComponent } from './components/home/home.component'
import { LoginCheckGuardService } from './service/routeGuardService/login-check-guard.service'
import { RedirectToLoginUserService } from './service/routeGuardService/redirect-to-login-user.service'
import { CommonModule } from '@angular/common'
import { ProcessedDataListComponent } from './components/home/processed-data-list/processed-data-list.component'
import { UserSignupComponent } from './components/user-signup/user-signup.component'

@NgModule({
  declarations: [AppComponent, UserLoginComponent, HomeComponent, ProcessedDataListComponent, UserSignupComponent],
  imports: [
    BrowserModule,
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(
      [
        {
          path: '',
          component: HomeComponent,
          canActivate: [RedirectToLoginUserService],
        },
        {
          path: 'login',
          component: UserLoginComponent,
          canActivate: [LoginCheckGuardService],
        },
        {
          path: 'signup',
          component: UserSignupComponent,
          canActivate: [LoginCheckGuardService],
        },
      ],
      { useHash: true }
    ),
  ],
  providers: [],

  bootstrap: [AppComponent],
})
export class AppModule {}
