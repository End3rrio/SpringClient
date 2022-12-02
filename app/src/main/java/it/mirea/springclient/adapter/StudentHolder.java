package it.mirea.springclient.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import it.mirea.springclient.R;

public class StudentHolder extends RecyclerView.ViewHolder {

    TextView name, year, speciality;

    public StudentHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.studentListItem_name);
        year = itemView.findViewById(R.id.studentListItem_year);
        speciality = itemView.findViewById(R.id.studentListItem_speciality);
    }
}
