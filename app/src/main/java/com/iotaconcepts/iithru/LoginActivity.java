package com.iotaconcepts.iithru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{
    Button next;
    EditText et_name, et_age,et_height,et_weight;
    RadioGroup myRadioGroup;
    RadioButton male, female;
    String name_str, age_str, sex_age_height_weight,height_str,weight_str;
    String height, weight;
    int a; // acts as a flag for male-female

    SessionManager name_sex_session;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        next = (Button)findViewById(R.id.bt_login_next);
        et_name = (EditText)findViewById(R.id.et_login_name);
        et_age = (EditText)findViewById(R.id.et_login_age);
        et_height = (EditText) findViewById(R.id.et_login_height);
        et_weight = (EditText) findViewById(R.id.et_login_weight);
        myRadioGroup = (RadioGroup)findViewById(R.id.rg_sex_radioGroup);
        male = (RadioButton)findViewById(R.id.rb_male);
        female = (RadioButton)findViewById(R.id.rb_female);

        a = 0; // 1- male, 2 - female
        name_sex_session = new SessionManager(getApplicationContext());


        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                name_str = et_name.getText().toString();
                age_str = et_age.getText().toString();
                height_str = et_height.getText().toString();
                weight_str = et_weight.getText().toString();

                if(name_str.equals(""))  // if no name is filled
                {
                    Toast.makeText(LoginActivity.this, "Please enter your name", Toast.LENGTH_LONG).show();
                }
                else if(height_str.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter your height", Toast.LENGTH_LONG).show();

                }
                else if(weight_str.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter your weight", Toast.LENGTH_LONG).show();

                }
                else                      // name filled
                {
                    if(!male.isChecked() && !female.isChecked()) // if no checkbox checked
                    {
                        Toast.makeText(LoginActivity.this, "Please enter your sex", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if(male.isChecked())
                        {
                            a = 1;
                        }
                        else if(female.isChecked())
                        {
                            a = 2;
                        }

                        if (age_str.equals(""))  // if no age is entered
                        {
                            Toast.makeText(LoginActivity.this, "Please enter your age", Toast.LENGTH_LONG).show();
                        }
                        else
                        {


                            /****** if Name, sex and age is filled - execute the code below!  ******/

                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);



                            // if User clicks Accept
                            alertDialog.setPositiveButton("Accept", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    // sex_age : purpose: storing sex and age data in one string
                                    sex_age_height_weight = Integer.toString(a);  // if male a=1, else if female a = 2
                                    sex_age_height_weight = sex_age_height_weight.concat("|"+age_str).concat("|"+height_str).concat("|"+weight_str);   //concat: sex_age = sex_age + age i.e. sex_age = a + age(of user)

                                    //Toast.makeText(LoginActivity.this, sex_age, Toast.LENGTH_SHORT).show();

                                    // Now store data in phone memory - using session manager

                                    // Name and (sex+age) details are stored in SessionManager Class (object name: name_sex_session)
                                    name_sex_session.createLoginSession(name_str, sex_age_height_weight);

                                    // Location details are stored in LocationSessionManager Class (object name: location_session)

                                    //Toast.makeText(LoginActivity.this, lat_s + " " + lon_s, Toast.LENGTH_LONG).show();

                                    // Start our main activity
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            });

                            alertDialog.show();


                        }
                    }
                }
            }
        });
    }
}
