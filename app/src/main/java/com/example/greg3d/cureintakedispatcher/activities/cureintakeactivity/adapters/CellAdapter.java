package com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.controls.LastIntakeView;
import com.example.greg3d.cureintakedispatcher.controller.DBController;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.model.LastIntakeRecord;

/**
 * Created by greg3d on 01.10.17.
 */
public class CellAdapter extends ArrayAdapter<LastIntakeRecord>
{
    public CellAdapter(View view) {
        super(view.getContext(), R.layout.schemecell, DBController.getInstance().getLastIntakeRecords());
    }
    public CellAdapter(Context context) {
        super(context, R.layout.schemecell, DBController.getInstance().getLastIntakeRecords());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LastIntakeRecord cell = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.schemecell, null);

        return getView(convertView, cell);
    }

    public static View getView(View convertView, LastIntakeRecord cell){
        LastIntakeView controls = new LastIntakeView();
        ActivityFactory.InitActivity(convertView, controls);

        controls.id_IntegerView.setValue(cell.id);
        controls.schemeId_IntegerView.setValue(cell.schemeId);
        controls.scheme_TextView.setText(cell.schemeName);
        controls.currentIntake_TextView.setText(cell.currentIntake);
        controls.intakeStatus_TextView.setText(cell.lastIntakeStatus);
        controls.intakeDate_TextView.setDate(cell.lastIntakeDate);
        controls.balanceDays_IntegerView.setValue(cell.daysRemaind);

        return convertView;
    }
}
