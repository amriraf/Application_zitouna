package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.myapplication.ui.Main3Activity;
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

import static com.example.myapplication.R.id.capital_deces_montant;
import static com.example.myapplication.R.id.date_naiss;
import static com.example.myapplication.R.id.dernier;
import static com.example.myapplication.R.id.dernier_mouniceb;
import static com.example.myapplication.R.id.effet;
import static com.example.myapplication.R.id.effet_mouniceb;

public class Main6Activity extends AppCompatActivity {
    double vk=0,vc=0,taux_tranche=0,mensu=0,trim=0,semes=0;
    private Button simuler;
    private ZitounaTextView txt;
    AlertDialog.Builder alertdialogbuilder;
    String[] AlertDialogItems = new String[]{
            "Accident",
            "Exonération",
            "Incapacité Permanente",
            "Maladies Redoutées"
    };
    List<String> ItemsIntoList;

    boolean[] Selectedtruefalse = new boolean[]{
            false,
            false,
            false,
            false
    };
    DatePickerDialog picker;
    private RadioGroup radioGroup;
    private RadioButton capital_cible, contribution_base;
    private EditText capital_cible_montant,contribution_base_montant,capital_deces,Contribution_exp_montant;
    private int i =0;
    EditText datenaiss,date_effet,date_echance;
    Button button_garantie;
    int q=0,s=0,d=0,f=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Contribution_exp_montant = (EditText) findViewById(R.id.contribution_exp);
        capital_deces=(EditText) findViewById(R.id.capital_deces_montant);
        capital_deces.setImeOptions(EditorInfo.IME_ACTION_DONE);
        simuler = (Button)findViewById(R.id.btn_simuler_2);
        button_garantie = (Button)findViewById(R.id.btn_garantie);
        button_garantie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialogbuilder = new AlertDialog.Builder(Main6Activity.this);
                ItemsIntoList = Arrays.asList(AlertDialogItems);
                alertdialogbuilder.setMultiChoiceItems(AlertDialogItems, Selectedtruefalse, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    }
                });
                alertdialogbuilder.setCancelable(false);
                alertdialogbuilder.setTitle("Sélectionner les garanties complémentaires");
                alertdialogbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int a = 0;
                        while(a < Selectedtruefalse.length)
                        {
                            boolean value = Selectedtruefalse[a];
                            if(value){
                                //textview.setText(textview.getText() + ItemsIntoList.get(a) + "\n");
                            }
                            a++;
                        }
                    }
                });
                AlertDialog dialog = alertdialogbuilder.create();
                dialog.show();
            }
        });
        datenaiss =(EditText) findViewById(R.id.date_naiss_mouniceb);
        datenaiss.setInputType(InputType.TYPE_NULL);
        datenaiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(Main6Activity.this,R.style.Datepicker,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                datenaiss.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.getWindow().setGravity(Gravity.CENTER);
                picker.show();
            }
        });
        date_effet=(EditText) findViewById(effet_mouniceb);
        date_effet.setInputType(InputType.TYPE_NULL);
        date_effet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(Main6Activity.this,R.style.Datepicker,
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
        date_echance=(EditText) findViewById(dernier_mouniceb);
        date_echance.setInputType(InputType.TYPE_NULL);
        date_echance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(Main6Activity.this,R.style.Datepicker,
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

        capital_cible_montant= (EditText) findViewById(R.id.capital_ciblee_montant);
        contribution_base_montant=(EditText) findViewById(R.id.Contribution_de_base_montant);


        final Drawable originalDrawable = capital_cible_montant.getBackground();
        contribution_base_montant.setEnabled(false);
        capital_cible_montant.setEnabled(false);
        contribution_base_montant.setBackgroundColor(getResources().getColor(R.color.disable));
        capital_cible_montant.setBackgroundColor(getResources().getColor(R.color.disable));

        final Spinner spinner_deces =(Spinner) findViewById(R.id.spinner_deces);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final Spinner spinner_tranche = (Spinner) findViewById(R.id.spinner_10);
        txt = (ZitounaTextView) findViewById(R.id.text_contribution);
        radioGroup= (RadioGroup) findViewById(R.id.radio_group_contribution);
        contribution_base=(RadioButton) findViewById(R.id.contribution_base);
        capital_cible =(RadioButton) findViewById(R.id.capital_cible);
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
                this,R.layout.spinner_item,choice_list_10){
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
                    DynamicToast.makeSuccess(getApplicationContext(), selectedItemText + " : Choisi",
                            999999).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        String[] taux = new String[]{
                "Croissance contribution",
                "0%",
                "1%",
                "2%",
                "3%","4%","5%","6%","7%","8%","9%","10%"
        };
        String[] taux_deces = new String[]{
                "Croissance Capital décès",
                "-10%","-9%","-8%","-7%","-6%","-5%","-4%","-3%","-2%","-1%",
                "0%",
                "1%",
                "2%",
                "3%","4%","5%","6%","7%","8%","9%","10%"
        };
        final List<String> choice_list_1 = new ArrayList<>(Arrays.asList(taux_deces));
        final ArrayAdapter<String> spinnerArrayAdapter_1 = new ArrayAdapter<String>(
                this,R.layout.spinner_item,choice_list_1){
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
        spinnerArrayAdapter_1.setDropDownViewResource(R.layout.spinner_item);
        spinner_deces.setAdapter(spinnerArrayAdapter_1);
        spinner_deces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    DynamicToast.makeSuccess(getApplicationContext(), selectedItemText + " : Choisi",
                            999999).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final List<String> choice_list = new ArrayList<>(Arrays.asList(taux));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,choice_list){
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
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);

                if(position > 0){
                    // Notify the selected item text
                    DynamicToast.makeSuccess(getApplicationContext(), selectedItemText + " : Choisi",
                            999999).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
 //       spinner.setVisibility(View.INVISIBLE);
        spinner.setEnabled(false);
        spinner.setClickable(false);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId==contribution_base.getId()){
                    DynamicToast.makeSuccess(Main6Activity.this, "Merci de choisir le montant de la contribution de base",
                            999999).show();
                    txt.setTextColor(getResources().getColor(R.color.color_end));
                    txt.setText("Contribution de base Choisi");
                    contribution_base_montant.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    contribution_base_montant.setBackgroundDrawable(originalDrawable);
                    capital_cible_montant.setBackgroundColor(getResources().getColor(R.color.disable));
                    capital_cible_montant.setText("");
                    spinner.setEnabled(true);
                    spinner.setClickable(true);
                    capital_cible_montant.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    contribution_base_montant.setEnabled(true);
                    capital_cible_montant.setEnabled(false);
                    spinnerArrayAdapter.remove("-10%");
                    spinnerArrayAdapter.remove("-9%");
                    spinnerArrayAdapter.remove("-8%");
                    spinnerArrayAdapter.remove("-7%");
                    spinnerArrayAdapter.remove("-6%");
                    spinnerArrayAdapter.remove("-5%");
                    spinnerArrayAdapter.remove("-4%");
                    spinnerArrayAdapter.remove("-3%");
                    spinnerArrayAdapter.remove("-2%");
                    spinnerArrayAdapter.remove("-1%");
                    spinnerArrayAdapter.remove("0%");
                    spinnerArrayAdapter.remove("1%");
                    spinnerArrayAdapter.remove("2%");
                    spinnerArrayAdapter.remove("3%");
                    spinnerArrayAdapter.remove("4%");
                    spinnerArrayAdapter.remove("5%");
                    spinnerArrayAdapter.remove("6%");
                    spinnerArrayAdapter.remove("7%");
                    spinnerArrayAdapter.remove("8%");
                    spinnerArrayAdapter.remove("9%");
                    spinnerArrayAdapter.remove("10%");

                    if(spinnerArrayAdapter.getItem(0).equals("Croissance contribution")){
                        spinnerArrayAdapter.remove("Croissance contribution");
                    }
                    spinnerArrayAdapter.remove("Croissance Capital décès");
                    spinnerArrayAdapter.add("Croissance contribution");
                    spinnerArrayAdapter.add("0%");
                    spinnerArrayAdapter.add("1%");
                    spinnerArrayAdapter.add("2%");
                    spinnerArrayAdapter.add("3%");
                    spinnerArrayAdapter.add("4%");
                    spinnerArrayAdapter.add("5%");
                    spinnerArrayAdapter.add("6%");
                    spinnerArrayAdapter.add("7%");
                    spinnerArrayAdapter.add("8%");
                    spinnerArrayAdapter.add("9%");
                    spinnerArrayAdapter.add("10%");
                }else if(checkedId==capital_cible.getId()){
                    DynamicToast.makeSuccess(Main6Activity.this, "Merci de choisir le montant du capital ciblé",
                            999999).show();
                    txt.setTextColor(getResources().getColor(R.color.color_end));
                    txt.setText("Capital Ciblé Choisi");
                    spinner.setEnabled(false);
                    spinner.setClickable(false);
                    contribution_base_montant.setEnabled(false);
                    capital_cible_montant.setEnabled(true);
                    capital_cible_montant.setBackgroundDrawable(originalDrawable);
                    contribution_base_montant.setBackgroundColor(getResources().getColor(R.color.disable));
                    contribution_base_montant.setText("");
                    i=2;
                }
            }
        });
        simuler.setOnClickListener(new View.OnClickListener() {
            String text = spinner.getSelectedItem().toString();
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId!=contribution_base.getId() && selectedId != capital_cible.getId()){
                    AlertDialog.Builder dialog =new AlertDialog.Builder(Main6Activity.this,R.style.CustomDialogTheme);
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
                }else if(selectedId==contribution_base.getId()){
                    AlertDialog.Builder dialog =new AlertDialog.Builder(Main6Activity.this,R.style.CustomDialogTheme);
                    dialog.setTitle("Erreur !");
                    dialog.setIcon(R.drawable.ic_info_white_24dp);
                    if(contribution_base_montant.getText().toString().equals("")){
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.d1);
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
                    }else if(spinner.getSelectedItem().toString().equals("Croissance contribution")){
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
                    }else if(capital_deces.getText().toString().equals("")){
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.d6);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ Capital Décès est vide");
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
                    }else if(spinner_deces.getSelectedItem().toString().equals("Croissance Capital décès")){
                        dialog.setMessage("Veuillez choisir un taux de croissance du capital décès");
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
                    }else if (Contribution_exp_montant.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.d11);
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
                    }
                    else if (datenaiss.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.d3);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date de naissance est vide");
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
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.d4);
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
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.d5);
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
                    }else if(spinner_tranche.getSelectedItem().toString().equals("Revenu Annuel Tranche")){
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
                        String ch1 = datenaiss.getText().toString();
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
                        if(Selectedtruefalse[0]==true){
                            q=1;
                        }else{
                            q=0;
                        }

                        if (Selectedtruefalse[1]==true){
                            s=1;
                        }else{
                            s=0;
                        }

                        if (Selectedtruefalse[2]==true){
                            d=1;
                        }else{
                            d=0;
                        }

                        if (Selectedtruefalse[3]==true){
                            f=1;
                        }else{
                            f=0;
                        }

                        if(spinner.getSelectedItem().toString().equals("0%")){
                            vc=0;
                        }else if(spinner.getSelectedItem().toString().equals("1%")){
                            vc=0.01;
                        }
                        else if(spinner.getSelectedItem().toString().equals("2%")){
                            vc=0.02;
                        }
                        else if(spinner.getSelectedItem().toString().equals("3%")){
                            vc=0.03;
                        }
                        else if(spinner.getSelectedItem().toString().equals("4%")){
                            vc=0.04;
                        }else if(spinner.getSelectedItem().toString().equals("5%")){
                            vc=0.05;
                        }else if(spinner.getSelectedItem().toString().equals("6%")){
                            vc=0.06;
                        }else if(spinner.getSelectedItem().toString().equals("7%")){
                            vc=0.07;
                        }else if(spinner.getSelectedItem().toString().equals("8%")){
                            vc=0.08;
                        }else if(spinner.getSelectedItem().toString().equals("9%")){
                            vc=0.09;
                        }else if(spinner.getSelectedItem().toString().equals("10%")){
                            vc=0.1;
                        }

                        if(spinner_deces.getSelectedItem().toString().equals("0%")){
                            vk=0;
                        }else if(spinner_deces.getSelectedItem().toString().equals("1%")){
                            vk=0.01;
                        }
                        else if(spinner_deces.getSelectedItem().toString().equals("2%")){
                            vk=0.02;
                        }
                        else if(spinner_deces.getSelectedItem().toString().equals("3%")){
                            vk=0.03;
                        }
                        else if(spinner_deces.getSelectedItem().toString().equals("4%")){
                            vk=0.04;
                        }else if(spinner_deces.getSelectedItem().toString().equals("5%")){
                            vk=0.05;
                        }else if(spinner_deces.getSelectedItem().toString().equals("6%")){
                            vk=0.06;
                        }else if(spinner_deces.getSelectedItem().toString().equals("7%")){
                            vk=0.07;
                        }else if(spinner_deces.getSelectedItem().toString().equals("8%")){
                            vk=0.08;
                        }else if(spinner_deces.getSelectedItem().toString().equals("9%")){
                            vk=0.09;
                        }else if(spinner_deces.getSelectedItem().toString().equals("10%")){
                            vk=0.1;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-1%")){
                            vk=-0.01;
                        }
                        else if(spinner_deces.getSelectedItem().toString().equals("-2%")){
                            vk=-0.02;
                        }
                        else if(spinner_deces.getSelectedItem().toString().equals("-3%")){
                            vk=-0.03;
                        }
                        else if(spinner_deces.getSelectedItem().toString().equals("-4%")){
                            vk=-0.04;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-5%")){
                            vk=-0.05;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-6%")){
                            vk=-0.06;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-7%")){
                            vk=-0.07;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-8%")){
                            vk=-0.08;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-9%")){
                            vk=-0.09;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-10%")){
                            vk=-0.1;
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
                        /*Toast.makeText(Main6Activity.this,String.valueOf(age),Toast.LENGTH_LONG).show();
                        Toast.makeText(Main6Activity.this,String.valueOf(durée),Toast.LENGTH_LONG).show();
                        Toast.makeText(Main6Activity.this,String.valueOf(capital_deces),Toast.LENGTH_LONG).show();
                        Toast.makeText(Main6Activity.this,String.valueOf(contribution_base_montant),Toast.LENGTH_LONG).show();
                        Toast.makeText(Main6Activity.this,String.valueOf(q),Toast.LENGTH_LONG).show();
                        Toast.makeText(Main6Activity.this,String.valueOf(s),Toast.LENGTH_LONG).show();
                        Toast.makeText(Main6Activity.this,String.valueOf(d),Toast.LENGTH_LONG).show();
                        Toast.makeText(Main6Activity.this,String.valueOf(f),Toast.LENGTH_LONG).show();*/
                        final ProgressDialog progress;
                        progress = new ProgressDialog(Main6Activity.this);
                        progress.setTitle("S'il vous plaît, attendez");
                        progress.setMessage("Simulation en cours...");
                        progress.setCancelable(false);
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.setIcon(R.drawable.ic_schedule_grey_500_24dp);
                        progress.show();
                        JSONObject params = new JSONObject();
                        try {
                            int captial_deces_1 = Integer.parseInt(capital_deces.getText().toString());
                            int contribution = Integer.parseInt(contribution_base_montant.getText().toString());
                            int Contribution_exp = Integer.parseInt(Contribution_exp_montant.getText().toString());
                            params.put("age", age);
                            params.put("n", durée);
                            params.put("q", q);
                            params.put("s", s);
                            params.put("d", d);
                            params.put("f", f);
                            params.put("taux",taux_tranche);
                            params.put("id",Integer.parseInt(LoginActivity.id_string));
                            params.put("vc",vc);
                            params.put("vk",vk);
                            params.put("Contribution_exp",Contribution_exp);
                            params.put("captial_deces",captial_deces_1);
                            params.put("contribution",contribution);
                            mensu = contribution*0.0845;
                            trim = contribution*0.2530;
                            semes = contribution*0.5040;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.POST, Constants.Calcul_ENDPOINT2, params, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {

                                            double prime_pré = response.getDouble("prime_pré");
                                            double prime_acc = response.getDouble("prime_acc");
                                            double prime_exo = response.getDouble("prime_exo");
                                            double prime_IP = response.getDouble("prime_IP");
                                            double prime_MR = response.getDouble("prime_MR");
                                            double epargne = response.getDouble("epargne");
                                            double epargne_constitu = response.getDouble("epargne_constitu");
                                            double epargne_constitu_1 = response.getDouble("epargne_constitu_1");
                                            double gain = response.getDouble("gain");
                                            // double mensu = response.getDouble("mensuelle");

                                            if (prime_pré > 0 && epargne >0){
                                                progress.hide();
                                                PromptDialog pDialog = new PromptDialog(Main6Activity.this);
                                                pDialog.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS);
                                                pDialog.setCancelable(false);
                                                pDialog.getWindow().setLayout(900,900);
                                               // if((prime_acc ==0)  && (prime_exo==0) && (prime_IP==0) && (prime_MR==0)){
                                                    pDialog.setAnimationEnable(true).setTitleText("Takaful Mounacib").
                                                            setContentText(/*"Contribution Totale Annuelle : "+String.valueOf(annuelle)+" DT"
                                                                    +"\n"+ "Contribution Mensuelle           : "+String.valueOf(mensu)+" DT"
                                                                    +"\n"+ "Contribution Trimestrielle       : "+String.valueOf(trim)+" DT"
                                                                    +"\n"+ "Contribution Semestrielle       : "+String.valueOf(semes)+" DT"
                                                                    +"\n"+""
                                                                    +"\n"+*/
                                                                    "Epargne Constitué : "+String.valueOf(epargne_constitu)+" DT"
                                                                            +"\n"+ "Epargne Constitué 2 : "+String.valueOf(epargne_constitu_1)+" DT"                                                                                     +"\n"+""
                                                                                     +"\n"+ "Contribution Mensuelle           : "+String.valueOf(mensu)+" DT"
                                                                                     +"\n"+ "Contribution Trimestrielle       : "+String.valueOf(trim)+" DT"
                                                                                     +"\n"+ "Contribution Semestrielle       : "+String.valueOf(semes)+" DT"
                                                                                     +"\n"+""
                                                                             +"\n"+     "Garantie"
                                                                             +"\n"+ "Garantie Décès          : "+String.valueOf(prime_pré)+" DT"
                                                                             +"\n"+ "Garantie Épargne        : "+String.valueOf(epargne)+" DT"
                                                                             +"\n"+ "Accident                : "+String.valueOf(prime_acc)+" DT"
                                                                             +"\n"+ "Exonération             : "+String.valueOf(prime_exo)+" DT"
                                                                             +"\n"+ "Incapacité Permanente   : "+String.valueOf(prime_IP)+" DT"
                                                                             +"\n"+ "Maladies Redoutées      : "+String.valueOf(prime_MR)+" DT" +"\n"+"" +"\n"+ "Gain d'impôt            : "+String.valueOf(gain)+" DT");
                                                    //setContentText("Votre prime est égale à : "+String.valueOf(prime_bourse)+" DT");
                                                    //Html.fromHtml("<font color='#23A7A7'>Garantie</font>")
                                               // }
                                            /*else if((prime_univ >0)&& (prime_prof ==0)){
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
                                                }*/
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
                                        AlertDialog.Builder dialog =new AlertDialog.Builder(Main6Activity.this,R.style.CustomDialogTheme);
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
                        MySingleton.getInstance(Main6Activity.this).addToRequestQueue(jsonObjectRequest);
                    }
                }else if(selectedId==capital_cible.getId()) {
                    AlertDialog.Builder dialog =new AlertDialog.Builder(Main6Activity.this,R.style.CustomDialogTheme);
                    dialog.setTitle("Erreur !");
                    dialog.setIcon(R.drawable.ic_info_white_24dp);
                    if(capital_cible_montant.getText().toString().equals("")){
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.d2);
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
                    else if(capital_deces.getText().toString().equals("")){
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.d6);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ Capital Décès est vide");
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
                    }else if(spinner_deces.getSelectedItem().toString().equals("Croissance Capital décès")){
                        dialog.setMessage("Veuillez choisir un taux de croissance du capital décès");
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
                    }else if (Contribution_exp_montant.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.d11);
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
                    }

                    else if (datenaiss.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.d3);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date de naissance est vide");
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
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.d4);
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
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.d5);
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
                    }else if(spinner_tranche.getSelectedItem().toString().equals("Revenu Annuel Tranche")){
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
                        String ch1 = datenaiss.getText().toString();
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

                        if(Selectedtruefalse[0]==true){
                            q=1;
                        }else{
                            q=0;
                        }

                        if (Selectedtruefalse[1]==true){
                            s=1;
                        }else{
                            s=0;
                        }

                        if (Selectedtruefalse[2]==true){
                            d=1;
                        }else{
                            d=0;
                        }

                        if (Selectedtruefalse[3]==true){
                            f=1;
                        }else{
                            f=0;
                        }
                        if(spinner.getSelectedItem().toString().equals("0%")){
                            vc=0;
                        }else if(spinner.getSelectedItem().toString().equals("1%")){
                            vc=0.01;
                        }
                        else if(spinner.getSelectedItem().toString().equals("2%")){
                            vc=0.02;
                        }
                        else if(spinner.getSelectedItem().toString().equals("3%")){
                            vc=0.03;
                        }
                        else if(spinner.getSelectedItem().toString().equals("4%")){
                            vc=0.04;
                        }else if(spinner.getSelectedItem().toString().equals("5%")){
                            vc=0.05;
                        }else if(spinner.getSelectedItem().toString().equals("6%")){
                            vc=0.06;
                        }else if(spinner.getSelectedItem().toString().equals("7%")){
                            vc=0.07;
                        }else if(spinner.getSelectedItem().toString().equals("8%")){
                            vc=0.08;
                        }else if(spinner.getSelectedItem().toString().equals("9%")){
                            vc=0.09;
                        }else if(spinner.getSelectedItem().toString().equals("10%")){
                            vc=0.1;
                        }

                        if(spinner_deces.getSelectedItem().toString().equals("0%")){
                            vk=0;
                        }else if(spinner_deces.getSelectedItem().toString().equals("1%")){
                            vk=0.01;
                        }
                        else if(spinner_deces.getSelectedItem().toString().equals("2%")){
                            vk=0.02;
                        }
                        else if(spinner_deces.getSelectedItem().toString().equals("3%")){
                            vk=0.03;
                        }
                        else if(spinner_deces.getSelectedItem().toString().equals("4%")){
                            vk=0.04;
                        }else if(spinner_deces.getSelectedItem().toString().equals("5%")){
                            vk=0.05;
                        }else if(spinner_deces.getSelectedItem().toString().equals("6%")){
                            vk=0.06;
                        }else if(spinner_deces.getSelectedItem().toString().equals("7%")){
                            vk=0.07;
                        }else if(spinner_deces.getSelectedItem().toString().equals("8%")){
                            vk=0.08;
                        }else if(spinner_deces.getSelectedItem().toString().equals("9%")){
                            vk=0.09;
                        }else if(spinner_deces.getSelectedItem().toString().equals("10%")){
                            vk=0.1;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-1%")){
                            vk=-0.01;
                        }
                        else if(spinner_deces.getSelectedItem().toString().equals("-2%")){
                            vk=-0.02;
                        }
                        else if(spinner_deces.getSelectedItem().toString().equals("-3%")){
                            vk=-0.03;
                        }
                        else if(spinner_deces.getSelectedItem().toString().equals("-4%")){
                            vk=-0.04;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-5%")){
                            vk=-0.05;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-6%")){
                            vk=-0.06;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-7%")){
                            vk=-0.07;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-8%")){
                            vk=-0.08;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-9%")){
                            vk=-0.09;
                        }else if(spinner_deces.getSelectedItem().toString().equals("-10%")){
                            vk=-0.1;
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
                        progress = new ProgressDialog(Main6Activity.this);
                        progress.setTitle("S'il vous plaît, attendez");
                        progress.setMessage("Simulation en cours...");
                        progress.setCancelable(false);
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.setIcon(R.drawable.ic_schedule_grey_500_24dp);
                        progress.show();
                        JSONObject params = new JSONObject();
                        try {
                            int captial_deces_1 = Integer.parseInt(capital_deces.getText().toString());
                            int contribution = Integer.parseInt(capital_cible_montant.getText().toString());
                            int Contribution_exp = Integer.parseInt(Contribution_exp_montant.getText().toString());
                            params.put("age", age);
                            params.put("n", durée);
                            params.put("q", q);
                            params.put("s", s);
                            params.put("d", d);
                            params.put("f", f);
                            params.put("taux",taux_tranche);
                            params.put("vc",vc);
                            params.put("vk",vk);
                            params.put("id",Integer.parseInt(LoginActivity.id_string));
                            params.put("Contribution_exp",Contribution_exp);
                            params.put("captial_deces",captial_deces_1);
                            params.put("contribution",contribution);
                            mensu = contribution*0.0845;
                            trim = contribution*0.2530;
                            semes = contribution*0.5040;
                           /* params.put("bourse", val);
                            params.put("univ", val1);
                            params.put("prof", val2);
                            params.put("b", b);*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.POST, Constants.Calcul_ENDPOINT2, params, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            double prime_pré = response.getDouble("prime_pré");
                                            double prime_acc = response.getDouble("prime_acc");
                                            double prime_exo = response.getDouble("prime_exo");
                                            double prime_IP = response.getDouble("prime_IP");
                                            double prime_MR = response.getDouble("prime_MR");
                                            double epargne = response.getDouble("epargne");
                                            double epargne_constitu = response.getDouble("epargne_constitu");
                                            double epargne_constitu_1 = response.getDouble("epargne_constitu_1");
                                            double gain = response.getDouble("gain");
                                            // double mensu = response.getDouble("mensuelle");
                                            if (prime_pré > 0 && epargne >0){
                                                progress.hide();
                                                PromptDialog pDialog = new PromptDialog(Main6Activity.this);
                                                pDialog.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS);
                                                pDialog.setCancelable(false);
                                                pDialog.getWindow().setLayout(900,900);
                                                // if((prime_acc ==0)  && (prime_exo==0) && (prime_IP==0) && (prime_MR==0)){
                                                pDialog.setAnimationEnable(true).setTitleText("Takaful Mounacib").
                                                        setContentText(/*"Contribution Totale Annuelle : "+String.valueOf(annuelle)+" DT"
                                                                    +"\n"+ "Contribution Mensuelle           : "+String.valueOf(mensu)+" DT"
                                                                    +"\n"+ "Contribution Trimestrielle       : "+String.valueOf(trim)+" DT"
                                                                    +"\n"+ "Contribution Semestrielle       : "+String.valueOf(semes)+" DT"
                                                                    +"\n"+""
                                                                    +"\n"+*/
                                                                "Epargne Constitué : "+String.valueOf(epargne_constitu)+" DT"
                                                                        +"\n"+ "Epargne Constitué 2 : "+String.valueOf(epargne_constitu_1)+" DT"
                                                                        +"\n"+""
                                                                        +"\n"+ "Contribution Mensuelle           : "+String.valueOf(mensu)+" DT"
                                                                        +"\n"+ "Contribution Trimestrielle       : "+String.valueOf(trim)+" DT"
                                                                        +"\n"+ "Contribution Semestrielle       : "+String.valueOf(semes)+" DT"
                                                                        +"\n"+""
                                                                        +"\n"+ "Garantie"
                                                                        +"\n"+ "Garantie Décès          : "+String.valueOf(prime_pré)+" DT"
                                                                        +"\n"+ "Garantie Épargne        : "+String.valueOf(epargne)+" DT"
                                                                        +"\n"+ "Accident                : "+String.valueOf(prime_acc)+" DT"
                                                                        +"\n"+ "Exonération             : "+String.valueOf(prime_exo)+" DT"
                                                                        +"\n"+ "Incapacité Permanente   : "+String.valueOf(prime_IP)+" DT"
                                                                        +"\n"+ "Maladies Redoutées      : "+String.valueOf(prime_MR)+" DT"
                                                                        +"\n"+""
                                                                        +"\n"+ "Gain d'impôt            : "+String.valueOf(gain)+" DT");

                                                //setContentText("Votre prime est égale à : "+String.valueOf(prime_bourse)+" DT");
                                                //Html.fromHtml("<font color='#23A7A7'>Garantie</font>")
                                                // }
                                            /*else if((prime_univ >0)&& (prime_prof ==0)){
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
                                                }*/
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
                                        AlertDialog.Builder dialog =new AlertDialog.Builder(Main6Activity.this,R.style.CustomDialogTheme);
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
                        MySingleton.getInstance(Main6Activity.this).addToRequestQueue(jsonObjectRequest);
                    }
                }
            }
        });



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.mygradient_4));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();
        if (id==android.R.id.home){
            this.finish();
        }
        switch (item.getItemId()){
            case R.id.action_Deconnexion:
                AlertDialog.Builder dialog =new AlertDialog.Builder(Main6Activity.this,R.style.CustomDialogTheme);
                dialog.setTitle("Déconnexion");
                dialog.setIcon(R.drawable.ic_help_outline_white_24dp);
                dialog.setMessage("Etes-vous sûr de vouloir quitter l'application?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                SharedPreferences mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
                                SharedPreferences.Editor editor = mPreferences.edit();
                                editor.remove("USERNAME");
                                editor.remove("PASSWORD");
                                editor.commit();
                                Message message =new Message();
                                message.obj="NOT SUCCESS";
                                handler.sendMessage(message);
                                finish();
                            }
                        }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                final AlertDialog alert = dialog.create();
                alert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button negativeButton= ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                        Button positiveButton =((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,2f);
                        negativeButton.setLayoutParams(params);
                        positiveButton.setLayoutParams(params);

                    }
                });
                alert.show();
                Button positiveButton = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                Button negativeButton = alert.getButton(AlertDialog.BUTTON_NEGATIVE);
                positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));
                negativeButton.setTextColor(Color.parseColor("#FF0B8B42"));
                positiveButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
                negativeButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
                return true;
        }
        return super.onOptionsItemSelected(item);

    }


    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            String loginmsg =(String)msg.obj;
            if (loginmsg.equals("NOT SUCCESS")){
                Intent intent = new Intent(Main6Activity.this, LoginActivity.class);
                intent.putExtra("LoginMessage","Logged Out");
                startActivity(intent);
                removeDialog(0);
            }
        }
    };

   /* @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/
}
