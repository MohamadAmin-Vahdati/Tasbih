package com.mvp.tasbih.Presenter.Utility;

import android.content.Context;

import androidx.room.Room;

import com.mvp.tasbih.Model.Zekr;
import com.mvp.tasbih.Presenter.DataBase.MyDataBase;
import com.mvp.tasbih.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CheckDateBase {

    public static void CheckDataBase(Context context){

        File dbfile = context.getDatabasePath("AzkarDB");
        if (!dbfile.exists()){

            MyDataBase dataBase  = Room.databaseBuilder(context , MyDataBase.class,"AzkarDB").allowMainThreadQueries().build();
            List<Zekr> Default_Azkar = new ArrayList<>();

            Default_Azkar.add(new Zekr("يَا رَبَّ الْعَالَمِينَ","ای پروردگار جهانیان",100,0,100, R.raw.sound_shanbe));
            Default_Azkar.add(new Zekr("يَا ذَالْجَلالِ وَالْاِکْرامَ","ای صاحب جلال و بزرگواری",100,0,100,R.raw.sound_1shanbe));
            Default_Azkar.add(new Zekr("يَا قاضِیَ الْحاجات","ای برآورنده حاجت ها",100,0,100,R.raw.sound_2shanbe));
            Default_Azkar.add(new Zekr("يَا اَرْحَمَ الرّاحِمین","ای مهربان ترین مهربانان",100,0,100,R.raw.sound_3shanbe));
            Default_Azkar.add(new Zekr("يَا حَیُّ یا قَیّوم","ای زنده ، ای پاینده",100,0,100,R.raw.sound_4shanbe));
            Default_Azkar.add(new Zekr("لا اِلهَ اِلّا اللهُ الْمَلِکُ الْحَقُّ الْمُبین","نیست خدایی جز الله فرمانروای حق و آشکار",100,0,100,R.raw.sound_5shanbe));
            Default_Azkar.add(new Zekr("اَللّهُمَّ صَلِّ عَلی مُحَمَّدٍ وَ آلِ مُحَمَّدٍ","خدایا بر محمد و آل محمد درود فرست",100,0,100,R.raw.sound_jome));
            Default_Azkar.add(new Zekr("اللهُ اَکْبَرُ","خدا بزرگتر است",34,0,-1,R.raw.sound_allahakbar));
            Default_Azkar.add(new Zekr("اَلْحَمْدُ للهُ","سپاس و ستایش مخصوص خداست",33,0,-1,R.raw.sound_alhamdolleh));
            Default_Azkar.add(new Zekr("سُبْحانَ الله","پاک و منزه است خدا",33,0,-1,R.raw.sound_sobhnallah));
            Default_Azkar.add(new Zekr("اَللّهُمَّ صَلِّ عَلی مُحَمَّدٍ وَ آلِ مُحَمَّدٍ","خدایا بر محمد و آل محمد درود فرست",100,0,-1,R.raw.sound_jome));
            Default_Azkar.add(new Zekr("اَسْتَغْفِرُ الله وَاَتُوبُ اِلَیهِ","از خداوند طلب آمرزش می کنم و من به سوی او توبه و بازگشت می نمایم",100,0,-1,R.raw.sound_astaghferollah));
            Default_Azkar.add(new Zekr("سُبْحانَ الله، وَالحَمْدُ لله، وَلا اِلهَ اِلاَّ الله وَالله اَکبَرُِ","خداوند، متعال پاک و منزّه است، و ثنا مخصوص اوست، و نیست خدائی سزاوار پرستش مگر خداوند و او بزرگ تر است از آنچه که او را وصف کنند",30,0,-1,R.raw.sound_tasbihatarbae));
            Default_Azkar.add(new Zekr("اَلعَفو","مرا ببخش",100,0,-1,R.raw.sound_alafv));
            Default_Azkar.add(new Zekr("لَا حَوْلَ وَ لَا قُوَّةَ إِلَّا بِاللَّهِ الْعَلِیِّ الْعَظِیمِ","لَا حَوْلَ وَ لَا قُوَّةَ إِلَّا بِاللَّهِ الْعَلِیِّ الْعَظِیمِ",100,0,-1,R.raw.sound_lahawle));
            Default_Azkar.add(new Zekr("یا الله","خدايا",100,0,-1,R.raw.sound_yaallah));
            Default_Azkar.add(new Zekr("یا رَزَّاقُ","ای روزی دهنده",100,0,-1,R.raw.sound_yarazzaq));

            dataBase.azkarDAO().insertAll(Default_Azkar);
        }

    }


}
