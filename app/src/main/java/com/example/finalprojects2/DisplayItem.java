package com.example.finalprojects2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class DisplayItem extends AppCompatActivity {

    ImageView imageView;
    TextView imageDetails,imagePrice;
    Button orderShownItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);

        imageView=findViewById(R.id.item_display);
        imageDetails=findViewById(R.id.item_details_display);
        imagePrice=findViewById(R.id.item_price_display);
        orderShownItem=findViewById(R.id.order_shown_item);
        imageView.setImageResource(getIntent().getIntExtra("image_id",00));
        imageDetails.setText(getIntent().getStringExtra("item_details"));
        imagePrice.setText(Integer.toString(getIntent().getIntExtra("item_price",00)));

        final String item_details=getIntent().getStringExtra("item_details");
        StockAdd.getStocks();
        StockAdd.databaseStocks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot stockSnapshot:dataSnapshot.getChildren()){
                    StockReg stockReg=stockSnapshot.getValue(StockReg.class);
                    String item_det=stockReg.getItemName();
                    if(item_det.equals(item_details)){
                        if(stockReg.getCurrentStockAvailaible()==0){
                            orderShownItem.setEnabled(false);
                            orderShownItem.setText("CURRENTLY OUT OF STOCK");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        orderShownItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisplayItem.this, PlaceOrder.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}