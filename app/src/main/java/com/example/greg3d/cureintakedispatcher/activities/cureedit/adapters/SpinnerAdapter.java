package com.example.greg3d.cureintakedispatcher.activities.cureedit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.model.FarmacyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg3d on 28.10.17.
 */

public class SpinnerAdapter extends ArrayAdapter<String> {

    public SpinnerAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_spinner_item, getData());
        this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public static List<String> getData(){
        List<FarmacyModel> list = DBHelper.getRecords(FarmacyModel.class);
        List<String> result = new ArrayList<>();
        for(FarmacyModel model: list){
            result.add(model.name);
        }
        return result;
    }

//    @Override
//    public View getDropDownView(int position, View convertView,
//                                ViewGroup parent) {
//
//        return getCustomView(position, convertView, parent);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        return getCustomView(position, convertView, parent);
//    }

//    public View getCustomView(int position, View convertView, ViewGroup parent) {
//        FarmacyModel cell = getItem(position);
//
//        if (convertView == null)
//            convertView = LayoutInflater.from(getContext())
//                    .inflate(R.layout.cure_item, null);
//
//        TextView label = (TextView) convertView.findViewById(R.id.ce_CureItem);
//        label.setText(cell.name);
//        return convertView;
//    }
}
