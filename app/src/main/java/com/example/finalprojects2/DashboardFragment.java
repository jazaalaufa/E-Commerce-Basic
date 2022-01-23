package com.example.finalprojects2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.admin_fragment_dashboard, container, false);

        Button btnadd = (Button) root.findViewById(R.id.btnAddStock);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStock();
            }
        });


        return root;
    }

    public void AddStock() {
        Intent intent = new Intent(getActivity(), StockAdd.class);
        startActivity(intent);
    }
}
