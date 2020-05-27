package com.example.healthrecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MedRecordsAdapter extends ArrayAdapter<HealthRecords> {

    private ArrayList<HealthRecords> list;
    private Context context;
    private static class ViewHolder {
        TextView disease, date, p_email, d_email, syms, meds;
    }

    public MedRecordsAdapter(ArrayList<HealthRecords> list, Context context){
        super(context, R.layout.med_list, list);
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HealthRecords records = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.med_list, parent, false);
            viewHolder.disease = convertView.findViewById(R.id.ml_disease);
            viewHolder.date = convertView.findViewById(R.id.ml_date);
            viewHolder.p_email = convertView.findViewById(R.id.ml_p_email);
            viewHolder.d_email = convertView.findViewById(R.id.ml_d_email);
            viewHolder.syms = convertView.findViewById(R.id.ml_sym);
            viewHolder.meds = convertView.findViewById(R.id.ml_meds);

            convertView.setTag(viewHolder);
        }
        else viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.disease.setText(records.getDisease());
        viewHolder.date.setText(records.getDate());
        viewHolder.p_email.setText(records.getpID());
        viewHolder.d_email.setText(records.getdId());
        viewHolder.syms.setText(records.getSymptoms().toString());
        viewHolder.meds.setText(records.getMedicine().toString());

        return convertView;
    }
}
