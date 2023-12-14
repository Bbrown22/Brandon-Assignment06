package com.coderscamp.Assignment06;

public class SalesData {
	private String date;
	private int sales;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public SalesData(String date, int sales) {
		this.date = date;
		this.sales = sales;
	}
	
	public String getYear() {
		return date.split("-") [0];
	}
	public String getMonth() {
		return date.split("-") [1];
	}

}
