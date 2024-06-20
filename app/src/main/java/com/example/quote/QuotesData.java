package com.example.quote;

public class QuotesData {
    public  int id;
    public String quote;

    public QuotesData(int id, String quote){
        this.id = id;
        this.quote = quote;
    }

    public QuotesData(){

    }

    public String toString(){
        return quote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}