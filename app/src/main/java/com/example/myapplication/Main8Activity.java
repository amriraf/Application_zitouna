package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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
import com.example.myapplication.ui.LoginActivity;
import com.example.myapplication.ui.TachesFragment;
import com.google.android.material.textfield.TextInputLayout;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;

import cn.refactor.lib.colordialog.PromptDialog;

import static com.example.myapplication.R.id.CIN;
import static com.example.myapplication.R.id.btn_simuler;

public class Main8Activity extends AppCompatActivity {
    EditText N_Cotat,Nom_Client,N_tele,N_CIN,Adresse_,Code_postal,Gouvernorat,Ville;
    private Button ajouter;
    private Integer userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        N_Cotat=(EditText) findViewById(R.id.N_cotation) ;
        Nom_Client=(EditText) findViewById(R.id.Nom_Client) ;
        N_tele=(EditText) findViewById(R.id.N_tele) ;
        N_CIN=(EditText) findViewById(R.id.CIN) ;
        Adresse_=(EditText) findViewById(R.id.Adresse_cli) ;
        Code_postal=(EditText) findViewById(R.id.Code_Post) ;
        Gouvernorat=(EditText) findViewById(R.id.Gouvernorat) ;
        Ville=(EditText) findViewById(R.id.Ville) ;
        final String code_agence=TachesFragment.x1;
        final String agence=TachesFragment.x2;
        final String Inter=TachesFragment.x3;
        final String userId_string =LoginActivity.id_string;
        userId=Integer.parseInt(userId_string);

        ajouter=(Button) findViewById(R.id.btn_ajouter);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (N_Cotat.getText().toString().trim().equals("")||Nom_Client.getText().toString().trim().equals("")
                        ||N_tele.getText().toString().trim().equals("")||N_CIN.getText().toString().trim().equals("")
                        ||Adresse_.getText().toString().trim().equals("")||Code_postal.getText().toString().trim().equals("")
                        ||Gouvernorat.getText().toString().trim().equals("")||Ville.getText().toString().trim().equals("")){

                    AlertDialog.Builder dialog =new AlertDialog.Builder(Main8Activity.this,R.style.CustomDialogTheme);
                    dialog.setTitle("Erreur !");
                    dialog.setIcon(R.drawable.ic_info_white_24dp);
                    dialog.setCancelable(true);
                    if (N_Cotat.getText().toString().trim().equals("")){
                        TextInputLayout b1 = (TextInputLayout) findViewById(R.id.d1);
                        b1.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ capital est Numéro de cotation");
                    }
                    else if (Nom_Client.getText().toString().trim().equals("")){
                        TextInputLayout b2 = (TextInputLayout) findViewById(R.id.d2);
                        b2.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date de Nom de client est vide");
                    }
                    else if (N_tele.getText().toString().trim().equals("")){
                        TextInputLayout b3 = (TextInputLayout) findViewById(R.id.d6);
                        b3.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date Numéro du téléphone est vide");
                    }
                    else if (N_CIN.getText().toString().trim().equals("")){
                        TextInputLayout b4 = (TextInputLayout) findViewById(R.id.d11);
                        b4.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ date CIN / Registre de commerce est vide");
                    }
                    else if (Adresse_.getText().toString().trim().equals("")){
                        TextInputLayout b5 = (TextInputLayout) findViewById(R.id.d3);
                        b5.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ Adresse est vide");
                    }else if (Code_postal.getText().toString().trim().equals("")){
                        TextInputLayout b5 = (TextInputLayout) findViewById(R.id.d4);
                        b5.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ code postal est vide");
                    }else if (Gouvernorat.getText().toString().trim().equals("")){
                        TextInputLayout b5 = (TextInputLayout) findViewById(R.id.d5);
                        b5.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ gouvernorat est vide");
                    }else if (Ville.getText().toString().trim().equals("")){
                        TextInputLayout b5 = (TextInputLayout) findViewById(R.id.d70);
                        b5.setError("Ce champ est obligatoire");
                        dialog.setMessage("Le champ Ville est vide");
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
                    String ch1 = N_Cotat.getText().toString();
                    String ch2 = Nom_Client.getText().toString();
                    String ch3 = N_tele.getText().toString();
                    String ch4 = N_CIN.getText().toString();
                    String ch5 = Adresse_.getText().toString();
                    String ch6 = Code_postal.getText().toString();
                    String ch7 = Gouvernorat.getText().toString();
                    String ch8 = Ville.getText().toString();

                    final ProgressDialog progress;
                    progress = new ProgressDialog(Main8Activity.this);
                    progress.setTitle("S'il vous plaît, attendez");
                    progress.setMessage("Votre demande est en cours de traitement...");
                    progress.setCancelable(false);
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setIcon(R.drawable.ic_schedule_grey_500_24dp);
                    progress.show();
                    JSONObject params = new JSONObject();
                    try {
                        params.put("N_cotation", ch1);
                        params.put("code_agence", code_agence);
                        params.put("agence", agence);
                        params.put("Nom_client", ch2);
                        params.put("Num_tele", ch3);
                        params.put("CIN", ch4);
                        params.put("Adresse_client", ch5);
                        params.put("Code_postal", ch6);
                        params.put("Gouvernorat", ch7);
                        params.put("Ville", ch8);
                        params.put("Inter", Inter);
                        params.put("userId", userId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.POST, Constants.Insert_ENDPOINT, params, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        Integer etat = response.getInt("etat");
                                        if (etat > 0){
                                            progress.hide();
                                            PromptDialog pDialog = new PromptDialog(Main8Activity.this);
                                            pDialog.setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS);
                                            pDialog.setCancelable(false);
                                            pDialog.getWindow().setLayout(900,900);
                                            pDialog.setAnimationEnable(true).setTitleText("Nouvelle Demande").setContentText("Votre demande a été traitée avec succès");
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
                                    AlertDialog.Builder dialog =new AlertDialog.Builder(Main8Activity.this,R.style.CustomDialogTheme);
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
                    MySingleton.getInstance(Main8Activity.this).addToRequestQueue(jsonObjectRequest);
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
                AlertDialog.Builder dialog =new AlertDialog.Builder(Main8Activity.this,R.style.CustomDialogTheme);
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
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("LoginMessage","Logged Out");
                startActivity(intent);
                removeDialog(0);
            }
        }
    };
}
