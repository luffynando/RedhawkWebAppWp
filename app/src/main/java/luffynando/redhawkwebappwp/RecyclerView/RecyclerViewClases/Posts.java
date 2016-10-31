package luffynando.redhawkwebappwp.RecyclerView.RecyclerViewClases;

/**
 * Created by luffynando on 30/10/2016.
 */

public class Posts {
    private String title, image, fecha, contenido, id;

    public Posts(String title, String image, String fecha, String id, String contenido) {
        this.title = title;
        this.image = image;
        this.fecha = fecha;
        this.id = id;
        this.contenido= contenido;
    }
    public String getTitle() {
        return title;
    }


    public String getImage() {
        return image;
    }

    public String getFecha() {
        return fecha;
    }

    public String getId(){
        return id;
    }

    public String getContenido(){
        return contenido;
    }
}