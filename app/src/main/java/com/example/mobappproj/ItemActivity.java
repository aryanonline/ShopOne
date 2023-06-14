package com.example.mobappproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    TextView titleView, pubView, genView, priceView;

    Button goBack, buyNow;

    String title, pub, gen;
    double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        titleView = findViewById(R.id.titleView);
        pubView = findViewById(R.id.pubView);
        genView = findViewById(R.id.genView);
        priceView = findViewById(R.id.priceView);

        goBack = findViewById(R.id.goBackHomeBtn);
        buyNow = findViewById(R.id.checkOutBtn);

        Intent intent = getIntent();
        title = intent.getStringExtra("EXTRA_TITLE");
        pub = intent.getStringExtra("EXTRA_PUB");
        gen = intent.getStringExtra("EXTRA_GEN");
        price = intent.getDoubleExtra("EXTRA_PRICE", 0.0);

        titleView.setText(title);
        pubView.setText(pub);
        genView.setText(gen);
        priceView.setText("$"+String.format("%.2f", price));

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ItemActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(ItemActivity.this, BuyActivity.class);
                j.putExtra("EXTRA_PRICE", price);
                j.putExtra("EXTRA_TITLE", title);
                startActivity(j);
            }
        });
    }
}