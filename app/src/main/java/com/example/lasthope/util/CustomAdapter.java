package com.example.lasthope.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lasthope.R;
import com.example.lasthope.model.ListData;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<ListData> implements View.OnClickListener{

    private ArrayList<ListData> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtLine;
        TextView txtDir;
        TextView txtDelay;
    }

    public CustomAdapter(ArrayList<ListData> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        ListData listData =(ListData)object;

        switch (v.getId())
        {
            case R.id.line:
                Snackbar.make(v, " Jedzisz tam " , Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ListData listData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtLine = (TextView) convertView.findViewById(R.id.line);
            viewHolder.txtDir = (TextView) convertView.findViewById(R.id.direction);
            viewHolder.txtDelay = (TextView) convertView.findViewById(R.id.delay);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtLine.setText(listData.getLine());
        viewHolder.txtDir.setText(listData.getDirection());
        viewHolder.txtDelay.setText(listData.getDelay());
        // Return the completed view to render on screen
        return convertView;
    }
}