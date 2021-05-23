package com.jvmr.controledecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jvmr.controledecursos.model.AppDatabase;
import com.jvmr.controledecursos.model.entities.Aluno;
import com.jvmr.controledecursos.model.entities.Curso;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppDatabase db;
    Button btnAluno;
    Button btnCurso;
    TextView qtdRegsitroAlunos, qtdRegistroCursos;
    List<Curso> cursos;
    List<Aluno> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAluno = findViewById(R.id.btnAluno);
        btnCurso = findViewById(R.id.btnCurso);
        qtdRegistroCursos = findViewById(R.id.registroCursos);
        qtdRegsitroAlunos = findViewById(R.id.registroAlunos);

        btnAluno.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, AlunoActivity.class);
            startActivity(it);
        });

        btnCurso.setOnClickListener(v -> {
            Intent it2 = new Intent(MainActivity.this, CursoActivity.class);
            startActivity(it2);
        });


        db = AppDatabase.getInstance(getApplicationContext());

        atualizarQuantidadesDeRegistros();

    }

    private void atualizarQuantidadesDeRegistros() {
        cursos = db.cursoDao().getAll();
        alunos = db.alunoDao().getAll();

        qtdRegsitroAlunos.setText(Integer.toString(alunos.size()));
        qtdRegistroCursos.setText(Integer.toString(cursos.size()));
    }

    @Override
    protected void onResume() {
        super.onResume();

        atualizarQuantidadesDeRegistros();
    }
}