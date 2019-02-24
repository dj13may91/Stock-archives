import {AfterViewChecked, Component, Input, NgZone, OnInit} from '@angular/core';
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import {StockModal} from "../stock/stock.component";

@Component({
  selector: 'app-stock-charts',
  templateUrl: './stock-charts.component.html',
  styleUrls: ['./stock-charts.component.css']
})
export class StockChartsComponent implements OnInit, AfterViewChecked {
  private chart: am4charts.XYChart;

  @Input() public stockData: StockModal[];

  constructor(private zone: NgZone) {
  }

  ngOnInit() {
  }

  ngAfterViewChecked() {
    this.zone.runOutsideAngular(() => {
      if (this.chart) {
        this.chart.dispose();
      }
    });

    this.zone.runOutsideAngular(() => {
      let chart = am4core.create("chartdiv", am4charts.XYChart);

      chart.paddingRight = 20;
      chart.readerTitle = this.stockData[0].symbol;

      let data = [];
      console.log(this.stockData);
      this.stockData.forEach((stock) => {
        data.push({date: stock.instance, close: Math.round(stock.close)});
      });

      chart.data = data;

      let dateAxis = chart.xAxes.push(new am4charts.DateAxis());
      dateAxis.renderer.grid.template.location = 0;
      dateAxis.title.text = 'Date';

      let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
      valueAxis.tooltip.disabled = true;
      valueAxis.title.text = chart.readerTitle + ' Closing value';

      let close = chart.series.push(new am4charts.LineSeries());
      close.dataFields.dateX = "date";
      close.dataFields.valueY = "close";

      close.tooltipText = "{valueY.value}";
      chart.cursor = new am4charts.XYCursor();

      // let scrollbarX = new am4charts.XYChartScrollbar();
      // scrollbarX.series.push(close);
      // chart.scrollbarX = scrollbarX;

      this.chart = chart;
    });

  }
}
