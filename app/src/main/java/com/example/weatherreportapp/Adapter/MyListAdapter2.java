package com.example.weatherreportapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherreportapp.Model.Modelclass;
import com.example.weatherreportapp.R;

import java.util.ArrayList;


public class MyListAdapter2 extends RecyclerView.Adapter<MyListAdapter2.ViewHolder>{
    private Context context;
    private ArrayList<Modelclass> courseModelArrayList;

    // Constructor
    public MyListAdapter2(Context context, ArrayList<Modelclass> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item2, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Modelclass model = courseModelArrayList.get(position);
        holder.dt.setText(model.getDt());
        String text = model.getTempmin();

        holder.tempmin.setText(text+"°C");
        holder.tempmax.setText(model.getTempmax()+"°C");
        holder.wind.setText(model.getPressure()+" Pa");
        holder.hum.setText(model.getHum()+"%");

    }


    @Override
    public int getItemCount() {
        return courseModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dt,tempmin,tempmax,wind,hum;
        public ViewHolder(View itemView) {
            super(itemView);
            this.dt = (TextView) itemView.findViewById(R.id.textView1);
            this.tempmin = (TextView) itemView.findViewById(R.id.textViewmin);
            this.tempmax = (TextView) itemView.findViewById(R.id.textViewmax);
            this.hum = (TextView) itemView.findViewById(R.id.textView3);
            this.wind = (TextView) itemView.findViewById(R.id.textView4);
        }
    }
}