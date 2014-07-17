package concur.hack.policyaudit.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import concur.hack.policyaudit.R;

/**
 * Created by RatanK on 17/07/2014.
 */
public class ExpenseAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ExpenseItem expenseItem;

    public ExpenseAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public ExpenseItem getExpenseItem() {
        return expenseItem;
    }

    public void setExpenseItem(ExpenseItem expenseItem) {
        this.expenseItem = expenseItem;
    }

    @Override
    public int getCount() {
        return 0;//items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;//expenseItem.getExpenseHeader().getLineItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;//expenseItem.getExpenseHeader().getLineItems().get(position).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if(v == null)
        {
            v = inflater.inflate(R.layout.fragment_expense, viewGroup, false);
        }



        return v;
    }
}
