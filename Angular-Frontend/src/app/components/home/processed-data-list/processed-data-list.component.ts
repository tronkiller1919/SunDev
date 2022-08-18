import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core'
import { catchError, EMPTY } from 'rxjs'
import { ProcessedComponentData } from 'src/app/model/processed-component-data.model'
import { ApiCallService } from 'src/app/service/apiServices/api-call.service'
import { DataService } from 'src/app/service/data.service'

@Component({
  selector: 'app-processed-data-list',
  templateUrl: './processed-data-list.component.html',
  styleUrls: ['./processed-data-list.component.scss'],
})
export class ProcessedDataListComponent implements OnInit, OnChanges {
  orderPlaced: boolean = false

  constructor(private dataService: DataService, private apiCallService: ApiCallService) {}

  processedComponentData!: ProcessedComponentData

  @Input() componentData!: ProcessedComponentData

  @Output() modalOpenEmitter: EventEmitter<boolean> = new EventEmitter<boolean>()

  ngOnInit(): void {
    this.orderPlaced = false
    this.processedComponentData = this.dataService.getProcessedComponentData()
  }

  ngOnChanges(): void {}

  confirmOrder = () => {
    this.apiCallService
      .confirmOrder()
      .pipe(
        catchError(error => {
          console.log(error)
          return EMPTY
        })
      )
      .subscribe(data => {
        this.dataService.setProcessedComponentData(data)
        this.orderPlaced = true
        this.processedComponentData = this.dataService.getProcessedComponentData()
        console.log(this.processedComponentData.message)
      })
  }

  editOrder = () => {
    this.modalOpenEmitter.emit()
  }
}
