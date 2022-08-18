import { Component, HostListener, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { Title } from "@angular/platform-browser";
import { catchError, EMPTY } from "rxjs";
import { ProcessedComponentData } from "src/app/model/processed-component-data.model";
import { ApiCallService } from "src/app/service/apiServices/api-call.service";
import { DataService } from "src/app/service/data.service";
import { NavigateRouteService } from "src/app/service/navigate-route.service";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  componentTypes = ["Integral", "Accessory"];
  error: boolean = false;
  errorMessage: string = "";
  modalOpen: boolean = false;
  default = this.componentTypes[0];
  LOGOUT_EXP_MILLS: number = 10 * 60 * 1000;
  processedComponentData!: ProcessedComponentData;

  constructor(
    private dataService: DataService,
    private apiCallService: ApiCallService,
    private navigateRoute: NavigateRouteService,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.modalOpen = false;
    this.orderComponentFormData.controls["componentType"].setValue(
      this.default
    );
    setTimeout(() => {
      this.navigateRoute.loginTimedOut();
    }, this.LOGOUT_EXP_MILLS);

    this.titleService.setTitle("Home - Return order management");
  }

  @HostListener("document:keydown.escape", ["$event"]) onKeydownHandler(
    event: KeyboardEvent
  ) {
    if (this.modalOpen) this.closeModal();
  }

  orderComponentFormData = new FormGroup({
    customerName: new FormControl("", Validators.required),
    contactNumber: new FormControl("", [
      Validators.required,
      Validators.minLength(10),
    ]),
    componentType: new FormControl(""),
    componentName: new FormControl("", Validators.required),
    quantity: new FormControl("", Validators.required),
  });

  reviewOrder = () => {
    this.dataService.setOrderComponentData(this.orderComponentFormData.value);

    this.apiCallService
      .processComponentService(this.dataService.getOrderComponentData())
      .pipe(
        catchError((error) => {
          if (error.status === 403) {
            this.error = true;
            this.navigateRoute.loginTimedOut();
            console.log(error);
          }
          return EMPTY;
        })
      )
      .subscribe((data) => {
        this.dataService.setProcessedComponentData(data);
        this.modalOpen = true;
        this.processedComponentData =
          this.dataService.getProcessedComponentData();
      });
  };

  cancelOrder = () => {
    this.modalOpen = false;
    this.orderComponentFormData.reset();
    this.orderComponentFormData.controls["componentType"].setValue(
      this.default
    );
  };

  logout = () => {
    try {
      this.navigateRoute.logout();
    } catch (error) {
      console.log(error);
    }
  };

  closeModal = () => (this.modalOpen = !this.modalOpen);
}
