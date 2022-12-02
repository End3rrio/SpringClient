package it.mirea.springclient.retrofit;

import java.util.List;

import it.mirea.springclient.model.Student;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StudentApi {
    @GET("/student/getAll")
    Call<List<Student>> getAllStudents();

    @POST("/student/save")
    Call<Student> save(@Body Student student);

}
