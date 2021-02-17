package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.helper.Constants;
import com.example.myapplication.helper.MySingleton;
import com.google.android.material.textfield.TextInputLayout;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import cn.refactor.lib.colordialog.PromptDialog;

import static com.example.myapplication.R.id.btn_simuler;
import static com.example.myapplication.R.id.btn_simuler_1;
import static com.example.myapplication.R.id.date_naiss;
import static com.example.myapplication.R.id.dernier;
import static com.example.myapplication.R.id.effet;

public class SecondFragment extends Fragment {
    EditText datenaiss_participant,date_effet,date_echance,datenaiss_bene,durée_bourse,bourse,capital_univ,capital_prof;
    DatePickerDialog picker;
    private Button simuler;
    public SecondFragment(){}
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_second,container,false);
        bourse=(EditText) view.findViewById(R.id.Bourse_objectif) ;
        capital_univ=(EditText) view.findViewById(R.id.capital_universitaire) ;
        capital_prof=(EditText) view.findViewById(R.id.capital_professionnel) ;

        durée_bourse =(EditText) view.findViewById(R.id.Durée_service_bourse) ;
        date_effet=(EditText) view.findViewById(R.id.Date_effet);
        date_effet.setInputType(InputType.TYPE_NULL);
        date_effet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(getContext(),R.style.Datepicker,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date_effet.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getWindow().setGravity(Gravity.CENTER);
                picker.show();
            }
        });
        date_echance=(EditText) view.findViewById(R.id.Date_echance);
        date_echance.setInputType(InputType.TYPE_NULL);
        date_echance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(getContext(),R.style.Datepicker,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date_echance.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getWindow().setGravity(Gravity.CENTER);
                picker.show();
            }
        });
        datenaiss_participant =(EditText) view.findViewById(R.id.Date_de_naiss_participant);
        datenaiss_participant.setInputType(InputType.TYPE_NULL);
        datenaiss_participant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(getContext(),R.style.Datepicker,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                datenaiss_participant.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.getWindow().setGravity(Gravity.CENTER);
                picker.show();
            }
        });

        datenaiss_bene =(EditText) view.findViewById(R.id.Date_de_naiss_béneficaire);
        datenaiss_bene.setInputType(InputType.TYPE_NULL);
        datenaiss_bene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(getContext(),R.style.Datepicker,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                datenaiss_bene.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.getWindow().setGravity(Gravity.CENTER);
                picker.show();
            }
        });


        simuler=(Button) view.findViewById(btn_simuler_1);
        simuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bourse.getText().toString().equals("")||capital_prof.getText().toString().equals("")||capital_univ.getText().toString().equals("")||date_echance.getText().toString().equals("")||date_effet.getText().toString().equals("")
                        ||datenaiss_participant.getText().toString().equals("")||datenaiss_bene.getText().toString().equals("")
                        ||durée_bourse.getText().toString().equals(""))
                {

                    AlertDialog.Builder dialog =new AlertDialog.Builder(getContext(),R.style.CustomDialogTheme);
                    dialog.setTitle("Erreur !");
                    dialog.setIcon(R.drawable.ic_info_white_24dp);
                    dialog.setCancelable(true);
                    if (datenaiss_participant.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.c1);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date de naissance de participant est vide");
                    }
                    else if (datenaiss_bene.getText().toString().trim().equals("")){
                        TextInputLayout b2 = (TextInputLayout) view.findViewById(R.id.c2);
                        b2.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date de naissance de bénéficaire est vide");
                    }
                    else if (date_effet.getText().toString().trim().equals("")){
                        TextInputLayout b3 = (TextInputLayout) view.findViewById(R.id.c3);
                        b3.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date d'effet est vide");
                    }
                    else if (date_echance.getText().toString().trim().equals("")){
                        TextInputLayout b4 = (TextInputLayout) view.findViewById(R.id.c4);
                        b4.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date d'échance est vide");
                    }
                    else if (durée_bourse.getText().toString().trim().equals("")){
                        TextInputLayout b5 = (TextInputLayout) view.findViewById(R.id.c5);
                        b5.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ durée de la bourse est vide");
                    }
                    else if (bourse.getText().toString().trim().equals("")){
                        TextInputLayout b5 = (TextInputLayout) view.findViewById(R.id.c6);
                        b5.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ bourse objectif est vide");
                    }
                    else if (capital_univ.getText().toString().trim().equals("")){
                        TextInputLayout b5 = (TextInputLayout) view.findViewById(R.id.c7);
                        b5.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ capital universitaire est vide");
                    }
                    else if (capital_prof.getText().toString().trim().equals("")){
                        TextInputLayout b5 = (TextInputLayout) view.findViewById(R.id.c8);
                        b5.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ capital professionel est vide");
                    }
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int id){
                                }
                            });
                    AlertDialog alert = dialog.create();
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
                else{
                    String ch0 = datenaiss_bene.getText().toString();
                    String ch1 = datenaiss_participant.getText().toString();
                    String ch2 = date_effet.getText().toString();
                    String ch3 = date_echance.getText().toString();
                    DateTime date0, date1,date2,date3 = new DateTime();
                    date0 = DateTime.parse(ch0, DateTimeFormat.forPattern("dd/MM/yyyy"));
                    date1 = DateTime.parse(ch1, DateTimeFormat.forPattern("dd/MM/yyyy"));
                    date2 =DateTime.parse(ch2, DateTimeFormat.forPattern("dd/MM/yyyy"));
                    date3 =DateTime.parse(ch3, DateTimeFormat.forPattern("dd/MM/yyyy"));
                    float x = Days.daysBetween(date0,date2).getDays();
                    float y = Days.daysBetween(date1,date2).getDays();
                    int age = Math.round(y/365) ;

                    int age_bene =Math.round(x/365) ;
                    float y1 = Days.daysBetween(date2,date3).getDays();
                    int durée = Math.round(y1/365) ;

                    final ProgressDialog progress;
                    progress = new ProgressDialog(getContext());
                    progress.setTitle("S'il vous plaît, attendez");
                    progress.setMessage("Simulation en cours...");
                    progress.setCancelable(false);
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setIcon(R.drawable.ic_schedule_grey_500_24dp);
                    progress.show();
                    JSONObject params = new JSONObject();
                    try {
                        int b = Integer.parseInt(durée_bourse.getText().toString());
                        int val = Integer.parseInt(bourse.getText().toString());
                        int val1 = Integer.parseInt(capital_univ.getText().toString());
                        int val2 = Integer.parseInt(capital_prof.getText().toString());
                        params.put("age_x", age);
                        params.put("age_y", age_bene);
                        params.put("e", durée);
                        params.put("bourse", val);
                        params.put("univ", val1);
                        params.put("prof", val2);
                        params.put("b", b);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.POST, Constants.Calcul_ENDPOINT1, params, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        double prime_bourse = response.getDouble("prime_bourse");
                                        double prime_univ = response.getDouble("prime_univ");
                                        double prime_prof = response.getDouble("prime_prof");
                                        double annuelle = response.getDouble("annuelle");
                                        double trim = response.getDouble("trimestrielle");
                                        double semes = response.getDouble("semestrielle");
                                        double mensu = response.getDouble("mensuelle");
                                        if (prime_bourse > 0){
                                            progress.hide();
                                            PromptDialog pDialog = new PromptDialog(getContext());
                                            pDialog.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS);
                                            pDialog.setCancelable(false);
                                            pDialog.getWindow().setLayout(900,900);
                                            if((prime_univ ==0)  && (prime_prof==0)){
                                                pDialog.setAnimationEnable(true).setTitleText("Takaful Imtiez+").
                                                        setContentText("Contribution Totale Annuelle : "+String.valueOf(annuelle)+" DT"
                                                                +"\n"+ "Contribution Mensuelle           : "+String.valueOf(mensu)+" DT"
                                                                +"\n"+ "Contribution Trimestrielle       : "+String.valueOf(trim)+" DT"
                                                                +"\n"+ "Contribution Semestrielle       : "+String.valueOf(semes)+" DT"
                                                                +"\n"+""
                                                                +"\n"+ "Garantie"
                                                                +"\n"+ "Bourse en cas de décès          : "+String.valueOf(prime_bourse)+" DT");
                                                //setContentText("Votre prime est égale à : "+String.valueOf(prime_bourse)+" DT");
                                                //Html.fromHtml("<font color='#23A7A7'>Garantie</font>")
                                            } else if((prime_univ >0)&& (prime_prof ==0)){
                                                pDialog.setAnimationEnable(true).setTitleText("Takaful Imtiez+").
                                                        setContentText("Contribution Totale Annuelle : "+String.valueOf(annuelle)+" DT"
                                                                +"\n"+ "Contribution Mensuelle           : "+String.valueOf(mensu)+" DT"
                                                                +"\n"+ "Contribution Trimestrielle       : "+String.valueOf(trim)+" DT"
                                                                +"\n"+ "Contribution Semestrielle       : "+String.valueOf(semes)+" DT"
                                                                +"\n"+""
                                                                +"\n"+ "Garantie"
                                                                +"\n"+ "Bourse en cas de décès          : "+String.valueOf(prime_bourse)+" DT"
                                                                +"\n"+ "Capital (à la vie universitaire) : "+String.valueOf(prime_univ)+" DT");
                                            }else if ((prime_univ ==0)&& (prime_prof >0)){
                                                pDialog.setAnimationEnable(true).setTitleText("Takaful Imtiez+").
                                                        setContentText("Contribution Totale Annuelle : "+String.valueOf(annuelle)+" DT"
                                                                +"\n"+ "Contribution Mensuelle           : "+String.valueOf(mensu)+" DT"
                                                                +"\n"+ "Contribution Trimestrielle       : "+String.valueOf(trim)+" DT"
                                                                +"\n"+ "Contribution Semestrielle       : "+String.valueOf(semes)+" DT"
                                                                +"\n"+""
                                                                +"\n"+ "Garantie"
                                                                +"\n"+ "Bourse en cas de décès          : "+String.valueOf(prime_bourse)+" DT"
                                                                +"\n"+ "Capital (à la vie professionnelle) : "+String.valueOf(prime_univ)+" DT");
                                            }else if ((prime_univ >0)&& (prime_prof >0)){
                                                pDialog.setAnimationEnable(true).setTitleText("Takaful Imtiez+").
                                                        setContentText("Contribution Totale Annuelle : "+String.valueOf(annuelle)+" DT"
                                                                +"\n"+ "Contribution Mensuelle           : "+String.valueOf(mensu)+" DT"
                                                                +"\n"+ "Contribution Trimestrielle       : "+String.valueOf(trim)+" DT"
                                                                +"\n"+ "Contribution Semestrielle       : "+String.valueOf(semes)+" DT"
                                                                +"\n"+""
                                                                +"\n"+ "Garantie"
                                                                +"\n"+ "Bourse en cas de décès          : "+String.valueOf(prime_bourse)+" DT"
                                                                +"\n"+ "Capital (à la vie universitaire) : "+String.valueOf(prime_univ)+" DT"
                                                                +"\n"+ "Capital (à la vie professionnelle) : "+String.valueOf(prime_prof)+" DT");
                                            }
                                            pDialog.setPositiveListener("ok", new PromptDialog.OnPositiveListener() {
                                                @Override
                                                public void onClick(PromptDialog dialog) {
                                                    dialog.dismiss();
                                                }
                                            }).show();
                                            Window window = pDialog.getWindow();
                                            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
                                    AlertDialog.Builder dialog =new AlertDialog.Builder(getContext(),R.style.CustomDialogTheme);
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
                                    AlertDialog alert = dialog.create();
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
                    MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
                }
            }
            //}

        });
        //return inflater.inflate(R.layout.fragment_second,container,false);
        return view;

    }
}
