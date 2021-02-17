package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.customviews.ZitounaEditText;
import com.example.myapplication.helper.Constants;
import com.example.myapplication.helper.MySingleton;
import com.google.android.material.button.MaterialButton;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {
    MaterialButton loginBTN;
    ZitounaEditText loginET, passwordET;
    public static String role1= null;
    public static int id ;
    public static String id_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        passwordET = findViewById(R.id.et_password);
        loginET = findViewById(R.id.et_login);
        loginBTN = findViewById(R.id.btn_login);
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }

    private void performLogin() {

        final String login = loginET.getText().toString().trim();
        final String password = passwordET.getText().toString().trim();
        if(login.equals("")||password.equals("")){
            AlertDialog.Builder dialog =new AlertDialog.Builder(LoginActivity.this,R.style.CustomDialogTheme);
            //dialog.setTitle(Html.fromHtml("<font color='#B0BEC5'>Info !</font>"));
            dialog.setTitle("Erreur !");
            dialog.setIcon(R.drawable.ic_info_white_24dp);
            dialog.setMessage("Veuillez remplir tous les champs ");
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
            Button positiveButton = alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE);
            Button negativeButton = alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE);
            positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));
            negativeButton.setTextColor(Color.parseColor("#FF0B8B42"));
            positiveButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
            negativeButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
        }
        else{
            final ProgressDialog progress;
            progress = new ProgressDialog(this);
            progress.setTitle("S'il vous plaît, attendez");
            progress.setMessage("Vérification en cours...");
            progress.setCancelable(false);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIcon(R.drawable.ic_schedule_grey_500_24dp);
            progress.show();
            JSONObject params = new JSONObject();
            try {
                params.put("login", login);
                params.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, Constants.LOGIN_ENDPOINT, params, new Response.Listener<JSONObject>() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onResponse(JSONObject response) {
                            //Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            try {
                                int status = response.getInt("status");
                                if (status == 1){
                                    role1 = response.getString("role");
                                    id = response.getInt("id");
                                    id_string = String.valueOf(id);
                                    //startActivity(new Intent(LoginActivity.this, TachesFragment.class).putExtra("Key", id_string));
                                    DynamicToast.makeSuccess(LoginActivity.this, "Bienvenue dans l'application Zitouna Takaful",
                                            999999).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("role", response.getString("role"));
                                    startActivity(intent);
                                    finish();
                                }else if (status == 0){
                                    progress.hide();
                                    AlertDialog.Builder dialog =new AlertDialog.Builder(LoginActivity.this,R.style.CustomDialogTheme);
                                    dialog.setTitle("Info !");
                                    dialog.setIcon(R.drawable.ic_info_white_24dp);
                                    dialog.setCancelable(true);
                                    dialog.setMessage("Login ou Mot De Passe incorrect ");
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
                                    Button positiveButton = alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE);
                                    Button negativeButton = alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE);
                                    positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));
                                    negativeButton.setTextColor(Color.parseColor("#FF0B8B42"));
                                    positiveButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
                                    negativeButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            //Toast.makeText(LoginActivity.this, "Error " + error.getCause(), Toast.LENGTH_SHORT).show();
                            progress.hide();
                            /*Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("role", "agent");
                            startActivity(intent);
                            finish();*/
                            AlertDialog.Builder dialog =new AlertDialog.Builder(LoginActivity.this,R.style.CustomDialogTheme);
                            dialog.setTitle("Erreur de connexion !");
                            //dialog.setTitle(Html.fromHtml("<font color='##424242'>Erreur de connexion !</font>"));
                            dialog.setIcon(R.drawable.wifi);
                            dialog.setMessage("Veuillez vérifier votre connexion internet ");
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
                            Button positiveButton = alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE);
                            Button negativeButton = alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE);
                            positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));
                            negativeButton.setTextColor(Color.parseColor("#FF0B8B42"));
                            positiveButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
                            negativeButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
                        }
                    });

// Access the RequestQueue through your singleton class.
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        }
    }
    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";
    private final String DefaultUnameValue = "";
    private String UnameValue;
    private final String DefaultPasswordValue = "";
    private String PasswordValue;
    @Override
    public void onPause() {
        super.onPause();
        savePreferences();
    }
    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
    }
    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        UnameValue = loginET.getText().toString();
        PasswordValue = passwordET.getText().toString();
        editor.putString(PREF_UNAME, UnameValue);
        editor.putString(PREF_PASSWORD, PasswordValue);
        editor.commit();
    }
    private void loadPreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        UnameValue = settings.getString(PREF_UNAME, DefaultUnameValue);
        PasswordValue = settings.getString(PREF_PASSWORD, DefaultPasswordValue);
        loginET.setText(UnameValue);
        passwordET.setText(PasswordValue);
    }
}