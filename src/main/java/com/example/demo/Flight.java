package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String beginAirport;
    private String endAirport;
    private String price;
    private String airline;

    public Flight(Date date, String beginAirport, String endAirport, String price, String airline) {
        this.date = date;
        this.beginAirport = beginAirport;
        this.endAirport = endAirport;
        this.price = price;
        this.airline = airline;
    }

    public Flight() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBeginAirport() {
        return beginAirport;
    }

    public void setBeginAirport(String beginAirport) {
        this.beginAirport = beginAirport;
    }

    public String getEndAirport() {
        return endAirport;
    }

    public void setEndAirport(String endAirport) {
        this.endAirport = endAirport;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
