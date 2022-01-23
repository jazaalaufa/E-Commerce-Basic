package com.example.finalprojects2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class WomensClothing extends AppCompatActivity {

    private RecyclerView recyclerView;
    int[] images={R.drawable.w0,R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5};
    String details[]={"Women Clothing 1","Women Clothing 2","Women Clothing 3","Women Clothing 4","Women Clothing 5","Women Clothing 6"};
    int[] prices={6999,1299,4159,1899,2949,1499};
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_womens_clothing);

        recyclerView=findViewById(R.id.rvWomen);
        final String sna=getIntent().getStringExtra("NAME");
        final String sph=getIntent().getStringExtra("PHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        layoutManager=new GridLayoutManager(this,1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerAdapter(images,details,prices,this,sna,sph,spa,"WomensClothing");
        recyclerView.setAdapter(adapter);
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