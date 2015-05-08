package domain;

public class LoanRequest {

	private int loanRequestNumber;
	private double discount;
	private double total;
	private double downPayment;
	private double loanAmount;
	private double loanPayment;
	
	public LoanRequest(int loanRequestNumber, double discount, double total,
			double downPayment, double loanAmount, double loanPayment) {
		super();
		this.loanRequestNumber = loanRequestNumber;
		this.discount = discount;
		this.total = total;
		this.downPayment = downPayment;
		this.loanAmount = loanAmount;
		this.loanPayment = loanPayment;
	}

	public int getLoanRequestNumber() {
		return loanRequestNumber;
	}

	public void setLoanRequestNumber(int loanRequestNumber) {
		this.loanRequestNumber = loanRequestNumber;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(double downPayment) {
		this.downPayment = downPayment;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getLoanPayment() {
		return loanPayment;
	}

	public void setLoanPayment(double loanPayment) {
		this.loanPayment = loanPayment;
	}

}
