package com.example.roomdatabase;

import android.icu.text.Replaceable;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface  MainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insert(MainData mainData);

//delete
    @Delete
    void delete(MainData mainData);
    @Delete
    void reset(List<MainData>  mainData);
//update
    @Query("UPDATE name_table SET Name=:sText WHERE ID=:sID")
    void update(int sID,String sText);

    //get alll data
    @Query("SELECT * FROM name_table")
    List<MainData> getAll();

}
