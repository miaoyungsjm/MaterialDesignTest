package com.example.materialtest;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.materialtest.adapter.MyAdapter;
import com.example.materialtest.bean.Fruit;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private NavigationView mNavigationView;
    private TextView nav_other_tv;

    private FloatingActionButton fab;


    private Fruit[] fruits = {
            new Fruit("Apple", R.drawable.p1),
            new Fruit("Banana", R.drawable.p2),
            new Fruit("Orange", R.drawable.p3),
            new Fruit("Watermelon", R.drawable.p4),
            new Fruit("Pear", R.drawable.p5),
            new Fruit("Grape", R.drawable.p6),
            new Fruit("Cherry", R.drawable.p7)
    };
    private List<Fruit> mList = new ArrayList<>();

    private MyAdapter myAdapter;
    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    swipeRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 抽屉式布局 DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

        // NavigationView
//        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
//        mNavigationView.setCheckedItem(R.id.nav_call);
//        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                mDrawerLayout.closeDrawers();
//                return true;
//            }
//        });

//        View headview = mNavigationView.inflateHeaderView(R.layout.nav_other);
//        nav_tv = (TextView) findViewById(R.id.nav_tv);
//        nav_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "one", Toast.LENGTH_SHORT).show();
//            }
//        });

        // 自定义导航栏
        nav_other_tv = findViewById(R.id.nav_other_tv);
        nav_other_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();
            }
        });

        // 悬浮按钮 FloatingActionButton
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "FAB Click", Toast.LENGTH_SHORT).show();

                // Snackbar
                Snackbar.make(v, "Snackbar Show  - Delete Data -", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Undo Click - Restored Data -",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });


        // 初始化数据
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                mList.add(fruits[j]);
            }
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(mList);
        recyclerView.setAdapter(myAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }
    // 刷新
    private void refresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // handle 更新布局
                mHandler.sendEmptyMessage(0);
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delect:
                Toast.makeText(this, "Delect", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
