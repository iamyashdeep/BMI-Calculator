package com.example.yashdeepsingh.myapplication3;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import static android.content.Context.MODE_WORLD_READABLE;

public class MainActivity extends AppCompatActivity {


    private EditText newuser;
    private EditText username;
    private EditText password;
    HashMap<String,String> name_pass=new HashMap<String,String>();
    HashMap<String,Integer> name_no=new HashMap<String,Integer>();
    public static final String MyPREFERENCES = "MyPrefs" ;






    @SuppressLint("WorldReadableFiles")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = (Button) findViewById(R.id.button1);
        //newuser = (EditText) findViewById(R.id.new_user);
        username = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.pass_word);
        name_pass.put("Yash","2014");
        name_pass.put("Sid","2015");
        name_pass.put("Arya","2016");
        name_no.put("Yash",number());
        name_no.put("Arya",number());
        name_no.put("Sid",number());



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (newuser.getEditableText().toString().equals("Yes")) {
//                    name_pass.put(username.toString(), password.toString());
//                    int application_no = number();
//                    name_no.put(username.getText().toString(), application_no);
//                    SharedPreferences.Editor editor = sharedpreferences.edit();
//                    editor.putString("username", username.getText().toString());
//                    editor.putInt("number", application_no);
//                    editor.apply();
//                    Intent intent = new Intent(view.getContext(), Main_first.class);
//                    startActivity(intent);
//                } else {
                    if (name_pass.get(username.getText().toString()).equals(password.getText().toString())) {
                        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("username", username.getText().toString());
                        editor.putString("number", name_no.get(username.getText().toString()).toString());
                        editor.commit();
                        Toast.makeText(MainActivity.this, "Shared Done", Toast.LENGTH_SHORT).show();

                        System.out.print(name_no.get(username.getText().toString()).toString());
                        write_external();
                        write_internal();
                        username.setText("");
                        password.setText("");
                        Intent intent = new Intent(view.getContext(),Main_first.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                        username.setText("");
                        password.setText("");
                    }

                }

        });
    }
    public int number()
    {
        Random rand = new Random();
        return(rand.nextInt(1000));
    }

    public boolean write_external()
    {
        String state=Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            File file;
            file = Environment.getExternalStorageDirectory();
            File directory = new File(file.getAbsolutePath() + "/AppFile");
            if (!directory.exists()) {
                directory.mkdir();
            }
            File file1 = new File(directory, "DataFile.txt");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file1);
                fileOutputStream.write(username.getText().toString().getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
             Toast.makeText(MainActivity.this, "External Saved", Toast.LENGTH_SHORT).show();

            }

        else
        { Toast.makeText(MainActivity.this, "No sd card", Toast.LENGTH_SHORT).show();
        }
            return true;
    }

    public boolean write_internal()
    {
        try {
            FileOutputStream fileOutputStream = openFileOutput("DataInt.txt",MODE_PRIVATE);
            fileOutputStream.write(username.getText().toString().getBytes());
            fileOutputStream.close();
             Toast.makeText(MainActivity.this, "Internal Saved", Toast.LENGTH_SHORT).show();

            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return true;
    }


}

