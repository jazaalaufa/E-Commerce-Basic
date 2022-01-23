package com.example.finalprojects2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class OtherItems extends AppCompatActivity {

    private RecyclerView recyclerView;
    int[] images={R.drawable.o1,R.drawable.o2,R.drawable.o3,R.drawable.o4,R.drawable.o5,R.drawable.o6,R.drawable.o7,R.drawable.o8};
    String details[]={"Other Item 1","Other Item 2","Other Item 3","Other Item 4","Other Item 5","Other Item 6","Other Item 7","Other Item 8"};
    int[] prices={699,1299,459,899,949,499,599,799};
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_items);

        recyclerView=findViewById(R.id.rvOther);
        final String sna=getIntent().getStringExtra("NAME");
        final String sph=getIntent().getStringExtra("PHONE");
        final String spa=getIntent().getStringExtra("PASSWORD");
        layoutManager=new GridLayoutManager(this,1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerAdapter(images,details,prices,this,sna,sph,spa,"OtherItems");
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