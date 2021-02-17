package com.example.myapplication.ui.slideshow;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;

public class SlideshowFragment extends Fragment {
 private ImageView youtube, slideshare;
    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        youtube = (ImageView) root.findViewById(R.id.youtube);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browser= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/zitounatakaful"));
                startActivity(browser);
            }
        });
        slideshare =(ImageView) root.findViewById(R.id.slideshare);
        slideshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browser= new Intent(Intent.ACTION_VIEW,Uri.parse("https://fr.slideshare.net/ZitounaTakaful/vf-contenu-slide-share-1-17-06-2014"));
                startActivity(browser);

            }
        });
        //final TextView textView = root.findViewById(R.id.text_slideshow);
        //slideshowViewModel.getText().observe(this, new Observer<String>() {
            //@Override
            //public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            //}
        //});
        return root;
    }
}