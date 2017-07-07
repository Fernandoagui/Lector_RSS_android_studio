package com.example.fers.journal.journal;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fers on 16/01/17.
 */

public class PostArrayAdapter extends ArrayAdapter{
    Context contexto;
    int layoutResourceID;
    Post[] posts;

    public PostArrayAdapter(Context contexto, int layoutResourceID, Post[] posts){
        super(contexto, layoutResourceID, posts);
        this.contexto = contexto;
        this.layoutResourceID = layoutResourceID;
        this.posts = posts;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        PostHolder holder;

        if(row == null){
            LayoutInflater inflater = ((Activity)contexto).getLayoutInflater();
            row = inflater.inflate(layoutResourceID, parent, false);

            holder = new PostHolder();
            holder.tipoIconoPublicacion = (ImageView)row.findViewById(R.id.imageViewIconoTipoPost);
            holder.imagenPerfilUsuario = (ImageView)row.findViewById(R.id.imageViewPerfilUsuario);
            holder.descripcionHoraPublicacion = (TextView)row.findViewById(R.id.textViewInformacionPost);
            holder.direccionURLimagen = (ImageView)row.findViewById(R.id.imageViewImagenPost);
            holder.tituloPublicacion = (TextView)row.findViewById(R.id.textViewTituloPost);
            holder.descripcionPublicacion = (TextView)row.findViewById(R.id.textViewDescripcionPost);
            holder.cardPost = (LinearLayout)row.findViewById(R.id.cardPost);

            row.setTag(holder);
        }else{
            holder = (PostHolder)row.getTag();
        }
        Post post = posts[position];
        holder.tipoIconoPublicacion.setImageResource(post.tipoIconoPublicacion);
        holder.imagenPerfilUsuario.setImageResource(post.imagenUsuario);
        holder.descripcionHoraPublicacion.setText(Html.fromHtml("<b>"+post.nombreUsuario+"</b>"+ " " + post.descripcionHoraPublicacion));
        holder.direccionURLimagen.setImageResource(post.direccionURLimagen);
        holder.tituloPublicacion.setText(post.tituloPublicacion);
        holder.descripcionPublicacion.setText(post.descripcionPublicacion);
        holder.cardPost.setBackgroundColor(contexto.getResources().getColor(post.colorCard));
        return row;
    }

    static class PostHolder{
        ImageView tipoIconoPublicacion;
        ImageView imagenPerfilUsuario;
        TextView descripcionHoraPublicacion;
        ImageView direccionURLimagen;
        TextView tituloPublicacion;
        TextView descripcionPublicacion;
        LinearLayout cardPost;
    }
}
