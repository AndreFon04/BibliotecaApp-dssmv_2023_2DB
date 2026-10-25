package com.example.myapplication;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button instrtrs = findViewById(R.id.button1);
        instrtrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentInstrtrScrn = new Intent(MainActivity.this, InstructorScreen.class);
                startActivity(intentInstrtrScrn);
            }
        });

        Button stnts = findViewById(R.id.button2);
        stnts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        Button enrlls = findViewById(R.id.button3);
        enrlls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override //serve para chamar o metodo inflate que passa um Menu resource num Menu object (é o que deixa selecionar as opcoes do menu)
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = (MenuInflater) getMenuInflater();
        menuInflater.inflate(R.menu.optionmenu_main,menu);
        return true;
    }

    @Override //este codigo faz com que ao selecionar uma das opcoes do menu, neste caso settings e reload, apareça uma especie de nuvem pop-up que diz "___ option selected"
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.reload) {
            Toast.makeText(getApplicationContext(),"Reload option selected", Toast.LENGTH_LONG).show();
            return true;
        } else if (itemId == R.id.settings) {
            Toast.makeText(getApplicationContext(),"Settings option selected", Toast.LENGTH_LONG).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}