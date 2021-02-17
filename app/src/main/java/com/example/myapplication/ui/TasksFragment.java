package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.Main8Activity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.helper.Constants;
import com.example.myapplication.helper.MySingleton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.refactor.lib.colordialog.PromptDialog;

public class TasksFragment extends Fragment {
    EditText Taux_rendement,Fraisadministration,Chargements_acquisition,Taux_bourse,taux_rachat;
    private Button save;
    View rootView;
    MaterialButton loginBTN;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_tasks, container, false);
        Taux_rendement = (EditText)rootView.findViewById(R.id.Taux_rendement);
        Fraisadministration = (EditText)rootView.findViewById(R.id.Fraisadministration);
        Chargements_acquisition = (EditText)rootView.findViewById(R.id.Chargements_acquisition);
        Taux_bourse = (EditText)rootView.findViewById(R.id.Taux_bourse);
        taux_rachat = (EditText)rootView.findViewById(R.id.taux_rachat);
        save=(Button) rootView.findViewById(R.id.btn_save);

        final ProgressDialog progress;
        progress = new ProgressDialog(getActivity());
        progress.setTitle("S'il vous plaît, attendez");
        progress.setMessage("Chargement en cours...");
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIcon(R.drawable.ic_schedule_grey_500_24dp);
        progress.show();
        JSONObject params = new JSONObject();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, Constants.get_settigns, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Integer etat = response.getInt("etat");
                            String v1 = response.getString("v1");
                            String v2 = response.getString("v2");
                            String v3 = response.getString("v3");
                            String v4 = response.getString("v4");
                            String v5 = response.getString("v5");
                            if (etat > 0){
                                progress.hide();
                                Taux_rendement.setText(v1);
                                Fraisadministration.setText(v2);
                                Chargements_acquisition.setText(v3);
                                Taux_bourse.setText(v4);
                                taux_rachat.setText(v5);
                                DynamicToast.makeSuccess(getActivity() ,"Chargement effectué avec succès",
                                        999999).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        progress.hide();
                        androidx.appcompat.app.AlertDialog.Builder dialog =new androidx.appcompat.app.AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            dialog.setTitle("Erreur de connexion !");
                            dialog.setMessage("Veuillez vérifier votre connexion internet ");
                            dialog.setIcon(R.drawable.wifi);
                        }else{
                            dialog.setTitle("Erreur !");
                            dialog.setMessage("Veuillez vérifier les informations saisies");
                            dialog.setIcon(R.drawable.ic_info_white_24dp);
                        }
                        dialog.setCancelable(true);
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("OK",
                                new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int id){
                                    }
                                });
                        androidx.appcompat.app.AlertDialog alert = dialog.create();
                        alert.setOnShowListener(new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface dialog) {
                                Button positiveButton =((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,0.5f);
                                positiveButton.setLayoutParams(params);
                            }
                        });
                        alert.show();
                    }
                });
        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);



















        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Taux_rendement.getText().toString().trim().equals("")||Fraisadministration.getText().toString().trim().equals("")
                        ||Chargements_acquisition.getText().toString().trim().equals("")||Taux_bourse.getText().toString().trim().equals("")
                        ||taux_rachat.getText().toString().trim().equals("")){

                    androidx.appcompat.app.AlertDialog.Builder dialog =new androidx.appcompat.app.AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
                    dialog.setTitle("Erreur !");
                    dialog.setIcon(R.drawable.ic_info_white_24dp);
                    dialog.setCancelable(true);
                    if (Taux_rendement.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) rootView.findViewById(R.id.s1);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ taux de rendement espéré est vide");
                    }
                    else if (Fraisadministration.getText().toString().trim().equals("")){
                        TextInputLayout b2 = (TextInputLayout) rootView.findViewById(R.id.s2);
                        b2.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ frais d'administration est vide");
                    }
                    else if (Chargements_acquisition.getText().toString().trim().equals("")){
                        TextInputLayout b3 = (TextInputLayout) rootView.findViewById(R.id.s3);
                        b3.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ chargements d'acquisition est vide");
                    }
                    else if (Taux_bourse.getText().toString().trim().equals("")){
                        TextInputLayout b4 = (TextInputLayout) rootView.findViewById(R.id.s4);
                        b4.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ taux de frais de la bourse est vide");
                    }
                    else if (taux_rachat.getText().toString().trim().equals("")){
                        TextInputLayout b5 = (TextInputLayout) rootView.findViewById(R.id.s5);
                        b5.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ taux de rachat est vide");
                    }
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int id){
                                }
                            });
                    androidx.appcompat.app.AlertDialog alert = dialog.create();
                    alert.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface dialog) {
                            Button positiveButton =((androidx.appcompat.app.AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,0.5f);
                            positiveButton.setLayoutParams(params);
                        }
                    });
                    alert.show();
                }
                else{
                    String ch1 = Taux_rendement.getText().toString();
                    String ch2 = Fraisadministration.getText().toString();
                    String ch3 = Chargements_acquisition.getText().toString();
                    String ch4 = Fraisadministration.getText().toString();
                    String ch5 = taux_rachat.getText().toString();

                    final ProgressDialog progress;
                    progress = new ProgressDialog(getActivity());
                    progress.setTitle("S'il vous plaît, attendez");
                    progress.setMessage("Enrigstrement en cours...");
                    progress.setCancelable(false);
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setIcon(R.drawable.ic_schedule_grey_500_24dp);
                    progress.show();
                    JSONObject params = new JSONObject();
                    try {
                        params.put("v1", ch1);
                        params.put("v2", ch2);
                        params.put("v3", ch3);
                        params.put("v4", ch4);
                        params.put("v5", ch5);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.POST, Constants.update_settigns, params, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        Integer etat = response.getInt("etat");
                                        if (etat > 0){
                                            progress.hide();
                                            DynamicToast.makeSuccess(getActivity() ,"Modification effectué avec succès",
                                                    999999).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // TODO: Handle error
                                    progress.hide();
                                    androidx.appcompat.app.AlertDialog.Builder dialog =new androidx.appcompat.app.AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
                                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                        dialog.setTitle("Erreur de connexion !");
                                        dialog.setMessage("Veuillez vérifier votre connexion internet ");
                                        dialog.setIcon(R.drawable.wifi);
                                    }else{
                                        dialog.setTitle("Erreur !");
                                        dialog.setMessage("Veuillez vérifier les informations saisies");
                                        dialog.setIcon(R.drawable.ic_info_white_24dp);
                                    }
                                    dialog.setCancelable(true);
                                    dialog.setCancelable(false);
                                    dialog.setPositiveButton("OK",
                                            new DialogInterface.OnClickListener(){
                                                public void onClick(DialogInterface dialog, int id){
                                                }
                                            });
                                    androidx.appcompat.app.AlertDialog alert = dialog.create();
                                    alert.setOnShowListener(new DialogInterface.OnShowListener() {
                                        @Override
                                        public void onShow(DialogInterface dialog) {
                                            Button positiveButton =((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,0.5f);
                                            positiveButton.setLayoutParams(params);
                                        }
                                    });
                                    alert.show();
                                }
                            });
                    MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
                }
            }
        });
        return rootView;
    }
}
