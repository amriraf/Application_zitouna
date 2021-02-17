package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class productActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ViewPager2 viewPager2 = findViewById(R.id.viewpager2);
        /*List<Slideritem> slideritems = new ArrayList<>();
        slideritems.add(new Slideritem(R.drawable.yn));
        slideritems.add(new Slideritem(R.drawable.yn));
        slideritems.add(new Slideritem(R.drawable.yn));
        viewPager2.setAdapter(new Slideradapter(slideritems,viewPager2));*/
        List<product> product = new ArrayList<>();
        product product1 = new product();
        product1.star=4.8f;
        product1.imageurl="https://www.infinityandroid.com/images/india_taj_mahal.jpg";
        product1.image = R.drawable.yn;
        product1.title="rr";
        product1.product="imtiez";
        product.add(product1);

        product product2 = new product();
        product2.star=4.8f;
        product2.imageurl="https://www.infinityandroid.com/images/india_taj_mahal.jpg";
        product2.image = R.drawable.yn;
        product2.title="rr";
        product2.product="imtiez";
        product.add(product2);

        product product3 = new product();
        product3.star=4.8f;
        product3.imageurl="viewPager2";
        product3.image = R.drawable.yn;
        product3.title="rr";
        product3.product="imtiez";
        product.add(product3);
        viewPager2.setAdapter(new productAdapter(product));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r =1 - Math.abs(position);
                page.setScaleY(0.95f + r * 0.05f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
    }
}
