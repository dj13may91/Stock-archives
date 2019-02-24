import {Component, Input, OnInit} from '@angular/core';
import {BackendServiceComponent} from "../backend-service/backend-service.component";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {
  @Input()
  public stocks: StockModal[] = [];

  constructor(public backendSvc: BackendServiceComponent) {
  }

  ngOnInit() {
    let page = 0;
    while(page < 10){
      this.backendSvc.getStockTop100(page++).subscribe(
        (response) => {
          console.log(page);
          setTimeout(() => {
            this.stocks.push(...response['content']);
          }, 3000);
        },
        (error: HttpErrorResponse) => console.log(error));
    }
  }
}

export class StockModal {
  public id: number;
  public instance: Date;
  public low: number;
  public high: number;
  public openValue: number;
  public close: number;
  public symbol: string;
  public volume: number;
}
