package com.example.finalprojects2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class Shoes extends AppCompatActivity {

    private RecyclerView recyclerView;
    int[] images={R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6};
    String details[]={"Shoe 1","Shoe 2","Shoe 3","Shoe 4","Shoe 5","Shoe 6"};
    int[] prices={1299,1599,3499,3199,5999,899};
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes);

        recyclerView=findViewById(R.id.rvShoes);
        final String sna=getIntent().getStringExtra("NAME");
        final String sph=getIntent().getStringExtra("PHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        layoutManager=new GridLayoutManager(this,1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerAdapter(images,details,prices,this,sna,sph,spa,"Shoes");
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