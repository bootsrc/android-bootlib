package com.bootsrc.androidbootlib;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.bootsrc.androidbootlib.consts.FragmentCode;
import com.bootsrc.androidbootlib.ui.msg.MsgFragment;
import com.bootsrc.androidbootlib.ui.explore.ExploreFragment;
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
    private ExploreFragment exploreFragment;
    private MineFragment mineFragment;
    private MsgFragment msgFragment;
    private long lastClickBackTime;

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
            case R.id.item_explore:
                setChioceItem(FragmentCode.EXPLORE);
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

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastClickBackTime > 2000) { // 后退阻断
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            lastClickBackTime = System.currentTimeMillis();
        } else { // 关掉app
            super.onBackPressed();
        }
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

            case FragmentCode.EXPLORE:
                if (exploreFragment == null) {
                    exploreFragment = new ExploreFragment();
                    transaction.add(R.id.nav_host_fragment, exploreFragment);
                } else {
                    transaction.show(exploreFragment);
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
        if (exploreFragment != null) {
            transaction.hide(exploreFragment);
        }
        if (msgFragment != null) {
            transaction.hide(msgFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }
}
