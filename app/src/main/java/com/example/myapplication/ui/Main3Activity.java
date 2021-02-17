package com.example.myapplication.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ab.radiogroupes.RadioGroupErrorSupport;
import com.example.myapplication.R;
import com.example.myapplication.customviews.ZitounaTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;



public class Main3Activity extends AppCompatActivity {
    EditText nature;
    EditText message;
    private ZitounaTextView txt;
    private RadioGroupErrorSupport radioGroupErrorSupport;
    private RadioGroup radioGroup;
    private RadioButton grande_branche, takaful_tamouil, directeur_vie,directeur_technique;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        radioGroupErrorSupport = (RadioGroupErrorSupport) findViewById(R.id.radio_group_1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        nature=findViewById(R.id.nature);
        message= findViewById(R.id.message);
        nature.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        radioGroup= (RadioGroup) findViewById(R.id.radio_group_1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.grande_branche){
                    DynamicToast.makeSuccess(Main3Activity.this, "Vous allez envoyer votre email à “Grande Branche“",
                            999999).show();
                    txt.setTextColor(getResources().getColor(R.color.color_end));
                    txt.setText("Grande Branche Choisi");
                }else if (checkedId==R.id.Takaful_tamouil){
                    DynamicToast.makeSuccess(Main3Activity.this, "Vous allez envoyer votre email à “Takaful Tamouil“",
                            999999).show();
                    txt.setTextColor(getResources().getColor(R.color.color_end));
                    txt.setText("Takaful Tamouil Choisi");
                }else if (checkedId==R.id.directeur_vie){
                    DynamicToast.makeSuccess(Main3Activity.this, "Vous allez envoyer votre email au Directeur Vie",
                            999999).show();
                    txt.setTextColor(getResources().getColor(R.color.color_end));
                    txt.setText("Directeur Vie Choisi");
                }else if (checkedId==R.id.directeur_technique){
                    DynamicToast.makeSuccess(Main3Activity.this, "Vous allez envoyer votre email au Directeur Technique",
                            999999).show();
                    txt.setTextColor(getResources().getColor(R.color.color_end));
                    txt.setText("Directeur Technique Choisi");
                }
            }
        });
        grande_branche =(RadioButton) findViewById(R.id.grande_branche);
        takaful_tamouil=(RadioButton) findViewById(R.id.Takaful_tamouil);
        directeur_vie =(RadioButton) findViewById(R.id.directeur_vie);
        directeur_technique=(RadioButton) findViewById(R.id.directeur_technique);
        txt=(ZitounaTextView) findViewById(R.id.text_email) ;
        Button btnsend=findViewById(R.id.btn_envoyer);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (toolbar != null) {
            toolbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.mygradient_4));
        }

    }
    private void sendMail(){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        String subject= nature.getText().toString();
        String contenu= message.getText().toString();
        Intent intent= new Intent(Intent.ACTION_SEND);
        if ((selectedId == grande_branche.getId())) {
            intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"Grande.Branche@zitounatakaful.com"});
        }else if((selectedId == takaful_tamouil.getId())){
            intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"Takaful.Tamouil@zitounatakaful.com"});
        }else if((selectedId == directeur_vie.getId())){
            intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"Samir.Hassine@zitounatakaful.com"});
        }else if((selectedId == directeur_technique.getId())){
            intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"Fahmi.Mekaouer@zitounatakaful.com"});
        }
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,contenu);

        intent.setType("message/rfc822");
        if(selectedId!=grande_branche.getId()&&selectedId != takaful_tamouil.getId()&& selectedId != directeur_vie.getId()&& selectedId != directeur_technique.getId()) {
            RadioGroupErrorSupport rd=(RadioGroupErrorSupport)radioGroup;
            rd.setErrorTextColor(Color.RED);
            rd.setErrorTextSize(12);
            rd.setError("Ce champ est obligatoire");
            //radioGroup.setBackgroundResource(R.drawable.error_background);
            AlertDialog.Builder dialog =new AlertDialog.Builder(Main3Activity.this,R.style.CustomDialogTheme);
            dialog.setTitle("Erreur !");
            dialog.setIcon(R.drawable.ic_info_white_24dp);
            dialog.setMessage("Aucun responsable / service choisi");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

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
            Button positiveButton = alert.getButton(AlertDialog.BUTTON_POSITIVE);
            Button negativeButton = alert.getButton(AlertDialog.BUTTON_NEGATIVE);
            positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));
            negativeButton.setTextColor(Color.parseColor("#FF0B8B42"));
            positiveButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
            negativeButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
        }
         else if (subject.trim().equals("")){
            TextInputLayout aa = (TextInputLayout) findViewById(R.id.aa);
            aa.setError("Ce champ est obligatoire");
             //nature.setBackgroundResource(R.drawable.error_background);
             AlertDialog.Builder dialog =new AlertDialog.Builder(Main3Activity.this,R.style.CustomDialogTheme);
            dialog.setTitle("Erreur !");
            dialog.setIcon(R.drawable.ic_info_white_24dp);
            dialog.setMessage("Le champ objet est vide");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
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
             Button positiveButton = alert.getButton(AlertDialog.BUTTON_POSITIVE);
             Button negativeButton = alert.getButton(AlertDialog.BUTTON_NEGATIVE);
             positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));
             negativeButton.setTextColor(Color.parseColor("#FF0B8B42"));
             positiveButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
             negativeButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
        }
        else if (contenu.trim().equals("")){

            TextInputLayout aa1 = (TextInputLayout) findViewById(R.id.aa1);
            aa1.setError("Ce champ est obligatoire");
            AlertDialog.Builder dialog =new AlertDialog.Builder(Main3Activity.this,R.style.CustomDialogTheme);
            //dialog.setTitle(Html.fromHtml("<font color='#FF7F27'>Erreur</font>"));
            dialog.setTitle("Erreur !");
            dialog.setMessage("Le champ contenu est vide");
            dialog.setIcon(R.drawable.ic_info_white_24dp);
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
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
             Button positiveButton = alert.getButton(AlertDialog.BUTTON_POSITIVE);
             Button negativeButton = alert.getButton(AlertDialog.BUTTON_NEGATIVE);
             positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));
             negativeButton.setTextColor(Color.parseColor("#FF0B8B42"));
             positiveButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
             negativeButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
        }
            else {
             startActivity(Intent.createChooser(intent,"Comment voulez-vous procéder..."));
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
                AlertDialog.Builder dialog =new AlertDialog.Builder(Main3Activity.this,R.style.CustomDialogTheme);
                dialog.setTitle("Déconnexion");
                dialog.setMessage("Etes-vous sûr de vouloir quitter l'application?");
                dialog.setIcon(R.drawable.ic_help_outline_white_24dp);
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
                Intent intent = new Intent(Main3Activity.this, LoginActivity.class);
                intent.putExtra("LoginMessage","Logged Out");
                startActivity(intent);
                removeDialog(0);
            }
        }
    };
}
