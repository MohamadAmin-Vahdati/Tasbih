package com.mvp.tasbih.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Zekr {

    @PrimaryKey(autoGenerate = true)
    private int  ID ;

    @ColumnInfo(name = "Zekr")
    private String Zekr ;

    @ColumnInfo(name = "Translate")
    private String Translate ;

    @ColumnInfo(name = "FullCount")
    private int FullCount ;

    @ColumnInfo(name = "LastCount")
    private int LastCount ;

    @ColumnInfo(name = "Sound")
    private int Sound ;

    @ColumnInfo(name = "Suggested")
    private int Suggested ;

    public Zekr(){}


    public Zekr(String zekr, String translate, int fullCount, int lastCount, int suggested , int sound) {
        Zekr = zekr;
        Translate = translate;
        FullCount = fullCount;
        LastCount = lastCount;
        Sound = sound;
        Suggested = suggested;
    }

    public String getZekr() {
        return Zekr;
    }

    public void setZekr(String zekr) {
        Zekr = zekr;
    }

    public String getTranslate() {
        return Translate;
    }

    public void setTranslate(String translate) {
        Translate = translate;
    }

    public int getFullCount() {
        return FullCount;
    }

    public void setFullCount(int fullCount) {
        FullCount = fullCount;
    }

    public int getLastCount() {
        return LastCount;
    }

    public void setLastCount(int lastCount) {
        LastCount = lastCount;
    }

    public int getSound() {
        return Sound;
    }

    public void setSound(int sound) {
        Sound = sound;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSuggested() {
        return Suggested;
    }

    public void setSuggested(int suggested) {
        Suggested = suggested;
    }

}
