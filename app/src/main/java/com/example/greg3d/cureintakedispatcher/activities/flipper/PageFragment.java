package com.example.greg3d.cureintakedispatcher.activities.flipper;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by greg3d on 03.11.17.
 */

public class PageFragment extends Fragment {

    private int pageNumber;

    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }
}