package com.example.hannah.foxtailproject;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private ItemModel model;

    private ImageButton placeholder;
    private Button add;
    private Button cancel;
    private TextView name;
    private TextView location;
    private DatePicker date;
    private RatingBar ratingBar;
    private TextView review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().hide();

        placeholder = (ImageButton) findViewById(R.id.placeholder);
        add = (Button) findViewById(R.id.add);
        cancel = (Button) findViewById(R.id.cancel);
        name = (TextView) findViewById(R.id.name);
        location = (TextView) findViewById(R.id.location);
        date = (DatePicker) findViewById(R.id.date);
        ratingBar = (RatingBar) findViewById(R.id.score);
        review = (TextView) findViewById(R.id.review);
        model = new ItemModel();

        placeholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.saveItem(name.getText().toString(), location.getText().toString(), date.getYear(), date.getMonth(), date.getDayOfMonth(), ratingBar.getRating(), review.getText().toString());
                Intent intent = new Intent(AddActivity.this, ListActivity.class);
                startActivity(intent);
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
