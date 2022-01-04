package com.example.demo_app;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class FournisseurActivity extends AppCompatActivity{


    RecyclerView recyclerView;
    DatabaseReference reference;
    FirebaseDatabase database;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fournisseur);

        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerview_fournisseur);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("fournisseurs");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        int id = item.getItemId();
        if (mToggle.onOptionsItemSelected(item)){

            Toast.makeText(this,"button clicker",Toast.LENGTH_SHORT).show();
            return true ;
        }
        if (id== R.id.nav_client)
        {
            Intent intent   = new Intent(this, ClientActivity.class);
            startActivity(intent);
        }
        if (id== R.id.nav_fournisseur)
        {
            Intent intent   = new Intent(this, FournisseurActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerOptions<Fournisseur> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Fournisseur>().setQuery(reference,Fournisseur.class).build();
        FirebaseRecyclerAdapter<Fournisseur,FournisseurHolder> firebaseRecyclerAdapter_fournisseur = new FirebaseRecyclerAdapter<Fournisseur, FournisseurHolder>(firebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull FournisseurHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Fournisseur model) {
                holder.setView(getApplication(),model.getName(),model.getID());
                holder.position = position;
                System.out.println(model.getID());

            }

            @NonNull
            @Override
            public FournisseurHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fournisseur_data_activity,parent,false);
                return new FournisseurHolder(view);
            }

        };
        firebaseRecyclerAdapter_fournisseur.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter_fournisseur);
    }

}
