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

public class CursoAdapter extends BaseAdapter {

    private final List<Curso> cursos;
    private final Activity act;


    public CursoAdapter(List<Curso> cursos, Activity act) {
        this.cursos = cursos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return cursos.size();
    }

    @Override
    public Curso getItem(int position) {
        return cursos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cursos.get(position).cursoId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.item_lista, parent, false);
        Curso curso = cursos.get(position);

        TextView titulo = (TextView) view.findViewById(R.id.item_lista_titulo);
        TextView subtitulo = (TextView) view.findViewById(R.id.item_lista_subtitulo);

        titulo.setText(curso.nomeCurso);
        subtitulo.setText("Carga horária:" + Integer.toString(curso.qtdeHoras));


        return view;
    }
}
