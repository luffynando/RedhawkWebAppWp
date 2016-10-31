package luffynando.redhawkwebappwp.Fragments;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import luffynando.redhawkwebappwp.Herramientas.JsonParser;
import luffynando.redhawkwebappwp.RecyclerView.RecyclerViewClases.Posts;
import luffynando.redhawkwebappwp.R;
import luffynando.redhawkwebappwp.RecyclerView.RecyclerViewClases.RecyclerViewAdaptador.AdaptadorPrincipal;
import luffynando.redhawkwebappwp.RecyclerView.RecyclerViewClases.RecyclerViewDecoraciones.DividerItemDecoration;

/**
 * Created by luffynando on 30/10/2016.
 */

public class FragmentPrincipal extends Fragment {
    String urlpost = "https://public-api.wordpress.com/rest/v1.1/sites/tiopixel.wordpress.com/posts/?number=10&fields=ID,title,content,date,featured_image";
    JSONObject jsonObject;
    JSONArray jsonArray;
    SwipeRefreshLayout swipeRefreshLayout;
    int postNumber;
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    String[] postTitle, postFecha, postImage, postFeatured, postid, postExcerpt;
    Boolean error= false;
    ArrayList<Posts> post;
    View view;
    int recyclerViewPaddingTop;
    TypedValue typedValueToolbarHeight = new TypedValue();
    Toolbar toolbar;
    FrameLayout statusBar;
    int orientation;
    Boolean reverseLayout;
    AdaptadorPrincipal adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_principal, container, false);

        sharedPreferences = getActivity().getSharedPreferences("VALUES", Context.MODE_PRIVATE);

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        statusBar = (FrameLayout) getActivity().findViewById(R.id.statusBar);


        // Configurando la alimentacion de la clase principal
        recyclerViewPrincipal(view);

        // Configurando el swipe para el momento de dar refresh
        swipeToRefresh(view);
        return view;
    }

    private void recyclerViewPrincipal(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewDevelop);

        // Divider
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(android.R.drawable.divider_horizontal_bright)));

        // improve performance if you know that changes in content
        // do not change the size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        if (sharedPreferences.getBoolean("checkeditem1", true)) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        if (sharedPreferences.getBoolean("checkeditem2", false)) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        if (sharedPreferences.getBoolean("checkeditem3", false)) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        if (sharedPreferences.getBoolean("checkeditem4", false)) {
            orientation = GridLayoutManager.VERTICAL;
            reverseLayout = false;
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2, orientation, reverseLayout);
            recyclerView.setLayoutManager(layoutManager);
        }
        if (sharedPreferences.getBoolean("checkeditem5", false)) {
            orientation = GridLayoutManager.VERTICAL;
            reverseLayout = false;
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2, orientation, reverseLayout);
            recyclerView.setLayoutManager(layoutManager);
        }
        if (sharedPreferences.getBoolean("checkeditem6", false)) {
            orientation = GridLayoutManager.VERTICAL;
            reverseLayout = false;
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2, orientation, reverseLayout);
            recyclerView.setLayoutManager(layoutManager);
        }

        /*if (sharedPreferences.getBoolean("checked1", true) != false) {
            urlPost = "http://redhawkdevs.com/?json=get_category_posts"+fixestest+"&order=DES&count=999";
        } else {
            if (sharedPreferences.getBoolean("checked2", false) != false) {
                urlPost = "http://redhawkdevs.com/?json=get_category_posts"+fixestest+"&order=ASC&count=999";
            } else {
                urlPost = "http://redhawkdevs.com/?json=get_category_posts"+fixestest+"&order_by=title&count=999";
            }
        }*/

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setPadding(0, recyclerViewPaddingTop, 0, 0);

        new AsyncTaskNewsParseJson().execute(urlpost);

    }

    public class AsyncTaskNewsParseJson extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
        }

        // Adquiriendo los datos con el plugin, el formato recibido es de tipo Json, asi que tenemos que pasarlo a string para poder usarlo.
        @Override
        protected String doInBackground(String... url) {

            urlpost = url[0];
            try {
                jsonObject= JsonParser.readJsonFromUrl(urlpost);
                postNumber = jsonObject.getJSONArray("posts").length();
                jsonArray = jsonObject.getJSONArray("posts");
                postTitle = new String[postNumber];
                postid = new String[postNumber];
                postExcerpt= new String[postNumber];
                postFecha= new String[postNumber];
                postImage= new String[postNumber];
                postFecha= new String[postNumber];
                postFeatured= new String[postNumber];
                for (int i = 0; i < postNumber; i++) {
                    postTitle[i] = Html.fromHtml(jsonObject.getJSONArray("posts").getJSONObject(i).getString("title")).toString();
                    postExcerpt[i] = Html.fromHtml(jsonObject.getJSONArray("posts").getJSONObject(i).getString("content")).toString();
                    postid[i]=  Html.fromHtml(jsonObject.getJSONArray("posts").getJSONObject(i).getString("ID")).toString();
                    postFecha[i]= jsonObject.getJSONArray("posts").getJSONObject(i).getString("date");
                    postImage[i]= Html.fromHtml(jsonObject.getJSONArray("posts").getJSONObject(i).getString("featured_image")).toString();
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                postTitle = new String[0];
                error = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            post = new ArrayList<>();

            //Datos usados en el listView
            if (postTitle.length != 0) {
                for (int i = 0; i < postNumber; i++) {
                    post.add(new Posts(postTitle[i], postImage[i], postFecha[i], postid[i], postExcerpt[i]));
                }
            }
            if (error) {
                Toast.makeText(getActivity(), "Error de conexión", Toast.LENGTH_LONG).show();
            }

            //Creamos nuestro adaptador modificado

            adapter= new AdaptadorPrincipal(getActivity(), post);

            // Creamos el recyclerView
            recyclerView.setAdapter(adapter);

            swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
            swipeRefreshLayout.setRefreshing(false);

            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
        }
    }


    private void swipeToRefresh(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        int start = convertToPx(0), end = recyclerViewPaddingTop + convertToPx(16);
        swipeRefreshLayout.setProgressViewOffset(true, start, end);
        TypedValue typedValueColorPrimary = new TypedValue();
        TypedValue typedValueColorAccent = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.colorPrimary, typedValueColorPrimary, true);
        getActivity().getTheme().resolveAttribute(R.attr.colorAccent, typedValueColorAccent, true);
        final int colorPrimary = typedValueColorPrimary.data, colorAccent = typedValueColorAccent.data;
        swipeRefreshLayout.setColorSchemeColors(colorPrimary, colorAccent);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AsyncTaskNewsParseJson().execute(urlpost);
            }
        });
    }

    public int convertToPx(int dp) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (dp * scale + 0.5f);
    }

    public void animationTranslationY(View view, int duration, int interpolator, int translationY) {
        Animator slideInAnimation = ObjectAnimator.ofFloat(view, "translationY", translationY);
        slideInAnimation.setDuration(duration);
        slideInAnimation.setInterpolator(new AccelerateInterpolator(interpolator));
        slideInAnimation.start();
    }
}
