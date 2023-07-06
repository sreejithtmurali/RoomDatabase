package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.database.DatabaseUtilsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText text;
Button add,reset;
RecyclerView recyclerView;
static List<MainData> Datalist=new ArrayList<>();
LinearLayoutManager linearLayoutManager;
RoomDB roodbobj;
CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.item);
        add=findViewById(R.id.add);
        reset=findViewById(R.id.reset);
        roodbobj=RoomDB.getInstance(this);
        recyclerView=findViewById(R.id.recycler);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        customAdapter=new CustomAdapter(MainActivity.this,roodbobj,Datalist);
        recyclerView.setAdapter(customAdapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data=text.getText().toString().trim();
                if(data!=null){
                    // initalize mainData
                 MainData classobj=new MainData();
                    classobj.setName(data);
                    roodbobj.mainDao().insert(classobj);
                 text.setText("");
                 Datalist.clear();
                 Datalist.addAll(roodbobj.mainDao().getAll());
                 customAdapter.notifyDataSetChanged();
                }


            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roodbobj.mainDao().reset(Datalist);
                Datalist.clear();
                Datalist.addAll(roodbobj.mainDao().getAll());
                customAdapter.notifyDataSetChanged();
            }
        });


    }
}