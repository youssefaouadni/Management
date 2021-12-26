package com.example.demo_app;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class FournisseurHolder extends RecyclerView.ViewHolder {
    View view;
    public FournisseurHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView;
    }

    public void setView(Context context , String name, String ID )
    {
        TextView nametv= view.findViewById(R.id.Name_tv);
        TextView typetv= view.findViewById(R.id.Type_tv);

        nametv.setText(name);
        typetv.setText(ID);

    }
}
