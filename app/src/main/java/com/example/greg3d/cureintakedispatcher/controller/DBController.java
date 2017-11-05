package com.example.greg3d.cureintakedispatcher.controller;

import android.database.Cursor;
import android.util.Log;

import com.example.greg3d.cureintakedispatcher.constants.IntakeStatus;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.helpers.Tools;
import com.example.greg3d.cureintakedispatcher.model.BaseModel;
import com.example.greg3d.cureintakedispatcher.model.FarmacyHistoryModel;
import com.example.greg3d.cureintakedispatcher.model.LastIntakeRecord;

import java.util.ArrayList;
import java.util.List;

import static com.example.greg3d.cureintakedispatcher.helpers.DBHelper.getRecords;

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

    public List<LastIntakeRecord> getAllIntakeHistoryRecords() {
        String query =
                " select H.ID as ID, S.ID as SCHEME_ID, S.NAME as S_NAME, H.LAST_DATE as ACTION_DATE, H.INTAKE_NUM as INTAKE_ID_DAYE_NUM, " +
                " F.NAME as F_NAME, S.INTAKE_IN_DAY_COUNT as D_COUNT, H.STATUS as STATUS, S.DURATION as DURATION," +
                " H.DAYS_REMAIND as DAYS_REMAIND " +
                " from [INTAKE_HISTORY_TABLE] H, [INTAKE_SCHEME_TABLE] as S, [FARMACY_TABLE] F " +
                " where S.ID = H.SCHEME_ID and S.FARMACY_ID = F.ID" +
//                " and S.DELETED = 0 " +
                " and H.STATUS < 2 " +
                " ORDER BY H.LAST_DATE DESC";
        return getIntakeRecords(query);
    }

    public static List<FarmacyHistoryModel> getAllFarmacyHistoryRecords(){
        String query =
                " SELECT * FROM [FARMACY_HISTORY_TABLE] FH " +
                " WHERE DELETED = 0 " +
                //" ORDER BY LAST_DATE DESC";
                " ORDER BY LAST_DATE DESC";
        return DBHelper.getRecords(FarmacyHistoryModel.class, query);
    }


    public static List<FarmacyHistoryModel> getLastFarmacyHistoryRecords(){
        String query =
                " SELECT FH.* FROM [FARMACY_TABLE] F, [FARMACY_HISTORY_TABLE] FH " +
                " WHERE F.ID = FH.FARMACY_ID " +
                " AND F.LAST_DATE = FH.LAST_DATE" +
                " AND F.DELETED = 0 " +
                " ORDER BY F.NAME";
        return DBHelper.getRecords(FarmacyHistoryModel.class, query);
    }


    private List<LastIntakeRecord> getIntakeRecords(String query){
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


        public List<LastIntakeRecord> getLastIntakeRecords(){
        String query =
                " select H.ID as ID, S.ID as SCHEME_ID, S.NAME as S_NAME, H.LAST_DATE as ACTION_DATE, H.INTAKE_NUM as INTAKE_ID_DAYE_NUM, " +
                " F.NAME as F_NAME, S.INTAKE_IN_DAY_COUNT as D_COUNT, H.STATUS as STATUS, S.DURATION as DURATION," +
                " H.DAYS_REMAIND as DAYS_REMAIND " +
                " from [INTAKE_HISTORY_TABLE] H, [INTAKE_SCHEME_TABLE] as S, [FARMACY_TABLE] F " +
                " where S.ID = H.SCHEME_ID and S.FARMACY_ID = F.ID" +
                " and H.LAST_DATE = S.LAST_DATE" +
                " and S.DELETED = 0 " +
                " ORDER BY S.NAME";
        return getIntakeRecords(query);
    }

    private LastIntakeRecord fillLastIntakeRecord(Cursor cursor){
        LastIntakeRecord record = new LastIntakeRecord();
        record.schemeName = String.format("%s %s",
                cursor.getString(cursor.getColumnIndex("F_NAME")),
                cursor.getString(cursor.getColumnIndex("S_NAME"))
                );
        String status = "Х.З.";
        switch(cursor.getInt(cursor.getColumnIndex("STATUS"))){
            case IntakeStatus.CANCELED:
                status = "Пропустил";
                break;
            case IntakeStatus.INTAKED:
                status = "Принял   ";
                break;
            case IntakeStatus.NEW:
                status = "Новая    ";
                break;
        }
        record.lastIntakeStatus = status;
        record.lastIntakeDate = Tools.longToDate(cursor.getLong(cursor.getColumnIndex("ACTION_DATE")));
        record.currentIntake = String.format("Номер приема %s из %s",
                cursor.getString(cursor.getColumnIndex("INTAKE_ID_DAYE_NUM")),
                cursor.getString(cursor.getColumnIndex("D_COUNT"))
        );
        record.daysRemaind = String.format("Дней осталось %s",
                cursor.getString(cursor.getColumnIndex("DAYS_REMAIND")));
        record.id = Integer.valueOf(cursor.getString(cursor.getColumnIndex("ID")));
        record.schemeId = Integer.valueOf(cursor.getString(cursor.getColumnIndex("SCHEME_ID")));
        return record;
    }

    //////////////////////////////////////////////////////////////////////////
    public <T extends BaseModel> T getLastCureRecordByDate(T model){
        String query = String.format(
                "SELECT * FROM [%s] WHERE FARMACY_ID = %s ORDER BY LAST_DATE DESC"
                , model.getClassName());
        return getRecords(model, query).get(0);
    }


}
