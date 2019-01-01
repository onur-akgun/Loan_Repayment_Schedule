package edu.westga.cs6311.payments.model;

/**
 * Models a loan account that requires a monthly payment
 * 
 * @author	CS6311
 * @version	Fall 2018
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * ~~ DO NOT MODIFY THE CONTENTS OF THIS FILE AT ALL ~~
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 */
public class Loan {
	private double originalBalance;
	private double rate;
	private int numberOfYears;
	
	/**
	 * Constructor used to initialize the Account object
	 * 
	 * @param originalBalance	The initial loan balance
	 * @param rate				The annual interest rate between 0 (0%) and 1 (100%)
	 * @param numberOfYears		The loan's term in years
	 */
	public Loan(double originalBalance, double rate, int numberOfYears) {
		if (originalBalance < 0) {
			originalBalance = 0;
		}
		if (rate < 0 || rate > 1) {
			rate = 0;
		}
		if (numberOfYears <= 0) {
			numberOfYears = 1;
		}
		this.originalBalance = originalBalance;
		this.rate = rate;
		this.numberOfYears = numberOfYears;
	}
	
	/**
	 * Returns the term of the loan in years
	 * @return	The term of the loan in years
	 */
	public int getTermInYears() {
		return this.numberOfYears;
	}
	
	/**
	 * This method calculates the monthly payment for this loan
	 * @return	The fixed monthly payment for this loan
	 */
	public double getMonthlyPayment() {
		double numerator = this.rate / 12 * this.originalBalance;
		double denominator = 1 - Math.pow(1 + this.rate / 12, -this.numberOfYears * 12);
		return numerator / denominator;
	}
	
	/**
	 * This method returns the interest amount due for the specified 
	 * 	month in the payment schedule
	 * @param	month	The month in which the interest is to be calculated
	 * 					0 < month < this.numberOfYears * 12
	 * @param	year	The year in which the interest is to be calculated
	 * 					0 < year < the loan's term in years
	 * @return	The amount of interest due on the month, year specified
	 */
	public double getInterestOn(int month, int year) {
		return this.rate / 12 * this.getBalanceOn(month, year);
	}
	
	
	/**
	 * Returns the remaining loan balance on the month specified
	 * @param	month	The month to find the balance due
	 * 					0 < month < this.numberOfYears * 12
	 * @param	year	The year in which the interest is to be calculated
	 * 					0 < year < the loan's term in years
	 * @return	The remaining balance on the month, year specified
	 */
	public double getBalanceOn(int month, int year) {
		int currentMonth = (year - 1) * 12 + month;
		double onePlusMonthlyRate = 1 + this.rate / 12;
		double numerator = this.originalBalance * (Math.pow(onePlusMonthlyRate, this.numberOfYears * 12)
						- Math.pow(onePlusMonthlyRate, currentMonth));
		double denominator = Math.pow(onePlusMonthlyRate, this.numberOfYears * 12) - 1;
		return numerator / denominator;
	}
}
