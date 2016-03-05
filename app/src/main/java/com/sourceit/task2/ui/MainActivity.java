package com.sourceit.task2.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sourceit.task2.R;

import static com.sourceit.task2.ui.Countries.countryList;
import static com.sourceit.task2.ui.DataBaseCreator.CountryCoulumns.NAME;
import static com.sourceit.task2.ui.RegistrationActivity.PASSWORD;
import static com.sourceit.task2.ui.RegistrationActivity.REGISTER;
import static com.sourceit.task2.ui.RegistrationActivity.REGISTRATION;
import static com.sourceit.task2.ui.RegistrationActivity.UNREGISTER;


public class MainActivity extends AppCompatActivity {

    private Button login;
    private EditText name;
    private EditText password;

    private Intent intent;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") || password.getText().toString().equals("")) {
                    String toastText = new String("Введите ");
                    if (name.getText().toString().equals("")) {
                        toastText += "имя, ";
                    } else if (!sp.getString(NAME, "").equals(name.getText().toString())) {
                        toastText += "правильное имя, ";
                    }
                    if (password.getText().toString().equals("")) {
                        toastText += "пароль!";
                    }
                    Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
                } else {

                    if (sp.getString(NAME, "").equalsIgnoreCase(name.getText().toString()) && sp.getString(PASSWORD, "").equals(password.getText().toString())) {
                        intent = new Intent(MainActivity.this, MainScreenActivity.class);
                        startActivity(intent);
                    } else {

                        String toastText = new String("Вы ввели неправильно ");
                        if (!sp.getString(NAME, "").equalsIgnoreCase(name.getText().toString())) {
                            toastText += "имя, ";
                        }
                        if (!sp.getString(PASSWORD, "").equals(password.getText().toString())) {
                            toastText += "пароль!";
                        }
                        Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        if (sp.getString(REGISTER, UNREGISTER).equals(UNREGISTER)) {
            intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
    }

    private void init() {
        countryList = DataBaseMaster.getInstance(this).getAllCountry();

        sp = getSharedPreferences(REGISTRATION, Context.MODE_PRIVATE);

        login = (Button) findViewById(R.id.button_login);
        name = (EditText) findViewById(R.id.login_name);
        password = (EditText) findViewById(R.id.login_password);
    }
}
