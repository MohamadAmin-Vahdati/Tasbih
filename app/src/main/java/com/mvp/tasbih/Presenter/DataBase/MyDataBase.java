package com.mvp.tasbih.Presenter.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mvp.tasbih.Model.Zekr;

@Database(entities = {Zekr.class},version = 1)
public abstract class MyDataBase extends RoomDatabase {

    public abstract AzkarDAO azkarDAO ();
}
