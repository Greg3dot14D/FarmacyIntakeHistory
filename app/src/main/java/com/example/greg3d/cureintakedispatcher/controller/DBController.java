package com.example.greg3d.cureintakedispatcher.controller;

import android.database.Cursor;
import android.util.Log;

import com.example.greg3d.cureintakedispatcher.framework.annotations.Name;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.model.BaseModel;
import com.example.greg3d.cureintakedispatcher.model.LastIntakeRecord;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg3d on 16.10.17.
 */

public class DBController {

    String LOG_TAG = "DBController";

    private static DBController instance;

//    public List<HistoryRecordModel> getLastHistoryRecords(){
//        return null;
//    }

    public static DBController getInstance(){
        if(instance == null)
            instance = new DBController();
        return instance;
    }

    public <T extends BaseModel> List<T> selectRecords(Class<T> clazz){

        String query = String.format("SELECT * FROM [%s]", clazz.getAnnotation(Name.class).value());

        Log.d(LOG_TAG, query);

        List<T> list = new ArrayList<>();

        Cursor cursor = DBHelper.getDb().rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                try {
                    list.add(fillRecord(cursor, clazz.newInstance()));
                } catch (InstantiationException e) {
                    throw new RuntimeException(e.getMessage());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e.getMessage());
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    private <T extends BaseModel> T fillRecord(Cursor cursor, T model){
        List<Field> fields = model.getFieldList();

        for(Field f: fields){
            if(f.isAnnotationPresent(Name.class)) {
                String fieldName = f.getAnnotation(Name.class).value();
                model.setValue(fieldName, cursor.getString(cursor.getColumnIndex(fieldName)));
            }
        }
        return model;
    }

    public List<LastIntakeRecord> getLastIntakeRecords(){

        String query = "select S.NAME as S_NAME, H.INTAKE_TIME as ACTION_DATE, H.INTAKE_NUM as INTAKE_ID_DAYE_NUM, " +
                " F.NAME as F_NAME, S.INTAKE_IN_DAY_COUNT as D_COUNT, H.STATUS as STATUS, S.DURATION as DURATION" +
                " from [INTAKE_HISTORY_TABLE] H, [INTAKE_SCHEME_TABLE] as S, [FARMACY_TABLE] F " +
                " where S.ID = H.SCHEME_ID and S.FARMACY_ID = F.ID";

        Log.d(LOG_TAG, query);

        List<LastIntakeRecord> recordList = new ArrayList<>();

        Cursor cursor = DBHelper.getDb().rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                recordList.add(fillLastIntakeRecord(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return recordList;
    }

    private LastIntakeRecord fillLastIntakeRecord(Cursor cursor){
        LastIntakeRecord record = new LastIntakeRecord();
        record.schemeName = String.format("%s %s",
                cursor.getString(cursor.getColumnIndex("F_NAME")),
                cursor.getString(cursor.getColumnIndex("S_NAME"))
                );
        String status = "Х.З.";
        switch(cursor.getInt(cursor.getColumnIndex("STATUS"))){
            case 0:
                status = "Пропустил";
                break;
            case 1:
                status = "Принял";
                break;
        }
        record.lastIntake = String.format("%s %s",
                status,
                cursor.getString(cursor.getColumnIndex("ACTION_DATE")));
        record.currentIntake = String.format("Номер приема %s из %s",
                cursor.getString(cursor.getColumnIndex("INTAKE_ID_DAYE_NUM")),
                cursor.getString(cursor.getColumnIndex("D_COUNT")));
        record.dayBalance = String.format("Дней осталось %s",
                cursor.getString(cursor.getColumnIndex("DURATION")));
        return record;
    }


}
