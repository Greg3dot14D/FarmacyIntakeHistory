package com.example.greg3d.cureintakedispatcher.activities.flipper.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.greg3d.cureintakedispatcher.activities.flipper.PageFragment;

/**
 * Created by greg3d on 03.11.17.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager mgr) {
        super(mgr);
    }
    @Override
    public int getCount() {
        return(10);
    }
    @Override
    public Fragment getItem(int position) {
        return(PageFragment.newInstance(position));
    }
}
