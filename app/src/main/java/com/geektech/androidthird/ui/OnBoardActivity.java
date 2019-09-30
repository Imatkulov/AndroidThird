package com.geektech.androidthird.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.geektech.androidthird.R;
import com.google.android.material.tabs.TabLayout;

public class OnBoardActivity extends AppCompatActivity {


    public static void start(Context context){
        context.startActivity(new Intent(context, OnBoardActivity.class));
    }
    ViewPager mViewPager;
    TabLayout tabLayout;
    TextView textView;
    Button buttonskip, button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        buttonskip = findViewById(R.id.buttonSkip);
        button  = findViewById(R.id.it_button);

        mViewPager =  findViewById(R.id.viewpager);
        mViewPager.setAdapter(new onBoardAdapter(this));
        tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mViewPager, true);

        textView = findViewById(R.id.textView);

    }
    public class onBoardAdapter extends PagerAdapter {

        private Context context;

        public onBoardAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            LayoutInflater inflater = LayoutInflater.from(context);
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.activity_on_board, collection, false);
            ImageView onBoardImage = layout.findViewById(R.id.it_imageView);
            switch (position){
                case 0:
                    onBoardImage.setImageDrawable(getResources().getDrawable(R.drawable.lets_start));
                    layout.setBackgroundColor(getResources().getColor(R.color.colorPrimary1));
                    textView.setText("Welcome");
                    break;
                case 1:
                    onBoardImage.setImageDrawable(getResources().getDrawable(R.drawable.lets_start2));
                    layout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark1));
                    textView.setText("press next");
                    break;
                case 2:
                    onBoardImage.setImageDrawable(getResources().getDrawable(R.drawable.smile1));
                    layout.setBackgroundColor(getResources().getColor(R.color.colorAccent1));
                    textView.setText("also next");
                    break;
                case 3:
                    onBoardImage.setImageDrawable(getResources().getDrawable(R.drawable.smile2));
                    layout.setBackgroundColor(getResources().getColor(R.color.colorAccent2));
                    textView.setText("press start");
                    break;
            }
            collection.addView(layout);
            return layout;

        }
    }

}
