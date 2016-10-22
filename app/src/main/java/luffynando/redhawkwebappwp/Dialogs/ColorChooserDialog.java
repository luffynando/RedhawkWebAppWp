package luffynando.redhawkwebappwp.Dialogs;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import luffynando.redhawkwebappwp.Ajustes_Activity;
import luffynando.redhawkwebappwp.R;

public class ColorChooserDialog extends DialogFragment implements View.OnClickListener {
    CardView cardView1, cardView2, cardView3, cardView4, cardView5, cardView6, cardView7, cardView8, cardView9, cardView10;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10;
    Button buttonDisagree, buttonAgree;
    View view;
    int currentTheme;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ActivityOptions options;
    Intent intent;
    Boolean themeChanged = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Save current theme to use when user press dismiss inside dialog
        sharedPreferences = getActivity().getSharedPreferences("VALUES", Context.MODE_PRIVATE);
        currentTheme = sharedPreferences.getInt("THEME", 0);

        //inflate theme_dialog.xml
        view = inflater.inflate(R.layout.theme_dialog, container);

        // remove title (already defined in theme_dialog.xml)
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Declare buttons and onClick methods
        dialogButtons();

        setUltimoThemeBooton(currentTheme);

        return view;
    }

    private void dialogButtons() {
        cardView1 = (CardView) view.findViewById(R.id.card_view1);
        cardView2 = (CardView) view.findViewById(R.id.card_view2);
        cardView3 = (CardView) view.findViewById(R.id.card_view3);
        cardView4 = (CardView) view.findViewById(R.id.card_view4);
        cardView5 = (CardView) view.findViewById(R.id.card_view5);
        cardView6 = (CardView) view.findViewById(R.id.card_view6);
        cardView7 = (CardView) view.findViewById(R.id.card_view7);
        cardView8 = (CardView) view.findViewById(R.id.card_view8);
        cardView9 = (CardView) view.findViewById(R.id.card_view9);
        cardView10 = (CardView) view.findViewById(R.id.card_view10);
        textView1= (TextView) view.findViewById(R.id.textView1);
        textView2= (TextView) view.findViewById(R.id.textView2);
        textView3= (TextView) view.findViewById(R.id.textView3);
        textView4= (TextView) view.findViewById(R.id.textView4);
        textView5= (TextView) view.findViewById(R.id.textView5);
        textView6= (TextView) view.findViewById(R.id.textView6);
        textView7= (TextView) view.findViewById(R.id.textView7);
        textView8= (TextView) view.findViewById(R.id.textView8);
        textView9= (TextView) view.findViewById(R.id.textView9);
        textView10= (TextView) view.findViewById(R.id.textView10);

        buttonDisagree = (Button) view.findViewById(R.id.buttonDisagree);
        buttonAgree = (Button) view.findViewById(R.id.buttonAgree);

        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);
        cardView4.setOnClickListener(this);
        cardView5.setOnClickListener(this);
        cardView6.setOnClickListener(this);
        cardView7.setOnClickListener(this);
        cardView8.setOnClickListener(this);
        cardView9.setOnClickListener(this);
        cardView10.setOnClickListener(this);
        buttonDisagree.setOnClickListener(this);
        buttonAgree.setOnClickListener(this);
    }

    public void regresarBotones(){
        textView1.setTypeface(null, Typeface.NORMAL);
        textView2.setTypeface(null, Typeface.NORMAL);
        textView3.setTypeface(null, Typeface.NORMAL);
        textView4.setTypeface(null, Typeface.NORMAL);
        textView5.setTypeface(null, Typeface.NORMAL);
        textView6.setTypeface(null, Typeface.NORMAL);
        textView7.setTypeface(null, Typeface.NORMAL);
        textView8.setTypeface(null, Typeface.NORMAL);
        textView9.setTypeface(null, Typeface.NORMAL);
        textView10.setTypeface(null, Typeface.NORMAL);

        textView1.setTextSize(14);
        textView2.setTextSize(14);
        textView3.setTextSize(14);
        textView4.setTextSize(14);
        textView5.setTextSize(14);
        textView6.setTextSize(14);
        textView7.setTextSize(14);
        textView8.setTextSize(14);
        textView9.setTextSize(14);
        textView10.setTextSize(14);
    }

    private void setUltimoThemeBooton(int theme) {
        switch (theme){
            case 1:
                regresarBotones();
                textView1.setTypeface(null, Typeface.BOLD);
                textView1.setTextSize(16);
                break;
            case 2:
                regresarBotones();
                textView2.setTypeface(null, Typeface.BOLD);
                textView2.setTextSize(16);
                break;
            case 3:
                regresarBotones();
                textView3.setTypeface(null, Typeface.BOLD);
                textView3.setTextSize(16);
                break;
            case 4:
                regresarBotones();
                textView4.setTypeface(null, Typeface.BOLD);
                textView4.setTextSize(16);
                break;
            case 5:
                regresarBotones();
                textView5.setTypeface(null, Typeface.BOLD);
                textView5.setTextSize(16);
                break;
            case 6:
                regresarBotones();
                textView6.setTypeface(null, Typeface.BOLD);
                textView6.setTextSize(16);
                break;
            case 7:
                regresarBotones();
                textView7.setTypeface(null, Typeface.BOLD);
                textView7.setTextSize(16);
                break;
            case 8:
                regresarBotones();
                textView8.setTypeface(null, Typeface.BOLD);
                textView8.setTextSize(16);
                break;
            case 9:
                regresarBotones();
                textView9.setTypeface(null, Typeface.BOLD);
                textView9.setTextSize(16);
                break;
            case 10:
                regresarBotones();
                textView10.setTypeface(null, Typeface.BOLD);
                textView10.setTextSize(16);
                break;
            default:
                regresarBotones();
                textView1.setTypeface(null, Typeface.BOLD);
                textView1.setTextSize(16);
                break;

        }
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_view1:
                regresarBotones();
                textView1.setTypeface(null, Typeface.BOLD);
                textView1.setTextSize(16);
                sharedPreferences.edit().putBoolean("THEMECHANGED", true).apply();
                ((Ajustes_Activity) getActivity()).setThemeFragment(1);
                break;
            case R.id.card_view2:
                regresarBotones();
                textView2.setTypeface(null, Typeface.BOLD);
                textView2.setTextSize(16);
                sharedPreferences.edit().putBoolean("THEMECHANGED", true).apply();
                ((Ajustes_Activity) getActivity()).setThemeFragment(2);
                break;
            case R.id.card_view3:
                regresarBotones();
                textView3.setTypeface(null, Typeface.BOLD);
                textView3.setTextSize(16);
                sharedPreferences.edit().putBoolean("THEMECHANGED", true).apply();
                ((Ajustes_Activity) getActivity()).setThemeFragment(3);
                break;
            case R.id.card_view4:
                regresarBotones();
                textView4.setTypeface(null, Typeface.BOLD);
                textView4.setTextSize(16);
                sharedPreferences.edit().putBoolean("THEMECHANGED", true).apply();
                ((Ajustes_Activity) getActivity()).setThemeFragment(4);
                break;
            case R.id.card_view5:
                regresarBotones();
                textView5.setTypeface(null, Typeface.BOLD);
                textView5.setTextSize(16);
                sharedPreferences.edit().putBoolean("THEMECHANGED", true).apply();
                ((Ajustes_Activity) getActivity()).setThemeFragment(5);
                break;
            case R.id.card_view6:
                regresarBotones();
                textView6.setTypeface(null, Typeface.BOLD);
                textView6.setTextSize(16);
                sharedPreferences.edit().putBoolean("THEMECHANGED", true).apply();
                ((Ajustes_Activity) getActivity()).setThemeFragment(6);
                break;
            case R.id.card_view7:
                regresarBotones();
                textView7.setTypeface(null, Typeface.BOLD);
                textView7.setTextSize(16);
                sharedPreferences.edit().putBoolean("THEMECHANGED", true).apply();
                ((Ajustes_Activity) getActivity()).setThemeFragment(7);
                break;
            case R.id.card_view8:
                regresarBotones();
                textView8.setTypeface(null, Typeface.BOLD);
                textView8.setTextSize(16);
                sharedPreferences.edit().putBoolean("THEMECHANGED", true).apply();
                ((Ajustes_Activity) getActivity()).setThemeFragment(8);
                break;
            case R.id.card_view9:
                regresarBotones();
                textView9.setTypeface(null, Typeface.BOLD);
                textView9.setTextSize(16);
                sharedPreferences.edit().putBoolean("THEMECHANGED", true).apply();
                ((Ajustes_Activity) getActivity()).setThemeFragment(9);
                break;
            case R.id.card_view10:
                regresarBotones();
                textView10.setTypeface(null, Typeface.BOLD);
                textView10.setTextSize(16);
                sharedPreferences.edit().putBoolean("THEMECHANGED", true).apply();
                ((Ajustes_Activity) getActivity()).setThemeFragment(10);
                break;
            case R.id.buttonDisagree:
                sharedPreferences.edit().putBoolean("THEMECHANGED", false).apply();
                ((Ajustes_Activity) getActivity()).setThemeFragment(currentTheme);
                getDialog().dismiss();
                break;
            case R.id.buttonAgree:
                sharedPreferences.edit().putBoolean("THEMECHANGED", true).apply();
                intent = new Intent(getActivity(), Ajustes_Activity.class);
                startActivity(intent);
                break;
        }
    }
}