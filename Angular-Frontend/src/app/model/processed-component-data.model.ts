export class ProcessedComponentData {
  public customerName!: string
  public customerNumber!: string
  public componentType!: string
  public componentName!: string
  public quantity!: number
  public processingCharge!: number
  public packagingAndDeliveryCharge!: number
  public dateOfDelivery!: Date
  public message!: string
  public totalOrderCost!: number

  constructor(obj: Object) {
    Object.assign(this, obj)
  }
}
