package org.example;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

public class App {
    private static final String SYMBOL = "DJI"; // Dow Jones Industrial Average
    private static final long INTERVAL_MS = 5000; // 5 seconds

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                queryAndStoreStockPrice();
            }
        }, 0, INTERVAL_MS);
    }

    private static void queryAndStoreStockPrice() {
        try {
            Stock stock = YahooFinance.get(SYMBOL);
            StockQuote quote = stock.getQuote();
            BigDecimal price = quote.getPrice();
            BigDecimal change = quote.getChangeInPercent();
            String timestamp = java.time.LocalDateTime.now().toString();

            System.out.println("Timestamp: " + timestamp);
            System.out.println("Symbol: " + SYMBOL);
            System.out.println("Price: " + price);
            System.out.println("Change (%): " + change);
            System.out.println();
        } catch (Exception e) {
            System.err.println("Error fetching stock data: " + e.getMessage());
        }
    }
}
