package com.example.greg3d.cureintakedispatcher.helpers;

import android.view.View;

import com.example.greg3d.cureintakedispatcher.css.Colors;

/**
 * Created by greg3d on 28.10.17.
 */

public class CellHelper {
    private View view;
    private long id;

    public long getId(){
        return this.id;
    }

    public void resetSelect(){
        if(this.view == null)
            return;
        this.view.setBackgroundColor(Colors.DEFAULT_CELL_COLOR);
    }

    public void setSelect(View view, long id){
        this.id = id;
        this.view = view;
        this.view.setBackgroundColor(Colors.SELECTED_CELL_COLOR);
    }
}
