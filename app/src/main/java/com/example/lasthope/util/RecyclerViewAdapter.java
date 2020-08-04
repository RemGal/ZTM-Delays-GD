package com.example.lasthope.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lasthope.MainActivity;
import com.example.lasthope.R;
import com.example.lasthope.model.ListData;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //https://youtu.be/Vyqz_-sJGFk

    private ArrayList<ListData> dataSet;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<ListData> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.line.setText(dataSet.get(position).getLine());
        holder.direction.setText(dataSet.get(position).getDirection());
        holder.delay.setText(dataSet.get(position).getDelay());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dataSet.get(position); <- tutaj dataModel nazywa się dataSet z jakiegoś powodu, ale to zostawmy
                // dataModel= dataModels.get(position);
                //StartVehicleTrackerActivity()
                //Tak jak w MainActivity było z listview - jak to zrobić?
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView line;
        TextView direction;
        TextView delay;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
