package com.example.fers.journal.journal;

public class Post {
    int tipoIconoPublicacion;
    int imagenUsuario;
    String nombreUsuario;
    String descripcionHoraPublicacion;
    int direccionURLimagen;
    String tituloPublicacion;
    String descripcionPublicacion;
    String URLnoticia;
    int colorCard;

    public Post(){
        super();
    }

    public Post(int tipoIconoPublicacion, int imagenUsuario, String nombreUsuario, String descripcionHoraPublicacion, int direccionURLimagen, String tituloPublicacion, String descripcionPublicacion, String URLnoticia) {
        this.tipoIconoPublicacion = tipoIconoPublicacion;
        this.imagenUsuario = imagenUsuario;
        this.nombreUsuario = nombreUsuario;
        this.descripcionHoraPublicacion = descripcionHoraPublicacion;
        this.direccionURLimagen = direccionURLimagen;
        this.tituloPublicacion = tituloPublicacion;
        this.descripcionPublicacion = descripcionPublicacion;
        this.URLnoticia = URLnoticia;
        switch (tipoIconoPublicacion){
            case R.drawable.pencil:
                colorCard = R.color.colorPencilPost;
                break;
            case R.drawable.heart:
                colorCard = R.color.colorHeartPost;
                break;
            case R.drawable.star:
                colorCard = R.color.colorStarPost;
                 break;
            default:
                colorCard = R.color.colorPencilPost;
                break;
        };
    }
}
