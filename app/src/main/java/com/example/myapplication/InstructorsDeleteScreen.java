package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class InstructorsDeleteScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructors_delete_screen);
        EditText initials = (EditText) findViewById(R.id.editTextText_InstDelete);
        EditText name = (EditText) findViewById(R.id.editTextText2_InstDelete);
        Button delete = (Button) findViewById(R.id.button1_InstDelete);
        Button cancel = (Button) findViewById(R.id.button2_InstDelete);

        Bundle extras = getIntent().getExtras(); //retrieves extras from InstructorScreen (Intent formation origin)
        if (extras != null){
            String instructor_name = extras.getString("instructor_name");
            String instructor_initials = extras.getString("instructor_initials");
            name.setText(instructor_name);
            initials.setText(instructor_initials);
        }

        delete.setOnClickListener(new View.OnClickListener() { //vai apagar o professor da lista de professores
            @Override
            public void onClick(View v){
                String instructor_initials = initials.getText().toString();
                String instructor_name = name.getText().toString();
                Toast.makeText(InstructorsDeleteScreen.this, "Instructor deleted", Toast.LENGTH_LONG).show();
                initials.setText("");
                finish(); //termina a activity e volta para InstructorScreen
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() { //vai sair do deletemenu
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}