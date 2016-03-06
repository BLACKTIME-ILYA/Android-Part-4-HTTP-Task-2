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

import java.util.HashSet;

import static com.sourceit.task2.ui.Countries.countryList;
import static com.sourceit.task2.ui.DataBaseCreator.CountryCoulumns.NAME;
import static com.sourceit.task2.ui.RegistrationActivity.CURRENT;
import static com.sourceit.task2.ui.RegistrationActivity.PASSWORD;
import static com.sourceit.task2.ui.RegistrationActivity.REGISTER;
import static com.sourceit.task2.ui.RegistrationActivity.REGISTRATION;
import static com.sourceit.task2.ui.RegistrationActivity.UNREGISTER;


public class MainActivity extends AppCompatActivity {

    private Button login;
    private EditText name;
    private EditText password;
    private Button registration;

    private Intent intent;
    private SharedPreferences sp;

    private HashSet users;
    private String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        users = (HashSet) sp.getStringSet(NAME, users);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") || password.getText().toString().equals("")) {
                    String toastText = new String("Введите ");
                    if (name.getText().toString().equals("")) {
                        toastText += "имя, ";
                    } else if (!users.contains(name.getText().toString())) {
                        toastText += "зарегистрированное имя, ";
                    }

                    if (password.getText().toString().equals("")) {
                        toastText += "пароль!";
                    }

                    toast(toastText);
                } else {

                    if (users.contains(name.getText().toString())) {
                        currentUser = name.getText().toString();
                        if (sp.getInt(currentUser + PASSWORD, 0) == password.getText().toString().hashCode()) {
                            intent = new Intent(MainActivity.this, MainScreenActivity.class);
                            intent.putExtra(CURRENT, currentUser);
                            startActivity(intent);
                        } else {
                            toast("пароль неверный!");
                        }
                    } else {
                        toast("такого пользователя не существует!");
                    }
                }
            }
        });

        if (sp.getString(REGISTER, UNREGISTER).equals(UNREGISTER)) {
            intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
    }

    private void toast(String value) {
        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
    }

    private void init() {
        countryList = DataBaseMaster.getInstance(this).getAllCountry();

        sp = getSharedPreferences(REGISTRATION, Context.MODE_PRIVATE);

        registration = (Button) findViewById(R.id.button_registration_in_login);
        login = (Button) findViewById(R.id.button_login);
        name = (EditText) findViewById(R.id.login_name);
        password = (EditText) findViewById(R.id.login_password);
    }
}
