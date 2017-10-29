package com.example.greg3d.cureintakedispatcher.framework.helpers;

import android.view.View;

/**
 * Created by greg3d on 30.04.17.
 */
public class ViewHelper {
    private View view;

    public ViewHelper(View view){
        this.view = view;
    }

    public <T extends View> boolean idEquals(T view){
        return this.view.getId() == view.getId();
    }
}
