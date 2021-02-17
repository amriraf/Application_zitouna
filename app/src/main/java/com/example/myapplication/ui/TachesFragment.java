package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.Main8Activity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Mes_taches;
import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewAdapter;
import com.example.myapplication.helper.Constants;
import com.example.myapplication.helper.MySingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class TachesFragment extends Fragment {
    View rootView;
    FloatingActionButton fab;
    public static String x1="",x2="",x3="";
    String x="",x4="",x5="",x6="",x7="",x8="",x9="",x10="",x11="";
    ProgressDialog progress;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_taches, container, false);
        final SwipeRefreshLayout pullToRefresh = rootView.findViewById(R.id.pullToRefresh);
        pullToRefresh.setColorSchemeColors(getResources().getColor(R.color.swipe_color_3),getResources().getColor(R.color.swipe_color_2)
                ,getResources().getColor(R.color.swipe_color_1));
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
                final List<Mes_taches> mes_taches = new ArrayList<>();
                JSONObject params = new JSONObject();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST,Constants.BASE_URL+"get_tasks_user?userId="+LoginActivity.id_string, params, new Response.Listener<JSONObject>() {

                            @SuppressLint("ResourceAsColor")
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    int status = response.getInt("status");
                                    int empty_checked= response.getInt("empty");
                                    if(empty_checked>0){
                                        JSONArray tasks = response.getJSONArray("data");
                                        for (int i = 0; i < tasks.length(); i++) {
                                            JSONObject temp = tasks.getJSONObject(i);
                                            x =temp.getString("N_cotation");
                                            x1 =temp.getString("code_agence");
                                            x2 =temp.getString("agence");
                                            x3 =temp.getString("Inter");
                                            x4 =temp.getString("Nom_client");
                                            x5 =temp.getString("num_tele");
                                            x6 =temp.getString("CIN");
                                            x7 =temp.getString("Adresse_client");
                                            x8 =temp.getString("Code_postal");
                                            x9 =temp.getString("Gouvernorat");
                                            x10 =temp.getString("Ville");
                                            x11 =temp.getString("Etat");
                                            mes_taches.add(new Mes_taches("Cotation","N° Cotation: "+String.valueOf(x),"Agence: "+String.valueOf(x2),"Code Agence: "+String.valueOf(x1),
                                                    "Intermediaire: "+String.valueOf(x3),"Nom Client: "+String.valueOf(x4),"N° Telephone: "+String.valueOf(x5),
                                                    "N°CIN / Registre Commerce: "+String.valueOf(x6),"Adresse Client: "+String.valueOf(x7),"Code Postal: "+String.valueOf(x8),
                                                    "Gouvernorat: "+String.valueOf(x9),"Ville: "+String.valueOf(x10),"Etat: "+String.valueOf(x11)));
                                        }
                                    }else if (empty_checked==0){
                                        androidx.appcompat.app.AlertDialog.Builder dialog =new androidx.appcompat.app.AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
                                        dialog.setTitle("Info !");
                                        dialog.setMessage("Vous n'avez aucune tâche en cours...");
                                        dialog.setIcon(R.drawable.ic_info_white_24dp);
                                        dialog.setCancelable(false);
                                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        });
                                        androidx.appcompat.app.AlertDialog alert = dialog.create();
                                        alert.setOnShowListener(new DialogInterface.OnShowListener() {
                                            @Override
                                            public void onShow(DialogInterface dialog) {
                                                Button positiveButton =((androidx.appcompat.app.AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
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

                                    if (status == 1){
                                        progress.hide();
                                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                                        recyclerView.setLayoutManager(linearLayoutManager);
                                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mes_taches);
                                        recyclerView.setAdapter(adapter);

                                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                            @Override
                                            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                                super.onScrolled(recyclerView, dx, dy);
                                                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                                                    fab.hide();
                                                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                                                    fab.show();
                                                }
                                            }
                                        });


                                    }else {
                                        //progress.hide();
                                        AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
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
                                progress.hide();
                                AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
                                dialog.setTitle("Erreur de connexion !");
                                //dialog.setTitle(Html.fromHtml("<font color='##424242'>Erreur de connexion !</font>"));
                                dialog.setIcon(R.drawable.wifi);
                                dialog.setMessage("Veuillez vérifier votre connexion internet ");
                                dialog.setCancelable(false);
                                dialog.setPositiveButton("Réessayer",
                                        new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog, int id){
                                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                                ft.detach(TachesFragment.this).attach(TachesFragment.this).commit();

                                            }

                                        });
                                dialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
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
                                Button positiveButton = alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE);
                                Button negativeButton = alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE);
                                positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));
                                negativeButton.setTextColor(Color.parseColor("#FF0B8B42"));
                                positiveButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
                                negativeButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
                            }
                        });
                MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
                pullToRefresh.setRefreshing(false);
            }
        });
        progress = new ProgressDialog(getActivity());
        progress.setTitle("S'il vous plaît, attendez");
        progress.setMessage("Chargement de vos tâches en cours...");
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIcon(R.drawable.ic_schedule_grey_500_24dp);
        progress.show();


        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        final List<Mes_taches> mes_taches = new ArrayList<>();
        JSONObject params = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST,Constants.BASE_URL+"get_tasks_user?userId="+LoginActivity.id_string, params, new Response.Listener<JSONObject>() {

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int status = response.getInt("status");
                            int empty_checked= response.getInt("empty");
                            if(empty_checked>0){
                                JSONArray tasks = response.getJSONArray("data");
                                for (int i = 0; i < tasks.length(); i++) {
                                    JSONObject temp = tasks.getJSONObject(i);
                                    x =temp.getString("N_cotation");
                                    x1 =temp.getString("code_agence");
                                    x2 =temp.getString("agence");
                                    x3 =temp.getString("Inter");
                                    x4 =temp.getString("Nom_client");
                                    x5 =temp.getString("num_tele");
                                    x6 =temp.getString("CIN");
                                    x7 =temp.getString("Adresse_client");
                                    x8 =temp.getString("Code_postal");
                                    x9 =temp.getString("Gouvernorat");
                                    x10 =temp.getString("Ville");
                                    x11 =temp.getString("Etat");
                                    mes_taches.add(new Mes_taches("Cotation","N° Cotation: "+String.valueOf(x),"Agence: "+String.valueOf(x2),"Code Agence: "+String.valueOf(x1),
                                            "Intermediaire: "+String.valueOf(x3),"Nom Client: "+String.valueOf(x4),"N° Telephone: "+String.valueOf(x5),
                                            "N°CIN / Registre Commerce: "+String.valueOf(x6),"Adresse Client: "+String.valueOf(x7),"Code Postal: "+String.valueOf(x8),
                                            "Gouvernorat: "+String.valueOf(x9),"Ville: "+String.valueOf(x10),"Etat: "+String.valueOf(x11)));
                                }
                            }else if (empty_checked==0){
                                androidx.appcompat.app.AlertDialog.Builder dialog =new androidx.appcompat.app.AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
                                dialog.setTitle("Info !");
                                dialog.setMessage("Vous n'avez aucune tâche en cours...");
                                dialog.setIcon(R.drawable.ic_info_white_24dp);
                                dialog.setCancelable(false);
                                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                androidx.appcompat.app.AlertDialog alert = dialog.create();
                                alert.setOnShowListener(new DialogInterface.OnShowListener() {
                                    @Override
                                    public void onShow(DialogInterface dialog) {
                                        Button positiveButton =((androidx.appcompat.app.AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
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

                            if (status == 1){
                                progress.hide();
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                                recyclerView.setLayoutManager(linearLayoutManager);
                                RecyclerViewAdapter adapter = new RecyclerViewAdapter(mes_taches);
                                recyclerView.setAdapter(adapter);

                                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                    @Override
                                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                        super.onScrolled(recyclerView, dx, dy);
                                        if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                                            fab.hide();
                                        } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                                            fab.show();
                                        }
                                    }
                                });


                            }else {
                                //progress.hide();
                                AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
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
                        progress.hide();
                        AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity(),R.style.CustomDialogTheme);
                        dialog.setTitle("Erreur de connexion !");
                        //dialog.setTitle(Html.fromHtml("<font color='##424242'>Erreur de connexion !</font>"));
                        dialog.setIcon(R.drawable.wifi);
                        dialog.setMessage("Veuillez vérifier votre connexion internet ");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("Réessayer",
                                new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int id){
                                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                                        ft.detach(TachesFragment.this).attach(TachesFragment.this).commit();

                                    }

                                });
                        dialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
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
                        Button positiveButton = alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE);
                        Button negativeButton = alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE);
                        positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));
                        negativeButton.setTextColor(Color.parseColor("#FF0B8B42"));
                        positiveButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
                        negativeButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));
                    }
                });
        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
        fab =(FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), Main8Activity.class);
                startActivity(intent);
            }
        });
        return rootView;

    }
    List intializeData(List<Mes_taches> mes_taches1){
        return mes_taches1;
    }

}
