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
    List<String> lineItemDetailList;

    public LineItemDetailAdapter(Context context, List<String> lineItemDetailList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.lineItemDetailList = lineItemDetailList;
    }


    @Override
    public int getCount() {
        return lineItemDetailList.size();
    }

    @Override
    public Object getItem(int position) {
        return lineItemDetailList.get(position);
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

            String[] textArray = lineItemDetailList.get(position).split("\\|");

            if(textArray != null && textArray.length > 0) {
                TextView textView1 = (TextView) lineItemDetailView.findViewById(R.id.txtView1);
                textView1.setText(textArray[0]);

                if(textArray.length == 2) {
                    TextView textView2 = (TextView) lineItemDetailView.findViewById(R.id.txtView2);
                    textView2.setText(textArray[1]);
                }
            }

        } else {
            lineItemDetailView = view;
        }

        return lineItemDetailView;
    }
}
