package com.example.logind.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.logind.db.daos.EmpalmeDao;
import com.example.logind.db.entidades.EmpalmeEntity;

@Database(entities = {EmpalmeEntity.class},version = 1,exportSchema = false)
public abstract class DataBaseAppCasMag extends RoomDatabase {
    public abstract EmpalmeDao empalmeDao();
    private static volatile DataBaseAppCasMag INSTANCE;

    public static DataBaseAppCasMag getDataBase(final Context context){
        if(INSTANCE == null){
            synchronized (DataBaseAppCasMag.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataBaseAppCasMag.class,"db_casmag")
                            .allowMainThreadQueries()
                           // .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public static void destroyInstance(){INSTANCE = null;}
//metodo para la migracion de la base de datos
  /*  static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE grupo_mantenimiento_fo(id_gm INTEGER PRIMARY KEY NOT NULL,nobre_grupo TEXT,proyecto TEXT,regional TEXT,empresa TEXT )");
        }
    };*/
}
