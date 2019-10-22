package com.geektech.androidthird.ui.onBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geektech.androidthird.R;
import com.geektech.androidthird.data.ViewpagerData;
import com.geektech.androidthird.ui.main.MainActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class OnBoardActivity extends AppCompatActivity {


    public static void start(Context context) {
        context.startActivity(new Intent(context, OnBoardActivity.class));
    }

    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabDots)
    TabLayout tabLayout;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.buttonSkip)
    Button buttonSkip;
    @BindView(R.id.it_button)
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        //TODO init in separate method
//        buttonSkip = findViewById(R.id.buttonSkip);
//        button = findViewById(R.id.it_button);
        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setAdapter(new OnBoardAdapter(getData()));
        tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(mViewPager, true);
//        textView = findViewById(R.id.textView);
    }

    public void Skip(View view) {

    }

    public void onClickNext(View view) {
        int i = mViewPager.getCurrentItem();
        if (i == 3) {
            MainActivity.start(this);
            finish();
        } else {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        }
    }

    private ArrayList<ViewpagerData> getData() {
        ArrayList<ViewpagerData> data = new ArrayList<>();
        data.add(new ViewpagerData(getResources().getString(R.string.intro_name_1), R.drawable.group4));
        data.add(new ViewpagerData(getResources().getString(R.string.intro_name_2), R.drawable.update));
        data.add(new ViewpagerData(getResources().getString(R.string.intro_name_3), R.drawable.delete));
        data.add(new ViewpagerData(getResources().getString(R.string.intro_name_4), R.drawable.thank));

        return data;
    }
}