package com.jvmr.controledecursos.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jvmr.controledecursos.model.daos.AlunoDao;
import com.jvmr.controledecursos.model.daos.CursoDao;
import com.jvmr.controledecursos.model.entities.Aluno;
import com.jvmr.controledecursos.model.entities.Curso;

@Database(
        entities = {Aluno.class, Curso.class}, version = 3
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AlunoDao alunoDao();
    public abstract CursoDao cursoDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,
                AppDatabase.class, "database-name")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }
}
