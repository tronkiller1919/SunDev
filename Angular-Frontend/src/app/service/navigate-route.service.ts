import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { DataService } from "./data.service";

@Injectable({
  providedIn: "root",
})
export class NavigateRouteService {
  constructor(private router: Router, private dataService: DataService) {}

  navigateToHome = () => this.router.navigate([""]);
  navigateToLogin = () => this.router.navigate(["/login"]);

  logout = () => {
    this.dataService.clearLocalStorage();
    this.navigateToLogin();
  };

  loginTimedOut = () => {
    alert("Login timeout!! Redirecting to login page");
    setTimeout(() => {
      this.logout();
    }, 200);
  };
}
