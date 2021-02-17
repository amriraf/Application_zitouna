package com.example.myapplication;

import android.Manifest;
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
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FirstFragment_1 extends Fragment {
    private ZitounaTextView txt_10;
    private static final int REQUEST_PHONE_CALL = 1;
    private static final int REQUEST_SEND_SMS = 2;
    Intent intent;
    Button dialBtn, smsBtn;
    private RadioGroup radioGroup;
    private EditText contenu;
    private RadioGroup radioGroup_10;
    private RadioButton grande_branche_10, takaful_tamouil_10, directeur_vie_10, directeur_technique_10, GRC_10,directeur_technique_3,directeur_vie_3;
    private RadioGroup radioGroup_3;
    String phoneNo = "";
    String smsContent;
    SmsManager smsManager = SmsManager.getDefault();
    public FirstFragment_1(){}
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_first_fragment_1,container,false);
        dialBtn=(Button) view.findViewById(R.id.btn_appeler_10) ;
        grande_branche_10 = (RadioButton) view.findViewById(R.id.grande_branche_10);
        takaful_tamouil_10 = (RadioButton) view.findViewById(R.id.Takaful_tamouil_10);
        directeur_vie_10 = (RadioButton) view.findViewById(R.id.directeur_vie_10);
        directeur_technique_10 = (RadioButton) view.findViewById(R.id.directeur_technique_10);
        GRC_10 = (RadioButton) view.findViewById(R.id.GRC10);
        txt_10 = (ZitounaTextView) view.findViewById(R.id.text_phone_10);
        radioGroup_10 = (RadioGroup) view.findViewById(R.id.radio_group_20);
        radioGroup_10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.grande_branche_10) {
                    DynamicToast.makeSuccess(getContext(), "Vous allez appeler votre le service “Grande Branche“",
                            999999).show();
                    txt_10.setTextColor(getResources().getColor(R.color.color_end));
                    txt_10.setText("Grande Branche Choisi");
                } else if (checkedId == R.id.Takaful_tamouil_10) {
                    DynamicToast.makeSuccess(getContext(), "Vous allez appeler le service “Takaful Tamouil“",
                            999999).show();
                    txt_10.setTextColor(getResources().getColor(R.color.color_end));
                    txt_10.setText("Takaful Tamouil Choisi");
                } else if (checkedId == R.id.directeur_vie_10) {
                    DynamicToast.makeSuccess(getContext(), "Vous allez appeler le Directeur Vie",
                            999999).show();
                    txt_10.setTextColor(getResources().getColor(R.color.color_end));
                    txt_10.setText("Directeur Vie Choisi");
                } else if (checkedId == R.id.directeur_technique_10) {
                    DynamicToast.makeSuccess(getContext(), "Vous allez appeler le Directeur Technique",
                            999999).show();
                    txt_10.setText("Directeur Technique Choisi");
                } else {
                    DynamicToast.makeSuccess(getContext(), "Vous allez appeler le service “GRC“",
                            999999).show();
                    txt_10.setTextColor(getResources().getColor(R.color.color_end));
                    txt_10.setText("GRC Choisi");
                }
            }
        });
        dialBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedId = radioGroup_10.getCheckedRadioButtonId();
                if(selectedId!=grande_branche_10.getId()&&selectedId != takaful_tamouil_10.getId()
                        && selectedId != directeur_vie_10.getId()&& selectedId != directeur_technique_10.getId()
                && selectedId!=GRC_10.getId()) {
                    RadioGroupErrorSupport rd1=(RadioGroupErrorSupport)radioGroup_10;
                    rd1.setErrorTextColor(Color.RED);
                    rd1.setErrorTextSize(12);
                    rd1.setError("Ce champ est obligatoire");
                    AlertDialog.Builder dialog =new AlertDialog.Builder(getContext(),R.style.CustomDialogTheme);
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
                else{
                    intent = new Intent(Intent.ACTION_CALL);
                if ((selectedId == grande_branche_10.getId())) {
                    intent.setData(Uri.parse("tel:36420077"));
                } else if ((selectedId == takaful_tamouil_10.getId())) {
                    intent.setData(Uri.parse("tel:36420076"));
                } else if ((selectedId == directeur_vie_10.getId())) {
                    intent.setData(Uri.parse("tel:36420021"));
                } else if ((selectedId == directeur_technique_10.getId())) {
                    intent.setData(Uri.parse("tel:36420021"));
                } else if ((selectedId == GRC_10.getId())) {
                    intent.setData(Uri.parse("tel:36420087"));
                }
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                } else {
                    if (selectedId==grande_branche_10.getId()||selectedId == takaful_tamouil_10.getId()||selectedId == directeur_vie_10.getId()||selectedId == directeur_technique_10.getId()||selectedId==GRC_10.getId()){
                        startActivity(intent);
                    }
                        /*else {
                        AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
                        dialog.setTitle("Erreur !");
                        dialog.setIcon(R.drawable.ic_info_white_24dp);
                        dialog.setMessage("Aucun responsable / service choisi");
                        RadioGroupErrorSupport rd=(RadioGroupErrorSupport)radioGroup_10;
                        rd.setErrorTextColor(Color.RED);
                        rd.setErrorTextSize(12);
                        rd.setError("Vous devez choisir un reponsable / service");
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
                    }*/
                    }
                }
            }
        });
        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    //public void onDialCall_10(View view) {

    //}
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode==REQUEST_PHONE_CALL){
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                }
        }
    }
}
