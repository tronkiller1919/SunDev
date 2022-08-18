export class OrderComponent {
  public customerName!: string
  public contactNumber!: string
  public componentType!: string
  public componentName!: string
  public quantity!: number
  constructor(obj: Object) {
    Object.assign(this, obj)
  }
}
