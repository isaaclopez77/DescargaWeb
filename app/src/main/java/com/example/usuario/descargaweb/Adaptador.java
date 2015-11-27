package com.example.usuario.descargaweb;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends ArrayAdapter<String>{
    private int res;
    private List<String> list;
    private LayoutInflater lInflator;

    public class ViewHolder{
        public TextView tv1;
    }

    public Adaptador(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.res=resource;
        this.list=objects;
        this.lInflator=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder gv = new ViewHolder();

        if(convertView==null){
            convertView = lInflator.inflate(res,null);
            TextView tv = (TextView)convertView.findViewById(R.id.tv);
            gv.tv1=tv;
        }

        TextView tv = (TextView)convertView.findViewById(R.id.tv);
        tv.setText(list.get(position));
        
        return convertView;
    }
}
