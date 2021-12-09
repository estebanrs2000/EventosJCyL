package com.example.protectodam.ui.ajustes;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.protectodam.R;

import java.util.ArrayList;
import java.util.Locale;

public class AjustesFragment extends Fragment {

    private Spinner sp_idiomas;
    private Button bt_cambiarIdioma;
    private ArrayList<String> listaIdiomas = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ajustes, container, false);

        sp_idiomas = root.findViewById(R.id.sp_idiomas);
        bt_cambiarIdioma = root.findViewById(R.id.bt_cambiarIdioma);

        listaIdiomas.add(getString(R.string.espanol));
        listaIdiomas.add(getString(R.string.ingles));
        listaIdiomas.add(getString(R.string.portugues));

        ArrayAdapter adaptador=new ArrayAdapter(AjustesFragment.this.getContext(),android.R.layout.simple_list_item_1,listaIdiomas);
        sp_idiomas.setAdapter(adaptador);

        bt_cambiarIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int posicion = sp_idiomas.getSelectedItemPosition();

                switch (posicion){

                    case 0:
                        Locale idiom_es = new Locale("es", "ES");
                        Locale.setDefault(idiom_es);
                        Configuration config_es = new Configuration();
                        config_es.setLocale(idiom_es);
                        getContext().getResources().updateConfiguration(config_es, getContext().getResources().getDisplayMetrics());
                        break;
                    case 1:
                        Locale idiom_en = new Locale("en", "");
                        Locale.setDefault(idiom_en);
                        Configuration config_en = new Configuration();
                        config_en.setLocale(idiom_en);
                        getContext().getResources().updateConfiguration(config_en, getContext().getResources().getDisplayMetrics());
                        break;
                    case 2:
                        Locale idiom_pt = new Locale("pt", "PT");
                        Locale.setDefault(idiom_pt);
                        Configuration config_pt = new Configuration();
                        config_pt.setLocale(idiom_pt);
                        getContext().getResources().updateConfiguration(config_pt, getContext().getResources().getDisplayMetrics());
                        break;
                }

            }
        });


        return root;
    }

}