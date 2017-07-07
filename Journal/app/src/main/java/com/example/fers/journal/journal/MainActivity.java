package com.example.fers.journal.journal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class MainActivity extends AppCompatActivity{
    SlidingPaneLayout mSlidingPanel;
    ImageView appImage;
    TextView TitleText;
    ImageView botonMenu;
    ImageButton imageViewHome;
    LinearLayout main;
    ListView listView;
    Context contexto;
    LinearLayout cine;
    LinearLayout home;
    LinearLayout musica;
    LinearLayout politica;
    LinearLayout videojuegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contexto = this;
        imageViewHome = (ImageButton)findViewById(R.id.imageViewHome);
        home = (LinearLayout)findViewById(R.id.linearLayoutPerfil);
        cine = (LinearLayout)findViewById(R.id.linearLayoutCine);
        musica = (LinearLayout)findViewById(R.id.linearLyoutMusica);
        politica = (LinearLayout)findViewById(R.id.linearLayoutPolitica);
        videojuegos = (LinearLayout)findViewById(R.id.linearLayoutVideojuegos);
        mSlidingPanel = (SlidingPaneLayout) findViewById(R.id.SlidingPanel);
        appImage = (ImageView)findViewById(android.R.id.home);
        botonMenu = (ImageView) findViewById(R.id.botonMenuLateral);
        TitleText = (TextView)findViewById(android.R.id.title);
        main = (LinearLayout)findViewById(R.id.main);
        listView = (ListView)findViewById(R.id.listViewPost);
        View header = (View) getLayoutInflater().inflate(R.layout.listview_header, null);
        listView.addHeaderView(header);
        mSlidingPanel.setPanelSlideListener(panelListener);
        botonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSlidingPanel.isOpen()){
                    mSlidingPanel.closePane();
                }
                else{
                    mSlidingPanel.openPane();
                }
            }
        });
        listViewRSSRecuest(1);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listViewRSSRecuest(1);
                mSlidingPanel.closePane();

            }
        });
        imageViewHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listViewRSSRecuest(1);
                mSlidingPanel.closePane();

            }
        });
        cine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listViewRSSRecuest(2);
                mSlidingPanel.closePane();
            }
        });
        musica.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listViewRSSRecuest(3);
                mSlidingPanel.closePane();
            }
        });
        politica.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listViewRSSRecuest(4);
                mSlidingPanel.closePane();
            }
        });
        videojuegos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listViewRSSRecuest(1);
                mSlidingPanel.closePane();
            }
        });

    }

    SlidingPaneLayout.PanelSlideListener panelListener = new SlidingPaneLayout.PanelSlideListener(){
        @Override
        public void onPanelClosed(View arg0) {
            // TODO Auto-genxxerated method stub
        }

        @Override
        public void onPanelOpened(View arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPanelSlide(View arg0, float arg1) {
            // TODO Auto-generated method stub
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case android.R.id.home:
                if(mSlidingPanel.isOpen()){
                    mSlidingPanel.closePane();
                }
                else{
                    mSlidingPanel.openPane();
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void listViewRSSRecuest(int caso){
        String url = "";
        switch(caso){
            case 1:
                // Videojuegos
                url = "http://www.anaitgames.com/feed/rss";
                break;
            case 2:
                // Cine
                url = "http://rss.sensacine.com/actualidad/cine.xml";
                break;
            case 3:
                // Música
                url = "http://elpais.com/tag/rss/musica/a/";
                break;
            case 4:
                // Política
                url = "http://www.jornada.unam.mx/rss/politica.xml";
                break;
            default:
                url = "http://www.anaitgames.com/feed/rss";
                break;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, null, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                try{
                    XmlToJson xmlToJson = new XmlToJson.Builder(res).build();
                    JSONObject objetoJson = xmlToJson.toJson();
                    Log.d("JSON", objetoJson.toString());
                    JSONArray postsJson = objetoJson.getJSONObject("rss").getJSONObject("channel").getJSONArray("item");
                    Log.d("ObteniendoItem", postsJson.toString());
                    Post[] posts = new Post[postsJson.length()];
                    for(int i=0; i<postsJson.length(); i++){
                        JSONObject posti = postsJson.getJSONObject(i);
                        String titulo = posti.getString("title");
                        Log.d("Titulo", titulo);
                        String autor;
                        if(posti.has("dc:creator"))
                            autor = posti.getString("dc:creator");
                        else  if(posti.has("category"))
                            autor = posti.getString("category");
                        else
                            autor = "Anonymous";
                        String horaPublicacion = posti.getString("pubDate");
                        String descripcion;
                        if(posti.has("description"))
                            descripcion = posti.getString("description");
                        else{
                            descripcion = "";
                        }
                        if(40<titulo.length()){
                            titulo = titulo.substring(0, 30);
                            titulo+="...";
                        }
                        if(80<descripcion.length()){
                            descripcion = descripcion.substring(0, 80);
                            descripcion+="...";
                        }
                        String URLnoticia = posti.getString("link");
                        int imagenFondoPost;
                        int tipoIcono = 0;
                        double random = Math.random();
                        if(Math.random()<0.2){
                            tipoIcono = R.drawable.heart;
                        }else if(random<0.8){
                            tipoIcono = R.drawable.pencil;
                        }else{
                            tipoIcono = R.drawable.star;
                        }
                        random = Math.random();
                        if(random<0.2){
                            imagenFondoPost = R.drawable.imagenpostgenerico;
                        }else if(random<0.4){
                            imagenFondoPost = R.drawable.imagenpostgenerico2;
                        }else if(random<0.6){
                            imagenFondoPost = R.drawable.imagenpostgenerico3;
                        }else if(random<0.8){
                            imagenFondoPost = R.drawable.imagenpostgenerico4;
                        }else{
                            imagenFondoPost = R.drawable.imagenpostgenerico5;
                        }
                        int imagenPerfil;
                        random = Math.random();
                        if(random<0.2){
                            imagenPerfil = R.drawable.androide2;
                        }else if(random<0.4){
                            imagenPerfil = R.drawable.androide3;
                        }else if(random<0.6){
                            imagenPerfil = R.drawable.androide4;
                        }else if(random<0.8){
                            imagenPerfil = R.drawable.androide6;
                        }else{
                            imagenPerfil = R.drawable.androide7;
                        }
                        posts[i] = new Post(tipoIcono, imagenPerfil, autor, horaPublicacion, imagenFondoPost, titulo, descripcion, URLnoticia);
                    }
                    PostArrayAdapter postArrayAdapter = new PostArrayAdapter(contexto, R.layout.listview_post_row, posts);
                    listView.setAdapter(postArrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Post post = (Post) parent.getItemAtPosition(position);
                            String urlPost = post.URLnoticia;
                            Intent intent = new Intent(getApplicationContext(), WebviewActivity.class);
                            intent.putExtra("URL", urlPost);
                            startActivity(intent);
                        }
                    });
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }
        });
    }
}