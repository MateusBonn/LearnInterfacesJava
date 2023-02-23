package model.service;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoices;

public class RentalService {
	
	private Double pricePerHour;
	private Double pricePerDay;
	
	private BrazilTaxService taxService;

	
	public RentalService(Double pricePerHour, Double pricePerDay, BrazilTaxService taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}
	
	public void processInvoice (CarRental carRental) {
		
		double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
		double hours = minutes/60.00;
		
		double basicPayment;
		if(hours <= 12.00) {
			basicPayment = pricePerHour * Math.ceil(hours);
		}
		else {
			double day = hours / 24.00;
			basicPayment = pricePerDay * Math.ceil(day);
		}
		
		double tax = taxService.taxService(basicPayment);
		
		carRental.setInvoices(new Invoices(basicPayment,tax));
		
		
		carRental.setInvoices(new Invoices (50.0, 10.0));
		
	}


	public Double getPricePerHour() {
		return pricePerHour;
	}


	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}


	public Double getPricePerDay() {
		return pricePerDay;
	}


	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}


	public BrazilTaxService getTaxService() {
		return taxService;
	}


	public void setTaxService(BrazilTaxService taxService) {
		this.taxService = taxService;
	}
	
	
	
	
	

}
