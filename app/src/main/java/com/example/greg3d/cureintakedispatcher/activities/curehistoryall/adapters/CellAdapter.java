package com.example.greg3d.cureintakedispatcher.activities.curehistoryall.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.controller.DBController;
import com.example.greg3d.cureintakedispatcher.model.FarmacyHistoryModel;

import java.util.Date;
import java.util.List;

/**
 * Created by greg3d on 01.10.17.
 */
public class CellAdapter extends ArrayAdapter<FarmacyHistoryModel>
{
    public CellAdapter(Context context) {
        super(context, R.layout.cure_cell, getData());
    }

    public CellAdapter(Context context, List<FarmacyHistoryModel> data) {
        super(context, R.layout.cure_cell, data);
    }


    private static List<FarmacyHistoryModel> getData(){
        return DBController.getAllFarmacyHistoryRecords();
    }

    private static List<FarmacyHistoryModel> getData(Date startDate, Date endDate){
        return DBController.getFarmacyHistoryRecordsByDate(startDate, endDate);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FarmacyHistoryModel cell = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.cure_cell, null);

        return com.example.greg3d.cureintakedispatcher.activities.curehistory.adapters.CellAdapter
                .getView(convertView, cell);
    }
}
