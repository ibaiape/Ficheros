package com.example.dm2.ficheros;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Ejercicio1 extends AppCompatActivity {

    boolean sdDisponible = false;
    boolean sdAccesoEscritura= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio1);

        String estado= Environment.getExternalStorageState();
        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            sdDisponible = true;
            sdAccesoEscritura = true;
        }
        else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
            sdAccesoEscritura = false;
    }

    public void escribirFicheroInt(View v) {
        try{
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("texto.txt",Context.MODE_PRIVATE));
            osw.write(((EditText)findViewById(R.id.txtContenido)).getText().toString()+"\n");
            osw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void escribirFicheroExt(View v){
        try {
            File ruta_sd = getBaseContext().getExternalFilesDir(null);
            File f = new File (ruta_sd.getAbsolutePath(),"prueba_sd.txt");
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(f));
            osw.write(((EditText)findViewById(R.id.txtContenido)).getText().toString()+"\n");
            osw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leerFicheroInt(View v) {
        try{
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("texto.txt")));
            String total = "";
            String linea= fin.readLine();
            while(linea!=null){
                total += linea+"\n";
                linea = fin.readLine();
            }
            fin.close();
            ((TextView)findViewById(R.id.lblContenido)).setText(total);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void leerFicheroExt(View v) {
        try {
            File ruta_sd = getBaseContext().getExternalFilesDir(null);
            File f= new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String total = "";
            String linea= br.readLine();
            while(linea!=null){
                total += linea+"\n";
                linea = br.readLine();
            }
            br.close();
            ((TextView)findViewById(R.id.lblContenido)).setText(total);
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void leerRecurso(View v){
        try {
            InputStream fraw = getResources().openRawResource(R.raw.raw1);
            BufferedReader brin = new BufferedReader( new InputStreamReader(fraw));
            String total = "";
            String linea= brin.readLine();
            while(linea!=null){
                total += linea+"\n";
                linea = brin.readLine();
            }
            fraw.close();
            ((TextView)findViewById(R.id.lblContenido)).setText(total);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void borrarFicheroInt(View v) {
        try{
            File f = new File(getFilesDir(), "texto.txt");
            String fin = f.delete() ? "Fichero interno borrado con éxito":"Error al borrar fichero interno";
            ((TextView)findViewById(R.id.lblContenido)).setText(fin);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void borrarFicheroExt(View v) {
        try {
            File ruta_sd = getBaseContext().getExternalFilesDir(null);
            File f= new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
            String fin = f.delete() ? "Fichero externo borrado con éxito":"Error al borrar fichero externo";
            ((TextView)findViewById(R.id.lblContenido)).setText(fin);
        }catch (Exception e){
            e.getStackTrace();
        }
    }

}
