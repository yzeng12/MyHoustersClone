package com.example.yzeng.myhoustersclone.DataBase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.yzeng.myhoustersclone.Document.DataBaseDocument;
import com.example.yzeng.myhoustersclone.TodoList.DataBaseTodoList;
import com.example.yzeng.myhoustersclone.trip.DataBaseTripList;
import com.example.yzeng.myhoustersclone.tenant.DatabaseTenant;
import com.example.yzeng.myhoustersclone.transaction.DataBaseTransaction;
import com.example.yzeng.myhoustersclone.pojo.PropertyTable;

@Database (entities = {DataBaseDocument.class,DataBaseTodoList.class,
        DataBaseTransaction.class, DatabaseTenant.class,PropertyTable.class,DataBaseTripList.class},version = 2 )
public abstract class OurRoomDataBase extends RoomDatabase {
    public abstract DataBaseDao DatabaseDao();
    private static OurRoomDataBase INSTANCE;

    public static OurRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OurRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            OurRoomDataBase.class, "Our_ALLSOME_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.

                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
