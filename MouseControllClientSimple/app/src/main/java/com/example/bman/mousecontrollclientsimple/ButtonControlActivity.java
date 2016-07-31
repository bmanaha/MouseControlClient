package com.example.bman.mousecontrollclientsimple;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ButtonControlActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_control);


        Intent intent = getIntent();
        String ipAddress = intent.getStringExtra("IP");
        int port = intent.getIntExtra("PORT",0);

if(ipAddress != null) {
    TextView IpPort = (TextView) findViewById(R.id.IpPort);
    IpPort.setText(ipAddress + " : " + port);
}
        //

    }

    public void buttonLeft(View view) {

        String request = "mouseMove 10 0";
        Log.d("MINE", "request: " + request);

        RequestResponseTask task = new RequestResponseTask();//
        task.execute(request);//
        // doInBackground//
        String response = getResponse(request);

        // onPostExecute//

        TextView error = (TextView) findViewById(R.id.error);
        error.setText(response);
    }
    public void buttonRight(View view) {

        String request = "mouseMove -10 0";
        Log.d("MINE", "request: " + request);

        RequestResponseTask task = new RequestResponseTask();//
        task.execute(request);//
        // doInBackground//
        String response = getResponse(request);

        // onPostExecute//

        TextView error = (TextView) findViewById(R.id.error);
        error.setText(response);
    }
    public void buttonUp(View view) {

        String request = "mouseMove 0 10";
        Log.d("MINE", "request: " + request);

        RequestResponseTask task = new RequestResponseTask();//
        task.execute(request);//
        // doInBackground//
        String response = getResponse(request);

        // onPostExecute//

        TextView error = (TextView) findViewById(R.id.error);
        error.setText(response);
    }
    public void buttonDown(View view) {

        String request = "mouseMove 0 -10";
        Log.d("MINE", "request: " + request);

        RequestResponseTask task = new RequestResponseTask();//
        task.execute(request);//
        // doInBackground//
        String response = getResponse(request);

        // onPostExecute//

        TextView error = (TextView) findViewById(R.id.error);
        error.setText(response);
    }
    public void buttonClick(View view) {

        String request = "mouse1Click";
        Log.d("MINE", "request: " + request);

        RequestResponseTask task = new RequestResponseTask();//
        task.execute(request);//
        // doInBackground//
        String response = getResponse(request);

        // onPostExecute//

        TextView error = (TextView) findViewById(R.id.error);
        error.setText(response);
    }
    public void buttonKeyA(View view) {

        String request = "keyboard a";
        Log.d("MINE", "request: " + request);

        RequestResponseTask task = new RequestResponseTask();//
        task.execute(request);//
        // doInBackground//
        String response = getResponse(request);

        // onPostExecute//

        TextView error = (TextView) findViewById(R.id.error);
        error.setText(response);
    }



    private class RequestResponseTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... request) {
            Log.d("TASK", "request: " + request[0]);
            return getResponse(request[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            Log.d("TASK", "response: " + response);
            TextView error = (TextView) findViewById(R.id.error);
            error.setText(response);
        }
    }

    /**
     * Will throw NetworkOnMainThreadException if executed from the main thread.
     * in API version 11 and up (aka. Honeycomb aka. Android 3.0)
     *
     * @param request
     * @return
     */
    private String getResponse(String request) {
        Intent intent = getIntent();
        String ipAddress = intent.getStringExtra("IP");
        int port = intent.getIntExtra("PORT",0);
        try {
            Socket socket = new Socket(ipAddress, port);//SPECIAL_IP_TO_HOST_COMPUTER, PORT_NUMBER
            OutputStream output = socket.getOutputStream();
            output.write((request + "\n").getBytes());
            output.flush();
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = input.readLine();
            socket.close();
            return response;
        } catch (IOException ex) {
            Log.e("SHIT", ex.toString());
            return ex.toString();
        } catch (Exception ex) {
            Log.e("SHIT", ex.toString());
            return ex.toString();
        }
    }
}

