package com.example.aaya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class FurnitureActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public static final String EXTRA_TEXT = "com.example.aaya.EXTRA_TEXT";
    private EditText roomno;
    private Button button;
    DatabaseReference reff;
    Furniture furniture;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture);

        button=findViewById(R.id.button7);
        mDisplayDate = findViewById(R.id.editText8);
        roomno = findViewById(R.id.editText5);
        reff = FirebaseDatabase.getInstance().getReference().child("Furniture");
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        com.example.aaya.FurnitureActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {

                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);

                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);

            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                furniture= new Furniture();
                furniture.setRoomno(roomno.getText().toString().trim());
                furniture.setmDisplayDate(mDisplayDate.getText().toString().trim());
                reff.push().setValue(furniture);
                Toast.makeText(FurnitureActivity.this,"data inserted successfuly",Toast.LENGTH_LONG).show();
                Intent intToHome = new Intent(FurnitureActivity.this,EndActivity.class);
                startActivity(intToHome);
            }
        });
    }
}
