package com.example.orlandoacort.examenunidad2;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> {

    private ArrayList<String> mTitulo = new ArrayList<>();
    private ArrayList<String> mFecha = new ArrayList<>();
    private ArrayList<String> mDescripcion = new ArrayList<>();
    private Activity mActivity;
    private int lastPosition = -1;

    public Adaptador(MainActivity activity, ArrayList<String> mTitulo, ArrayList<String> mFecha, ArrayList<String> mDescripcion) {
        this.mActivity = activity;
        this.mTitulo = mTitulo;
        this.mFecha = mFecha;
        this.mDescripcion = mDescripcion;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_titulo, tv_fecha, tv_descripcion;

        public MyViewHolder(View view) {
            super(view);
            tv_titulo = (TextView) view.findViewById(R.id.txtTitulo);
            tv_fecha = (TextView) view.findViewById(R.id.txtFecha);
            tv_descripcion = (TextView) view.findViewById(R.id.txtDescripcion);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_titulo.setText(mTitulo.get(position));
        holder.tv_fecha.setText(mFecha.get(position));
        holder.tv_descripcion.setText(mDescripcion.get(position));
    }

    @Override
    public int getItemCount() {
        return mTitulo.size();
    }

}
