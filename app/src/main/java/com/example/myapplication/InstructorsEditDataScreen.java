package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

//edit data vai ser usado para mudar as inicias e nome do professor bem como a disciplina que ensinam

public class InstructorsEditDataScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructors_edit_data_screen);
        EditText initials = (EditText) findViewById(R.id.editTextText_InstEditData);
        EditText name = (EditText) findViewById(R.id.editTextText2_InstEditData);
        Button edit = (Button) findViewById(R.id.button1_InstEditData);
        Button cancel = (Button) findViewById(R.id.button2_InstEditData);
        edit.setOnClickListener(new View.OnClickListener() { //vai editar o professor da lista de professores
            @Override
            public void onClick(View v){
                String instructor_initials = initials.getText().toString();
                String instructor_name = name.getText().toString();
                Toast.makeText(InstructorsEditDataScreen.this, "Instructor edited", Toast.LENGTH_LONG).show();
                initials.setText("");
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() { //vai sair do editmenu
            @Override
            public void onClick(View v){

            }
        });
    }
}