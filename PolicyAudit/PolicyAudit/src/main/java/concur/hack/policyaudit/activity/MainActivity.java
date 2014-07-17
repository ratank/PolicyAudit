package concur.hack.policyaudit.activity;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import concur.hack.poilcyaudit.fragment.ExpenseFragment;
import concur.hack.poilcyaudit.fragment.ImageFragment;
import concur.hack.policyaudit.R;
import static concur.hack.policyaudit.activity.Const.*;


public class MainActivity extends ActionBarActivity implements ExpenseFragment.OnFragmentInteractionListener {

    private static final String EXPENSE_FRAGMENT = "expense.fragment";
    private static final String IMAGE_FRAGMENT = "image.fragment";

    private ExpenseFragment expenseFragment;
    private ImageFragment imageFragment;

    private ArrayList<HashMap> lineItemsListMap;
    private List<ExpenseItem> expenseItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parse();


        initExpenseView();

        //FragmentManager fm = getSupportFragmentManager();
        /*expenseFragment = (ExpenseFragment) fm.findFragmentByTag(EXPENSE_FRAGMENT);

        if (expenseFragment == null) {
            expenseFragment = new ExpenseFragment();
            expenseFragment.setImageAdapter(new ExpenseAdapter(this));
            //expenseFragment.setPriceToBeatSearchFragmentOnClickListener(this);
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.left_pane, expenseFragment, EXPENSE_FRAGMENT);
            ft.commit();
        }*/

        /*imageFragment = (ImageFragment) fm.findFragmentByTag(IMAGE_FRAGMENT);
        if (imageFragment == null) {
            imageFragment = new ImageFragment();
            imageFragment.setImageAdapter(new ImageAdapter(this));
            //imageFragment.setPriceToBeatSearchFragmentOnClickListener(this);
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.right_pane, imageFragment, IMAGE_FRAGMENT);
            ft.commit();
        }*/

    }

    private void parse() {
        PolicyAuditParser parser = new PolicyAuditParser(this);
        try {
            expenseItemsList = parser.GetParsedExpenseItemsFromJSON();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initExpenseView() {
        //init header details view
        GridView headerDetailsView = (GridView)findViewById(R.id.headerDetailsId);
        ExpenseHeader exp = expenseItemsList.get(0).getExpenseHeader();
        List<String> headerDetailsList = exp.getHeaderDetailsList();
        headerDetailsView.setAdapter(new HeaderDetailAdapter(this, headerDetailsList));

        //init line items view
        ListView lineItemsView = (ListView) findViewById(R.id.lineItemsViewId);
        //add header
        View lineItemHeaderView = (View)getLayoutInflater().inflate(R.layout.line_item_list_view_header,null);
        lineItemsView.addHeaderView(lineItemHeaderView);
        lineItemsView.setHeaderDividersEnabled(true);
        //populateLineItemsListMap();
        List<LineItem> lineItemsList = expenseItemsList.get(0).getLineItems();//populateLineItemsList(expenseItemsList.get(0).getLineItems());
        //lineItemsView.setAdapter(new LineItemListViewAdapter(this, lineItemsListMap));
        lineItemsView.setAdapter(new LineItemListViewAdapter(this, lineItemsList));

        lineItemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                int itemPostition = position;
                LineItem lineItem = (LineItem)parent.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(), "Loaded Receipts and Line Items for " + lineItem.getExpenseType(), Toast.LENGTH_SHORT).show();
                showLineItemDetails(lineItem);
                showQuestions(lineItem);
                if(lineItem.isReceiptExists()) {
                    showReceipts(lineItem.getImageIds());
                }

                /*else {
                    hideReceipts();
                }*/
            }
        });
    }

    /*private List<LineItem> populateLineItemsList(List<LineItem> lineItemsList) {
        if(lineItemsList == null || lineItemsList.size() == 0) {
            //create temp data
            lineItemsList = new ArrayList<LineItem>(4);

            LineItem l1 = new LineItem();
            l1.setExpenseType("Dinner");
            l1.setRequestedAmount(120.00);
            lineItemsList.add(l1);

            LineItem l2 = new LineItem();
            l2.setExpenseType("Lunch");
            l2.setRequestedAmount(120.00);
            l2.setStatus(2);
            lineItemsList.add(l2);

            LineItem l3 = new LineItem();
            l3.setExpenseType("Hotel");
            l3.setRequestedAmount(520.00);
            lineItemsList.add(l3);
        }

        return lineItemsList;
    }*/

    //init line item details view
    private void showLineItemDetails(LineItem lineItem) {
        //LinearLayout lineItemDetailsView = (LinearLayout)findViewById(R.id.lineItemDetailsViewId);

        TextView hdrTxt = ((TextView) findViewById(R.id.lineItemDetailsHdrId));
        //init line items view
        if(lineItem.getLineItemDetailList() != null && lineItem.getLineItemDetailList().size() > 0) {
            hdrTxt.setVisibility(View.VISIBLE);
            hdrTxt.setText(lineItem.getExpenseType() + " - Line Item Details");
            GridView lineItemDetailsView = (GridView) findViewById(R.id.lineItemDetailsViewId);
            lineItemDetailsView.setAdapter(new LineItemDetailAdapter(this, lineItem.getLineItemDetailList()));
        } else {
            hdrTxt.setVisibility(View.GONE);
        }
    }

    private void showQuestions(LineItem lineItem) {
        if(lineItem.getQuestions() != null && lineItem.getQuestions().size() > 0) {
            GridView questionsView = (GridView) findViewById(R.id.questionViewId);
            questionsView.setAdapter(new QuestionItemAdapter(this, lineItem.getQuestions()));
        }
    }

    private void showReceipts(List<String> imageNames) {
        FragmentManager fm = getSupportFragmentManager();
        imageFragment = (ImageFragment) fm.findFragmentByTag(IMAGE_FRAGMENT);
        List<ImageItem> imageItems = new ArrayList<ImageItem>();
        //if (imageFragment == null) {
            imageFragment = new ImageFragment();
            if(imageNames != null || imageNames.size() > 0) {
                imageItems = new ArrayList<ImageItem>();
                int resID = -1;
                for (int i = 0; i < imageNames.size(); i++) {
                    resID = this.getResources().getIdentifier(imageNames.get(i).split("\\.")[0] , "drawable", this.getPackageName());
                    imageItems.add(new ImageItem(imageNames.get(i), resID));
                }
            }
            imageFragment.setImageAdapter(new ImageAdapter(this, imageItems));
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.right_pane, imageFragment, IMAGE_FRAGMENT);
            ft.commit();
        //}
    }

    private void hideReceipts() {
        FragmentManager fm = getSupportFragmentManager();
        imageFragment = (ImageFragment) fm.findFragmentByTag(IMAGE_FRAGMENT);
        List<ImageItem> imageItems = new ArrayList<ImageItem>();
        if (imageFragment != null) {
            //imageFragment.
        }
    }

    /*private void populateLineItemsListMap() {

        lineItemsListMap = new ArrayList<HashMap>();

        HashMap temp0 = new HashMap();
        temp0.put(FIRST_COLUMN,"Transaction Date");
        temp0.put(SECOND_COLUMN, "Expense Type");
        temp0.put(THIRD_COLUMN, "Requested Amount");
        temp0.put(FOURTH_COLUMN, "Status");
        lineItemsListMap.add(temp0);

            HashMap temp = new HashMap();
            temp.put(FIRST_COLUMN,"07/17/2014");
            temp.put(SECOND_COLUMN, "Dinner");
            temp.put(THIRD_COLUMN, "GBP 120.00");
            temp.put(FOURTH_COLUMN, "");
        lineItemsListMap.add(temp);

            HashMap temp1 = new HashMap();
            temp1.put(FIRST_COLUMN,"07/15/2014");
            temp1.put(SECOND_COLUMN, "Lunch");
            temp1.put(THIRD_COLUMN, "GBP 20.00");
            temp1.put(FOURTH_COLUMN, "some status");
        lineItemsListMap.add(temp1);

            HashMap temp2 = new HashMap();
            temp2.put(FIRST_COLUMN,"08/15/2014");
            temp2.put(SECOND_COLUMN, "Breakfast");
            temp2.put(THIRD_COLUMN, "GBP 20.00");
            temp2.put(FOURTH_COLUMN, "");
        lineItemsListMap.add(temp2);

    }*/




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //TODO - later
    public void onFragmentInteraction(Uri uri) {
    }


}
