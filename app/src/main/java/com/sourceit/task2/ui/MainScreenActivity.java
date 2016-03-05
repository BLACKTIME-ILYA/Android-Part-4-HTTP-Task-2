package com.sourceit.task2.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sourceit.task2.R;
import com.sourceit.task2.ui.model.Country;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import static com.sourceit.task2.ui.Countries.countriesFromSubregion;
import static com.sourceit.task2.ui.Countries.countryList;
import static com.sourceit.task2.ui.RegistrationActivity.COUNTRY;
import static com.sourceit.task2.ui.RegistrationActivity.REGISTRATION;

public class MainScreenActivity extends AppCompatActivity {

    public static final String COUNTCORRECT = "countcorrect";
    public static final String SPECIFIEDQUESTIONS = "specifiedquestions";
    public static final String QUESTION = "question";
    public static final String ANSWER = "answer";

    private TextView question;
    private EditText answer;
    private Button buttonAnswer;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private String userCapital;
    private String userSubregion;
    private String partQuestion = "Какая столица ";
    private String questionAnswer;
    private int countCorrectAnswer;
    private HashSet<String> specifiedQuestions;

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        init();

        for (Country country : countryList) {
            if (country.name.equalsIgnoreCase(sp.getString(COUNTRY, ""))) {
                userSubregion = country.subregion;
                questionAnswer = country.capital;
                userCapital = country.capital;
            }
        }

        for (Country country : countryList) {
            if (country.subregion.equals(userSubregion)) {
                countriesFromSubregion.add(country);
            }
        }

        if (!sp.contains(SPECIFIEDQUESTIONS)) {

            firstQuestion();

        } else {
            questionAnswer = sp.getString(ANSWER, "");
            question.setText(sp.getString(QUESTION, "").toString());
            countCorrectAnswer = sp.getInt(COUNTCORRECT, 0);
            specifiedQuestions = (HashSet) sp.getStringSet(SPECIFIEDQUESTIONS, specifiedQuestions);
        }

        buttonAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionAnswer.equalsIgnoreCase(answer.getText().toString())) {
                    countCorrectAnswer++;
                }
                setQuestion();
                answer.setText("");
            }
        });
    }

    private void firstQuestion() {
        question.setText(partQuestion + sp.getString(COUNTRY, "").substring(0, 1).toUpperCase() + sp.getString(COUNTRY, "").substring(1, sp.getString(COUNTRY, "").length()).toLowerCase() + "?");
        specifiedQuestions.add(questionAnswer);
    }

    private void setQuestion() {
        if (specifiedQuestions.size() == countriesFromSubregion.size()) {

            Toast.makeText(getApplicationContext(), "Your knowledge is great!" + "Correct answers - " + countCorrectAnswer, Toast.LENGTH_SHORT).show();
            specifiedQuestions.clear();
            countCorrectAnswer = 0;
            questionAnswer = userCapital;
            firstQuestion();

        } else {
            Country tempCountry = countriesFromSubregion.get(random.nextInt(countriesFromSubregion.size()));
            if (specifiedQuestions.contains(tempCountry.capital)) {
                setQuestion();
            } else {
                question.setText(partQuestion + tempCountry.name + "?");
                questionAnswer = tempCountry.capital;
                specifiedQuestions.add(questionAnswer);
            }
        }
    }

    private void init() {
        random = new Random();

        question = (TextView) findViewById(R.id.question);
        answer = (EditText) findViewById(R.id.answer);
        buttonAnswer = (Button) findViewById(R.id.button_answer);

        countriesFromSubregion = new ArrayList<>();
        specifiedQuestions = new HashSet<>();

        sp = getSharedPreferences(REGISTRATION, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        editor.putInt(COUNTCORRECT, countCorrectAnswer);
        editor.putStringSet(SPECIFIEDQUESTIONS, specifiedQuestions);
        editor.putString(QUESTION, question.getText().toString());
        editor.putString(ANSWER, questionAnswer);
        editor.apply();
    }
}
