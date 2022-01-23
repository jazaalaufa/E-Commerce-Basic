package com.example.finalprojects2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class Electronics extends AppCompatActivity {

    private RecyclerView recyclerView;
    int[] images={R.drawable.e1,R.drawable.e2,R.drawable.e3,R.drawable.e4,R.drawable.e5,R.drawable.e6,R.drawable.e7,R.drawable.e8,R.drawable.e9,R.drawable.e10,R.drawable.e11,R.drawable.e12};
    String details[]={"Electronic Item 1","Electronic Item 2","Electronic Item 3","Electronic Item 4","Electronic Item 5","Electronic Item 6","Electronic Item 7","Electronic Item 8","Electronic Item 9","Electronic Item 10","Electronic Item 11","Electronic Item 12",};
    int[] prices={5000000,45000000,55000000,6000000,3499900,1299900,4339900,1299900,4999900,1399900,399000};
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics);

        recyclerView=findViewById(R.id.rvElect);
        final String sna=getIntent().getStringExtra("NAME");
        final String sph=getIntent().getStringExtra("PHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        layoutManager=new GridLayoutManager(this,1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerAdapter(images,details,prices,this,sna,sph,spa,"Electronics");
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