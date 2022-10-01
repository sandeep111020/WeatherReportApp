package com.example.weatherreportapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.weatherreportapp.Adapter.MyListAdapter2;
import com.example.weatherreportapp.Model.Modelclass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HomeActivity2 extends AppCompatActivity {

    private static final String TAG = "hello";
    private ArrayList<Modelclass> ModelArrayList2;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jsonObject = PreferenceManager.
                        getDefaultSharedPreferences(HomeActivity2.this).getString("theJson","");

                Log.v(TAG,jsonObject);
                try {

                    ModelArrayList2 = new ArrayList<>();
                    JSONObject response= new JSONObject(jsonObject);



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





                    RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
                    MyListAdapter2 adapter2 = new MyListAdapter2(HomeActivity2.this, ModelArrayList2);
                    recyclerView2.setHasFixedSize(true);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(HomeActivity2.this));
                    recyclerView2.setAdapter(adapter2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}