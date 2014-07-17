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
public class HeaderDetailAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<String> headerDetailsList;

    public HeaderDetailAdapter(Context context, List<String> headerDetailsList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.headerDetailsList = headerDetailsList;
    }


    @Override
    public int getCount() {
        return headerDetailsList.size();
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
        View hdrDetailView = null;

        if (view == null) {
            hdrDetailView = new View(context);
            hdrDetailView = inflater.inflate(R.layout.gridview_textitems, null);

            String[] textArray = headerDetailsList.get(position).split("\\|");

            TextView textView1 = (TextView) hdrDetailView.findViewById(R.id.txtView1);
            textView1.setText(textArray[0]);

            TextView textView2 = (TextView) hdrDetailView.findViewById(R.id.txtView2);
            textView2.setText(textArray[1]);

        } else {
            hdrDetailView = view;
        }

        return hdrDetailView;
    }
}
