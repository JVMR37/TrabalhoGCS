package com.jvmr.controledecursos;
// campo de busca
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.jvmr.controledecursos.adapters.AlunoAdapter;
import com.jvmr.controledecursos.model.AppDatabase;
import com.jvmr.controledecursos.model.entities.Aluno;

import java.util.List;

public class AlunoActivity extends AppCompatActivity {
    ImageButton imgBtnVoltar;
    ExtendedFloatingActionButton adcAluno;
    private ListView listaAlunos;
    private AppDatabase db;
    List<Aluno> arrayListAluno;
    AlunoAdapter alunoAdapter;
    Aluno aluno;
    private int id1, id2; //menu item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        db = AppDatabase.getInstance(getApplicationContext());

        imgBtnVoltar = findViewById(R.id.imgBtnVoltar2);
        adcAluno = findViewById(R.id.adicionarAluno);
        listaAlunos = findViewById(R.id.listaAlunos);

        registerForContextMenu(listaAlunos);

        imgBtnVoltar.setOnClickListener(v -> {
            finish();
        });

        listaAlunos.setOnItemClickListener(new
                                                   AdapterView.OnItemClickListener() {
                                                       @Override
                                                       public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                                           Aluno alunoSelecionado = (Aluno) alunoAdapter.getItem(position);
                                                           Intent it = new Intent(AlunoActivity.this, EditAlunoActivity.class);

                                                           it.putExtra("chave_aluno", alunoSelecionado);

                                                           startActivity(it);
                                                       }
                                                   });

        listaAlunos.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        aluno = alunoAdapter.getItem(position);
                        return false;
                    }
                }
        );

        adcAluno.setOnClickListener(v -> {
            Intent it2 = new Intent(AlunoActivity.this, EditAlunoActivity.class);
            startActivity(it2);
        });

        preencheLista();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mDelete = menu.add(Menu.NONE, id1, 1, "Deletar Aluno");
        MenuItem mSair = menu.add(Menu.NONE, id2, 2, "Cancelar");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int retornoBD;
                retornoBD = db.alunoDao().delete(aluno);
                if (retornoBD <= 0) {
                    alert("Erro de exclusão!");
                } else {
                    alert("Registro excluído com sucesso!");
                }
                preencheLista();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        preencheLista();
    }

    public void preencheLista() {

        arrayListAluno = db.alunoDao().getAll();

        if (listaAlunos != null) {

            alunoAdapter = new AlunoAdapter(arrayListAluno, this);

          /*  arrayAdapterAluno = new ArrayAdapter<Aluno>(AlunoActivity.this,
                    android.R.layout.simple_list_item_1, arrayListAluno);*/

            listaAlunos.setAdapter(alunoAdapter);
        }
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
