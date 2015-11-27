package com.example.usuario.descargaweb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.net.MalformedURLException;
import java.net.URL;

public class Principal extends AppCompatActivity{

    private URL url;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        et = (EditText)findViewById(R.id.etLink);
    }

    public void descarga(View v){
        String preUrl = et.getText().toString();

        try {
            url=new URL(preUrl);
        } catch (MalformedURLException e) {
            Log.v("ESTADO_ERROR","MALFORMEDURL");
        }

        Intent i = new Intent(this,ListaImgs.class);
        i.putExtra("url",url);
        startActivity(i);
    }

}
