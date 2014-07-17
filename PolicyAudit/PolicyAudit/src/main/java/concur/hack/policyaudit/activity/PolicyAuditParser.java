package concur.hack.policyaudit.activity;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import concur.hack.policyaudit.R;

/**
 * Created by RatanK on 17/07/2014.
 */
public class PolicyAuditParser {

    private Context context;

    public PolicyAuditParser() {
    }

    public PolicyAuditParser(Context context) {
        this.context = context;
    }

    public List<ExpenseItem> GetParsedExpenseItemsFromJSON() throws IOException, JSONException {
        JSONObject data = getContent();
        JSONObject auditData = data.getJSONObject("ReportAudit").
                getJSONObject("ServiceWorkbenchController_GetNextAuditAction").
                getJSONObject("AuditData");

        JSONArray jsonHeaders = auditData.getJSONArray("Header");
        JSONArray jsonLineItems = auditData.getJSONArray("LineItem");

        ExpenseItem expenseItem = new ExpenseItem();
        //extract header values
        ExpenseHeader header = GetExpenseHeader(jsonHeaders.getJSONObject(0));
        expenseItem.setExpenseHeader(header);

        //extract line items
        List<LineItem> lineItems = GetLineItems(jsonLineItems);
        expenseItem.setLineItems(lineItems);

        List<ExpenseItem> expenseItems = new ArrayList<ExpenseItem>();
        expenseItems.add(expenseItem);

        return expenseItems;
    }

    private ExpenseHeader GetExpenseHeader(JSONObject headerData) throws JSONException {

        ExpenseHeader expenseHeader = new ExpenseHeader();
        expenseHeader.setTransactionDate(headerData.getString("ReportDate"));
        expenseHeader.setTotalPostedAmount(headerData.getDouble("TotalPostedAmount"));
        expenseHeader.setEmployeeName(headerData.getString("EmpFullName"));
        expenseHeader.setExpenseName(headerData.getString("Name"));
        expenseHeader.setExpenseId(headerData.getString("ReportId"));

        expenseHeader.setCompanyName(headerData.getJSONObject("EntityDetails").
                getString("EntityName"));

        return expenseHeader;
    }

    private LineItem GetSingleLineItem(JSONObject json) throws JSONException{
        LineItem item = new LineItem();

        item.setTransactionDate(json.getString("TransactionDate"));
        item.setBusinessPurpose("Training"); //constant
        item.setCity("London"); //constant
        item.setCurrency("UK, Pound Sterling");//constant
        item.setExchangeRate(1); //constant
        item.setExpenseType(json.getString("ExpDesc"));

        List<String> images = new ArrayList<String>();
        String csvList = json.getString("ImageID");
        String[] arrayOfImages = csvList.split(",");
        for (int i = 0; i < arrayOfImages.length; i++) {
            images.add(arrayOfImages[i]);
        }
        item.setImageIds(images);

        item.setPaymentType("CASH");
        item.setRequestedAmount(json.getDouble("RequestAmountPosted"));
        item.setStatus(json.getInt("Status"));
        item.setVendorDescription(""); //constant
        item.setReceiptExists(json.getBoolean("ReceiptExists"));

        List<Question> questions = GetQuestions(json.getJSONArray("Questions"));
        item.setQuestions(questions);

        return item;
    }

    private List<Question> GetQuestions(JSONArray items) throws JSONException{
        List<Question> questions = new ArrayList<Question>();
        for (int i = 0; i < items.length(); i++) {
            questions.add(GetSingleQuestion(items.getJSONObject(i)));
        }
        return questions;
    }

    private Question GetSingleQuestion(JSONObject json) throws JSONException {

        Question item = new Question();
        item.id = json.getInt("id");
        item.question = json.getString("question");
        item.control = json.getString("control");
        item.sequenceNumber = json.getInt("sequenceNumber");

        if (item.control.equals("DROP")) {
            List<PossibleAnswer> answers = GetPossibleAnswers(json.getJSONArray("possibleanswers"));
            item.possibleAnswers = answers;
        }

        if(item.control.equals("FILD")) {
            //handle EditFieldInfo class - TBD if time permits
        }

        return item;
    }

    private List<PossibleAnswer> GetPossibleAnswers(JSONArray items) throws JSONException {
        List<PossibleAnswer> answers = new ArrayList<PossibleAnswer>();
        for (int i = 0; i < items.length(); i++) {
            answers.add(GetSingleAnswer(items.getJSONObject(i)));
        }
        return answers;
    }

    private PossibleAnswer GetSingleAnswer(JSONObject json) throws JSONException {
        PossibleAnswer answer = new PossibleAnswer();
        answer.answerId = json.getString("answerId");
        answer.displayText= json.getString("displayText");
        answer.state = json.getString("state");
        return answer;
    }

    private List<LineItem> GetLineItems(JSONArray items) throws JSONException{

        List<LineItem> lineItems = new ArrayList<LineItem>();
        for (int i = 0; i < items.length(); i++) {
            lineItems.add(GetSingleLineItem(items.getJSONObject(i)));
        }
        return lineItems;
    }

    public JSONObject getContent() throws IOException, JSONException {
        BufferedReader bufferedReader = null;
        try {
            InputStream inStream = context.getResources().openRawResource(R.raw.concur);

            BufferedInputStream bufferedStream = new BufferedInputStream(
                    inStream);
            InputStreamReader reader = new InputStreamReader(bufferedStream);
            bufferedReader = new BufferedReader(reader);
            StringBuilder builder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line);
                line = bufferedReader.readLine();
            }
            JSONObject data = new JSONObject(builder.toString());
            return data;

        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

}
