import { AbstractControl, ValidationErrors } from '@angular/forms'

export class CustomValidator {
  constructor() {}

  static matchPassword(controlName: string, compareControlName: string): ValidationErrors | null {
    return (controls: AbstractControl) => {
      const control = controls.get(controlName)
      const compareControl = controls.get(compareControlName)

      if (compareControl?.errors && !compareControl.errors['isInvalid']) return null
      if (control?.value !== compareControl?.value) {
        compareControl?.setErrors({ isInvalid: true, message: 'Passwords does not match' })
        return { isInvalid: false }
      } else return null
    }
  }
}
