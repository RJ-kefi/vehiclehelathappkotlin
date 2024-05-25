package com.example.vehiclehealth;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataActivity extends AppCompatActivity {

    private static final String TAG = "DataActivity";
    private String email;
    private String API_URL="https://nodeserver-4smk.onrender.com/issuedata" ;
    private TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
//        AppCompatButton hbtn=findViewById(R.id.homebtn);
//        hbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(DataActivity.this, DashboardActivity.class);
//                startActivity(intent);
//            }
//        });


        textViewData = findViewById(R.id.text_view_data);


        // Fetch data from API endpoint
        new FetchDataAsyncTask().execute();

    }

    private class FetchDataAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection connection = null;
            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL(API_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                reader.close();
            } catch (IOException e) {
                Log.e(TAG, "Error fetching data: " + e.getMessage());
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            // Parse JSON data and display in TextView
            try {
                JSONArray jsonArray = new JSONArray(result);
                StringBuilder data = new StringBuilder();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String claimno = jsonObject.getString("claimno");
                    String body = jsonObject.getString("desc");
                    String time = jsonObject.getString("date");
                    String agent=jsonObject.getString("agent");
                    data.append("Claimno: ").append(claimno).append("\nAgent :").append(agent).append("\nDescription: ").append(body).append("\nTime").append(time).append("\n\n");
                }

                textViewData.setText(data.toString());
            } catch (JSONException e) {
                Log.e(TAG, "Error parsing JSON: " + e.getMessage());
            }
        }
    }
}
