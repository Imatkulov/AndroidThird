package com.geektech.androidthird.ui.base;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.geektech.androidthird.R;
import com.geektech.androidthird.utils.ToastUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract Integer getResId();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResId());
        ButterKnife.bind(this);
    }
}
