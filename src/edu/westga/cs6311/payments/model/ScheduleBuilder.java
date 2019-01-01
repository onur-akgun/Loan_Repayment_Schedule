package edu.westga.cs6311.payments.model;

/**
 * ScheduleBuilder build a String that represents the amortization 
 * 	schedule of loan balances over a period of years.
 * 
 * @author Ibrahim Tonifarah
 * @version 10/10/2018
 *
 */
public class ScheduleBuilder {
	private String theResults;
	private Loan theLoan;
	
	/**
	 * Builds a new ScheduleBuilder object to build the amortization 
	 * 	schedule for the Loan specified
	 * @param	theLoan		The Loan to get the amortization schedule
	 */
	public ScheduleBuilder(Loan theLoan) {
		this.theResults = "";
		this.theLoan = theLoan;
	}
		
	/**
	 * Builds the results table for the specified Loan
	 */
	public void buildTable() {
		this.buildHeading();
		this.buildYears();
	}
	
	/**
	 * Returns a String containing the results table for the Loan
	 * 
	 * @return	The full table
	 */
	public String getResults() {
		return this.theResults;
	}
	
	/**
	 * Helper method that will build the table's heading
	 */
	private void buildHeading() {
		this.theResults = "\nMonth   ";
		this.theResults += this.headingFormat("Payment");
		this.theResults += this.headingFormat("Principal");
		this.theResults += this.headingFormat("Interest");
		this.theResults += this.headingFormat("Balance");
		this.theResults += "\n";
	}
	
	/**
	 * This method will go through each of the years in the amortization schedule
	 * 	and call the method to build one year at a time
	 */
	private void buildYears() {
		for (int yearNumber = 1; yearNumber <= this.theLoan.getTermInYears(); yearNumber++) {
			this.theResults += this.buildNextYear(yearNumber);
		}
	}
	
	/**
	 * This method is used to build the amortization schedule for the year specified
	 * @param yearNumber	This is the year being calculated for the table
	 * @return				The String holding the formatted data for the year specified
	 */
	private String buildNextYear(int yearNumber) {
		String theYear = "";
		
		for (int monthNumber = 1; monthNumber <= 12; monthNumber++) {
			theYear += monthNumber + "\t";
			theYear += this.currencyFormat(this.theLoan.getMonthlyPayment());
			theYear += this.currencyFormat(this.theLoan.getMonthlyPayment() - this.theLoan.getInterestOn(monthNumber, yearNumber));
			theYear += this.currencyFormat(this.theLoan.getInterestOn(monthNumber, yearNumber));
			theYear += this.currencyFormat(this.theLoan.getBalanceOn(monthNumber, yearNumber)) + "\n";	
		}

		theYear += "~~~~ Year " + yearNumber + " ~~~~";
		
		return theYear + "\n";
	}
	
	/**
	 * This helper method is used to format the value as currency.  It makes the table
	 * 	look nicer
	 * @param 	value	The value to be formatted as currency
	 * @return	The value formatted as currency
	 */
	private String currencyFormat(double value) {
		return String.format("$%-11.2f", value);
	}
	
	/**
	 * This helper method is used to format the value as a heading to fit in the correct
	 * 	number of spaces
	 * @param 	value	The value to be formatted
	 * @return	The value formatted for a heading
	 */
	private String headingFormat(String value) {
		return String.format("%-12s", value);
	}
}
