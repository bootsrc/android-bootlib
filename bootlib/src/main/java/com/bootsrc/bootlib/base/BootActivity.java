package com.bootsrc.bootlib.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bootsrc.bootlib.R;
import com.bootsrc.bootlib.R2;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BootActivity extends AppCompatActivity {
    protected String defaultTitle = "";
    private Unbinder unbinder;

    @BindView(R2.id.toolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        toolbar.setTitle(defaultTitle);
        setSupportActionBar(toolbar);
    }

    protected abstract int getLayoutId();

    /**
     * 设置左上角back按钮
     */
    public void setBackArrow() {

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp);
        //给ToolBar设置左侧的图标
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(upArrow);
            // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    /**
     * 点击左上角的返回按钮，结束本Activity
     * home就是左上角的小箭头，在toolbar上
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onFinish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 抽象方法，用于结束activity
     */
    public abstract void onFinish();


    @Override
    public void onBackPressed() {
        onFinish();
        unbinder.unbind();
    }

    protected void setToolBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}
