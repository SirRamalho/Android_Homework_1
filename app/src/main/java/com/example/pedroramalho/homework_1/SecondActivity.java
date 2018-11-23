package com.example.pedroramalho.homework_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {


    public Spinner spinner;
    Button ok;
    Button cancel;
    public int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent previous_activity=getIntent();
        spinner = (Spinner) findViewById(R.id.spinner_v);
        ok=findViewById(R.id.ok);
        cancel=findViewById(R.id.cancel);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(SecondActivity.this, "i =" +i, Toast.LENGTH_SHORT).show();
                position=i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SecondActivity.this,"Select a color, PLEASE",Toast.LENGTH_SHORT).show();

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("color",position);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
