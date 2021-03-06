package com.example.greg3d.cureintakedispatcher.activities.editintake.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.model.FarmacyModel;

import java.util.List;

/**
 * Created by greg3d on 28.10.17.
 */

public class SpinnerAdapter extends ArrayAdapter<FarmacyModel> {

    public SpinnerAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_spinner_item, getData());
        this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

//    public static List<String> getData(){
//        List<FarmacyModel> list = DBHelper.getRecords(FarmacyModel.class);
//        List<String> result = new ArrayList<>();
//        for(FarmacyModel model: list){
//            result.add(model.name);
//        }
//        return result;
//    }

    public static List<FarmacyModel> getData(){
        return DBHelper.getRecords(FarmacyModel.class);
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        FarmacyModel cell = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.cure_item, null);

        TextView label = (TextView) convertView.findViewById(R.id.ce_CureItem);
        label.setText(cell.name);
        selectedFarmacyId = cell.id;
        Log.d("LLL", "" + position);
        return convertView;
    }

    private Integer selectedFarmacyId;

    public Integer getSelectedFarmacyId(){
        return selectedFarmacyId;
    }
}
