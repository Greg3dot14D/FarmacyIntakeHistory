package com.example.greg3d.cureintakedispatcher.activities.flipper;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.curehistory.CureHistoryActivity;
import com.example.greg3d.cureintakedispatcher.activities.curehistoryall.CureHistoryAllActivity;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.CureIntakeActivity;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeall.CureIntakeHistoryActivity;
import com.example.greg3d.cureintakedispatcher.activities.flipper.adapters.SamplePagerAdapter;
import com.example.greg3d.cureintakedispatcher.controller.CSVController;
import com.example.greg3d.cureintakedispatcher.dialog.MessageDialog;
import com.example.greg3d.cureintakedispatcher.drawers.NavigationDrawerFragment;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg3d on 03.11.17.
 *
 */
public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private CureIntakeActivity cureIntakeActivity;
    private CureIntakeHistoryActivity cureIntakeHistoryActivity;
    private CureHistoryActivity cureHistoryActivity;
    private CureHistoryAllActivity cureHistoryAllActivity;

    private NavigationDrawerFragment drawerFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new DBHelper(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> pages = new ArrayList<>();

        View page = inflater.inflate(R.layout.activity_cure_intake_all, null);
        cureIntakeHistoryActivity = new CureIntakeHistoryActivity(this, page);
        pages.add(page);

        page = inflater.inflate(R.layout.activity_cure_intake, null);
        cureIntakeActivity = new CureIntakeActivity(this, page);
        pages.add(page);

        page = inflater.inflate(R.layout.activity_cure_history, null);
        cureHistoryActivity = new CureHistoryActivity(this, page);
        pages.add(page);

        page = inflater.inflate(R.layout.activity_cure_history_all, null);
        cureHistoryAllActivity = new CureHistoryAllActivity(this, page);
        pages.add(page);

        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(pages);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);

        //setContentView(viewPager);

        //////////////////////////////////////////////////////////////////////////////
        setContentView(R.layout.flipper_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager pager = (ViewPager)findViewById(R.id.pages);
        pager.setAdapter(pagerAdapter);
    }

    @Override
    public void onClick(View view) {
        cureIntakeActivity.onClick(this, view);
        cureHistoryActivity.onClick(this, view);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_import) {
            CSVController.readTablesFromSD();
            CureHistoryActivity.refresh();
            CureIntakeActivity.refresh();
        } else if (id == R.id.nav_export) {
            CSVController.writeTablesToSD();
        } else if (id == R.id.nav_about) {
            new MessageDialog(this, "Эбаот", "Farmacy intake dispatcher\nWriten for Max Reziapkin\n\nBy Greg3D 06.11.2017");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}