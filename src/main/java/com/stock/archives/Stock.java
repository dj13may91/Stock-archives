package com.stock.archives;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STOCK")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_seq")
    @SequenceGenerator(name = "stock_seq", sequenceName = "stock_seq", allocationSize = 1,
            schema = "public", initialValue = 1)
    @Column(name = "ID", nullable = false)
    private Long id = null;

    @Column(name = "INSTANCE", nullable = false)
    private Date instance;

    @Column(name = "SYMBOL", nullable = false)
    private String symbol;

    @Column(name = "OPEN", nullable = false)
    private Double openValue;

    @Column(name = "CLOSE", nullable = false)
    private Double close;

    @Column(name = "LOW", nullable = false)
    private Double low;

    @Column(name = "HIGH", nullable = false)
    private Double high;

    @Column(name = "VOLUME", nullable = false)
    private int volume;

    public Stock(Date instance, String symbol, Double openValue, Double close, Double low, Double high, int volume) {
        this.instance = instance;
        this.symbol = symbol;
        this.openValue = openValue;
        this.close = close;
        this.low = low;
        this.high = high;
        this.volume = volume;
    }
}
