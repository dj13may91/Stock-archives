import {Component, OnInit, ViewChild} from '@angular/core';
import {BackendServiceComponent} from "../backend-service/backend-service.component";
import {HttpErrorResponse} from "@angular/common/http";
import {StockModal} from "../stock/stock.component";

@Component({
  selector: 'app-stock-by-name',
  templateUrl: './stock-by-name.component.html',
  styleUrls: ['./stock-by-name.component.css']
})
export class StockByNameComponent implements OnInit {

  public stockNames: string[] = [];
  public stockDetails: StockModal[] = [];
  @ViewChild('search') public searchText: string;
  public startDate: Date;
  public endDate: Date;

  constructor(public backendSvc: BackendServiceComponent) {
  }

  ngOnInit() {
    this.backendSvc.getAllStockNames().subscribe(
      (response: string[]) => this.stockNames.push(...response),
      (error: HttpErrorResponse) => console.log('error', error)
    );
    this.stockNames.sort();
  }

  public getStockDetails(stockName: string){
    this.backendSvc.getStockDetails(stockName).subscribe(
      (respose : StockModal[]) => this.stockDetails = respose,
      (error) => console.log('error getting stock details for ' + stockName, error)
    );
  }
}
