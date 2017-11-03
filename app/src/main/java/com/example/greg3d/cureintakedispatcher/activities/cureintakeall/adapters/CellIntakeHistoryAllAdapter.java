package com.example.greg3d.cureintakedispatcher.activities.cureintakeall.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.adapters.CellAdapter;
import com.example.greg3d.cureintakedispatcher.controller.DBController;
import com.example.greg3d.cureintakedispatcher.model.LastIntakeRecord;

import java.util.List;

/**
 * Created by greg3d on 01.10.17.
 */
public class CellIntakeHistoryAllAdapter extends ArrayAdapter<LastIntakeRecord>
{
    public CellIntakeHistoryAllAdapter(Context context) {
        super(context, R.layout.schemecell, getData());
    }

    private static List<LastIntakeRecord> getData(){
        return DBController.getInstance().getAllIntakeHistoryRecords();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LastIntakeRecord cell = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.schemecell, null);

        return CellAdapter.getView(convertView, cell);
    }
}
