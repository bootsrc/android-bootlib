package com.bootsrc.androidbootlib.ui.home;

import android.os.Bundle;

import com.bootsrc.androidbootlib.R;
import com.bootsrc.bootlib.base.BootActivity;

public class DemoActivity extends BootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        defaultTitle = "实例";
        super.onCreate(savedInstanceState);
        setBackArrow();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    public void onFinish() {
        finish();
    }
}
