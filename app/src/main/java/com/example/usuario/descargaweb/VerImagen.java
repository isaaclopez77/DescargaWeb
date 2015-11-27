package com.example.usuario.descargaweb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class VerImagen extends AppCompatActivity {

    public ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_img);
        iv = (ImageView) findViewById(R.id.imagen);

        //Obtengo el String del Intent
        Bundle b = getIntent().getExtras();
        String img = (String) b.get("img");

        //Ejecuto
        new VerImg().execute(img);
    }

    private class VerImg extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {

            String imageUrl = params[0];
            Bitmap bitmap = null;

            try{
                //Descargar imagen de la url
                InputStream input = new URL(imageUrl).openStream();

                bitmap = BitmapFactory.decodeStream(input);
            } catch (IOException e) {}
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            iv.setImageBitmap(bitmap);
            Toast.makeText(VerImagen.this,"Imagen cargada",Toast.LENGTH_SHORT).show();
        }
    }
}