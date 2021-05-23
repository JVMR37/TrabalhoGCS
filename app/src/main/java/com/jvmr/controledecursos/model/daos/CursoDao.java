package com.jvmr.controledecursos.model.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jvmr.controledecursos.model.entities.Curso;

import java.util.List;

@Dao
public interface CursoDao {

    @Query("SELECT * FROM curso")
    List<Curso> getAll();

    @Insert
     long insert(Curso curso);

    @Delete
     int delete(Curso curso);

    @Update
    void update(Curso curso);

    @Query("SELECT * FROM curso WHERE cursoId = :id")
    Curso getCursoById(int id);

}
