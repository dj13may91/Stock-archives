import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {StockComponent} from './stock/stock.component';
import {HttpClientModule} from "@angular/common/http";
import {DateConverterPipe} from './date-converter.pipe';
import {StockByNameComponent} from './stock-by-name/stock-by-name.component';
import {HeaderComponent} from './header/header.component';
import {BackendServiceComponent} from './backend-service/backend-service.component';
import {TextFilterPipe} from './text-filter.pipe';
import {FormsModule} from "@angular/forms";
import {DateFilterPipe} from "./date-filter.pipe";
import { StockChartsComponent } from './stock-charts/stock-charts.component';

@NgModule({
  declarations: [
    AppComponent,
    StockComponent,
    DateConverterPipe,
    StockByNameComponent,
    HeaderComponent,
    TextFilterPipe, DateFilterPipe, StockChartsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule, FormsModule
  ],
  providers: [BackendServiceComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
