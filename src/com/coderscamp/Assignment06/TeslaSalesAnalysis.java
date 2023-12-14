package com.coderscamp.Assignment06;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;

public class TeslaSalesAnalysis {
    public static void main(String[] args) throws IOException {
        List<SalesData> model3Sales = readSalesData("model3.csv");
        List<SalesData> modelSSales = readSalesData("modelS.csv");
        List<SalesData> modelXSales = readSalesData("modelX.csv");

        printSalesReport("Model 3", model3Sales);
        printSalesReport("Model S", modelSSales);
        printSalesReport("Model X", modelXSales);
    }

    private static List<SalesData> readSalesData(String fileName) throws IOException {
        List<SalesData> salesData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String date = convertDate(values[0]);
                salesData.add(new SalesData(date, Integer.parseInt(values[1])));
            }
        }
        return salesData;
    }

    private static String convertDate(String date) {
        String[] parts = date.split("-");
        String month = new DateFormatSymbols().getMonths()[Integer.parseInt(parts[0]) - 1];
        return parts[1] + "-" + month;
    }
    

    private static void printSalesReport(String modelName, List<SalesData> salesData) {
        System.out.println(modelName + " Yearly Sales Report");
        System.out.println("---------------------------");
        salesData.stream()
                .collect(Collectors.groupingBy(SalesData::getYear, Collectors.summingInt(s -> s.getSales())))
                .forEach((year, totalSales) -> System.out.println(year + " -> " + totalSales));

        SalesData bestMonth = salesData.stream().max(Comparator.comparingInt(s -> s.getSales())).orElse(null);
        SalesData worstMonth = salesData.stream().min(Comparator.comparingInt(s -> s.getSales())).orElse(null);

        if (bestMonth != null) {
            System.out.println("The best month for " + modelName + " was: " + bestMonth.getDate());
        }
        if (worstMonth != null) {
            System.out.println("The worst month for " + modelName + " was: " + worstMonth.getDate());
        }
        System.out.println();
    }
}