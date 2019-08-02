package com.quangle.rentingutilities.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.quangle.rentingutilities.R;

public class BaseActivity extends AppCompatActivity {

    protected ProgressBar progressBar;
    private static Context mContext;

    protected final void onCreate(Bundle savedInstanceState, int layoutId) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);

        mContext = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the home icon
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.toolbarprogress);
        progressBar.setVisibility(View.GONE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.hideProgressBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTitleActionBar(String title) {
        getSupportActionBar().setTitle(title);
    }

    public static void startHomeActivityAtTab(int tabItemId) {

        Intent intent = new Intent(mContext, HomeActivity.class);
        intent.putExtra(HomeActivity.ARG_TAB_ITEM_ID, tabItemId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//clear all top activity
        mContext.startActivity(intent);
    }

    public static void startHomeActivityAtTab(int tabIndex, int topItemsTabNavIndex) {

        Intent intent = new Intent(mContext, HomeActivity.class);
        intent.putExtra(HomeActivity.ARG_TAB_ITEM_ID, tabIndex);
        intent.putExtra("topItemsTabNavIndex", topItemsTabNavIndex);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//clear all top activity
        mContext.startActivity(intent);
    }

}
