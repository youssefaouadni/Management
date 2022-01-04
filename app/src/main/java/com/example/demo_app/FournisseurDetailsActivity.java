
package com.example.demo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FournisseurDetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference reference;
    FirebaseDatabase database;
    String myReq;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_details_fournisseur);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        myReq = intent.getStringExtra("fournisseur");
        reference = database.getReference("detailsfour"+myReq);

        System.out.println("myref"+reference);
    }

    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerOptions<FournisseurDetails> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<FournisseurDetails>().setQuery(reference,FournisseurDetails.class).build();
        FirebaseRecyclerAdapter<FournisseurDetails, FournisseurDetailsHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FournisseurDetails, FournisseurDetailsHolder>(firebaseRecyclerOptions) {


            @Override
            protected void onBindViewHolder(@NonNull FournisseurDetailsHolder holder, int position, @NonNull FournisseurDetails model) {


                holder.setView(getApplication(),model.getFournisseur(),model.getDateEcheance(),model.getMtApaye());


            }

            @NonNull
            @Override
            public FournisseurDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                System.out.println("print");
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fourdetails_data_activity,parent,false);
                return new FournisseurDetailsHolder(view);
            }

        };
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}