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
    private boolean isSelected = false;

    public GridViewHelper(View view, int id){
        this.gridView = (GridView)view.findViewById(id);
        this.init();
    }

    public GridViewHelper(Activity view, int id){
        this.gridView = (GridView)view.findViewById(id);
        this.init();
    }

//    public GridViewHelper(View view, int id){
//        this.gridView = (GridView)view.findViewById(id);
//        // Интервал между строк
//        this.gridView.setVerticalSpacing(5);
//        // Интервал между столбцов
//        this.gridView.setHorizontalSpacing(10);
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                cellHelper.resetSelect();
//                cellHelper.setSelect(view, id);
//            }
//        });
//    }

    private void init(){
        // Интервал между строк
        this.gridView.setVerticalSpacing(5);
        // Интервал между столбцов
        this.gridView.setHorizontalSpacing(10);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cellHelper.resetSelect();
                cellHelper.setSelect(view, id);
                setSelected();
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

    public View getView(){
        return this.cellHelper.getView();
    }

    public Object getSelectedObject(){
        return this.gridView.getSelectedItem();
    }

    private void setSelected(){
        //this.gridView.setSelected(true);
        this.isSelected = true;
    }

    public void setUnSelected(){
        //this.gridView.setSelected(false);
        this.isSelected = false;
    }

    public boolean isSelected(){
       //return this.gridView.isSelected();
        return this.isSelected;
    }
}
