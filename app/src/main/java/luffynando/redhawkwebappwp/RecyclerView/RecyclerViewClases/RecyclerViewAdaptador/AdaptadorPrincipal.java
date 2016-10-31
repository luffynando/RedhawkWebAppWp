package luffynando.redhawkwebappwp.RecyclerView.RecyclerViewClases.RecyclerViewAdaptador;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import luffynando.redhawkwebappwp.R;
import luffynando.redhawkwebappwp.RecyclerView.RecyclerViewClases.Posts;

/**
 * Created by luffynando on 30/10/2016.
 */

public class AdaptadorPrincipal extends RecyclerView.Adapter<AdaptadorPrincipal.ViewHolder> {

    private ArrayList<Posts> posts;
    private Context context;
    private SharedPreferences ordenPreferencias;
    private SharedPreferences.Editor editor;

    public AdaptadorPrincipal(Context context, ArrayList<Posts> posts) {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public AdaptadorPrincipal.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ordenPreferencias= context.getSharedPreferences("VALUES", Context.MODE_PRIVATE);
        if (ordenPreferencias.getBoolean("checkeditem1",true)) {
            // Create a new view by inflating the row item xml.
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_v1, parent, false);
            v.setOnClickListener(onClickListener);
            return new ViewHolder(v);
        }
        if (ordenPreferencias.getBoolean("checkeditem2",false)) {
            // Create a new view by inflating the row item xml.
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_v2, parent, false);
            v.setOnClickListener(onClickListener);
            return new ViewHolder(v);
        }
        if (ordenPreferencias.getBoolean("checkeditem3",false)) {
            // Create a new view by inflating the row item xml.
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_v3, parent, false);
            v.setOnClickListener(onClickListener);
            return new ViewHolder(v);
        }
        if (ordenPreferencias.getBoolean("checkeditem4",false)) {
            // Create a new view by inflating the row item xml.
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_v1, parent, false);
            v.setOnClickListener(onClickListener);
            return new ViewHolder(v);
        }
        if (ordenPreferencias.getBoolean("checkeditem5",false)) {
            // Create a new view by inflating the row item xml.
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_v2, parent, false);
            v.setOnClickListener(onClickListener);
            return new ViewHolder(v);
        }
        if (ordenPreferencias.getBoolean("checkeditem6",false)) {
            // Create a new view by inflating the row item xml.
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_v3, parent, false);
            v.setOnClickListener(onClickListener);
            return new ViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        ordenPreferencias= context.getSharedPreferences("VALUES", Context.MODE_PRIVATE);
        final TextView textViewTitle = (TextView) holder.view.findViewById(R.id.textViewItemTitle);
        final TextView textViewContent = (TextView) holder.view.findViewById(R.id.textViewItemContent);
        final ImageView imageViewImage = (ImageView) holder.view.findViewById(R.id.imageViewImage);
        final TextView textViewFecha = (TextView) holder.view.findViewById(R.id.textViewFecha);
        textViewTitle.setText(posts.get(position).getTitle());
        textViewContent.setText(posts.get(position).getContenido());
        if (posts.get(position).getImage() != ""){
            if (verificarUrl(posts.get(position).getImage())) {
                Picasso.with(context).load(posts.get(position).getImage()).into(imageViewImage);
            }
        }
        textViewFecha.setText(posts.get(position).getFecha());
        if (ordenPreferencias.getBoolean("PostFechaT",true)){
            textViewFecha.setVisibility(View.VISIBLE);
        }else{
            textViewFecha.setVisibility(View.GONE);
        }
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(context,"Ok",Toast.LENGTH_SHORT).show();
        }
    };

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return posts.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    private Boolean verificarUrl(String url){
        Boolean ban;
        try{
            URL url2= new URL(url);
            ban=true;
        }catch (MalformedURLException e){
            ban=false;
        }
        return ban;
    }
}
