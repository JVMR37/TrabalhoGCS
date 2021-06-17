package com.jvmr.controledecursos;
// campo de busca no topo da tela
import androidx.appcompat.app.AppCompatActivity;
// adicionando tratamento para possibilitar deleção de cursos com alunos cadastrados
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.jvmr.controledecursos.adapters.CursoAdapter;
import com.jvmr.controledecursos.model.AppDatabase;
import com.jvmr.controledecursos.model.entities.Aluno;
import com.jvmr.controledecursos.model.entities.Curso;

import java.util.List;

public class CursoActivity extends AppCompatActivity {
    ImageButton imgBtnVoltar;
    ExtendedFloatingActionButton adcCurso;
    private ListView listaCursos;
    private AppDatabase db;
    List<Curso> cursoList;
    CursoAdapter cursoAdapter;
    Curso curso;
    private int id1, id2; //menu item


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        db = AppDatabase.getInstance(getApplicationContext());

        imgBtnVoltar = findViewById(R.id.imgBtnVoltar);
        adcCurso = findViewById(R.id.adicionarCurso);
        listaCursos = findViewById(R.id.listaCursos);

        registerForContextMenu(listaCursos);

        listaCursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Curso cursoSelecionado = (Curso) cursoAdapter.getItem(position);
                Intent it = new Intent(CursoActivity.this, EditCursoActivity.class);

                it.putExtra("chave_curso", cursoSelecionado);

                startActivity(it);
            }
        });

        listaCursos.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        curso = cursoAdapter.getItem(position);
                        return false;
                    }
                }
        );

        imgBtnVoltar.setOnClickListener(v -> {
            finish();
        });

        adcCurso.setOnClickListener(v -> {
            Intent it2 = new Intent(CursoActivity.this, EditCursoActivity.class);
            startActivity(it2);
        });

        preencheLista();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mDelete = menu.add(Menu.NONE, id1, 1, "Deletar Curso");
        MenuItem mSair = menu.add(Menu.NONE, id2, 2, "Cancelar");
        mDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int retornoBD;
                retornoBD = db.cursoDao().delete(curso);
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

        cursoList = db.cursoDao().getAll();

        if (listaCursos != null) {

            cursoAdapter = new CursoAdapter(cursoList, this);

            listaCursos.setAdapter(cursoAdapter);
        }
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
