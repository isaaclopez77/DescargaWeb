package com.example.usuario.descargaweb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ListaImgs extends AppCompatActivity {
    public String sourceCode;
    public String sourceImg;
    private Adaptador ad;
    public ListView lv;
    public URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_imgs);
        try {
            init();
        } catch (IOException e) {
        }
    }

    public void init() throws MalformedURLException {
        //Recibo el intent con la url
        Bundle b = getIntent().getExtras();
        url =(URL)b.get("url");

        //Ejecuto
        new DescargaWeb().execute(url);
    }

    public class DescargaWeb extends AsyncTask<URL, Void, Void> {

        private BufferedReader in;
        private List<String> lista;

        @Override
        protected Void doInBackground(URL... params) {
            URL url = params[0];
            URLConnection yc = null;
            try {
                yc = url.openConnection();
                in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
            } catch (IOException e) {
            }

            String inputLine;
            String format = "<img src=\"";
            try {
                while ((inputLine = in.readLine()) != null) {
                    sourceCode += inputLine;
                    if(inputLine.contains(format)){
                        int ix = inputLine.indexOf(format)+format.length();
                        sourceImg = inputLine.substring(ix, inputLine.indexOf("\"",ix));
                        lista.add(sourceImg);
                    }
                }
            } catch (IOException e) {
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            sourceCode = "";
            sourceImg = "";
            lista = new ArrayList<>();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv = (ListView)findViewById(R.id.lv);
            ad=new Adaptador(ListaImgs.this, R.layout.item, lista);
            lv.setAdapter(ad);

            Toast.makeText(ListaImgs.this,"Lista creada",Toast.LENGTH_LONG).show();

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(ListaImgs.this, VerImagen.class);
                    i.putExtra("img",lista.get(position));
                    startActivity(i);
                }
            });

            try {
                in.close();
            } catch (IOException e) {
            }
        }
    }
}