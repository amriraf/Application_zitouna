package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.radiogroupes.RadioGroupErrorSupport;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.customviews.ZitounaTextView;
import com.example.myapplication.helper.Constants;
import com.example.myapplication.helper.MySingleton;
import com.example.myapplication.ui.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import cn.refactor.lib.colordialog.PromptDialog;

import static com.example.myapplication.R.id.dernier_mouniceb;
import static com.example.myapplication.R.id.effet_mouniceb;
import static com.facebook.FacebookSdk.getApplicationContext;

public class FirstFragment extends Fragment {
    double vk=0,vc=0,taux_tranche=0,mensu=0,trim=0,semes=0;
    EditText datenaiss_participant,date_effet,date_echance,datenaiss_bene,durée_bourse,bourse,contrib_exp;
    DatePickerDialog picker;
    private Button simuler;
    private EditText bourse_montant,contribution_base_montant;
    private RadioButton contribution_base, bourse_objectif;
    private RadioGroup radioGroup;
    private RadioButton bourse_radiobutton,contribution_base_radio_button;
    private ZitounaTextView txt;
    public FirstFragment(){}
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        //return inflater.inflate(R.layout.fragment_first,container,false);
        final View view = inflater.inflate(R.layout.fragment_first,container,false);
        contrib_exp=(EditText) view.findViewById(R.id.contribution_exp);
        durée_bourse = (EditText) view.findViewById(R.id.Durée_service_bourse_1) ;
        bourse_montant =(EditText) view.findViewById(R.id.bourse_objectif);
        contribution_base_montant =(EditText) view.findViewById(R.id.Contribution_de_base_montant_1);

        final Drawable originalDrawable = bourse_montant.getBackground();
        contribution_base_montant.setEnabled(false);
        bourse_montant.setEnabled(false);
        contribution_base_montant.setBackgroundColor(getResources().getColor(R.color.disable));
        bourse_montant.setBackgroundColor(getResources().getColor(R.color.disable));

        date_effet=(EditText) view.findViewById(R.id.effet_imtiez);
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
        date_echance=(EditText) view.findViewById(R.id.dernier_imtiez);
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
        datenaiss_participant =(EditText) view.findViewById(R.id.Date_de_naiss_participant_imtiez);
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

        datenaiss_bene =(EditText) view.findViewById(R.id.Date_de_naiss_béneficaire_imtiez);
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
        final Spinner spinner_tranche = (Spinner) view.findViewById(R.id.spinner_11);
        String[] tranche = new String[]{
                "Revenu Annuel Tranche",
                "0 à 5000",
                "5001 à 20000",
                "20001 à 30000",
                "30001 à 50000",
                "+ 50000"
        };
        final List<String> choice_list_10 = new ArrayList<>(Arrays.asList(tranche));
        final ArrayAdapter<String> spinnerArrayAdapter_10 = new ArrayAdapter<String>(
                getActivity(),R.layout.spinner_item,choice_list_10){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter_10.setDropDownViewResource(R.layout.spinner_item);

        spinner_tranche.setAdapter(spinnerArrayAdapter_10);
        spinner_tranche.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    DynamicToast.makeSuccess(getActivity().getApplicationContext(), selectedItemText + " : Choisi",
                            999999).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        txt = (ZitounaTextView) view.findViewById(R.id.text_contribution_1);
        radioGroup= (RadioGroup) view.findViewById(R.id.radio_group_contribution_1);
        contribution_base_radio_button=(RadioButton) view.findViewById(R.id.contribution_base);
        bourse_radiobutton =(RadioButton) view.findViewById(R.id.Bourse_Objectif);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId==contribution_base_radio_button.getId()){
                    DynamicToast.makeSuccess(getContext(), "Merci de choisir le montant de la contribution de base",
                            999999).show();
                    txt.setTextColor(getResources().getColor(R.color.color_end));
                    txt.setText("Contribution de base Choisi");
                    contribution_base_montant.setEnabled(true);
                    bourse_montant.setEnabled(false);
                    //contribution_base_montant.setVisibility(View.VISIBLE);
                    //bourse_montant.setVisibility(View.INVISIBLE);
                    contribution_base_montant.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    bourse_montant.setImeOptions(EditorInfo.IME_ACTION_DONE);

                    contribution_base_montant.setBackgroundDrawable(originalDrawable);
                    bourse_montant.setBackgroundColor(getResources().getColor(R.color.disable));
                    bourse_montant.setText("");
                }else if(checkedId==bourse_radiobutton.getId()){
                    DynamicToast.makeSuccess(getContext(), "Merci de choisir le montant de la bourse objectif",
                            999999).show();
                    txt.setTextColor(getResources().getColor(R.color.color_end));
                    txt.setText("Bourse Objectif Choisi");
                    bourse_montant.setBackgroundDrawable(originalDrawable);
                    contribution_base_montant.setBackgroundColor(getResources().getColor(R.color.disable));
                    contribution_base_montant.setText("");
                    bourse_montant.setEnabled(true);
                    contribution_base_montant.setEnabled(false);
                }
            }
        });
        final Spinner spinner_2 = (Spinner) view.findViewById(R.id.spinner_croissance_1);
        final String[] taux = new String[]{
                "Croissance contribution",
                "0%",
                "1%",
                "2%",
                "3%","4%","5%","6%","7%","8%","9%","10%"
        };
        final List<String> choice_list_2 = new ArrayList<>(Arrays.asList(taux));
        final ArrayAdapter<String> spinnerArrayAdapter_2 = new ArrayAdapter<String>(
                getActivity(),R.layout.spinner_item,choice_list_2){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter_2.setDropDownViewResource(R.layout.spinner_item);
        spinner_2.setAdapter(spinnerArrayAdapter_2);
        spinner_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    DynamicToast.makeSuccess(getActivity().getApplicationContext(), selectedItemText + " : Choisi",
                            999999).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        simuler = (Button) view.findViewById(R.id.btn_simuler_13);
        simuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId!=contribution_base_radio_button.getId() && selectedId != bourse_radiobutton.getId()){
                    AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
                    RadioGroupErrorSupport rd=(RadioGroupErrorSupport)radioGroup;
                    rd.setErrorTextColor(Color.RED);
                    rd.setErrorTextSize(12);
                    rd.setError("Ce champ est obligatoire");
                    dialog.setTitle("Erreur !");
                    dialog.setIcon(R.drawable.ic_info_white_24dp);
                    dialog.setMessage("Le choix de contribution est obligatoire");
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
                }else if(selectedId==contribution_base_radio_button.getId()){
                    AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
                    dialog.setTitle("Erreur !");
                    dialog.setIcon(R.drawable.ic_info_white_24dp);
                    if(contribution_base_montant.getText().toString().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.d1);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ contribution de base est vide");
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
                    }else if(spinner_2.getSelectedItem().toString().equals("Croissance contribution")){
                        dialog.setMessage("Veuillez choisir un taux de croissance de contribution");
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
                    }else if (datenaiss_participant.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.c1);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date naiss (participant) est vide");
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
                    else if (datenaiss_bene.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.c2);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date de naissance (béneficaire) est vide");
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
                    }else if (durée_bourse.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.c5);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ durée service de la bourse est vide");
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
                    }else if (contrib_exp.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.d11);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ contribution exceptionnelle est vide");
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
                    }else if (date_effet.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.c3);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date d'effet est vide");
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
                    }else if (date_echance.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.c30);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date d'échance est vide");
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
                    else if(spinner_tranche.getSelectedItem().toString().equals("Revenu Annuel Tranche")){
                        dialog.setMessage("Veuillez choisir votre revenu annuel par tranche");
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
                        DateTime date1,date2,date3 = new DateTime();
                        date1 = DateTime.parse(ch1, DateTimeFormat.forPattern("dd/MM/yyyy"));
                        date2 =DateTime.parse(ch2, DateTimeFormat.forPattern("dd/MM/yyyy"));
                        date3 =DateTime.parse(ch3, DateTimeFormat.forPattern("dd/MM/yyyy"));
                        float y = Days.daysBetween(date1,date2).getDays();
                        int age = Math.round(y/365) ;
                        float y1 = Days.daysBetween(date2,date3).getDays();
                        int durée = Math.round(y1/365) ;

                        if(spinner_2.getSelectedItem().toString().equals("0%")){
                            vc=0;
                        }else if(spinner_2.getSelectedItem().toString().equals("1%")){
                            vc=0.01;
                        }
                        else if(spinner_2.getSelectedItem().toString().equals("2%")){
                            vc=0.02;
                        }
                        else if(spinner_2.getSelectedItem().toString().equals("3%")){
                            vc=0.03;
                        }
                        else if(spinner_2.getSelectedItem().toString().equals("4%")){
                            vc=0.04;
                        }else if(spinner_2.getSelectedItem().toString().equals("5%")){
                            vc=0.05;
                        }else if(spinner_2.getSelectedItem().toString().equals("6%")){
                            vc=0.06;
                        }else if(spinner_2.getSelectedItem().toString().equals("7%")){
                            vc=0.07;
                        }else if(spinner_2.getSelectedItem().toString().equals("8%")){
                            vc=0.08;
                        }else if(spinner_2.getSelectedItem().toString().equals("9%")){
                            vc=0.09;
                        }else if(spinner_2.getSelectedItem().toString().equals("10%")){
                            vc=0.1;
                        }

                        if(spinner_tranche.getSelectedItem().toString().equals("0 à 5000")){
                            taux_tranche=0;
                        }else if(spinner_tranche.getSelectedItem().toString().equals("5001 à 20000")){
                            taux_tranche=0.26;
                        }
                        else if(spinner_tranche.getSelectedItem().toString().equals("20001 à 30000")){
                            taux_tranche=0.28;
                        }
                        else if(spinner_tranche.getSelectedItem().toString().equals("30001 à 50000")){
                            taux_tranche=0.32;
                        }else if(spinner_tranche.getSelectedItem().toString().equals("+ 50000")){
                            taux_tranche=0.36;
                        }
                        final ProgressDialog progress;
                        progress = new ProgressDialog(getActivity());
                        progress.setTitle("S'il vous plaît, attendez");
                        progress.setMessage("Simulation en cours...");
                        progress.setCancelable(false);
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.setIcon(R.drawable.ic_schedule_grey_500_24dp);
                        progress.show();
                        JSONObject params = new JSONObject();

                        int contribution = Integer.parseInt(contribution_base_montant.getText().toString());
                        int Contribution_exp = Integer.parseInt(contrib_exp.getText().toString());
                        int duree_bourse_1 = Integer.parseInt(durée_bourse.getText().toString());
                        try {

                            params.put("age", age);
                            params.put("n", durée);
                            params.put("duree_bourse", duree_bourse_1);
                            params.put("taux",taux_tranche);

                            params.put("id",Integer.parseInt(LoginActivity.id_string));
                            params.put("vc",vc);
                            params.put("Contribution_exp",Contribution_exp);
                            params.put("contribution",contribution);
                            mensu = contribution*0.0845;
                            trim = contribution*0.2530;
                            semes = contribution*0.5040;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.POST, Constants.Calcul_ENDPOINT3, params, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            double epargne_constitu = response.getDouble("epargne_constitu");
                                            double epargne_constitu_1 = response.getDouble("epargne_constitu_1");
                                            double bourse = response.getDouble("bourse");
                                            double bourse_1 = response.getDouble("bourse_1");
                                            double gain = response.getDouble("gain");
                                            if (epargne_constitu >0){
                                                progress.hide();
                                                PromptDialog pDialog = new PromptDialog(getActivity());
                                                pDialog.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS);
                                                pDialog.setCancelable(false);
                                                pDialog.getWindow().setLayout(900,900);
                                                pDialog.setAnimationEnable(true).setTitleText("Takaful Imtiez").
                                                        setContentText(
                                                                "Epargne Constitué : "+String.valueOf(epargne_constitu)+" DT"
                                                                        +"\n"+ "Epargne Constitué 2 : "+String.valueOf(epargne_constitu_1)+" DT"                                                                                     +"\n"+""
                                                                        +"\n"+ "Contribution Mensuelle           : "+String.valueOf(mensu)+" DT"
                                                                        +"\n"+ "Contribution Trimestrielle       : "+String.valueOf(trim)+" DT"
                                                                        +"\n"+ "Contribution Semestrielle       : "+String.valueOf(semes)+" DT"
                                                                        +"\n"+""
                                                                        +"\n"+ "Bourse Acquise          : "+String.valueOf(bourse)+" DT"
                                                                        +"\n"+ "Bourse Acquise Hyp2       : "+String.valueOf(bourse_1)+" DT"
                                                                        +"\n"+""
                                                                        +"\n"+ "Gain d'impôt            : "+String.valueOf(gain)+" DT");
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
                                        AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
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
                        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
                    }
                }else if(selectedId==bourse_radiobutton.getId()) {
                    AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
                    dialog.setTitle("Erreur !");
                    dialog.setIcon(R.drawable.ic_info_white_24dp);
                    if(bourse_montant.getText().toString().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.d2);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ capital ciblé est vide");
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
                    else if (datenaiss_participant.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.c1);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date naiss (participant) est vide");
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
                    else if (datenaiss_bene.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.c2);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date de naissance (béneficaire) est vide");
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
                    }else if (durée_bourse.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.c5);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ durée service de la bourse est vide");
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
                    }else if (contrib_exp.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.d11);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ contribution exceptionnelle est vide");
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
                    }else if (date_effet.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.c3);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date d'effet est vide");
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
                    }else if (date_echance.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) view.findViewById(R.id.c30);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date d'échance est vide");
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
                    else if(spinner_tranche.getSelectedItem().toString().equals("Revenu Annuel Tranche")){
                        dialog.setMessage("Veuillez choisir votre revenu annuel par tranche");
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
                        DateTime date1,date2,date3 = new DateTime();
                        date1 = DateTime.parse(ch1, DateTimeFormat.forPattern("dd/MM/yyyy"));
                        date2 =DateTime.parse(ch2, DateTimeFormat.forPattern("dd/MM/yyyy"));
                        date3 =DateTime.parse(ch3, DateTimeFormat.forPattern("dd/MM/yyyy"));
                        float y = Days.daysBetween(date1,date2).getDays();
                        int age = Math.round(y/365) ;
                        float y1 = Days.daysBetween(date2,date3).getDays();
                        int durée = Math.round(y1/365) ;

                        if(spinner_2.getSelectedItem().toString().equals("0%")){
                            vc=0;
                        }else if(spinner_2.getSelectedItem().toString().equals("1%")){
                            vc=0.01;
                        }
                        else if(spinner_2.getSelectedItem().toString().equals("2%")){
                            vc=0.02;
                        }
                        else if(spinner_2.getSelectedItem().toString().equals("3%")){
                            vc=0.03;
                        }
                        else if(spinner_2.getSelectedItem().toString().equals("4%")){
                            vc=0.04;
                        }else if(spinner_2.getSelectedItem().toString().equals("5%")){
                            vc=0.05;
                        }else if(spinner_2.getSelectedItem().toString().equals("6%")){
                            vc=0.06;
                        }else if(spinner_2.getSelectedItem().toString().equals("7%")){
                            vc=0.07;
                        }else if(spinner_2.getSelectedItem().toString().equals("8%")){
                            vc=0.08;
                        }else if(spinner_2.getSelectedItem().toString().equals("9%")){
                            vc=0.09;
                        }else if(spinner_2.getSelectedItem().toString().equals("10%")){
                            vc=0.1;
                        }

                        if(spinner_tranche.getSelectedItem().toString().equals("0 à 5000")){
                            taux_tranche=0;
                        }else if(spinner_tranche.getSelectedItem().toString().equals("5001 à 20000")){
                            taux_tranche=0.26;
                        }
                        else if(spinner_tranche.getSelectedItem().toString().equals("20001 à 30000")){
                            taux_tranche=0.28;
                        }
                        else if(spinner_tranche.getSelectedItem().toString().equals("30001 à 50000")){
                            taux_tranche=0.32;
                        }else if(spinner_tranche.getSelectedItem().toString().equals("+ 50000")){
                            taux_tranche=0.36;
                        }

                        final ProgressDialog progress;
                        progress = new ProgressDialog(getActivity());
                        progress.setTitle("S'il vous plaît, attendez");
                        progress.setMessage("Simulation en cours...");
                        progress.setCancelable(false);
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.setIcon(R.drawable.ic_schedule_grey_500_24dp);
                        progress.show();
                        JSONObject params = new JSONObject();
                        try {
                            int contribution = Integer.parseInt(bourse_montant.getText().toString());
                            int Contribution_exp = Integer.parseInt(contrib_exp.getText().toString());
                            int duree_bourse_1 = Integer.parseInt(durée_bourse.getText().toString());
                            params.put("age", age);
                            params.put("n", durée);
                            params.put("duree_bourse", duree_bourse_1);
                            params.put("taux",taux_tranche);
                            params.put("vc",vc);
                            params.put("id",Integer.parseInt(LoginActivity.id_string));
                            params.put("Contribution_exp",Contribution_exp);
                            params.put("contribution",contribution);
                            mensu = contribution*0.0845;
                            trim = contribution*0.2530;
                            semes = contribution*0.5040;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.POST, Constants.Calcul_ENDPOINT3, params, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            double epargne_constitu = response.getDouble("epargne_constitu");
                                            double epargne_constitu_1 = response.getDouble("epargne_constitu_1");
                                            double bourse = response.getDouble("bourse");
                                            double bourse_1 = response.getDouble("bourse_1");
                                            double gain = response.getDouble("gain");
                                            if (epargne_constitu >0){
                                                progress.hide();
                                                PromptDialog pDialog = new PromptDialog(getActivity());
                                                pDialog.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS);
                                                pDialog.setCancelable(false);
                                                pDialog.getWindow().setLayout(900,900);
                                                pDialog.setAnimationEnable(true).setTitleText("Takaful Imtiez").
                                                        setContentText(
                                                                "Epargne Constitué : "+String.valueOf(epargne_constitu)+" DT"
                                                                        +"\n"+ "Epargne Constitué 2 : "+String.valueOf(epargne_constitu_1)+" DT"                                                                                     +"\n"+""
                                                                        +"\n"+ "Contribution Mensuelle           : "+String.valueOf(mensu)+" DT"
                                                                        +"\n"+ "Contribution Trimestrielle       : "+String.valueOf(trim)+" DT"
                                                                        +"\n"+ "Contribution Semestrielle       : "+String.valueOf(semes)+" DT"
                                                                        +"\n"+""
                                                                        +"\n"+ "Bourse Acquise          : "+String.valueOf(bourse)+" DT"
                                                                        +"\n"+ "Bourse Acquise Hyp2       : "+String.valueOf(bourse_1)+" DT"
                                                                        +"\n"+""
                                                                        +"\n"+ "Gain d'impôt            : "+String.valueOf(gain)+" DT");

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
                                        AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
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
                        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
                    }
                }
            }
        });

        return view;
    }
}
