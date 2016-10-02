package com.example.yashdeepsingh.myapplication3;

/**
 * Created by Yashdeep Singh on 02-10-2016.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Main_addnew extends AppCompatActivity {
    private EditText height;
    private EditText weight;
    private TextView bmi;
    private Button cal;
    public Sqlite a;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public String uname;
    public String number1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew);
        cal = (Button) findViewById(R.id.button3);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        bmi = (TextView) findViewById(R.id.BMI);
       a= new Sqlite(this);

        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

            uname = prefs.getString("username", "No name defined");//"No name defined" is the default value.
            number1 = prefs.getString("number","0"); //0 is the default value.





        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float w = Float.parseFloat(weight.getText().toString());
                float h = Float.parseFloat(height.getText().toString());
                float bmii = w / (h * h);
                String bmiii= ""+bmii;
                bmi.setText(bmiii);
                boolean inserted = a.insertData(uname, h, w, bmii);
                if (inserted == true) {
                    Toast.makeText(Main_addnew.this, "Data Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Main_addnew.this, "Data Not Inserted", Toast.LENGTH_LONG).show();

                }


            }
        });
    }
}

