package it.mirea.springclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.mirea.springclient.R;
import it.mirea.springclient.model.Student;

public class StudentAdapter extends RecyclerView.Adapter<StudentHolder> {

    private List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_student_item, parent, false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student student = students.get(position);
        holder.name.setText(student.getName());
        holder.year.setText(student.getYear());
        holder.speciality.setText(student.getSpeciality());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
