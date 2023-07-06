package com.example.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//define tablename
@Entity(tableName = "name_table")

public class MainData implements Serializable {
    @PrimaryKey(autoGenerate = true) private  int ID;
    @ColumnInfo(name = "Name") private String Name;

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    @ColumnInfo(name = "phone") private String ph;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
