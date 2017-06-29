package com.example.kritikagopalakrishnan.downloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;


public class start_activity extends AppCompatActivity {
    EditText editText, editText2, editText3, editText4, editText5;
    String[] urls=new String[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try
        {
        editText= (EditText) findViewById(R.id.editText);
        if(editText.getText().toString()!="NULL") {
            urls[0] = editText.getText().toString();
        }
        editText2 = (EditText) findViewById(R.id.editText2);
        if(editText2.getText().toString().equals("NULL")) {
            urls[1] = editText2.getText().toString();
        }
        editText3 = (EditText) findViewById(R.id.editText3);
        if(editText3.getText().toString()!="NULL") {
            urls[2] = editText3.getText().toString();
        }
        editText4 = (EditText) findViewById(R.id.editText4);
        if(editText4.getText().toString()!="NULL") {
            urls[3] = editText4.getText().toString();
        }
        editText5 = (EditText) findViewById(R.id.editText5);
        if(editText5.getText().toString()!="NULL") {
            urls[4] = editText5.getText().toString();
        }
        }
        catch (Exception e)
        {

        }
    }
    public void buttonstartservice(View v) {
        //service intent along with the URLs.
            Intent intstart = new Intent(this, MyStartService.class);
        intstart.putExtra("URLs", urls);
        startService(intstart);
    }
}
