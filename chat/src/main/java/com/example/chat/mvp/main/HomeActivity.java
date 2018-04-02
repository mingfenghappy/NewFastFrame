package com.example.chat.mvp.main;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.chat.R;
import com.example.chat.base.Constant;
import com.example.chat.base.SlideBaseActivity;
import com.example.chat.mvp.settings.SettingsActivity;
import com.example.chat.mvp.wallpaper.WallPaperActivity;
import com.example.chat.mvp.search.SearchActivity;
import com.example.chat.mvp.searchFriend.SearchFriendActivity;
import com.example.chat.mvp.selectFriend.SelectedFriendsActivity;
import com.example.commonlibrary.BaseActivity;
import com.example.commonlibrary.utils.ToastUtils;


/**
 * 项目名称:    NewFastFrame
 * 创建人:      陈锦军
 * 创建时间:    2017/12/24     11:59
 * QQ:         1981367757
 */

public class HomeActivity extends SlideBaseActivity {





    @Override
    public void updateData(Object o) {

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ((HomeFragment) currentFragment).notifyNewIntentCome(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu_layout, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = (String) item.getTitle();
        switch (title) {
            case "搜索":
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case "添加好友":
                ToastUtils.showShortToast("点击了添加好友");
                SearchFriendActivity.start(this);
                break;
            case "建群":
                ToastUtils.showShortToast("点击了创建群");
                Intent selectIntent = new Intent(this, SelectedFriendsActivity.class);
                selectIntent.putExtra("from", "createGroup");
                startActivity(selectIntent);
                break;
            case "背景":
                ToastUtils.showShortToast("点击了背景");
                Intent wallPaperIntent = new Intent(this, WallPaperActivity.class);
                wallPaperIntent.putExtra("from", "wallpaper");
                startActivityForResult(wallPaperIntent, Constant.REQUEST_CODE_SELECT_WALLPAPER);
                break;
            case "设置":
                ToastUtils.showShortToast("点击了设置");
                SettingsActivity.start(this, Constant.REQUEST_CODE_EDIT_USER_INFO);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected boolean isNeedHeadLayout() {
        return false;
    }

    @Override
    protected boolean isNeedEmptyLayout() {
        return false;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        addOrReplaceFragment(HomeFragment.newInstance(), R.id.fl_activity_home_container);
    }


    private long mExitTime = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            ToastUtils.showShortToast("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }
}
