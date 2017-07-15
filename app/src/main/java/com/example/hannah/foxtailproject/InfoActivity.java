package com.example.hannah.foxtailproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private ItemModel model;

    private Button save;
    private Button cancel;
    private TextView info;
    private TextView name;
    private TextView location;
    private DatePicker date;
    private RatingBar ratingBar;
    private TextView review;
    private Item item;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getSupportActionBar().hide();

        model = new ItemModel();

        save = (Button) findViewById(R.id.info_save);
        cancel = (Button) findViewById(R.id.info_cancel);
        info = (TextView) findViewById(R.id.info);
        name = (TextView) findViewById(R.id.info_name);
        location = (TextView) findViewById(R.id.info_location);
        date = (DatePicker) findViewById(R.id.info_date);
        ratingBar = (RatingBar) findViewById(R.id.info_score);
        review = (TextView) findViewById(R.id.info_review);

        item = (Item) getIntent().getSerializableExtra("item");
        id = getIntent().getStringExtra("id");

        info.setText(item.name);
        name.setText(item.name);
        location.setText(item.location);
        date.updateDate(item.year, item.month, item.day);
        ratingBar.setRating(item.score);
        review.setText(item.review);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.updateItem(id, name.getText().toString(), location.getText().toString(), date.getYear(), date.getMonth(), date.getDayOfMonth(), ratingBar.getRating(), review.getText().toString());
                Intent intent = new Intent(InfoActivity.this, ListActivity.class);
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
