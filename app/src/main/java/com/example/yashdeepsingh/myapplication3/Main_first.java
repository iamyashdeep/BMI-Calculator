package com.example.yashdeepsingh.myapplication3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Main_first extends AppCompatActivity {

    private Button l1;
    private Button l2;
    private Button l3;
    private Button l4;

    private Sqlite a;
    private TextView appnumb;
    public static final String MyPREFERENCES = "MyPrefs" ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        a = new Sqlite(this);
        appnumb = (TextView) findViewById(R.id.Appno);
        l1 = (Button) findViewById(R.id.button);
        l2 = (Button) findViewById(R.id.button2);
        l3 = (Button) findViewById(R.id.button5);
        l4 = (Button) findViewById(R.id.button6);

        String id1 = null;
//
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        final String name = prefs.getString("username", "No name defined");//"No name defined" is the default value.
        id1 = prefs.getString("number", "0"); //0 is the default value.


        appnumb.setText(id1);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Main_addnew.class);
                startActivity(intent);
            }


        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Main_update.class);
                startActivity(intent);
            }


        });

        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = a.getData(name);
                if (res.getCount() == 0) {
                    // show message
                    showMessage("Error", "Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Username :" + res.getString(0) + "\n");
                    buffer.append("Height :" + res.getString(1) + "\n");
                    buffer.append("Weight :" + res.getString(2) + "\n");
                    buffer.append("BMI :" + res.getString(3) + "\n\n");
                }

                // Show all data
                showMessage("Data", buffer.toString());


            }


        });

        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer deletedRows = a.deleteData(name);
                if(deletedRows > 0)
                    Toast.makeText(Main_first.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Main_first.this,"Data not Deleted",Toast.LENGTH_LONG).show();


            }
        });
    }

    public void showMessage(String title,String Message){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();
        }









//    public void update()
//    {          Intent intent = new Intent(this, Main_update.class);
//                 startActivity(intent);
//    }
//
//          public void newuser()
//         {          Intent intent = new Intent(this, Main_addnew.class);
//                    startActivity(intent);
//         }


    public void back(View view)
    {finish();}



}

