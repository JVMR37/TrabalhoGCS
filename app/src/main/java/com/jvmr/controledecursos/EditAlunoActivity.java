package com.jvmr.controledecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.jvmr.controledecursos.adapters.CursoSpinAdapter;
import com.jvmr.controledecursos.model.AppDatabase;
import com.jvmr.controledecursos.model.entities.Aluno;
import com.jvmr.controledecursos.model.entities.Curso;

import java.util.List;

public class EditAlunoActivity extends AppCompatActivity {
    TextInputLayout nomeAlunoInput, cpfInput, emailInput, telefoneInput;
    Spinner cursoSpinner;
    ImageButton imgBtnVoltar;
    private Button btnSalvarAluno;
    Aluno aluno, altAluno;
    private AppDatabase db;
    List<Curso> cursoList;
    CursoSpinAdapter cursoSpinAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_aluno);

        Intent it = getIntent();
        altAluno = (Aluno) it.getSerializableExtra("chave_aluno");

        aluno = new Aluno();

        db = AppDatabase.getInstance(getApplicationContext());

        imgBtnVoltar = findViewById(R.id.imgBtnVoltar3);
        cursoSpinner = findViewById(R.id.cursosSpinner);
        nomeAlunoInput = findViewById(R.id.nomeAlunoInput);
        cpfInput = findViewById(R.id.cpfInput);
        emailInput = findViewById(R.id.emailInput);
        telefoneInput = findViewById(R.id.telefoneInput);
        btnSalvarAluno = findViewById(R.id.btnSalvarAluno);


        if (altAluno != null) {
            nomeAlunoInput.getEditText().setText(altAluno.nomeAluno);
            cpfInput.getEditText().setText(altAluno.cpf);

            emailInput.getEditText().setText(altAluno.email);
            telefoneInput.getEditText().setText(altAluno.telefone);
            aluno.alunoId = altAluno.alunoId;
        }

        cursoList = db.cursoDao().getAll();

        /*ArrayAdapter spinnerArrayAdpter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                cursoList
        );*/

        Curso [] cursoArray = new Curso[cursoList.size()];
        cursoList.toArray(cursoArray);

        cursoSpinAdapter = new CursoSpinAdapter(this, android.R.layout.simple_spinner_item, cursoArray);

        cursoSpinner.setAdapter(cursoSpinAdapter);

        imgBtnVoltar.setOnClickListener(v -> {
//            Intent it2 = new Intent(EditAlunoActivity.this, AlunoActivity.class);
//            startActivity(it);
            finish();
        });

        btnSalvarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = nomeAlunoInput.getEditText().getText().toString();
                String cpf = cpfInput.getEditText().getText().toString();
                String email = emailInput.getEditText().getText().toString();
                String telefone = telefoneInput.getEditText().getText().toString();
                long retornoBD;
                aluno.nomeAluno = nome;
                aluno.telefone = telefone;
                aluno.email = email;
                aluno.cpf = cpf;

                Curso cursoSelecionado = (Curso) cursoSpinner.getSelectedItem();

                aluno.cursoId = cursoSelecionado.cursoId;

                if (aluno.alunoId == null) {
                    retornoBD = db.alunoDao().insert(aluno);
                    if (retornoBD == -1) {
                        alert("Erro ao salvar o aluno!");
                    } else {
                        alert("Aluno cadastrado com sucesso!");
                    }
                } else {
                    db.alunoDao().update(aluno);
                }
                finish();
            }
        });
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}