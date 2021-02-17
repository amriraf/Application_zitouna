package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.radiogroupes.RadioGroupErrorSupport;
import com.example.myapplication.customviews.ZitounaTextView;
import com.example.myapplication.ui.Main3Activity;
import com.example.myapplication.ui.Main4Activity;
import com.google.android.material.textfield.TextInputLayout;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SecondFragment_1 extends Fragment {
    private TextView txt;
    private static final int REQUEST_PHONE_CALL = 1;
    private static final int REQUEST_SEND_SMS = 2;
    Intent intent;
    private RadioGroup radioGroup;
    Button dialBtn, smsBtn_30;
    private EditText contenu_30;
    private RadioGroup radioGroup_30;
    private RadioButton grande_branche_30, takaful_tamouil_30, BANQUE_TAKAFUL_30,service_vente,directeur_technique_30,directeur_vie_30;
    private RadioGroup radioGroup_3;
    private ZitounaTextView txt_1;
    String phoneNo_30 = "";
    String smsContent_30;
    SmsManager smsManager_30 = SmsManager.getDefault();
    public SecondFragment_1(){}
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_second_fragment_1,container,false);
        txt_1 = (ZitounaTextView) view.findViewById(R.id.text_sms_10);
        radioGroup_30= (RadioGroup) view.findViewById(R.id.radio_group_30);
        directeur_technique_30=(RadioButton) view.findViewById(R.id.directeur_technique_30);
        directeur_vie_30 =(RadioButton) view.findViewById(R.id.directeur_vie_30);
        grande_branche_30=(RadioButton) view.findViewById(R.id.grande_branche_30);
        takaful_tamouil_30=(RadioButton) view.findViewById(R.id.Takaful_tamouil_30);
        BANQUE_TAKAFUL_30 = (RadioButton) view.findViewById(R.id.Banque_takaful_30);
        service_vente=(RadioButton) view.findViewById(R.id.service_apres_vente_30);

        radioGroup_30.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId==directeur_technique_30.getId()){
                    DynamicToast.makeSuccess(getContext(), "Vous allez envoyer votre SMS au Directeur Technique",
                            999999).show();
                    txt_1.setTextColor(getResources().getColor(R.color.color_end));
                    txt_1.setText("Directeur Technique Choisi");
                    // phoneNo="27298105";
                    phoneNo_30="93692449";
                }else if(checkedId==directeur_vie_30.getId()){
                    DynamicToast.makeSuccess(getContext(), "Vous allez envoyer votre SMS au Directeur Vie",
                            999999).show();
                    txt_1.setTextColor(getResources().getColor(R.color.color_end));
                    txt_1.setText("Directeur Vie Choisi");
                    phoneNo_30="27298106";
                }else if(checkedId==grande_branche_30.getId()){
                    DynamicToast.makeSuccess(getContext(), "Vous allez envoyer votre SMS au service “Grande Branche“",
                            999999).show();
                    txt_1.setTextColor(getResources().getColor(R.color.color_end));
                    txt_1.setText("Grande Branche Choisi");
                    phoneNo_30="27298106";
                }else if(checkedId==takaful_tamouil_30.getId()){
                    DynamicToast.makeSuccess(getContext(), "Vous allez envoyer votre SMS au service “Takaful Tamouil“",
                            999999).show();
                    txt_1.setTextColor(getResources().getColor(R.color.color_end));
                    txt_1.setText("Takaful Tamouil Choisi");
                    phoneNo_30="27298106";
                }else if(checkedId==BANQUE_TAKAFUL_30.getId()){
                    DynamicToast.makeSuccess(getContext(), "Vous allez envoyer votre SMS au service “Banque Takaful“",
                            999999).show();
                    txt_1.setTextColor(getResources().getColor(R.color.color_end));
                    txt_1.setText("Banque Takaful Choisi");
                    phoneNo_30="27298106";
                }else if(checkedId==service_vente.getId()){
                    DynamicToast.makeSuccess(getContext(), "Vous allez envoyer votre SMS au “Service Après Vente“",
                            999999).show();
                    txt_1.setTextColor(getResources().getColor(R.color.color_end));
                    txt_1.setText("Service Après Vente Choisi");
                    phoneNo_30="27298106";
                }
            }
        });

        smsBtn_30 = (Button) view.findViewById(R.id.btn_sms_30);
        contenu_30 = (EditText) view.findViewById(R.id.sms_30);
        smsBtn_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup_30.getCheckedRadioButtonId();
                if (selectedId!=directeur_technique_30.getId() && selectedId != directeur_vie_30.getId()
                && selectedId!=grande_branche_30.getId()&& selectedId!=takaful_tamouil_30.getId()
                && selectedId!=BANQUE_TAKAFUL_30.getId() && selectedId!=service_vente.getId()){
                    AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
                    RadioGroupErrorSupport rd=(RadioGroupErrorSupport)radioGroup_30;
                    rd.setErrorTextColor(Color.RED);
                    rd.setErrorTextSize(12);
                    rd.setError("Ce champ est obligatoire");
                    dialog.setTitle("Erreur !");
                    dialog.setIcon(R.drawable.ic_info_white_24dp);
                    dialog.setMessage("Aucun responsable / service choisi");
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
                    smsContent_30 = contenu_30.getText().toString().trim();
                    if(!contenu_30.getText().toString().trim().equals("")){
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND_SMS);
                        } else {
                            smsManager_30.sendTextMessage(phoneNo_30, null, smsContent_30, null, null);
                            contenu_30.setText("");
                            DynamicToast.makeSuccess(getActivity().getApplicationContext(), "SMS Envoyé!", 999999).show();
                        }
                    }else if(contenu_30.getText().toString().trim().equals("")){
                        TextInputLayout yy=(TextInputLayout) view.findViewById(R.id.yy);
                        yy.setError("Ce champ est obligatoire");
                        AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
                        dialog.setTitle("Erreur !");
                        dialog.setIcon(R.drawable.ic_info_white_24dp);
                        dialog.setMessage("Le champ Contenu est vide ");
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
        return view;

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
@Override
public void onRequestPermissionsResult(int requestCode,
                                       String permissions[], int[] grantResults) {
   // switch (requestCode) {
        if (requestCode== REQUEST_SEND_SMS){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                smsManager_30.sendTextMessage(phoneNo_30, null, smsContent_30, null, null);
                contenu_30.setText("");
                DynamicToast.makeSuccess(getActivity().getApplicationContext(), "SMS Envoyé!", 999999).show();

            }
    }
    }
}
