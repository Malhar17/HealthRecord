package com.example.healthrecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LeaveAppAdapter extends ArrayAdapter<LeaveApplication> {

    private ArrayList<LeaveApplication> list;
    private Context context;
    private int userType;
    private ArrayList<String> id;
    private static class ViewHolder {
        TextView s_email, d_email, f_email, dates, d_text, f_text;
        Button f_approve, d_approve;
        ToggleButton d_toggle, f_toggle;
    }

    public LeaveAppAdapter(ArrayList<LeaveApplication> list, Context context, int userType, ArrayList<String> id){
        super(context, R.layout.la_lists, list);
        this.list = list;
        this.context = context;
        this.userType = userType;
        this.id = id;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LeaveApplication application = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.la_lists, parent, false);
            viewHolder.s_email = convertView.findViewById(R.id.la_s_email);
            viewHolder.d_email = convertView.findViewById(R.id.la_d_email);
            viewHolder.f_email = convertView.findViewById(R.id.la_f_email);
            viewHolder.dates = convertView.findViewById(R.id.la_dates);
            viewHolder.f_approve = convertView.findViewById(R.id.la_f_approve);
            viewHolder.d_approve = convertView.findViewById(R.id.la_d_approve);
            viewHolder.d_text = convertView.findViewById(R.id.la_d_text);
            viewHolder.f_text = convertView.findViewById(R.id.la_f_text);
            viewHolder.d_toggle = convertView.findViewById(R.id.la_d_toggle);
            viewHolder.f_toggle = convertView.findViewById(R.id.la_f_toggle);
            convertView.setTag(viewHolder);
        }
        else viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.s_email.setText(application.getsID());
        viewHolder.d_email.setText(application.getdID());
        viewHolder.f_email.setText(application.getfID());
        String temp = application.getStartDate() + " to " + application.getEndDate();
        viewHolder.dates.setText(temp);
        switch (userType){
            case 0:
                viewHolder.f_toggle.setVisibility(View.GONE);
                viewHolder.f_approve.setVisibility(View.GONE);
                viewHolder.d_toggle.setVisibility(View.GONE);
                viewHolder.d_approve.setVisibility(View.GONE);
                switch (application.isApprovedByDoc()){
                    case 0 :
                        temp = "Waiting for Doctor's Approval";
                        viewHolder.f_text.setText(temp);
                        viewHolder.d_text.setText(temp);
                        break;
                    case 1:
                        temp = "Rejected";
                        viewHolder.f_text.setText(temp);
                        viewHolder.d_text.setText(temp);
                        break;
                    case 2:
                        temp = "Accepted";
                        viewHolder.d_text.setText(temp);
                        switch (application.isApprovedByFac()){
                            case 0:
                                temp = "Waiting for Faculty's Approval";
                                viewHolder.f_text.setText(temp);
                                break;
                            case 1:
                                temp = "Rejected";
                                viewHolder.f_text.setText(temp);
                                break;
                            case 2:
                                temp = "Accepted";
                                viewHolder.f_text.setText(temp);
                        }
                }
                break;
            case 1:
                viewHolder.d_toggle.setVisibility(View.GONE);
                viewHolder.d_approve.setVisibility(View.GONE);
                switch (application.isApprovedByDoc()){
                    case 0:
                        temp = "Waiting for Doctor's Approval";
                        viewHolder.f_text.setText(temp);
                        viewHolder.d_text.setText(temp);
                        viewHolder.f_toggle.setVisibility(View.GONE);
                        viewHolder.f_approve.setVisibility(View.GONE);
                        break;
                    case 1:
                        temp = "Rejected";
                        viewHolder.f_text.setText(temp);
                        viewHolder.d_text.setText(temp);
                        viewHolder.f_toggle.setVisibility(View.GONE);
                        viewHolder.f_approve.setVisibility(View.GONE);
                        break;
                    case 2:
                        temp ="Accepted";
                        viewHolder.d_text.setText(temp);
                        switch (application.isApprovedByFac()){
                            case 0:
                                viewHolder.f_approve.setVisibility(View.VISIBLE);
                                viewHolder.f_toggle.setVisibility(View.VISIBLE);
                                break;
                            case 1:
                                temp = "Rejected";
                                viewHolder.f_text.setText(temp);
                                viewHolder.f_toggle.setVisibility(View.GONE);
                                viewHolder.f_approve.setVisibility(View.GONE);
                                break;
                            case 2:
                                temp = "Accepted";
                                viewHolder.f_text.setText(temp);
                                viewHolder.f_toggle.setVisibility(View.GONE);
                                viewHolder.f_approve.setVisibility(View.GONE);
                                break;
                        }
                        break;
                }
                break;
            case 2:
                viewHolder.f_toggle.setVisibility(View.GONE);
                viewHolder.f_approve.setVisibility(View.GONE);
                switch (application.isApprovedByDoc()){
                    case 0:
                        temp = "Waiting for Doctor's Approval";
                        viewHolder.f_text.setText(temp);
                        viewHolder.d_approve.setVisibility(View.VISIBLE);
                        viewHolder.d_toggle.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        temp = "Rejected";
                        viewHolder.f_text.setText(temp);
                        viewHolder.d_text.setText(temp);
                        viewHolder.d_toggle.setVisibility(View.GONE);
                        viewHolder.d_approve.setVisibility(View.GONE);
                        break;
                    case 2:
                        temp ="Accepted";
                        viewHolder.d_toggle.setVisibility(View.GONE);
                        viewHolder.d_approve.setVisibility(View.GONE);
                        viewHolder.d_text.setText(temp);
                        switch (application.isApprovedByFac()){
                            case 0:
                                temp = "Waiting for Faculty's Approval";
                                viewHolder.f_text.setText(temp);
                                break;
                            case 1:
                                temp = "Rejected";
                                viewHolder.f_text.setText(temp);
                                break;
                            case 2:
                                temp = "Accepted";
                                viewHolder.f_text.setText(temp);
                        }
                }
                break;
        }
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        viewHolder.f_approve.setOnClickListener(v -> {
            db.collection(LeaveApplication.collectionName).document(id.get(position)).
                    update("approvedByFac", viewHolder.f_toggle.isChecked()? 2 : 1).addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            viewHolder.f_toggle.setVisibility(View.GONE);
                            viewHolder.f_approve.setVisibility(View.GONE);
                            String temp1 = viewHolder.f_toggle.isChecked()?"Accepted":"Rejected";
                            viewHolder.f_text.setText(temp1);
                        }
            });
        });

        viewHolder.d_approve.setOnClickListener(v -> {
            db.collection(LeaveApplication.collectionName).document(id.get(position)).
                    update("approvedByDoc", viewHolder.d_toggle.isChecked()? 2 : 1).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    viewHolder.d_toggle.setVisibility(View.GONE);
                    viewHolder.d_approve.setVisibility(View.GONE);
                   String temp1 = viewHolder.d_toggle.isChecked()?"Accepted":"Rejected";
                    viewHolder.d_text.setText(temp1);
                    if (!viewHolder.d_toggle.isChecked()){
                        db.collection(LeaveApplication.collectionName).document(id.get(position)).
                                update("approvedByFac", 1).addOnCompleteListener(task1 -> {
                            if(task1.isSuccessful()){
                                viewHolder.f_toggle.setVisibility(View.GONE);
                                viewHolder.f_approve.setVisibility(View.GONE);
                                String temp2 = "Rejected";
                                viewHolder.f_text.setText(temp2);
                            }
                        });
                    }
                }
            });
        });

        return convertView;
    }
}
