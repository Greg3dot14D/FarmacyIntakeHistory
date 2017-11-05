package com.example.greg3d.cureintakedispatcher.activities.flipper;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.curehistory.CureHistoryActivity;
import com.example.greg3d.cureintakedispatcher.activities.curehistoryall.CureHistoryAllActivity;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.CureIntakeActivity;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeall.CureIntakeHistoryActivity;
import com.example.greg3d.cureintakedispatcher.activities.flipper.adapters.SamplePagerAdapter;
import com.example.greg3d.cureintakedispatcher.drawers.NavigationDrawerFragment;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg3d on 03.11.17.
 *
 */
public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener{

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

        setContentView(viewPager);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View view) {
        cureIntakeActivity.onClick(this, view);
        cureHistoryActivity.onClick(this, view);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }
}