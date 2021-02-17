package com.example.myapplication.ui;

import android.content.DialogInterface;
import android.os.Build;
import android.telephony.SmsManager;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {
    private TextView txt;
    private static final int REQUEST_PHONE_CALL = 1;
    private static final int REQUEST_SEND_SMS = 2;
    Intent intent;
    Button dialBtn, smsBtn;
    private EditText contenu;
    private RadioGroup radioGroup;
    private RadioButton grande_branche, takaful_tamouil, directeur_vie, directeur_technique, GRC,directeur_technique_3,directeur_vie_3;
    private RadioGroup radioGroup_3;
    String phoneNo = "";
    String smsContent;
    SmsManager smsManager = SmsManager.getDefault();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        radioGroup_3= (RadioGroup) findViewById(R.id.radio_group_3);
        directeur_technique_3=(RadioButton) findViewById(R.id.directeur_technique_3);
        directeur_vie_3 =(RadioButton) findViewById(R.id.directeur_vie_3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        smsBtn = (Button) findViewById(R.id.btn_sms);
        contenu = (EditText) findViewById(R.id.sms);
        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup_3.getCheckedRadioButtonId();
                if (selectedId!=directeur_technique_3.getId() && selectedId != directeur_vie_3.getId()){
                    AlertDialog.Builder dialog =new AlertDialog.Builder(Main4Activity.this,R.style.CustomDialogTheme);
                    dialog.setTitle("Erreur");
                    dialog.setCancelable(true);
                    dialog.setMessage("Vous devez choisir un responsable!");
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
                }else{
                    if (selectedId==directeur_technique_3.getId()){
                        // phoneNo="27298105";
                        phoneNo="93692449";
                    }else if(selectedId==directeur_vie_3.getId()){
                        phoneNo="27298106";
                    }
                    smsContent = contenu.getText().toString();
                    if(!contenu.getText().toString().equals("")){
                        if (ContextCompat.checkSelfPermission(Main4Activity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(Main4Activity.this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
                        } else {
                            smsManager.sendTextMessage(phoneNo, null, smsContent, null, null);
                            contenu.setText("");
                            Toast.makeText(getApplicationContext(), "SMS Envoyé!", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        AlertDialog.Builder dialog =new AlertDialog.Builder(Main4Activity.this,R.style.CustomDialogTheme);
                        dialog.setTitle("Erreur");
                        dialog.setCancelable(true);
                        dialog.setMessage("Le champs Contenu est vide!");
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
                }
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (toolbar != null) {
            toolbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.mygradient));
        }
        grande_branche = (RadioButton) findViewById(R.id.grande_branche_1);
        takaful_tamouil = (RadioButton) findViewById(R.id.Takaful_tamouil_1);
        directeur_vie = (RadioButton) findViewById(R.id.directeur_vie_1);
        directeur_technique = (RadioButton) findViewById(R.id.directeur_technique_1);
        GRC = (RadioButton) findViewById(R.id.GRC);
        txt = (TextView) findViewById(R.id.text_phone);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_2);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.grande_branche_1) {
                    Toast.makeText(getApplicationContext(), "Vous allez appeler le service Grande Branche",
                            Toast.LENGTH_SHORT).show();
                    txt.setText("Grande Branche Choisi!");
                } else if (checkedId == R.id.Takaful_tamouil_1) {
                    Toast.makeText(getApplicationContext(), "Vous allez appeler le service Takaful Tamouil",
                            Toast.LENGTH_SHORT).show();
                    txt.setText("Takaful Tamouil Choisi!");
                } else if (checkedId == R.id.directeur_vie_1) {
                    Toast.makeText(getApplicationContext(), "Vous allez appeler le Directeur Vie",
                            Toast.LENGTH_SHORT).show();
                    txt.setText("Directeur Vie Choisi!");
                } else if (checkedId == R.id.directeur_technique_1) {
                    Toast.makeText(getApplicationContext(), "Vous allez appeler le Directeur Technique",
                            Toast.LENGTH_SHORT).show();
                    txt.setText("Directeur Technique Choisi!");
                } else {
                    Toast.makeText(getApplicationContext(), "Vous allez appeler le service GRC",
                            Toast.LENGTH_SHORT).show();
                    txt.setText("GRC Choisi!");
                }
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onDialCall(View v) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        intent = new Intent(Intent.ACTION_CALL);
        if ((selectedId == grande_branche.getId())) {
            intent.setData(Uri.parse("tel:36420077"));
        } else if ((selectedId == takaful_tamouil.getId())) {
            intent.setData(Uri.parse("tel:36420076"));
        } else if ((selectedId == directeur_vie.getId())) {
            intent.setData(Uri.parse("tel:36420021"));
        } else if ((selectedId == directeur_technique.getId())) {
            intent.setData(Uri.parse("tel:36420021"));
        } else if ((selectedId == GRC.getId())) {
            intent.setData(Uri.parse("tel:36420087"));
        }
        if (ContextCompat.checkSelfPermission(Main4Activity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Main4Activity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        } else {
            if (selectedId==grande_branche.getId()||selectedId == takaful_tamouil.getId()||selectedId == directeur_vie.getId()||selectedId == directeur_technique.getId()||selectedId==GRC.getId()){
                startActivity(intent);
            }else {
                AlertDialog.Builder dialog =new AlertDialog.Builder(Main4Activity.this,R.style.CustomDialogTheme);
                dialog.setTitle("Erreur");
                dialog.setCancelable(true);
                dialog.setMessage("Aucun service choisi!");
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
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                }
                break;
            case REQUEST_SEND_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    smsManager.sendTextMessage(phoneNo, null, smsContent, null, null);
                    contenu.setText("");
                    Toast.makeText(getApplicationContext(), "SMS Envoyé!", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }
}