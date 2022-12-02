package it.mirea.springclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import it.mirea.springclient.adapter.StudentAdapter;
import it.mirea.springclient.model.Student;
import it.mirea.springclient.retrofit.RetrofitService;
import it.mirea.springclient.retrofit.StudentApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        recyclerView = findViewById(R.id.studentList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton floatingActionButton = findViewById(R.id.studentList_fab);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, StudentForm.class);
            startActivity(intent);
        });

        loadStudents();
    }

    private void loadStudents(){
        RetrofitService retrofitService = new RetrofitService();
        StudentApi studentApi = retrofitService.getRetrofit().create(StudentApi.class);
        studentApi.getAllStudents()
                .enqueue(new Callback<List<Student>>() {
                    @Override
                    public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Student>> call, Throwable t) {
                        Toast.makeText(StudentListActivity.this, "Failed to load Students", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<Student> students) {
        StudentAdapter studentAdapter = new StudentAdapter(students);
        recyclerView.setAdapter(studentAdapter);
    }

}