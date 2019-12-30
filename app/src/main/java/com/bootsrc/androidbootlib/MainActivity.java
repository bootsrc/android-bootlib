package com.bootsrc.androidbootlib;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.bootsrc.bootlib.adapter.FragmentAdapter;
import com.bootsrc.bootlib.base.BootActivity;
import com.bootsrc.bootlib.util.CollectionUtils;
import com.bootsrc.androidbootlib.ui.find.FindFragment;
import com.bootsrc.androidbootlib.ui.home.HomeFragment;
import com.bootsrc.androidbootlib.ui.mine.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BootActivity implements ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.view_pager)
    ViewPager viewPager;

    /**
     * 底部导航栏
     */
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    private String[] mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTitles = this.getResources().getStringArray(R.array.home_titles);
//        setToolBarTitle(mTitles[0]);
        defaultTitle = mTitles[0];
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initViews();
        initListeners();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initViews() {
        //主页内容填充
        Fragment[] fragments = new Fragment[]{
                new HomeFragment(),
                new FindFragment(),
                new MineFragment()
        };
        FragmentAdapter<Fragment> adapter = new FragmentAdapter<>(getSupportFragmentManager(), fragments);
        viewPager.setOffscreenPageLimit(mTitles.length - 1);
        viewPager.setAdapter(adapter);
    }

    protected void initListeners() {
        //主页事件监听
        viewPager.addOnPageChangeListener(this);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    //=============ViewPager===================//

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        MenuItem item = bottomNavigation.getMenu().getItem(position);
        setToolBarTitle(item.getTitle() + "");
        item.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    //================Navigation================//

    /**
     * 底部导航栏点击事件
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int index = CollectionUtils.arrayIndexOf(mTitles, menuItem.getTitle());
        if (index != -1) {
            setToolBarTitle(menuItem.getTitle() + "");
            viewPager.setCurrentItem(index, false);
            return true;
        }
        return false;
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
//            ClickUtils.exitBy2Click(2000, this);
        }
        return true;
    }

    @Override
    public void onFinish() {
        finish();
    }
}
