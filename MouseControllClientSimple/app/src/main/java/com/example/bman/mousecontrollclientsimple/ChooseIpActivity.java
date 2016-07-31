package com.example.bman.mousecontrollclientsimple;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChooseIpActivity extends Activity {
    //public final static String INFO = "com.example.MouseControllClientSimple.INFO";
    //public final static int INFOINT = "com.example.MouseControllClientSimple.INFOINT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_ip);
    }
    public void useIpPort(View view){

        EditText ipAddress_view = (EditText) findViewById(R.id.editTextIpAdress);
        EditText port_view = (EditText) findViewById(R.id.editTextPort);
        String txtport = port_view.getText().toString();

        //
        if (txtport.matches("\\d+")) {
            String ipAddress = ipAddress_view.getText().toString();
            int port = Integer.parseInt(txtport);
            Intent intent = new Intent(ChooseIpActivity.this,ButtonControlActivity.class);
            intent.putExtra("IP",ipAddress);
            intent.putExtra("PORT",port);
            startActivity(intent);
        }
        else{
            TextView error = (TextView) findViewById(R.id.error);
            error.setText("ERROR HAPPENED");
        }
    }
}
