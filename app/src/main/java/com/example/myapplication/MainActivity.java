package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.aware.DiscoverySession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

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

import com.example.myapplication.ui.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import static com.example.myapplication.ui.LoginActivity.role1;

public class MainActivity extends AppCompatActivity  {
    Button facebook;
    private AppBarConfiguration mAppBarConfiguration;
    String role;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.mygradient_4));
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        role = getIntent().getExtras().getString("role");
        if (role.equals("agent")){
            MenuItem settings = menu.findItem(R.id.parametrage);
            settings.setVisible(false);
        }else if (role.equals("admin")){
            MenuItem settings = menu.findItem(R.id.taches);
            settings.setVisible(false);
        }


        navigationView.setItemIconTintList(null);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send,R.id.parametrage, R.id.taches)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.BottomNavigationView_home);
        Menu menu1 = bottomNavigationView.getMenu();
        final String role1 = LoginActivity.role1;

        if (role1.equals("admin")){
            //bottomNavigationView.getMenu().findItem(R.id.taches).setTitle( "Paramètres");
            //MenuItem settings1 = menu1.findItem(R.id.taches);
            //settings1.setVisible(false);
        }else if (role1.equals("agent")){
            //bottomNavigationView.getMenu().findItem(R.id.taches).setTitle( "Tâches");
            //bottomNavigationView.getMenu().findItem(R.id.taches).setIcon(R.drawable.ic_description_white_24dp);
            //MenuItem settings1 = menu1.findItem(R.id.parametrage);
            //settings1.setVisible(false);
            //menu1.removeItem(R.id.parametrage);
            //menu1.add("Tâches").setIcon(R.drawable.ic_description_white_24dp);
            bottomNavigationView.getMenu().clear();
            bottomNavigationView.inflateMenu(R.menu.bottom_navigation_1);
        }
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        facebook = findViewById(R.id.button2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }




    /*public void OpenFacebookPage(View view){
        Intent browser= new Intent(Intent.ACTION_VIEW,Uri.parse(getFacebookPageURL(MainActivity.this)));
        startActivity(browser);
    }*/

    public void OpenFacebookPage(View view){
        Intent browser= new Intent(Intent.ACTION_VIEW,Uri.parse(getFacebookPageURL(MainActivity.this)));
        startActivity(browser);
    }

    public static String FACEBOOK_URL = "https://www.facebook.com/zitounatakaful.com.tn/";
    public static String FACEBOOK_PAGE_ID = "YourPageName";

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }
    public void OpenLinkedin(View view){
        Intent browser= new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.linkedin.com/company/assurances-zitouna-takaful/"));
        startActivity(browser);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_Deconnexion:
                // ContextThemeWrapper ctw =new ContextThemeWrapper(this,R.style.LE_THEME);
                AlertDialog.Builder dialog =new AlertDialog.Builder(MainActivity.this,R.style.CustomDialogTheme);
                dialog.setTitle("Déconnexion");
                //dialog.setTitle(Html.fromHtml("<font color='#424242'>Déconnexion</font>"));
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



