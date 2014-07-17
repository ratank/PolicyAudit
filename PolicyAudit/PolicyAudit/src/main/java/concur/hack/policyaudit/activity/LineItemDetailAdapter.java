package concur.hack.policyaudit.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import concur.hack.policyaudit.R;

/**
 * Created by RatanK on 17/07/2014.
 */
public class LineItemDetailAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<LineItem> lineItemDetailsList;

    public LineItemDetailAdapter(Context context, List<LineItem> lineItemDetailsList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.lineItemDetailsList = lineItemDetailsList;
    }


    @Override
    public int getCount() {
        return lineItemDetailsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View lineItemDetailView = null;

        if (view == null) {
            lineItemDetailView = new View(context);
            lineItemDetailView = inflater.inflate(R.layout.gridview_textitems, null);

            /*String[] textArray = lineItemDetailsList.get(position).split("\\|");

            TextView textView1 = (TextView) lineItemDetailView.findViewById(R.id.txtView1);
            textView1.setText(textArray[0]);

            TextView textView2 = (TextView) lineItemDetailView.findViewById(R.id.txtView2);
            textView2.setText(textArray[1]);*/

        } else {
            lineItemDetailView = view;
        }

        return lineItemDetailView;
    }
}
