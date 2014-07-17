package concur.hack.policyaudit.activity;

import java.util.Date;
import java.util.List;

/**
 * Created by RaufA on 17/07/2014.
 */
public class ExpenseHeader {

    private String expenseId;
    private String transactionDate;
    private double totalPostedAmount;
    private String companyName;
    private String employeeName;
    private String expenseName;

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Number getTotalPostedAmount() {
        return totalPostedAmount;
    }

    public void setTotalPostedAmount(double totalPostedAmount) {
        this.totalPostedAmount = totalPostedAmount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }
}
