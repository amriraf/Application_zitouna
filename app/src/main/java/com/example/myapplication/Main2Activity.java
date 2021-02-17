package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.helper.Constants;
import com.example.myapplication.helper.MySingleton;
import com.example.myapplication.helper.MySingleton1;
import com.example.myapplication.ui.LoginActivity;
import com.example.myapplication.ui.Main3Activity;
import com.example.myapplication.ui.TachesFragment;
import com.example.myapplication.ui.TasksFragment;
import com.example.myapplication.ui.gallery.GalleryFragment;
import com.example.myapplication.ui.home.HomeFragment;
import com.example.myapplication.ui.share.ShareFragment;
import com.example.myapplication.ui.slideshow.SlideshowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.kinda.alert.KAlertDialog;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.concurrent.TimeUnit;

import cn.refactor.lib.colordialog.ColorDialog;
import cn.refactor.lib.colordialog.PromptDialog;

import static com.example.myapplication.R.id.*;

public class Main2Activity extends AppCompatActivity  {
    private int mYear, mMonth, mDay;
    private DatePickerDialog datePickerDialog;
    private Button simuler, Button_date_naiss,Button_date_effet,Button_date_echance;
    EditText datenaiss,date_effet,date_echance,franchise;
    EditText capital;
    DatePickerDialog picker;
    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        franchise=(EditText) findViewById(R.id.franchise) ;
        capital=(EditText) findViewById(R.id.capital) ;
        capital.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        }); 
        capital.setImeOptions(EditorInfo.IME_ACTION_DONE);


        date_effet=(EditText) findViewById(effet);
        date_effet.setInputType(InputType.TYPE_NULL);
        date_effet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(Main2Activity.this,R.style.Datepicker,
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
        date_echance=(EditText) findViewById(dernier);
        date_echance.setInputType(InputType.TYPE_NULL);
        date_echance.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(Main2Activity.this,R.style.Datepicker,
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
        datenaiss =(EditText) findViewById(date_naiss);
        datenaiss.setInputType(InputType.TYPE_NULL);
        datenaiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(Main2Activity.this,R.style.Datepicker,
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




        simuler=(Button) findViewById(btn_simuler);
        simuler.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (capital.getText().toString().trim().equals("")||datenaiss.getText().toString().trim().equals("")||date_effet.getText().toString().trim().equals("")||date_echance.getText().toString().trim().equals("")||franchise.getText().toString().trim().equals("")){

                    AlertDialog.Builder dialog =new AlertDialog.Builder(Main2Activity.this,R.style.CustomDialogTheme);
                    dialog.setTitle("Erreur !");
                    dialog.setIcon(R.drawable.ic_info_white_24dp);
                    dialog.setCancelable(true);
                    if (capital.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.b1);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ capital est vide");
                    }
                    else if (datenaiss.getText().toString().trim().equals("")){
                        TextInputLayout b2 = (TextInputLayout) findViewById(R.id.b2);
                        b2.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date de naissance est vide");
                    }
                    else if (date_effet.getText().toString().trim().equals("")){
                        TextInputLayout b3 = (TextInputLayout) findViewById(R.id.b3);
                        b3.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date d'effet est vide");
                    }
                    else if (date_echance.getText().toString().trim().equals("")){
                        TextInputLayout b4 = (TextInputLayout) findViewById(R.id.b4);
                        b4.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date d'échance est vide");
                    }
                    else if (franchise.getText().toString().trim().equals("")){
                        TextInputLayout b5 = (TextInputLayout) findViewById(R.id.b5);
                        b5.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ franchise est vide");
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

                    String ch4=franchise.getText().toString();
                    float y2 = Float.valueOf(ch4);
                    int y3=Math.round(y2/12) ;


                    //Toast.makeText(Main2Activity.this,String.valueOf(y3),Toast.LENGTH_LONG).show();

                        final ProgressDialog progress;
                        progress = new ProgressDialog(Main2Activity.this);
                        progress.setTitle("S'il vous plaît, attendez");
                        progress.setMessage("Simulation en cours...");
                        progress.setCancelable(false);
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.setIcon(R.drawable.ic_schedule_grey_500_24dp);
                        progress.show();
                        JSONObject params = new JSONObject();
                        try {

                         int val = Integer.parseInt(capital.getText().toString());
                            params.put("age", age);
                            params.put("n", durée);
                            params.put("capital", val);
                            params.put("f", y3);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.POST, Constants.Calcul_ENDPOINT, params, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            double PUC = response.getDouble("PUC");
                                            if (PUC > 0){
                                                 progress.hide();
                                                PromptDialog pDialog = new PromptDialog(Main2Activity.this);
                                                pDialog.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS);
                                                pDialog.setCancelable(false);
                                                pDialog.getWindow().setLayout(900,900);
                                                pDialog.setAnimationEnable(true).setTitleText("Takaful Tamouil").setContentText("Votre prime est égale à: "+String.valueOf(PUC)+" DT");
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
                                        AlertDialog.Builder dialog =new AlertDialog.Builder(Main2Activity.this,R.style.CustomDialogTheme);
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
                        MySingleton.getInstance(Main2Activity.this).addToRequestQueue(jsonObjectRequest);
                    }
                }
            //}

        });



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.mygradient_4));
        };
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
                AlertDialog.Builder dialog =new AlertDialog.Builder(Main2Activity.this,R.style.CustomDialogTheme);
                dialog.setTitle("Déconnexion");
                dialog.setIcon(R.drawable.ic_help_outline_white_24dp);
                dialog.setCancelable(true);
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
                Intent intent = new Intent(Main2Activity.this, LoginActivity.class);
                intent.putExtra("LoginMessage","Logged Out");
                startActivity(intent);
                removeDialog(0);
            }
        }
    };
}
