package com.jvmr.controledecursos.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.jvmr.controledecursos.model.AppDatabase;

import java.io.Serializable;

@Entity(tableName = "aluno",
        foreignKeys = @ForeignKey(entity = Curso.class, parentColumns = "cursoId", childColumns = "cursoId")
)
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public Integer alunoId;

    @ColumnInfo(name = "telefone")
    public String telefone;

    // @ColumnInfo(name = "curso_id")
    public int cursoId;

    @ColumnInfo(name = "nome_aluno")
    public String nomeAluno;

    @ColumnInfo(name = "cpf")
    public String cpf;

    @ColumnInfo(name = "email")
    public String email;

    public Aluno() {
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "alunoId=" + alunoId +
                ", telefone='" + telefone + '\'' +
                ", cursoId=" + cursoId +
                ", nomeAluno='" + nomeAluno + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
