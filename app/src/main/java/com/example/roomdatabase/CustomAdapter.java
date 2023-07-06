package com.example.roomdatabase;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    //initialize
    Activity context;
    RoomDB dtabase;
    List<MainData> dataList;
    public CustomAdapter(Activity context, RoomDB dtabase, List<MainData> dataList) {
        this.context = context;
        this.dtabase = dtabase;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
       // initialize main data
       final MainData data=dataList.get(position);
       // initialize database
        dtabase=RoomDB.getInstance(context);

        //initiaize textview
        holder.name.setText(data.getName());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialise data
             MainData d=dataList.get(holder.getAdapterPosition());

             //get id
                final int sID=d.getID();
                String sName=d.getName();
                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.dialoge_layout);
                dialog.show();
                final EditText name=dialog.findViewById(R.id.editname);
                name.setText(data.getName());
                Button update=dialog.findViewById(R.id.buttonup);
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                       String newName= name.getText().toString();
                       // call the instance of RoomDatabase and pass the  id and new updated text that is newName here
                       dtabase.mainDao().update(sID,newName);

                       //notify Data is Updated
                        dataList.clear();
                        dataList.addAll(dtabase.mainDao().getAll());
                        notifyDataSetChanged();

                    }
                });

            }
        });



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialise data
                final MainData d=dataList.get(holder.getAdapterPosition());

                //get id
                final int sID=d.getID();
                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.dialoge_layout);
                dialog.show();
                final EditText name=dialog.findViewById(R.id.editname);
                name.setText(data.getName());
                Button update=dialog.findViewById(R.id.buttonup);
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        String newName= name.getText().toString();
                        // call the instance of RoomDatabase and pass class obj
                        dtabase.mainDao().delete(d);
                        int posi=holder.getAdapterPosition();
                        dataList.remove(posi);
                        notifyItemRemoved(posi);
                        notifyItemRangeChanged(position,dataList.size());

                    }
                });

            }
        });



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageButton edit,delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
