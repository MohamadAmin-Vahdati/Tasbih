package com.mvp.tasbih.Presenter.DataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mvp.tasbih.Model.Zekr;

import java.util.List;

@Dao
public interface AzkarDAO {

    @Insert
    void insertAll (List<Zekr> Default_Azkar) ;

    @Insert
    void insert (Zekr new_zekr) ;


    @Query("Select * from Zekr where ID > 11")
    List<Zekr> getAzkarForAzkarPage();

    @Query("Select * from Zekr where ID =:ID")
    Zekr getZekr(int ID);

    @Update
    void Update(Zekr zekr);


    @Query("Delete from Zekr where ID =:ID")
    void Delete(int ID);


}
