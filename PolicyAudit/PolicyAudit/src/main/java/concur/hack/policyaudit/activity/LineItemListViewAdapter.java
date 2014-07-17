package concur.hack.policyaudit.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import concur.hack.policyaudit.R;
import static concur.hack.policyaudit.activity.Const.*;
/**
 * Created by RatanK on 17/07/2014.
 */
public class LineItemListViewAdapter extends BaseAdapter
{
    private Context context;
    private LayoutInflater inflater;
    public List<LineItem> list;

    public LineItemListViewAdapter(Context context, List<LineItem> lineItemsList) {
        inflater = LayoutInflater.from(context);
        this.list = lineItemsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View lineItemView = null;

        if (convertView == null) {
            lineItemView = new View(context);
            lineItemView = inflater.inflate(R.layout.line_item_list_view_row, null);

            LineItem lineItem = (LineItem)getItem(position);

            //if(lineItem.getTransactionDate() != null) {
                TextView textView1 = (TextView) lineItemView.findViewById(R.id.FirstText);
                textView1.setText(lineItem.getTransactionDate().toString());
            //}

            TextView textView2 = (TextView) lineItemView.findViewById(R.id.SecondText);
            textView2.setText(lineItem.getExpenseType());

            if(lineItem.getRequestedAmount() != null) {
                TextView textView3 = (TextView) lineItemView.findViewById(R.id.ThirdText);
                textView3.setText(lineItem.getRequestedAmount().toString());
            }

            if(lineItem.getStatus() != 0) {
                TextView textView4 = (TextView) lineItemView.findViewById(R.id.FourthText);
                textView4.setText(Integer.toString(lineItem.getStatus()));
            }
        } else {
            lineItemView = convertView;
        }

        return lineItemView;
    }

}