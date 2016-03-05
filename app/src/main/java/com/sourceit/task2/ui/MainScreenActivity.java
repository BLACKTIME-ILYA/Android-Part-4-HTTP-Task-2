package com.sourceit.task2.ui;

import android.content.Context;
import android.content.Intent;
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
import com.sourceit.task2.utils.L;

import java.util.HashSet;
import java.util.Random;

import static com.sourceit.task2.ui.Countries.countryList;
import static com.sourceit.task2.ui.RegistrationActivity.COUNTRY;
import static com.sourceit.task2.ui.RegistrationActivity.CURRENT;
import static com.sourceit.task2.ui.RegistrationActivity.REGISTRATION;

public class MainScreenActivity extends AppCompatActivity {

    public static final String COUNTCORRECT = "countcorrect";

    public static final String QUESTION = "question";
    public static final String ANSWER = "answer";

    public static final int QUESTIONSPERCOUNTRY = 2;
    public static final int QUESTIONTYPES = 4;
    public static final int ONE = 0;
    public static final int TWO = 1;
    public static final int THREE = 2;
    public static final int FOUR = 3;
    public static final String COUNTQUESTIONPERCOUNTRY = "countquestionpercountry";
    public static final String SELECTCOUNTRY = "selectcountry";
    public static final String SPECIFIEDCOUNTRIES = "specifiedcountries";
    public static final String CURRENTQUESTION = "currentquestion";
    public static final String CURRENTCAPITAL = "currentcapital";
    public static final String CURRENTPOPULATION = "currentpopulation";
    public static final String CURRENTREGION = "currentregion";

    private TextView question;
    private EditText answer;
    private Button buttonAnswer;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private String partQuestion = "Какая столица ";
    String questionEndPart;

    private String questionAnswer;
    private int countCorrectAnswer;

    private Random random;

    Intent intent;
    String user;

    String selectCountry;
    HashSet<String> specifiedCountries;
    int countQuestionsPerCountry;
    int currentQuestion;

    String currentCapital;
    String currentRegion;
    String currentPopulation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        init();

        intent = getIntent();
        user = intent.getStringExtra(CURRENT);

        setDefaultCountry();

        if (!sp.contains(user + SPECIFIEDCOUNTRIES)) {
            question.setText(firstQuestion());
        } else {

            question.setText(sp.getString(user + QUESTION, "").toString());

            questionAnswer = sp.getString(user + ANSWER, "");
            countCorrectAnswer = sp.getInt(user + COUNTCORRECT, 0);
            countQuestionsPerCountry = sp.getInt(user + COUNTQUESTIONPERCOUNTRY, 0);
            selectCountry = sp.getString(user + SELECTCOUNTRY, "");

            specifiedCountries = (HashSet) sp.getStringSet(user + SPECIFIEDCOUNTRIES, specifiedCountries);
            currentQuestion = sp.getInt(user + CURRENTQUESTION, 0);

            currentCapital = sp.getString(user + CURRENTCAPITAL, "");
            currentPopulation = sp.getString(user + CURRENTPOPULATION, "");
            currentRegion = sp.getString(user + CURRENTREGION, "");
        }

        buttonAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                questionEndPart = setQuestion();

                if (currentQuestion == THREE) {
                    if (!answer.getText().toString().equals("")) {
                        if (questionAnswer.charAt(0) == answer.getText().charAt(0) || questionAnswer.length() == answer.getText().toString().length()) {
                            countCorrectAnswer++;
                        }
                    }
                } else {
                    if (questionAnswer.equalsIgnoreCase(answer.getText().toString())) {
                        countCorrectAnswer++;
                    }
                }

                question.setText(partQuestion + questionEndPart);
                countQuestionsPerCountry++;
                specifiedCountries.add(selectCountry);
                answer.setText("");
            }
        });
    }

    private String firstQuestion() {
        specifiedCountries.add(selectCountry);
        countQuestionsPerCountry++;
        currentQuestion = ONE;
        return (partQuestion + sp.getString(user + COUNTRY, "").substring(0, 1).toUpperCase() + sp.getString(user + COUNTRY, "").substring(1, sp.getString(user + COUNTRY, "").length()).toLowerCase() + "?");
    }

    private String setQuestion() {

        if (countQuestionsPerCountry == QUESTIONSPERCOUNTRY) {
            if (specifiedCountries.size() == countryList.size()) {

                Toast.makeText(getApplicationContext(), "Your knowledge is great!" + "Correct answers - " + countCorrectAnswer, Toast.LENGTH_SHORT).show();
                specifiedCountries.clear();
                countCorrectAnswer = 0;
                countQuestionsPerCountry = 0;
                currentQuestion = 0;
                setDefaultCountry();

                return firstQuestion();

            } else {
                selectCountry = randomCountry();
                countQuestionsPerCountry = 0;
            }
        }
        return questions(randomQuestion());
    }

    private String questions(int type) {

        String tempQuestion = new String();
        L.d("answers: " + specifiedCountries.size());

        if (type == ONE) {
            L.d("question1");

            partQuestion = "Какая столица ";
            currentQuestion = type;

            tempQuestion += selectCountry + "?";
            questionAnswer = currentCapital;

        } else if (type == TWO) {
            L.d("question2");
            partQuestion = "Столица какой страны - ";
            currentQuestion = type;

            tempQuestion += currentCapital + "?";
            questionAnswer = selectCountry;

        } else if (type == THREE) {
            L.d("question3");
            partQuestion = "Примерное количество населения ";
            currentQuestion = type;

            tempQuestion += selectCountry;
            questionAnswer = currentPopulation;

        } else if (type == FOUR) {
            L.d("question4");
            partQuestion = "К какой части света принадлежит страна ";
            currentQuestion = type;

            tempQuestion += selectCountry + "?";
            questionAnswer = currentRegion;
        }
        return tempQuestion;
    }

    private int randomQuestion() {
        int tempTypeQuestion = random.nextInt(QUESTIONTYPES);
        if (tempTypeQuestion == currentQuestion) {
            return randomQuestion();
        }
        if (tempTypeQuestion == TWO) {
            if (currentCapital.equals("")) {
                return randomQuestion();
            }
        }
        L.d("question num: " + tempTypeQuestion);
        return tempTypeQuestion;
    }

    private String randomCountry() {
        L.d("randomCountry");
        Country tempCountry = countryList.get(random.nextInt(countryList.size()));
        if (specifiedCountries.contains(tempCountry.name)) {
            return randomCountry();
        }
        currentCapital = tempCountry.capital;
        currentPopulation = tempCountry.population;
        currentRegion = tempCountry.region;
        return tempCountry.name;
    }

    private void setDefaultCountry() {
        for (Country country : countryList) {
            if (country.name.equalsIgnoreCase(sp.getString(user + COUNTRY, ""))) {
                questionAnswer = country.capital;
                selectCountry = country.name;
                currentRegion = country.region;
                currentPopulation = country.population;
                currentCapital = country.capital;
            }
        }
    }

    private void init() {
        random = new Random();

        question = (TextView) findViewById(R.id.question);
        answer = (EditText) findViewById(R.id.answer);
        buttonAnswer = (Button) findViewById(R.id.button_answer);

        specifiedCountries = new HashSet<>();

        sp = getSharedPreferences(REGISTRATION, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        editor.putString(user + ANSWER, questionAnswer);
        editor.putInt(user + COUNTCORRECT, countCorrectAnswer);
        editor.putString(user + QUESTION, question.getText().toString());

        editor.putInt(user + COUNTQUESTIONPERCOUNTRY, countQuestionsPerCountry);
        editor.putString(user + SELECTCOUNTRY, selectCountry);
        editor.putStringSet(user + SPECIFIEDCOUNTRIES, specifiedCountries);
        editor.putInt(user + CURRENTQUESTION, currentQuestion);
        editor.putString(user + CURRENTPOPULATION, currentPopulation);
        editor.putString(user + CURRENTREGION, currentRegion);
        editor.apply();

    }
}
