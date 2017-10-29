package com.example.greg3d.cureintakedispatcher.controller;

import com.example.greg3d.cureintakedispatcher.framework.annotations.Name;
import com.example.greg3d.cureintakedispatcher.helpers.CSVHelper;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.model.BaseModel;
import com.example.greg3d.cureintakedispatcher.model.FarmacyModel;
import com.example.greg3d.cureintakedispatcher.model.HistoryRecordModel;
import com.example.greg3d.cureintakedispatcher.model.SchemeModel;

import java.util.List;

/**
 * Created by greg3d on 27.10.17.
 */

public class CSVController {
    String LOG_TAG = "CSVController";

    private static CSVController instance;

    public static CSVController getInstance(){
        if(instance == null)
            instance = new CSVController();
        return instance;
    }

    public static void writeTablesToSD(){
        writeTable(HistoryRecordModel.class);
        writeTable(FarmacyModel.class);
        writeTable(SchemeModel.class);
    }

    private static <T extends BaseModel> void writeTable(Class<T> clazz){
        CSVHelper.getInstance().writeFileSD(clazz.getAnnotation(Name.class).value() + ".csv", DBHelper.getRecords(clazz));
    }

    public static void readTablesFromSD(){
        readTable(HistoryRecordModel.class);
        readTable(FarmacyModel.class);
        readTable(SchemeModel.class);
    }

    private static <T extends BaseModel> void readTable(Class<T> clazz){
        try {
            T model = clazz.newInstance();
            List<T> list = CSVHelper.getInstance().readFileSD(model.getClassName() + ".csv", clazz);
            DBHelper.getInstance().dropTable(model);
            DBHelper.getInstance().createTable(model);
            for(T record: list)
                DBHelper.getInstance().insertRecord(record);
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
