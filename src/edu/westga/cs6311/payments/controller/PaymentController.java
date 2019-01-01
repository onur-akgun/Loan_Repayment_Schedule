
package edu.westga.cs6311.payments.controller;

import java.util.Scanner;

import edu.westga.cs6311.payments.model.Loan;
import edu.westga.cs6311.payments.model.ScheduleBuilder;

/**
 * This class is used to build a table and display user loan results
 * 
 * @author Ibrahim Tonifarah
 * @version 10/11/2018
 *
 */
public class PaymentController {
	private ScheduleBuilder scheduleBuilder;
	private double initialLoanBalance;
	private double annualInterestRate;
	private int numberOfYears;
	private Scanner scan;

	/**
	 * Allows to initialize the instance variables
	 */
	public PaymentController() {
		this.scan = new Scanner(System.in);
		this.scheduleBuilder = null;
		this.initialLoanBalance = 0.0;
		this.annualInterestRate = 0.0;
		this.numberOfYears = 0;
	}

	/**
	 * Allows to accept user input for initial loan balance, loan interest rate, and
	 * loan term
	 */
	public void inputLoanData() {
		double testInitialLoanBalance = -1.0;
		while (testInitialLoanBalance < 0.0) {
			System.out.print("Please enter the loan's initial balance: ");
			String userInitialLoanBalance = this.scan.nextLine();
			testInitialLoanBalance = Double.parseDouble(userInitialLoanBalance);
		}
		this.initialLoanBalance = testInitialLoanBalance;

		double testUserLoanInterestRate = -1.0;
		while (testUserLoanInterestRate < 0.0 || testUserLoanInterestRate > 1.0) {
			System.out.print("Please enter the loan's interest rate (between 0.0 and 1.0): ");
			String userLoanInterestRate = this.scan.nextLine();
			testUserLoanInterestRate = Double.parseDouble(userLoanInterestRate);
		}
		this.annualInterestRate = testUserLoanInterestRate;

		int testUserLoanTerm;
		for (testUserLoanTerm = 0; testUserLoanTerm <= 0;) {
			System.out.print("Please enter the loan term in years: ");
			String userLoanTerm = this.scan.nextLine();
			testUserLoanTerm = Integer.parseInt(userLoanTerm);
		}
		this.numberOfYears = testUserLoanTerm;
	}

	/**
	 * Allows to create a user Loan and user scheduleBuilder
	 */
	public void createSchedule() {
		Loan userLoan = new Loan(this.initialLoanBalance, this.annualInterestRate, this.numberOfYears);
		this.scheduleBuilder = new ScheduleBuilder(userLoan);
	}

	/**
	 * Allows to build user loan results table
	 */
	public void buildTable() {
		this.scheduleBuilder.buildTable();
	}

	/**
	 * Allows to display the user loan results table
	 */
	public void displayTable() {
		System.out.println(this.scheduleBuilder.getResults());
	}
}
