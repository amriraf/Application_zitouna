package com.example.myapplication.ui.gallery;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.Main2Activity;
import com.example.myapplication.Main5Activity;
import com.example.myapplication.Main6Activity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.Adapterr;

import com.example.myapplication.ui.LoginActivity;
import com.example.myapplication.ui.Model;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class GalleryFragment extends Fragment{
	String role;
	ViewPager viewPager;
	List<Model> models;
	Adapterr adapter;
	Integer[] colors = null;
	ArgbEvaluator argbEvaluator =new ArgbEvaluator();

	private GalleryViewModel galleryViewModel;
	public View onCreateView(@NonNull LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		galleryViewModel =
				ViewModelProviders.of(this).get(GalleryViewModel.class);
		View root = inflater.inflate(R.layout.fragment_gallery, container, false);


		BottomNavigationView bottomNavigationView = (BottomNavigationView) root.findViewById(R.id.BottomNavigationView);
		String role1 = LoginActivity.role1;
        /*if (role1.equals("admin")){
            bottomNavigationView.getMenu().removeItem(R.id.taches);
        }else if (role1.equals("agent")){
            bottomNavigationView.getMenu().removeItem(R.id.parametrage);
        }*/

        /*models = new ArrayList<>();
        models.add(new Model(R.drawable.yn,"zdzd"));
        models.add(new Model(R.drawable.zx,"aaaa"));
        adapter = new Adapterr(models,getContext());
        viewPager = root.findViewById(R.id.viewpager_product);
        viewPager.setPadding(40, 0, 70, 0);
        viewPager.setPageMargin(20);
        viewPager.setAdapter(adapter);
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        /*CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 -Math.abs(position);
                page.setScaleY(0.85f + r *0.15f);
            }
        });*/
		// viewPager.setPageTransformer(compositePageTransformer);
		// viewPager.setPadding(0,0,0,0);

        /*CircleIndicator indicator = (CircleIndicator) root.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        Integer[] colors_temp={
                getResources().getColor(R.color.color_product1),
                getResources().getColor(R.color.color_product2)
        };
        colors = colors_temp;
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position<(adapter.getCount()-1)&& position<(colors.length-1)){
                    viewPager.setBackgroundColor((Integer)argbEvaluator.evaluate(positionOffset,colors[position],colors[position+1]));
                }else{
                    viewPager.setBackgroundColor(colors[colors.length-1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/



		ImageButton imageButton_2 =(ImageButton)root.findViewById(R.id.imageButtonmouncieb);
		imageButton_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(getActivity(), Main6Activity.class);
				startActivity(intent);
			}
		});
		ImageButton imageButton_1 =(ImageButton)root.findViewById(R.id.imageButtonimtiez);
		imageButton_1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(getActivity(), Main5Activity.class);
				startActivity(intent);
			}
		});
		ImageButton imageButton= (ImageButton) root.findViewById(R.id.simpleImageButton);
		imageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(getActivity(), Main2Activity.class);
				startActivity(intent);
			}
		});
		return root;
	}
}

