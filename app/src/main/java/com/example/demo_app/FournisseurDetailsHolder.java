package com.example.demo_app;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FournisseurDetailsHolder extends RecyclerView.ViewHolder {
    View view;
    public FournisseurDetailsHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }

    public void setView(Context context , String fournisseur , String dateEcheance , String MtaPaye )
    {
        TextView fournisseurtv= view.findViewById(R.id.Fournisseur_tv);
        TextView echeance= view.findViewById(R.id.Echance);
        TextView mtApaye = view.findViewById(R.id.MTAPaye);

        fournisseurtv.setText(fournisseur);
        echeance.setText(dateEcheance);
        mtApaye.setText(MtaPaye);

    }
}