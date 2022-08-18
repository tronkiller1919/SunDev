import { Injectable } from '@angular/core'
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router'
import { DataService } from '../data.service'
import { NavigateRouteService } from '../navigate-route.service'

@Injectable({
  providedIn: 'root',
})
export class LoginCheckGuardService implements CanActivate {
  constructor(private dataService: DataService, private navigateRoute: NavigateRouteService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.dataService.getItemFromLocalStorage('loggedIn') === 'TRUE' && this.dataService.getItemFromLocalStorage('Authorization')) {
      this.navigateRoute.navigateToHome()
      return false
    }
    return true
  }
}
