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
public class QuestionItemAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Question> questions;

    public QuestionItemAdapter(Context context, List<Question> questions) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.questions = questions;
    }


    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View questionView = null;

        if (convertView == null) {
            questionView = new View(context);
            questionView = inflater.inflate(R.layout.question_view, null);

            Question question = (Question)getItem(position);

            //add dynamically the elements
            //question.question
            if(question.control.equalsIgnoreCase("drop")) {
                //dropdown list if more than 5, will have possible answers
            } else if(question.control.equalsIgnoreCase("drop")) {

            }

        } else {
            questionView = convertView;
        }

        return questionView;
    }
}
