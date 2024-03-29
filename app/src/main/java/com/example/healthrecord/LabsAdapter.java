package com.example.healthrecord;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LabsAdapter extends ArrayAdapter<LabTests> {

    private ArrayList<LabTests> list;
    private Context context;
    private static class ViewHolder {
        TextView disease, date, p_email, d_email, syms, meds, sym_t, med_t;
    }

    public LabsAdapter(ArrayList<LabTests> list, Context context){
        super(context, R.layout.med_list, list);
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LabTests records = getItem(position);
        LabsAdapter.ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.med_list, parent, false);
            viewHolder.disease = convertView.findViewById(R.id.ml_disease);
            viewHolder.date = convertView.findViewById(R.id.ml_date);
            viewHolder.p_email = convertView.findViewById(R.id.ml_p_email);
            viewHolder.d_email = convertView.findViewById(R.id.ml_d_email);
            viewHolder.syms = convertView.findViewById(R.id.ml_sym);
            viewHolder.meds = convertView.findViewById(R.id.ml_meds);
            viewHolder.sym_t = convertView.findViewById(R.id.sym_t);
            viewHolder.med_t = convertView.findViewById(R.id.med_t);
            viewHolder.syms.setVisibility(View.GONE);
            viewHolder.meds.setVisibility(View.GONE);
            viewHolder.sym_t.setVisibility(View.GONE);
            viewHolder.med_t.setVisibility(View.GONE);
            viewHolder.disease.setTypeface(null, Typeface.BOLD);
            viewHolder.date.setTypeface(null, Typeface.BOLD);
            convertView.setTag(viewHolder);
        }
        else viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.disease.setText(records.getTestName());
        viewHolder.date.setText(records.isResult()? "Positive" : "Negative");
        viewHolder.p_email.setText(records.getpID());
        viewHolder.d_email.setText(records.getdID());
        return convertView;
    }
}
