package com.jvmr.controledecursos.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jvmr.controledecursos.R;
import com.jvmr.controledecursos.model.AppDatabase;
import com.jvmr.controledecursos.model.entities.Aluno;
import com.jvmr.controledecursos.model.entities.Curso;

import java.util.List;

public class AlunoAdapter extends BaseAdapter {

    private final List<Aluno> alunos;
    private final Activity act;
    private final AppDatabase db;


    public AlunoAdapter(List<Aluno> alunos, Activity act) {
        this.alunos = alunos;
        this.act = act;
        this.db = AppDatabase.getInstance(act.getApplicationContext());

    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).alunoId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.item_lista, parent, false);
        Aluno aluno = alunos.get(position);

        TextView titulo = (TextView) view.findViewById(R.id.item_lista_titulo);
        TextView subtitulo = (TextView) view.findViewById(R.id.item_lista_subtitulo);

        titulo.setText(aluno.nomeAluno);
        Curso curso = db.cursoDao().getCursoById(aluno.cursoId);
        subtitulo.setText(curso.nomeCurso);


        return view;
    }
}
