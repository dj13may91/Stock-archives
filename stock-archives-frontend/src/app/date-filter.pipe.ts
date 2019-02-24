import {Pipe, PipeTransform} from "@angular/core";
import {StockModal} from "./stock/stock.component";

@Pipe({
  name: 'dateFilter'
})
export class DateFilterPipe implements PipeTransform {
  transform(stocks: StockModal[], startDate?: Date, endDate?: Date) {
    if (!stocks) return [];
    let arr: StockModal[];
    if (startDate) {
      arr = this.applyStartDateFilter(stocks, startDate);
      if (endDate) {
        arr = this.applyEndDateFilter(arr, endDate);
      }
    } else if (endDate) {
      arr = this.applyEndDateFilter(stocks, endDate);
    }
    // console.log('startDate', startDate, 'endDate', endDate);
    return arr ? arr : stocks;
  }

  private applyStartDateFilter(arr: StockModal[], date: Date) {
    return arr.filter((stock) => {
      console.log(stock.instance);
      return stock.instance >= date;
    });
  }

  private applyEndDateFilter(arr: StockModal[], date: Date) {
    return arr.filter((stock) => {
      return stock.instance <= date;
    });
  }
}
