import { Injectable } from '@angular/core'
import { ErrorData } from '../model/error-data.model'
import { OrderComponent } from '../model/order-component.model'
import { ProcessedComponentData } from '../model/processed-component-data.model'

@Injectable({
  providedIn: 'root',
})
export class DataService {
  constructor() {}

  orderComponentData!: OrderComponent

  errorData!: ErrorData

  processedComponentData!: ProcessedComponentData

  setOrderComponentData = (orderComponentData: OrderComponent) => {
    this.orderComponentData = new OrderComponent(orderComponentData)
  }

  setProcessedComponentData = (object: Object) => {
    this.processedComponentData = new ProcessedComponentData(object)
    this.processedComponentData.totalOrderCost = this.processedComponentData.processingCharge + this.processedComponentData.packagingAndDeliveryCharge
  }

  setErrorData = (errorData: ErrorData, status: number) => {
    this.errorData = errorData
  }

  getOrderComponentData = () => this.orderComponentData

  getProcessedComponentData = () => this.processedComponentData

  getErrorData = () => this.errorData

  setInLocalStorage = (key: string, value: string) => localStorage.setItem(key, value)

  getFromLocalStorage = (key: string) => localStorage.getItem(key)

  getItemFromLocalStorage = (key: string) => localStorage.getItem(key)?.toString()

  clearLocalStorage = () => localStorage.clear()
}
