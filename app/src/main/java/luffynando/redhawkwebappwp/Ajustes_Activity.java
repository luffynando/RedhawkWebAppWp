package luffynando.redhawkwebappwp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import luffynando.redhawkwebappwp.Dialogs.ColorChooserDialog;
/**
 * Created by luffynando para redhawkdevs.
 * Clase encargada de realizar cada cosa en el layout settings
 */

public class Ajustes_Activity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Intent intent;
    int theme, scrollPositionX = 0, scrollPositionY = -100;
    FrameLayout frameLayoutSwitch, frameLayoutCache, frameLayoutFecha;
    RelativeLayout relativeLayoutChooseTheme;
    Boolean homeButton = false, themeChanged;
    SwitchCompat switchCompat, switchFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Selecciona el tema guardado por el usuario
        theme();

        // Configura esta clase para utilizar el layout creado para settings
        setContentView(R.layout.activity_settings);

        //Configura el status bar y el toolbar -- Implementacion desactiva por bugs menores
        //toolbarStatusBar();

        // Declaramos en esta parte los botones para ajustes.
        settingsButtons();

        // Pequelo arreglo speed/download para la navegacion drawer y regrese a la main activity.
        fixBooleanDownload();

        // Checa si el tema fue cambiado para ajustar en el main activity y demas activitys
        themeChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frameLayoutSwitch:
                if (switchCompat.isChecked()){
                    switchCompat.setChecked(false);
                    sharedPreferences = getSharedPreferences("VALUES", Context.MODE_PRIVATE);
                    editor.putBoolean("TitulosNav", false);
                    editor.apply();
                }else{
                    switchCompat.setChecked(true);
                    sharedPreferences = getSharedPreferences("VALUES", Context.MODE_PRIVATE);
                    editor.putBoolean("TitulosNav", true);
                    editor.apply();
                }
                break;
            case R.id.relativeLayoutChooseTheme:
                FragmentManager fragmentManager = getSupportFragmentManager();
                ColorChooserDialog dialog = new ColorChooserDialog();
                dialog.show(fragmentManager, "fragment_color_chooser");
                break;
            case R.id.frameLayoutCache:
                sharedPreferences.edit().clear().apply();
                CacheApp.getInstance().clearApplicationData();
                intent = new Intent(this, Ajustes_Activity.class);
                startActivity(intent);
                break;
            case R.id.frameLayoutSwitchMFecha:
                if (switchFecha.isChecked()){
                    switchFecha.setChecked(false);
                    sharedPreferences = getSharedPreferences("VALUES", Context.MODE_PRIVATE);
                    editor.putBoolean("PostFechaT", false);
                    editor.apply();
                }else{
                    switchFecha.setChecked(true);
                    sharedPreferences = getSharedPreferences("VALUES", Context.MODE_PRIVATE);
                    editor.putBoolean("PostFechaT", true);
                    editor.apply();
                }
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item_post clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Dialog dialog = new Dialog(Ajustes_Activity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.about_dialog);
            dialog.show();
            return true;
        }
        if (id == android.R.id.home) {
            if (!homeButton) {
                NavUtils.navigateUpFromSameTask(Ajustes_Activity.this);
            }
            if (homeButton) {
                if (!themeChanged) {
                    editor = sharedPreferences.edit();
                    editor.putBoolean("DOWNLOAD", false);
                    editor.apply();
                }
                intent = new Intent(Ajustes_Activity.this, MainActivity.class);
                startActivity(intent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(Ajustes_Activity.this, MainActivity.class);
        startActivity(intent);
    }

    public void theme() {
        sharedPreferences = getSharedPreferences("VALUES", Context.MODE_PRIVATE);
        theme = sharedPreferences.getInt("THEME", 0);
        settingTheme(theme);
    }

    private void themeChanged() {
        themeChanged = sharedPreferences.getBoolean("THEMECHANGED",false);
        homeButton = true;
    }

    private void settingsButtons() {
        relativeLayoutChooseTheme = (RelativeLayout) findViewById(R.id.relativeLayoutChooseTheme);
        frameLayoutSwitch = (FrameLayout) findViewById(R.id.frameLayoutSwitch);
        switchCompat = (SwitchCompat) findViewById(R.id.switchWidget);
        switchFecha= (SwitchCompat) findViewById(R.id.switchFecha);
        frameLayoutCache= (FrameLayout) findViewById(R.id.frameLayoutCache);
        frameLayoutFecha= (FrameLayout) findViewById(R.id.frameLayoutSwitchMFecha);
        switchCompat.setChecked(sharedPreferences.getBoolean("TitulosNav", true));
        switchFecha.setChecked(sharedPreferences.getBoolean("PostFechaT", true));
        frameLayoutSwitch.setOnClickListener(this);
        frameLayoutCache.setOnClickListener(this);
        frameLayoutFecha.setOnClickListener(this);
        relativeLayoutChooseTheme.setOnClickListener(this);
    }

    public void toolbarStatusBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void fixBooleanDownload() {

        // Fix download boolean value
        editor = sharedPreferences.edit();
        editor.putBoolean("DOWNLOAD", true);
        editor.apply();
    }

    public void setThemeFragment(int theme) {
        switch (theme) {
            case 1:
                editor = sharedPreferences.edit();
                editor.putInt("THEME", 1).apply();
                break;
            case 2:
                editor = sharedPreferences.edit();
                editor.putInt("THEME", 2).apply();
                break;
            case 3:
                editor = sharedPreferences.edit();
                editor.putInt("THEME", 3).apply();
                break;
            case 4:
                editor = sharedPreferences.edit();
                editor.putInt("THEME", 4).apply();
                break;
            case 5:
                editor = sharedPreferences.edit();
                editor.putInt("THEME", 5).apply();
                break;
            case 6:
                editor = sharedPreferences.edit();
                editor.putInt("THEME", 6).apply();
                break;
            case 7:
                editor = sharedPreferences.edit();
                editor.putInt("THEME", 7).apply();
                break;
            case 8:
                editor = sharedPreferences.edit();
                editor.putInt("THEME", 8).apply();
                break;
            case 9:
                editor = sharedPreferences.edit();
                editor.putInt("THEME", 9).apply();
                break;
            case 10:
                editor = sharedPreferences.edit();
                editor.putInt("THEME", 10).apply();
                break;
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


}
