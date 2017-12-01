package com.example.dm2.ficheros;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ejercicio3 extends AppCompatActivity {
    ArrayList<Webpage> datos = new ArrayList<Webpage>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);


        try {
            InputStream fraw = getResources().openRawResource(R.raw.raw3);
            BufferedReader brin = new BufferedReader( new InputStreamReader(fraw));
            String linea= brin.readLine();
            String[] partes = new String[4];
            while(linea!=null){
                partes = linea.split("; ");
                datos.add(new Webpage(partes[0],partes[1],partes[2],partes[3]));
                linea = brin.readLine();
            }
            fraw.close();

            Webpage[] stockArr = new Webpage[datos.size()];
            stockArr = datos.toArray(stockArr);

            AdaptadorWebpages adaptador = new AdaptadorWebpages(this, stockArr);
            ListView lstOpciones = (ListView)findViewById(R.id.lstOpciones);
            lstOpciones.setAdapter(adaptador);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }





    class AdaptadorWebpages extends ArrayAdapter<Webpage> {
        public AdaptadorWebpages(Context context, Webpage[] datos) {
            super(context, R.layout.listitem_webpage, datos);
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_webpage, null);

            TextView lblNombre = (TextView)item.findViewById(R.id.lblnombre);
            lblNombre.setText(datos.get(position).getNombre());

            TextView lblLink = (TextView)item.findViewById(R.id.lbllink);
            lblLink.setText(datos.get(position).getLink());

            ImageView imgPag = (ImageView)item.findViewById(R.id.imgpag);
            int id = 0;
            switch(datos.get(position).getImagen()){
                case "bing":
                    id = R.drawable.ic_twitch;
                    break;
                case "yahoo":
                    id = R.drawable.ic_twitter;
                    break;
                case "google":
                    id = R.drawable.ic_youtube;
                    break;
            }
            imgPag.setImageResource(id);

            TextView lblDescripcion = (TextView)item.findViewById(R.id.lbldesc);
            lblDescripcion.setText(datos.get(position).getDescripcion());
            return(item);
        }
    }
}
