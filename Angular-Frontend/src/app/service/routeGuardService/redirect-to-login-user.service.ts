import { Injectable } from '@angular/core'
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router'
import { DataService } from '../data.service'
import { NavigateRouteService } from '../navigate-route.service'

@Injectable({
  providedIn: 'root',
})
export class RedirectToLoginUserService implements CanActivate {
  constructor(private navigateRoute: NavigateRouteService, private dataService: DataService) {}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (
      this.dataService.getFromLocalStorage('loggedIn') === 'FALSE' ||
      this.dataService.getFromLocalStorage('loggedIn') === null ||
      !this.dataService.getFromLocalStorage('Authorization')
    ) {
      this.navigateRoute.navigateToLogin()
      return false
    }
    return true
  }
}
