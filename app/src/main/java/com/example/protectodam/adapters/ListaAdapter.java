package com.example.protectodam.adapters;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daimajia.easing.linear.Linear;
import com.example.protectodam.Evento;
import com.example.protectodam.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

    private int resource;
    private ArrayList<Evento> listaEventos;

    public ListaAdapter(ArrayList<Evento> listaEventos, int resource) {
        this.listaEventos = listaEventos;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Evento evento = listaEventos.get(position);
        //Log.println(Log.ERROR, "ViewHolder", "" + listaEventos.size());
        Log.println(Log.ERROR, "listaEvento4", "" + listaEventos.get(1).getTitulo());

        holder.tv_titulo.setText(evento.getTitulo());
        holder.tv_fecha.setText(evento.getFechaIni());
        holder.tv_localidad.setText(evento.getLocalidad());
        holder.tv_precio.setText(evento.getPrecio());
        holder.tv_destinatarios.setText(evento.getDestinatarios());

        ImageView iv = holder.iv_imagenEvento;

        iv.getLayoutParams().height = 300;
        iv.getLayoutParams().width = 300;

        Glide.with(holder.view).load(evento.getImagen()).into(iv);

    }

    @Override
    public int getItemCount() {
        return listaEventos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tv_titulo;
        final TextView tv_fecha;
        final TextView tv_localidad;
        final TextView tv_precio;
        final TextView tv_destinatarios;
        final ImageView iv_imagenEvento;

        public View view;

        public ViewHolder(View view) {
            super(view);

            this.view = view;
            this.tv_titulo = (TextView) view.findViewById(R.id.tv_titulo);
            this.tv_fecha = (TextView) view.findViewById(R.id.tv_fecha);
            this.tv_localidad = (TextView) view.findViewById(R.id.tv_localidad);
            this.tv_precio = (TextView) view.findViewById(R.id.tv_precio);
            this.tv_destinatarios = (TextView) view.findViewById(R.id.tv_destinatarios);
            this.iv_imagenEvento = (ImageView) view.findViewById(R.id.iv_imagenEvento);
        }
    }
}

