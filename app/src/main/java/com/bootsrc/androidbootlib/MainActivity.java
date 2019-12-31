package com.bootsrc.androidbootlib;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.bootsrc.androidbootlib.consts.FragmentCode;
import com.bootsrc.androidbootlib.ui.msg.MsgFragment;
import com.bootsrc.androidbootlib.ui.find.FindFragment;
import com.bootsrc.androidbootlib.ui.home.HomeFragment;
import com.bootsrc.androidbootlib.ui.mine.MineFragment;
import com.bootsrc.bootlib.base.BootActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;

public class MainActivity extends BootActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    /**
     * 底部导航栏
     */
    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    private String[] mTitles;
    private FragmentManager fManager;
    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private MineFragment mineFragment;
    private MsgFragment msgFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTitles = this.getResources().getStringArray(R.array.home_titles);
        defaultTitle = mTitles[0];
        super.onCreate(savedInstanceState);

        initViews();
        initListeners();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initViews() {
        fManager = getSupportFragmentManager();
        setChioceItem(0);
    }

    protected void initListeners() {
        navView.setOnNavigationItemSelectedListener(this);
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
        setToolBarTitle(menuItem.getTitle() + "");
        switch (menuItem.getItemId()) {
            case R.id.item_home:
                setChioceItem(FragmentCode.HOME);
                return true;
            case R.id.item_find:
                setChioceItem(FragmentCode.FIND);
                return true;
            case R.id.item_msg:
                setChioceItem(FragmentCode.MSG);
                return true;
            case R.id.item_mine:
                setChioceItem(FragmentCode.MINE);
                return true;
        }
        return false;
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
//            ClickUtils.exitBy2Click(2000, this);
//        }
        onBackPressed();
        return true;
    }

    @Override
    public void onFinish() {
        finish();
    }

    public void setChioceItem(int index) {
        FragmentTransaction transaction = fManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case FragmentCode.HOME:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.nav_host_fragment, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;

            case FragmentCode.FIND:
                if (findFragment == null) {
                    findFragment = new FindFragment();
                    transaction.add(R.id.nav_host_fragment, findFragment);
                } else {
                    transaction.show(findFragment);
                }
                break;

            case FragmentCode.MSG:
                if (msgFragment == null) {
                    msgFragment = new MsgFragment();
                    transaction.add(R.id.nav_host_fragment, msgFragment);
                } else {
                    transaction.show(msgFragment);
                }
                break;

            case FragmentCode.MINE:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.nav_host_fragment, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (findFragment != null) {
            transaction.hide(findFragment);
        }
        if (msgFragment != null) {
            transaction.hide(msgFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }
}
