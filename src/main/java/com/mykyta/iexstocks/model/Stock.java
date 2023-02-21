package com.mykyta.iexstocks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;


@Table(name = "stocks")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "symbol")
    private String symbol;

    @Column(name = "company_name")
    private String companyName;
    @Column(name = "currency")
    private String currency;
    @Column(name = "price")
    private double price;
    @Column(name = "stock_change")
    private double change;
    @Column(name = "volume")
    private Long volume;

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", currency='" + currency + '\'' +
                ", price=" + price +
                ", change=" + change +
                ", volume=" + volume +
                '}';
    }
}
