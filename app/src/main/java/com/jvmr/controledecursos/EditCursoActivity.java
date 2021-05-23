package com.jvmr.controledecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.jvmr.controledecursos.model.AppDatabase;
import com.jvmr.controledecursos.model.entities.Aluno;
import com.jvmr.controledecursos.model.entities.Curso;

public class EditCursoActivity extends AppCompatActivity {
    ImageButton imgBtnVoltar;
    TextInputLayout nomeCursoInput, quantidadeDeHorasInput;
    private Button btnSalvarCurso;
    Curso curso, altCurso;
    private AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_curso);

        Intent it = getIntent();
        altCurso = (Curso) it.getSerializableExtra("chave_curso");

        curso = new Curso();

        db = AppDatabase.getInstance(getApplicationContext());

        imgBtnVoltar = findViewById(R.id.imgBtnVoltar4);
        nomeCursoInput = findViewById(R.id.nomeCursoInput);
        quantidadeDeHorasInput = findViewById(R.id.quantidadeDeHorasInput);
        btnSalvarCurso = findViewById(R.id.btnSalvarCurso);

        if (altCurso != null) {
            nomeCursoInput.getEditText().setText(altCurso.nomeCurso);
            quantidadeDeHorasInput.getEditText().setText(Integer.toString(altCurso.qtdeHoras));

            curso.cursoId = altCurso.cursoId;
        }

        imgBtnVoltar.setOnClickListener(v -> {
//            Intent it2 = new Intent(EditCursoActivity.this, CursoActivity.class);
//            startActivity(it2);
            finish();
        });


        btnSalvarCurso.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String nomeCurso = nomeCursoInput.getEditText().getText().toString();
                        String quantidaedeDeHoras = quantidadeDeHorasInput.getEditText().getText().toString();

                        long retornBD;
                        curso.nomeCurso = nomeCurso;
                        curso.qtdeHoras = Integer.parseInt(quantidaedeDeHoras);

                        if (curso.cursoId == null) {
                            retornBD = db.cursoDao().insert(curso);
                            if (retornBD == -1) {
                                alert("Erro ao salvar curso!");
                            } else {
                                alert("Curso cadastrado com sucesso!");
                            }
                        } else {
                            db.cursoDao().update(curso);
                        }
                        finish();
                    }
                }
        );
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}