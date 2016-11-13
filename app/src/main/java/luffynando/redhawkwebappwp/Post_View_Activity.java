package luffynando.redhawkwebappwp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.AsyncTask;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import luffynando.redhawkwebappwp.Herramientas.JsonParser;

/**
 * Creado por luffynando para redhawkdevs.
 */

public class Post_View_Activity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    String urlpost, titulo, postContent, postFecha;
    final Context context = this;
    int theme;
    FrameLayout statusBar;
    Toolbar toolbar;
    JSONObject jsonObject;
    JSONArray categorias;
    int categoryNumber;
    Boolean error= false;
    ProgressDialog progressDialog;
    String[] cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get shared preferences
        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);

        // Select theme saved by user (always before setContentView)
        theme();

        // Set content to the view
        setContentView(R.layout.post_view);

        //Setup Status Bar and Toolbar
        toolbarStatusBar();

        final String id = getIntent().getExtras().getString("id");

        urlpost= "https://public-api.wordpress.com/rest/v1.1/sites/tiopixel.wordpress.com/posts/"+id+"?fields=title,content,categories,date";

        progressDialog = new ProgressDialog(Post_View_Activity.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        configurarContenido();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Dialog dialog = new Dialog(Post_View_Activity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.about_dialog);
            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void theme() {
        sharedPreferences = getSharedPreferences("VALUES", Context.MODE_PRIVATE);
        theme = sharedPreferences.getInt("THEME", 0);
        settingTheme(theme);
    }

    public void toolbarStatusBar() {

        titulo= sharedPreferences.getString("TITLE", "");
        ajustarStatus();
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Get support to the toolbar and change its title
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(titulo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void configurarContenido(){
        TextView textViewDetailTitle = (TextView) findViewById(R.id.textViewDetailTitle);
        ImageView imageViewImageDetail = (ImageView) findViewById(R.id.imageViewImageDetail);
        textViewDetailTitle.setText(sharedPreferences.getString("TITLE", ""));
        if (sharedPreferences.getString("IMAGE", "") != ""){
            if (verificarUrl(sharedPreferences.getString("IMAGE", ""))) {
                Picasso.with(context).load(sharedPreferences.getString("IMAGE", "")).into(imageViewImageDetail);
            }
        }
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
                postFecha= jsonObject.getString("date");
                postContent= jsonObject.getString("content");

            } catch (IOException | JSONException e) {
                e.printStackTrace();
                error = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            if (error) {
                Toast.makeText(Post_View_Activity.this, "Error de conexión", Toast.LENGTH_LONG).show();
            }

            TextView categoria= (TextView) findViewById(R.id.textViewcategoria);
            categoria.setText("Ola k ase?");
            TextView fecha= (TextView) findViewById(R.id.textViewDetailPrice);
            fecha.setText(postFecha);
            WebView webViewDetail = (WebView) findViewById(R.id.webViewDetail);
            webViewDetail.loadDataWithBaseURL(null, "<style>img{display: inline;height: auto;max-width: 100%;}</style>" +postContent, "text/html", "UTF-8", null);
            progressDialog.dismiss();
        }
    }

    public void ajustarStatus(){
        if (Build.VERSION.SDK_INT >=19) {
            FrameLayout status = (FrameLayout) findViewById(R.id.statusBar);
            status.setVisibility(View.GONE);
        }
    }


    public void settingTheme(int theme) {
        switch (theme) {
            case 1:
                setTheme(R.style.AppTheme);
                break;
            case 2:
                setTheme(R.style.AppTheme2);
                break;
            case 3:
                setTheme(R.style.AppTheme3);
                break;
            case 4:
                setTheme(R.style.AppTheme4);
                break;
            case 5:
                setTheme(R.style.AppTheme5);
                break;
            case 6:
                setTheme(R.style.AppTheme6);
                break;
            case 7:
                setTheme(R.style.AppTheme7);
                break;
            case 8:
                setTheme(R.style.AppTheme8);
                break;
            case 9:
                setTheme(R.style.AppTheme9);
                break;
            case 10:
                setTheme(R.style.AppTheme10);
                break;
            default:
                setTheme(R.style.AppTheme);
                break;
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
