package com.example.logind.db.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.logind.db.entidades.EmpalmeEntity;

import java.util.List;

@Dao
public interface EmpalmeDao {

    @Insert
    void insertEmpalme(EmpalmeEntity empalme);

    @Update
    void updateEmpalme(EmpalmeEntity empalme);

    @Delete
    void deleteEmpalme(EmpalmeEntity empalme);

    @Query("DELETE FROM empalmes")
    void deleteAllEmpalmes();

    @Query("DELETE FROM empalmes WHERE id_e = :idEmpalme")
    void deleteEmpalmeById(int idEmpalme);


    @Query("SELECT *FROM empalmes ORDER BY distancia ASC")
    LiveData<List<EmpalmeEntity>> getAllEmpalmes();

    @Query("SELECT *FROM empalmes WHERE id_e LIKE :id_empalme")
    EmpalmeEntity findEmpalmeById(int id_empalme);

    @Query("SELECT *FROM empalmes WHERE nombre_e LIKE :nombre_empalme")
    EmpalmeEntity findEmpalmeByNombre(String nombre_empalme);


    @Query("SELECT *FROM empalmes WHERE tramo like 'true'")
    LiveData<List<EmpalmeEntity>> getAllEmpalmesByTramo();
}
