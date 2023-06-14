package com.example.mobappproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class BuyActivity extends AppCompatActivity {

    TextView qtyCount, titleView, totalView;

    EditText emailInput, cardInput, expInput, cvvInput;

    int qty = 1;

    double price;

    ImageButton inc, dec;
    Button back, confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        emailInput = findViewById(R.id.emailInput);
        cardInput = findViewById(R.id.cardInput);
        expInput = findViewById(R.id.expInp);
        cvvInput = findViewById(R.id.cvvInput);

        qtyCount = findViewById(R.id.qtyCount);
        titleView = findViewById(R.id.titleView2);
        totalView = findViewById(R.id.totView);

        inc = findViewById(R.id.decCount2);
        dec = findViewById(R.id.decCount);
        back = findViewById(R.id.goBackHomeBtn2);

        Intent intent = getIntent();

        String title = intent.getStringExtra("EXTRA_TITLE");
        price = intent.getDoubleExtra("EXTRA_PRICE", 0.0);

        titleView.setText(title);
        totalView.setText("$"+String.format("%.2f", price));

        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qty <= 4){
                    qty += 1;
                    double newPrice = price * qty;

                    qtyCount.setText(Integer.toString(qty));
                    totalView.setText("$"+String.format("%.2f", newPrice));
                }else{
                    Toast.makeText(BuyActivity.this, "Max Quantity Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qty >= 2){
                    qty -= 1;
                    double newPrice = price * qty;

                    qtyCount.setText(Integer.toString(qty));
                    totalView.setText("$"+String.format("%.2f", newPrice));
                }else{
                    Toast.makeText(BuyActivity.this, "Quantity cannot be less than 1", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BuyActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        confirm = findViewById(R.id.confirmBuyBtn);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean email = false, card = false, exp = false, cvv = false;

                if(!emailInput.getText().toString().matches("")){
                    email = true;
                }
                if(!cardInput.getText().toString().matches("") && cardInput.getText().toString().length() == 16){
                    card = true;
                }
                if(!expInput.getText().toString().matches("") && expInput.getText().toString().length() == 4){
                    exp = true;
                }
                if(!cvvInput.getText().toString().matches("") && cvvInput.getText().toString().length() == 3){
                    cvv = true;
                }

                if(email && card && exp && cvv){
                    Intent j = new Intent(BuyActivity.this, confirmedActivity.class);
                    startActivity(j);
                }else{
                    Toast.makeText(BuyActivity.this, "Please Enter ALL Information", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}