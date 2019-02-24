import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {StockModal} from "../stock/stock.component";

@Injectable()
export class BackendServiceComponent implements OnInit {

  public backendUrl = "http://localhost:8080";
  public header = new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': '*',
    'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
  });

  public httpOptions: { headers: HttpHeaders };

  constructor(public http: HttpClient) {
  }

  ngOnInit() {
    this.httpOptions = {
      headers: this.header
    };
  }

  public getStockCount() {
    return this.http.get(this.backendUrl + '/count', this.httpOptions);
  }

  public getStockById() {
    this.http.get(this.backendUrl + '/id', this.httpOptions).subscribe(
      (response: StockModal) => response, // this.tempStock =
      (error: HttpErrorResponse) => console.log(error));
  }

  public getStockTop100(pageNumber: number) {
    return this.http.get(this.backendUrl + '/top25/' + pageNumber, this.httpOptions);
  }

  public getAllStockNames(){
    return this.http.get(this.backendUrl + '/stocknames', this.httpOptions);
  }

  public getStockDetails(stockName: string){
    return this.http.get(this.backendUrl + '/stockdetails/' + stockName, this.httpOptions);
  }

}
