package com.example.myapplication;

import android.content.Intent;
import android.util.Log;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;



public class InstructorScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private InstructorScreenList adapter;

    public class Instructor {
        private String initials;
        private String name;
        public String getName(){
            return name;
        }
        public String getInitials(){
            return initials;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_screen);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new InstructorScreenList(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);
        fetchInstructors();
    }

    @Override //faz aparecer opcoes qnd se clica nos 3 pontos na action bar e é o que deixa selecionar as opcoes do menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = (MenuInflater) getMenuInflater();
        menuInflater.inflate(R.menu.optionmenu_instructorscreen,menu);
        return true;
    }

    @Override //aparece um Toast da opcao escolhida
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.add) {
            Toast.makeText(getApplicationContext(),"Add option selected", Toast.LENGTH_LONG).show();
            return true;
        } else if (itemId == R.id.reload) {
            Toast.makeText(getApplicationContext(),"Reload option selected", Toast.LENGTH_LONG).show();
            return true;
        } else if (itemId == R.id.settings) {
            Toast.makeText(getApplicationContext(),"Settings option selected", Toast.LENGTH_LONG).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = (MenuInflater) getMenuInflater();
        menuInflater.inflate(R.menu.contexmenu_instructorscreen,menu);
        menu.setHeaderTitle("Select an Action");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = ((InstructorScreenList) recyclerView.getAdapter()).getSelectedItemPosition();
        if (position != RecyclerView.NO_POSITION) {
            InstructorScreen.Instructor instructor = adapter.instructors.get(position);
            int itemId = item.getItemId();
            if (itemId == R.id.delete) {
                Toast.makeText(getApplicationContext(), "Delete option selected: " + instructor.getName(), Toast.LENGTH_LONG).show();
                Intent deleteIntent = new Intent(InstructorScreen.this, InstructorsDeleteScreen.class);
                deleteIntent.putExtra("Instructor_name", instructor.getName());
                deleteIntent.putExtra("Instructor_initials", instructor.getInitials());
                startActivity(deleteIntent);
            } else if (itemId == R.id.editdata) {
                Toast.makeText(getApplicationContext(), "Edit data option selected: " + instructor.getName(), Toast.LENGTH_LONG).show();
                return true;
            } else if (itemId == R.id.editdetails) {
                Toast.makeText(getApplicationContext(), "Edit details option selected: " + instructor.getName(), Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return super.onContextItemSelected(item);
    }


    public interface InstructorService {
        @GET("/v1/instructor")
        Call<List<Instructor>> getInstructors();
    }

    public void fetchInstructors() { //faz GET dos nomes dos instructors
        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8585/v1/instructor/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create a service for the Instructor API
        InstructorService service = retrofit.create(InstructorService.class);

        // Make the GET request
        Call<List<Instructor>> call = service.getInstructors();
        call.enqueue(new Callback<List<Instructor>>() {
            @Override
            public void onResponse(Call<List<Instructor>> call, Response<List<Instructor>> response) {
                if (response.isSuccessful()) {
                    List<Instructor> instructors = response.body();
                    if (instructors != null && !instructors.isEmpty()) {
                        adapter = new InstructorScreenList(instructors);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e("InstructorScreen", "No instructors found.");
                    }
                } else {
                    Log.e("InstructorScreen", "Response not successful: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Instructor>> call, Throwable t) {
                Log.e("InstructorScreen", "Request failed: " + t.getMessage());
            }
        });
    }

    public interface InstructorDelete {
        @DELETE("/v1/instructor/{initials}")
        Call<Void> deleteInstructors(int instructorId);
    }

    public void deleteInstructors(int instructorId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8585/v1/instructor/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InstructorDelete service = retrofit.create(InstructorDelete.class);

        Call<Void> call = service.deleteInstructors(instructorId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Void instructors = response.body();
                } else {
                    Log.e("InstructorScreen", "Response not successful: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("InstructorScreen", "Request failed: " + t.getMessage());
            }
        });












    }












}

