package luffynando.redhawkwebappwp.Dialogs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import luffynando.redhawkwebappwp.Fragments.FragmentPrincipal;
import luffynando.redhawkwebappwp.MainActivity;
import luffynando.redhawkwebappwp.R;

/**
 * Created by f-fil on 30/10/2016.
 */

public class CambiaAspectoChooserDialog extends DialogFragment implements View.OnClickListener{
    private CheckedTextView itemButton1, itemButton2, itemButton3, itemButton4, itemButton5, itemButton6;
    private FrameLayout frameLayoutitemButton1, frameLayoutitemButton2, frameLayoutitemButton3, frameLayoutitemButton4, frameLayoutitemButton5, frameLayoutitemButton6;
    View view;
    Intent intent;
    SharedPreferences ordenPreferencias;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.item_view_layout, container);

        // remove title (already defined in theme_dialog.xml)
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Declare buttons and onClick methods
        dialogButtons();

        checarestado();

        return view;
    }

    public void checarestado() {
        ordenPreferencias = getActivity().getSharedPreferences("VALUES", Context.MODE_PRIVATE);
        itemButton1.setChecked(ordenPreferencias.getBoolean("checkeditem1",true));
        itemButton2.setChecked(ordenPreferencias.getBoolean("checkeditem2",false));
        itemButton3.setChecked(ordenPreferencias.getBoolean("checkeditem3",false));
        itemButton4.setChecked(ordenPreferencias.getBoolean("checkeditem4",false));
        itemButton5.setChecked(ordenPreferencias.getBoolean("checkeditem5",false));
        itemButton6.setChecked(ordenPreferencias.getBoolean("checkeditem6",false));
    }

    public void guardarPreferencias(){
        ordenPreferencias = getActivity().getSharedPreferences("VALUES", Context.MODE_PRIVATE);
        editor= ordenPreferencias.edit();
        boolean valor=itemButton1.isChecked();
        editor.putBoolean("checkeditem1", valor);
        valor=itemButton2.isChecked();
        editor.putBoolean("checkeditem2", valor);
        valor=itemButton3.isChecked();
        editor.putBoolean("checkeditem3", valor);
        valor=itemButton4.isChecked();
        editor.putBoolean("checkeditem4", valor);
        valor=itemButton5.isChecked();
        editor.putBoolean("checkeditem5", valor);
        valor=itemButton6.isChecked();
        editor.putBoolean("checkeditem6", valor);
        editor.apply();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        fragmentManager = (getActivity()).getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        FragmentPrincipal fragmentDesign = new FragmentPrincipal();
        fragmentTransaction.replace(R.id.fragment, fragmentDesign);
        fragmentTransaction.commit();
    }

    public void dialogButtons(){
        itemButton1= (CheckedTextView) view.findViewById(R.id.itemButton1);
        itemButton2= (CheckedTextView) view.findViewById(R.id.itemButton2);
        itemButton3= (CheckedTextView) view.findViewById(R.id.itemButton3);
        itemButton4= (CheckedTextView) view.findViewById(R.id.itemButton4);
        itemButton5= (CheckedTextView) view.findViewById(R.id.itemButton5);
        itemButton6= (CheckedTextView) view.findViewById(R.id.itemButton6);

        frameLayoutitemButton1= (FrameLayout) view.findViewById(R.id.frameLayoutitemButton1);
        frameLayoutitemButton2= (FrameLayout) view.findViewById(R.id.frameLayoutitemButton2);
        frameLayoutitemButton3= (FrameLayout) view.findViewById(R.id.frameLayoutitemButton3);
        frameLayoutitemButton4= (FrameLayout) view.findViewById(R.id.frameLayoutitemButton4);
        frameLayoutitemButton5= (FrameLayout) view.findViewById(R.id.frameLayoutitemButton5);
        frameLayoutitemButton6= (FrameLayout) view.findViewById(R.id.frameLayoutitemButton6);

        frameLayoutitemButton1.setOnClickListener(this);
        frameLayoutitemButton2.setOnClickListener(this);
        frameLayoutitemButton3.setOnClickListener(this);
        frameLayoutitemButton4.setOnClickListener(this);
        frameLayoutitemButton5.setOnClickListener(this);
        frameLayoutitemButton6.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.frameLayoutitemButton1:
                itemButton1.setChecked(true);
                itemButton2.setChecked(false);
                itemButton3.setChecked(false);
                itemButton4.setChecked(false);
                itemButton5.setChecked(false);
                itemButton6.setChecked(false);
                guardarPreferencias();
                dismiss();
                break;
            case R.id.frameLayoutitemButton2:
                itemButton1.setChecked(false);
                itemButton2.setChecked(true);
                itemButton3.setChecked(false);
                itemButton4.setChecked(false);
                itemButton5.setChecked(false);
                itemButton6.setChecked(false);
                guardarPreferencias();
                dismiss();
                break;
            case R.id.frameLayoutitemButton3:
                itemButton1.setChecked(false);
                itemButton2.setChecked(false);
                itemButton3.setChecked(true);
                itemButton4.setChecked(false);
                itemButton5.setChecked(false);
                itemButton6.setChecked(false);
                guardarPreferencias();
                dismiss();
                break;
            case R.id.frameLayoutitemButton4:
                itemButton1.setChecked(false);
                itemButton2.setChecked(false);
                itemButton3.setChecked(false);
                itemButton4.setChecked(true);
                itemButton5.setChecked(false);
                itemButton6.setChecked(false);
                guardarPreferencias();
                dismiss();
                break;
            case R.id.frameLayoutitemButton5:
                itemButton1.setChecked(false);
                itemButton2.setChecked(false);
                itemButton3.setChecked(false);
                itemButton4.setChecked(false);
                itemButton5.setChecked(true);
                itemButton6.setChecked(false);
                guardarPreferencias();
                dismiss();
                break;
            case R.id.frameLayoutitemButton6:
                itemButton1.setChecked(false);
                itemButton2.setChecked(false);
                itemButton3.setChecked(false);
                itemButton4.setChecked(false);
                itemButton5.setChecked(false);
                itemButton6.setChecked(true);
                guardarPreferencias();
                dismiss();
                break;
        }
    }
}
