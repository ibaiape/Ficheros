package com.example.dm2.ficheros;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ejercicio2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        ArrayList<String> datosSpinner = new ArrayList<String>();
        try {
            InputStream fraw = getResources().openRawResource(R.raw.raw2);
            BufferedReader brin = new BufferedReader( new InputStreamReader(fraw));
            String linea= brin.readLine();
            while(linea!=null){
                datosSpinner.add(linea);
                linea = brin.readLine();
            }
            fraw.close();
            Spinner cmbOpciones =(Spinner) findViewById(R.id.cmbOpciones);
            ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, datosSpinner);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cmbOpciones.setAdapter(adaptador);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

}
