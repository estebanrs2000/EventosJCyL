package com.example.protectodam.ui.busqueda;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.protectodam.Evento;
import com.example.protectodam.R;
import com.example.protectodam.adapters.ListaAdapter;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class BusquedaFragment extends Fragment implements OnMapReadyCallback{

    DatabaseReference databaseReference;

    private LinearLayout ll_datosEvento;
    private Button bt_mostrarDatos;
    private Spinner sp_titulos;
    private TextView tv_titulo;
    private TextView tv_fecha;
    private TextView tv_localidad;
    private TextView tv_horaIni;
    private TextView tv_horaFin;
    private TextView tv_precio;
    private TextView tv_destinatarios;
    private TextView tv_lugar;
    private ImageView iv_imagen;
    private MapView mv_lugar;

    private String titulo = "";
    private String fecha = "";
    private String horaIni = "";
    private String horaFin = "";
    private String localidad = "";
    private String precio = "";
    private String destinatarios = "";
    private String lugar = "";
    private String imagenEvento = "";
    private String coordenadas = "";

    private final ArrayList<Evento> listaEventos = new ArrayList<>();
    private final ArrayList<String> listaTitulos = new ArrayList<>();

    private MapView mv_mapa;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_busqueda, container, false);

        if (root != null){

            SupportMapFragment mapFragment = SupportMapFragment.newInstance();

            databaseReference = FirebaseDatabase.getInstance().getReference();

            ll_datosEvento = root.findViewById(R.id.ll_datosEvento);
            bt_mostrarDatos = root.findViewById(R.id.bt_mostrarDatosEvento);
            sp_titulos = root.findViewById(R.id.sp_titulos);

            tv_titulo = root.findViewById(R.id.tv_tituloBusqueda);
            tv_fecha = root.findViewById(R.id.tv_fechaBusqueda);
            tv_localidad = root.findViewById(R.id.tv_localidadBusqueda);
            tv_horaIni = root.findViewById(R.id.tv_horaIniBusqueda);
            tv_horaFin = root.findViewById(R.id.tv_horaFinBusqueda);
            tv_precio = root.findViewById(R.id.tv_precioBusqueda);
            tv_destinatarios = root.findViewById(R.id.tv_destinatariosBusqueda);
            tv_lugar = root.findViewById(R.id.tv_lugarBusqueda);
            iv_imagen = root.findViewById(R.id.iv_imagenBusqueda);

            DateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
            DateFormat inputFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);

            bt_mostrarDatos.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        bt_mostrarDatos.setBackground(getResources().getDrawable(R.color.rojoJuntaCyL));
                    } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        bt_mostrarDatos.setBackground(getResources().getDrawable(R.color.rojoJuntaCyL2));
                    }
                    return false;
                }
            });

            databaseReference.child("document/list").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        listaEventos.clear();
                        listaTitulos.clear();
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
                                Log.println(Log.ERROR, "fecha", "" + fecha);
                            }else {
                                fecha = "fecha";
                            }
                            if (ds.child("element/attribute/9/String").getValue() != null) {
                                horaIni = ds.child("element/attribute/9/string").getValue(String.class);
                                Log.println(Log.ERROR, "horaIni", "" + horaIni);
                            }else {
                                horaIni = "--:--";
                            }
                            if (ds.child("element/attribute/10/String").getValue() != null) {
                                horaFin = ds.child("element/attribute/10/string").getValue(String.class);
                                Log.println(Log.ERROR, "horaIni", "" + horaFin);
                            }else {
                                horaFin= "--:--";
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
                            if (ds.child("element/attribute/13/DirectorioPadre").getValue() != null) {
                                lugar = ds.child("element/attribute/13/DirectorioPadre").getValue(String.class);
                                Log.println(Log.ERROR, "lugar", "" + lugar);
                            }else {
                                lugar = " ";
                            }
                            if (ds.child("element/attribute/14/DirectorioPadre").getValue() != null) {
                                coordenadas = ds.child("element/attribute/14/DirectorioPadre").getValue(String.class);
                                Log.println(Log.ERROR, "coordenadas", "" + coordenadas);
                            }else {
                                coordenadas = "41.5965215 -4.117267900000002";
                            }
                            if (ds.child("element/attribute/21/link/reference").getValue() != null) {
                                imagenEvento = ds.child("element/attribute/21/link/reference").getValue(String.class);
                                Log.println(Log.ERROR, "imagenEvento", "" + imagenEvento);
                            }else {
                                imagenEvento = "https://www.gstatic.com/mobilesdk/160503_mobilesdk/logo/2x/firebase_28dp.png";
                            }
                            listaTitulos.add(titulo);
                            listaEventos.add(new Evento(titulo, fecha, localidad, horaIni, horaFin, precio, destinatarios, lugar, imagenEvento, coordenadas));


                            ArrayAdapter adaptador=new ArrayAdapter(BusquedaFragment.this.getContext(),android.R.layout.simple_list_item_1,listaTitulos);
                            sp_titulos.setAdapter(adaptador);

                        }


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("PRUEBAS", "Failed to read value.", error.toException());
                }
            });

            bt_mostrarDatos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SpinnerAdapter adaptador= sp_titulos.getAdapter();
                    String dato=adaptador.getItem(sp_titulos.getSelectedItemPosition()).toString();

                    //bt_mostrarDatos.setBackground(getDR.color.rojoJuntaCyL2);

                    for (Evento e : listaEventos){
                        if (e.getTitulo().equals(dato)){
                            ll_datosEvento.setVisibility(View.VISIBLE);

                            tv_titulo.setText(e.getTitulo());
                            tv_fecha.setText(e.getFechaIni());
                            tv_localidad.setText(e.getLocalidad());
                            tv_horaIni.setText(e.getHoraIni());
                            tv_horaFin.setText(e.getHoraFin());
                            tv_precio.setText(e.getPrecio());
                            tv_destinatarios.setText(e.getDestinatarios());
                            tv_lugar.setText(e.getLugar());

                            iv_imagen.getLayoutParams().height = 700;
                            iv_imagen.getLayoutParams().width = 700;

                            Log.println(Log.ERROR, "imagenEvento", "" + e.getImagen());

                            Glide.with(v).load(e.getImagen()).into(iv_imagen);

                        }


                    }


                }
            });

        }

        return root;
    }



    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}