package concur.hack.policyaudit.activity;

import java.util.List;

/**
 * Created by RaufA on 17/07/2014.
 */
public class Question
{
    public int id;
    public String question;
    public String control;
    public int sequenceNumber;
    public String questionLevel;
    public String resultId;
    public List<PossibleAnswer> possibleAnswers;
    public int metaKey;

}

