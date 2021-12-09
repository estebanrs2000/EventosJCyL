package com.example.protectodam.ui.listado;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.protectodam.adapters.ListaAdapter;
import com.example.protectodam.R;
import com.example.protectodam.Evento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ListadoFragment extends Fragment {

    private ListaAdapter listaAdapter;
    private RecyclerView recyclerView;
    final private ArrayList<Evento> listaEventos = new ArrayList<>();

    DatabaseReference databaseReference;

    private String titulo = "";
    private String fecha = "";
    private String localidad = "";
    private String precio = "";
    private String destinatarios = "";
    private String imagenEvento = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_listado, container, false);

        if(root != null) {
            recyclerView = root.findViewById(R.id.recyclerEventos);
            recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

            databaseReference = FirebaseDatabase.getInstance().getReference();

            DateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
            DateFormat inputFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);

            databaseReference.child("document/list").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        listaEventos.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (ds.child("element/attribute/1/string").getValue() != null){
                                titulo = ds.child("element/attribute/1/string").getValue(String.class);
                                Log.println(Log.ERROR, "titulo", "" + titulo);
                            }else {
                                titulo = "titulo";
                            }
                            if (ds.child("element/attribute/7/valor/0").getValue() != null) {
                                fecha = ds.child("element/attribute/7/valor/0").getValue(String.class);
                                Date date = null;
                                try {
                                    date = inputFormat.parse(fecha);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                fecha = outputFormat.format(date);
                                Log.println(Log.ERROR, "fecha", "" + outputFormat.format(date));
                            }else {
                                fecha = "fecha";
                            }
                            if (ds.child("element/attribute/16/LocalidadPadre").getValue() != null) {
                                localidad = ds.child("element/attribute/16/LocalidadPadre").getValue(String.class);

                                Log.println(Log.ERROR, "localidad", "" + localidad);
                            }else {
                                localidad = "localidad";
                            }
                            if (ds.child("element/attribute/17/string").getValue() != null) {
                                precio = ds.child("element/attribute/17/string").getValue(String.class);
                                Log.println(Log.ERROR, "precio", "" + precio);
                            }else {
                                precio = "Gratuito";
                            }
                            if (ds.child("element/attribute/19/valor/0").getValue() != null) {
                                destinatarios = ds.child("element/attribute/19/valor/0").getValue(String.class);
                                Log.println(Log.ERROR, "destinatarios", "" + destinatarios);
                            }else {
                                destinatarios = "Todos los públicos";
                            }
                            if (ds.child("element/attribute/21/link/reference").getValue() != null) {
                                imagenEvento = ds.child("element/attribute/21/link/reference").getValue(String.class);
                                Log.println(Log.ERROR, "imagenEvento", "" + imagenEvento);
                            }else {
                                imagenEvento = "https://www.gstatic.com/mobilesdk/160503_mobilesdk/logo/2x/firebase_28dp.png";
                            }

                            listaEventos.add(new Evento(titulo, fecha, localidad, precio, destinatarios, imagenEvento));

                        }
                        listaAdapter = new ListaAdapter(listaEventos, R.layout.lista_eventos);
                        recyclerView.setAdapter(listaAdapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("PRUEBAS", "Failed to read value.", error.toException());
                }
            });

        }

        return root;
    }


}