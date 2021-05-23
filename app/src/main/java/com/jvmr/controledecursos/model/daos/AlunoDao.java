package com.jvmr.controledecursos.model.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jvmr.controledecursos.model.entities.Aluno;

import java.util.List;

@Dao
public interface AlunoDao {
    @Query("SELECT * FROM aluno")
    List<Aluno> getAll();

    @Query("SELECT * FROM aluno WHERE alunoId IN (:userIds)")
    List<Aluno> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Aluno... alunos);

    @Insert
    long insert(Aluno aluno);

    @Delete
    int delete(Aluno aluno);

    @Update
     void update(Aluno aluno);
}
