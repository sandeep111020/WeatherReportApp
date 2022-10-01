package com.example.weatherreportapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherreportapp.Adapter.MyListAdapter2;
import com.example.weatherreportapp.Model.Modelclass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    EditText city;
    Button search;
    private static final String TAG = "hello";
    private ArrayList<Modelclass> ModelArrayList2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        city=findViewById(R.id.city);
        search=findViewById(R.id.submit);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityname= city.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(HomeActivity.this);
                if (isConnected()) {
                    Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show();
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.openweathermap.org/data/2.5/forecast?q="+cityname+"&appid=1635890035cbba097fd5c26c8ea672a1", null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {



                            try {
                                ModelArrayList2 = new ArrayList<>();


                                PreferenceManager.getDefaultSharedPreferences(HomeActivity.this).edit()
                                        .putString("theJson",response.toString()).apply();


                                System.out.println(response.toString());

                                JSONArray daily = response.getJSONArray("list");
                                String tscheck="";
                                for(int i=0;i<daily.length();i++){
                                    JSONObject object = daily.getJSONObject(i);
                                    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                                    String ts=object.getString("dt");
                                    cal.setTimeInMillis((Long.parseLong(ts) * 1000L));
                                    String date = DateFormat.format("dd/MM/yyy", cal).toString();
                                    if(tscheck.contains(date)){

                                    }else {
                                        String tempmin = object.getJSONObject("main").getString("temp_min");
                                        String tempmax = object.getJSONObject("main").getString("temp_max");
                                        String pressure = object.getJSONObject("main").getString("pressure");
                                        String humidity = object.getJSONObject("main").getString("humidity");



                                        ModelArrayList2.add(new Modelclass(date, tempmax, tempmin, humidity, pressure));
                                    }
                                    tscheck=date;
                                }

//

                                RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
                                MyListAdapter2 adapter2 = new MyListAdapter2(HomeActivity.this, ModelArrayList2);
                                recyclerView2.setHasFixedSize(true);
                                recyclerView2.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                                recyclerView2.setAdapter(adapter2);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // below line is use to display a toast message along with our error.
                            Log.v(TAG,"error message"+error.toString());
                            Toast.makeText(HomeActivity.this, "Fail to get data.."+error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    queue.add(jsonObjectRequest);
                }
                else{
                    Intent i = new Intent(HomeActivity.this,HomeActivity2.class);
                    startActivity(i);
                }


            }
        });

    }
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
}