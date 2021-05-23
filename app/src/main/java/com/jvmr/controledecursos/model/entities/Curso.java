package com.jvmr.controledecursos.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "curso")
public class Curso implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public Integer cursoId;

    @ColumnInfo(name = "nome_curso")
    public String nomeCurso;

    @ColumnInfo(name = "qtd_de_horas")
    public int qtdeHoras;

    public  Curso(){}

    @Ignore
    public Curso(String nomeCurso, int qtdeHoras) {
        this.nomeCurso = nomeCurso;
        this.qtdeHoras = qtdeHoras;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "cursoId=" + cursoId +
                ", nomeCurso='" + nomeCurso + '\'' +
                ", qtdeHoras=" + qtdeHoras +
                '}';
    }
}
