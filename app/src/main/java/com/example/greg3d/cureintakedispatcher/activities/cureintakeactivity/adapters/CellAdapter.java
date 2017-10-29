package com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.controls.LastIntakeView;
import com.example.greg3d.cureintakedispatcher.controller.DBController;
import com.example.greg3d.cureintakedispatcher.framework.factory.ViewFactory;
import com.example.greg3d.cureintakedispatcher.model.LastIntakeRecord;

/**
 * Created by greg3d on 01.10.17.
 */
public class CellAdapter extends ArrayAdapter<LastIntakeRecord>
{
    public CellAdapter(Context context) {
        super(context, R.layout.schemecell, DBController.getInstance().getLastIntakeRecords());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LastIntakeRecord cell = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.schemecell, null);

        LastIntakeView controls = new LastIntakeView();
        ViewFactory.InitView(convertView, controls);


        controls.scheme_TextView.setText(cell.schemeName);
        controls.currentIntake_TextView.setText(cell.currentIntake);
        controls.intakeDate_TextView.setText(cell.lastIntake);
        controls.balanceDays_TextView.setText(cell.dayBalance);

        return convertView;
    }
}
