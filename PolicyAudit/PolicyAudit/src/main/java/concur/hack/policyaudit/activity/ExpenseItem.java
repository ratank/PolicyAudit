package concur.hack.policyaudit.activity;

import java.util.List;

/**
 * Created by RaufA on 17/07/2014.
 */

public class ExpenseItem {
    private ExpenseHeader expenseHeader;
    private List<LineItem> lineItems;
    private int lineItemCount;

    public ExpenseHeader getExpenseHeader(){
        return this.expenseHeader;
    }

    public void setExpenseHeader(ExpenseHeader expenseHeader){
        this.expenseHeader = expenseHeader;

    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public int getLineItemCount() {
        if (lineItems != null){
            return lineItems.size();
        }
        return 0;
    }
}

