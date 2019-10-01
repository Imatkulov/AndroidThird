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

    public void Skip(View view) {
    }

    public void onClickNext(View view) {
                int i = mViewPager.getCurrentItem();
                if (i==4){
                    MainActivity.start(this);
                    finish();
                }else {
                    mViewPager.setCurrentItem(++i);
                }

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
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_view_pager, collection, false);
            ImageView onBoardImage = layout.findViewById(R.id.it_imageView);
            TextView textViewItem = layout.findViewById(R.id.textView);
            switch (position){
                case 0:
                    onBoardImage.setImageDrawable(context.getResources().getDrawable(R.drawable.group4));
//                    layout.setBackgroundColor(getResources().getColor(R.color.colorPrimary1));
                    textViewItem.setText("В данном приложении можете учиться))");
                    break;
                case 1:
                    onBoardImage.setImageDrawable(context.getResources().getDrawable(R.drawable.update));
//                    layout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark1));
                    textViewItem.setText("В данном приложении можете обновить");
                    break;
                case 2:
                    onBoardImage.setImageDrawable(context.getResources().getDrawable(R.drawable.delete));
//                    layout.setBackgroundColor(getResources().getColor(R.color.colorAccent1));
                    textViewItem.setText("В данном приложении можете удалить");
                    break;
                case 3:
                    onBoardImage.setImageDrawable(context.getResources().getDrawable(R.drawable.thank));
//                    layout.setBackgroundColor(getResources().getColor(R.color.colorAccent2));
                    textViewItem.setText("Спасибо что вы с нами");
                    break;
            }
            collection.addView(layout);
            return layout;

        }
    }

}
