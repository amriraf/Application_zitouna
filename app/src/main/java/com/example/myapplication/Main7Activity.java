package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.example.myapplication.ui.LoginActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Main7Activity extends AppCompatActivity {

    private int[] tabIcons = {
            R.drawable.ic_contact_phone_white_24dp,
            R.drawable.ic_sms_white_24dp
    };
    ViewPager viewPager;
    TabLayout tabLayout;
    MyFragmentPagerAdapter myFragmentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.mygradient_4));
        }
        viewPager = (ViewPager) findViewById(R.id.viewPager_1);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_1);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    protected void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment_1(),"Tél");
        adapter.addFragment(new SecondFragment_1(),"SMS");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList= new ArrayList<>();
        public ViewPagerAdapter(@NonNull FragmentManager manager) {
            super(manager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment,String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        public CharSequence getPageTitle(int position){
            return mFragmentTitleList.get(position);
        }
    }
    private void setupTabIcons(){
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
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
                AlertDialog.Builder dialog =new AlertDialog.Builder(Main7Activity.this,R.style.CustomDialogTheme);
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

