import { Injectable } from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class ApiUriService {
  constructor() {}

  readonly SIGNUP_API_URI: string =
    "http://localhost:8991/auth/signup";

  readonly LOGIN_API_URI: string =
    "http://localhost:8991/auth/login";

  readonly PROCESS_COMPONENT_API_URI: string =
    "http://localhost:8991/component-process/process-request";

  readonly CONFIRM_ORDER_API_URI: string =
    "http://localhost:8991/component-process/complete-processing";
}
