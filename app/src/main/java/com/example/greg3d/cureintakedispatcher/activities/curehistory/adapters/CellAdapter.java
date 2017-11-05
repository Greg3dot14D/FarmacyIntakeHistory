package com.example.greg3d.cureintakedispatcher.activities.curehistory.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.curehistory.controls.CureHistoryView;
import com.example.greg3d.cureintakedispatcher.controller.DBController;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.model.FarmacyHistoryModel;

import java.util.List;

/**
 * Created by greg3d on 01.10.17.
 */
public class CellAdapter extends ArrayAdapter<FarmacyHistoryModel>
{
    public CellAdapter(Context context) {
        super(context, R.layout.cure_cell, getData());
    }

    private static List<FarmacyHistoryModel> getData(){
        return DBController.getLastFarmacyHistoryRecords();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FarmacyHistoryModel cell = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.cure_cell, null);
        return getView(convertView, cell);
    }

    public static View getView(View convertView, FarmacyHistoryModel cell) {

        CureHistoryView controls = new CureHistoryView();

        ActivityFactory.InitActivity(convertView, controls);

        controls.id_TextView.setValue(cell.id);
        controls.farmacyId_TextView.setValue(cell.farmacyId);
        controls.cureName_TextView.setText(cell.name);
        controls.cureValume_TextView.setText(String.format("Объем %s",cell.volume));
        controls.curePrice_TextView.setText(String.format("Цена %s руб",cell.price));
        controls.purchaseDate_TextView.setDate(cell.purchaseDate);

        return convertView;
    }
}
