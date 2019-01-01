
package edu.westga.cs6311.payments.controller;

/**
 * This class is used to create a PaymentController, input user loan data, build
 * and display table schedule
 * 
 * @author Ibrahim Tonifarah
 * @version 10/11/2018
 *
 */
public class LoanDriver {

	/**
	 * This is the entry in to the application
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		PaymentController payment = new PaymentController();
		payment.inputLoanData();
		payment.createSchedule();
		payment.buildTable();
		payment.displayTable();
	}
}
