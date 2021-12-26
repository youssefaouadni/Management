package com.example.demo_app;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;


public class ClientHolder extends RecyclerView.ViewHolder {
    TextView textViewName;
    TextView textViewId;
    View view;
    int position;

    public String client;

    public ClientHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView;
        textViewName = itemView.findViewById(R.id.ClientName_tv);
        itemView.findViewById(R.id.ClientButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context myContext = view.getContext();
                Intent newintent = new Intent();

                int newPosition = position+1;
                client = String.valueOf(newPosition);
                newintent.putExtra("position",client);

                try {
                    newintent.setClass(myContext,ClientDetailsActivity.class);
                    myContext.startActivity(newintent);
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }


                System.out.println("client"+client);
            }
        });
    }


    public void setView(Context context, String ClientName, String category) {
        TextView titletv = view.findViewById(R.id.ClientName_tv);
        TextView categorytv = view.findViewById(R.id.Category_tv);

        titletv.setText(ClientName);
        categorytv.setText(category);

    }
}
