package com.example.greg3d.cureintakedispatcher.helpers;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Created by greg3d on 28.10.17.
 */

public class GridViewHelper {

    private GridView gridView;
    private final CellHelper cellHelper = new CellHelper();

    public GridViewHelper(Activity activity, int id){
        this.gridView = (GridView)activity.findViewById(id);
        // Интервал между строк
        this.gridView.setVerticalSpacing(5);
        // Интервал между столбцов
        this.gridView.setHorizontalSpacing(10);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cellHelper.resetSelect();
                cellHelper.setSelect(view, id);
            }
        });

    }

    public GridViewHelper setAdapter(ListAdapter adapter){
        this.gridView.setAdapter(adapter);
        return this;
    }

    public long getSelectedId(){
        return this.cellHelper.getId();
    }


}