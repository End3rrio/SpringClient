package it.mirea.springclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.mirea.springclient.model.Student;
import it.mirea.springclient.retrofit.RetrofitService;
import it.mirea.springclient.retrofit.StudentApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents(){
        TextInputEditText inputEditName = findViewById(R.id.form_textFieldName);
        TextInputEditText inputEditSpeciality = findViewById(R.id.form_textFieldSpeciality);
        TextInputEditText inputEditYear = findViewById(R.id.form_textFieldYear);
        MaterialButton buttonSave = findViewById(R.id.form_buttonSave);

        RetrofitService retrofitService = new RetrofitService();
        StudentApi studentApi = retrofitService.getRetrofit().create(StudentApi.class);

        buttonSave.setOnClickListener(view -> {
            String name = String.valueOf(inputEditName.getText());
            String speciality = String.valueOf(inputEditSpeciality.getText());
            String year = String.valueOf(inputEditYear.getText());

            Student student = new Student();
            student.setName(name);
            student.setSpeciality(speciality);
            student.setYear(year);

            studentApi.save(student)
                    .enqueue(new Callback<Student>() {
                        @Override
                        public void onResponse(Call<Student> call, Response<Student> response) {
                            Toast.makeText(StudentForm.this, "Save successful!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Student> call, Throwable t) {
                            Toast.makeText(StudentForm.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(StudentForm.class.getName()).log(Level.SEVERE, "Error occurred",t);
                        }
                    });
        });

    }
}