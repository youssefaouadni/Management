package com.example.demo_app;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class FournisseurHolder extends RecyclerView.ViewHolder {
    public String fournisseur;

    TextView textViewName;
    TextView textViewId;
    View view;
    int position;
    public FournisseurHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView;

        textViewName = itemView.findViewById(R.id.FournisseurName_tv);
        itemView.findViewById(R.id.FournisseurButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context myContext = view.getContext();
                Intent newintent = new Intent();

                int newPosition = position+1;
                fournisseur = String.valueOf(newPosition);
                newintent.putExtra("fournisseur",fournisseur);
                System.out.println("myfournisseur"+fournisseur);
                try {
                    newintent.setClass(myContext,FournisseurDetailsActivity.class);
                    myContext.startActivity(newintent);
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }


                System.out.println("myfournisseur"+fournisseur);
            }
        });
    }

    public void setView(Context context , String name, String ID )
    {
        TextView nametv= view.findViewById(R.id.FournisseurName_tv);
        TextView typetv= view.findViewById(R.id.Categoryfour_tv);

        nametv.setText(name);
        typetv.setText(ID);

    }
}
