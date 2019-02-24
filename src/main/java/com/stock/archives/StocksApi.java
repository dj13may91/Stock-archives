package com.stock.archives;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class StocksApi {

    @Autowired
    StockRepository repository;

    @Autowired
    StockRepositoryPages page;

    @Autowired
    ExcelToDB excelToDB;

    @RequestMapping(value = "health")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Stock> health() {
        double l = 0;
        Stock s = repository.save(new Stock(new Date(), "str", l, l, l, l, 0));
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "id")
    public ResponseEntity<Stock> getStockById() {
        long id = 5;
        Stock s = repository.getById(id);
        System.out.println("returning: " + s);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "top25/{pageNumber}")
    public ResponseEntity<org.springframework.data.domain.Page<Stock>> getTenStocks(@PathVariable int pageNumber) {
        Sort sortBy = new Sort(Sort.Direction.ASC, "id");
        Pageable ten = PageRequest.of(pageNumber, 100, sortBy);
        return new ResponseEntity<>(page.findAll(ten), HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "count")
    public ResponseEntity<Long> getTotalStocks() {
        long count = repository.count();
        System.out.println("total records: " + count);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "stocknames")
    public ResponseEntity<List<String>> getAllStockNames() {
        List<String> stockNames = repository.findDistinctBySymbol();
        return new ResponseEntity<>(stockNames, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(value = "stockdetails/{stockName}")
    public ResponseEntity<List<Stock>> getStockDetailsByStockName(@PathVariable String stockName) {
        List<Stock> stockDetails = repository.getStockBySymbolOrderByInstanceDesc(stockName);
        return new ResponseEntity<>(stockDetails, HttpStatus.OK);
    }


    @RequestMapping(value = "dumptodb")
    public ResponseEntity<String> saveExcelToDb() {
        int range = 0;
        int incCounter = 16384;
        List<Stock> stocks;
        try {
            stocks = excelToDB.getStockListFromExcel();
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>("Error reading excel, try again", HttpStatus.CREATED);
        }

        List<Stock> failedSaves = dbBulkInsert(range, incCounter, stocks);

        if (failedSaves.size() > 0)
            saveFailedOneByOne(failedSaves, incCounter);
        return new ResponseEntity<>("Saved", HttpStatus.CREATED);
    }

    private void saveFailedOneByOne(List<Stock> failedStocks, int incCounter) {
        List<Stock> failedSaves = new ArrayList<>();
        incCounter = incCounter / 2;
        failedSaves.addAll(dbBulkInsert(0, incCounter, failedStocks));
        while (failedSaves.size() > 0) {
            failedStocks.clear();
            failedStocks.addAll(failedSaves);
            failedSaves.clear();
            incCounter = failedStocks.size() > incCounter ? incCounter / 2 : failedStocks.size();
            if (incCounter == 1) {
                dbBulkInsert(0, incCounter, failedStocks);
            } else {
                failedSaves.addAll(dbBulkInsert(0, incCounter, failedStocks));
            }
        }
    }

    private List<Stock> dbBulkInsert(int range, int incCounter, List<Stock> stocks) {
        List<Stock> failedSaves = new ArrayList<>();
        System.out.println("inc counter: " + incCounter + "; objects to save: " + stocks.size());
        while (range < stocks.size()) {
            List<Stock> subList;
            try {
                subList = stocks.subList(range, range + incCounter);
                range = range + incCounter;
            } catch (IndexOutOfBoundsException ex) {
                subList = stocks.subList(range, stocks.size());
                range = stocks.size();
            }
            try {
                repository.saveAll(subList);
                System.out.print("-");
            } catch (Exception ex) {
                failedSaves.addAll(subList);
            }
        }
        System.out.println("\ninc counter: " + incCounter + "; failed count: " + failedSaves.size());
        return failedSaves;
    }
}
