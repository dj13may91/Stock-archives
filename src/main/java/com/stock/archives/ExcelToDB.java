package com.stock.archives;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class ExcelToDB {
    private static final String FILE_NAME = "stocks_dump.csv";

    public List<Stock> getStockListFromExcel() throws FileNotFoundException {
        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
        Scanner scan = new Scanner(excelFile);
        scan.nextLine();
        List<String> stockStrings = new ArrayList<>();
        List<Stock> stocks = new ArrayList<>();
        int count = 0;
        while (scan.hasNextLine()) {
            stockStrings.add(scan.nextLine());
        }
        List<String> err = new ArrayList<>();
        stockStrings.stream().forEach((stk) -> {
            try {
                stocks.add(StringToStock(stk));
            } catch (ParseException e) {
                err.add(stk);
            }
        });
        System.out.println(err);
//        System.out.println(stocks.size());
        return stocks;
    }

    public static Stock StringToStock(String line) throws ParseException {
        Stock stock = new Stock();
        String stockArr[] = line.split(",");
        Date date = new SimpleDateFormat("yyyy-mm-dd").parse(stockArr[0].split(" ")[0]);
        stock.setInstance(date);
        stock.setSymbol(stockArr[1]);
        stock.setLow(Double.parseDouble(stockArr[2]));
        stock.setHigh(Double.parseDouble(stockArr[3]));
        stock.setOpenValue(Double.parseDouble(stockArr[4]));
        stock.setClose(Double.parseDouble(stockArr[5]));
        stock.setVolume((int) Double.parseDouble(stockArr[6]));
        return stock;
    }
}
