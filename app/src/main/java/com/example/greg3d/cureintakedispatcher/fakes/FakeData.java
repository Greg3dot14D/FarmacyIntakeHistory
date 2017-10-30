package com.example.greg3d.cureintakedispatcher.fakes;

import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.helpers.Tools;
import com.example.greg3d.cureintakedispatcher.model.FarmacyHistoryModel;
import com.example.greg3d.cureintakedispatcher.model.FarmacyModel;
import com.example.greg3d.cureintakedispatcher.model.HistoryRecordModel;
import com.example.greg3d.cureintakedispatcher.model.SchemeModel;

import java.util.Date;

/**
 * Created by greg3d on 28.10.17.
 */

public class FakeData {

    private String LOG_TAG = "FakeData";

    private DBHelper db;

    public FakeData(DBHelper dbHelper){
        this.db = dbHelper;
    }

    public void createFakes(){
        this.setFakeFarmacy();
        this.setFakeHistory();
        this.setFakeScheme();
        this.setFakeFarmacyHistory();
    }

    private Date fakeDate = Tools.stringToDateTime("02:02:02 2017-02-02");

    private void setFakeFarmacyHistory(){
        FarmacyHistoryModel model = new FarmacyHistoryModel();

        db.dropTable(model);
        db.createTable(model);

        model.farmacyId = 1;
        model.name = "Аспирин";
        model.price = 21.5;
        model.volume = "20 мг";

        model.purchaseDate = fakeDate;

        db.insertRecord(model);

        model.farmacyId = 2;
        model.name = "Боролгин";
        model.price = 55.75;
        model.volume = "250 мл";
        model.purchaseDate = new Date();

        db.insertRecord(model);

        model.farmacyId = 2;
        model.name = "Боролгин";
        model.price = 55.77;
        model.volume = "250 мл";
        model.purchaseDate = fakeDate;

        db.insertRecord(model);
    }

    private void setFakeFarmacy(){
        FarmacyModel model = new FarmacyModel();

        db.dropTable(model);
        db.createTable(model);

        model.name = "Аспирин";
        model.volume = "20 мг";

        model.lastDate = fakeDate;

        db.insertRecord(model);

        model.name = "Боролгин";
        model.volume = "250 мл";

        db.insertRecord(model);
    }

    private void setFakeHistory(){
        HistoryRecordModel model = new HistoryRecordModel();

        db.dropTable(model);
        db.createTable(model);

        model.intakeNum = 1;
        model.intakeTime = Tools.stringToDateTime("01:01:01 2017-01-01");
        model.schemeId = 1;
        model.status = 1;

        db.insertRecord(model);

        model.intakeNum = 2;
        model.intakeTime = Tools.stringToDateTime("02:02:02 2017-02-02");
        model.schemeId = 2;
        model.status = 0;

        db.insertRecord(model);

        model.intakeNum = 2;
        model.intakeTime = Tools.stringToDateTime("01:01:01 2017-01-01");
        model.schemeId = 2;
        model.status = 0;

        db.insertRecord(model);
    }

    private void setFakeScheme(){
        SchemeModel model = new SchemeModel();

        db.dropTable(model);
        db.createTable(model);

        model.lastDate = fakeDate;

        model.duration = 5;
        model.farmacyId = 1;
        model.intake_count = 8;
        model.name = "2 шт 2 р/день";

        db.insertRecord(model);

        model.duration = 4;
        model.farmacyId = 2;
        model.intake_count = 4;
        model.name = "5 капель 3 р/день";

        db.insertRecord(model);

        SchemeModel m = new SchemeModel();
        m.id = 2;
        m.name = "11";
        m.duration = 7;

        //updateRecord(m);
        db.editRecord(m);
    }


//    ////////////////////////////////////////////////////////////////////////////////////////////
//
//    public void setRecords(List<DateRecord> list){
//        ////////this.deleteRecords();
//        for(DateRecord record: list){
//            String script = String.format("insert into [VISIT_DATETIME_TABLE] " +
//                            " ([DATETIME_IN], [DATETIME_OUT], [IS_DAYOFF], [IS_SHORTDAY]) " +
//                            " values ('%s', '%s', %s, %s)",
//                    Tools.dateTimeToString(record.dateIn),
//                    Tools.dateTimeToString(record.dateOut),
//                    record.isDayOff,
//                    record.isShortDay);
//
//            Log.d(LOG_TAG, script);
//
//            this.db.execSQL(script);
//        }
//    }
//
//    public List<HistoryRecordModel> getRecords(){
//        return getRecords(HistoryRecordModel.class);
//    }
//
//    public List<DateRecord> getRecords(Date date){
//        Log.d(LOG_TAG, String.format("select * from [VISIT_DATETIME_TABLE] where [DATETIME_IN] like '%s%%'",
//                Tools.dateTimeToString(date, "yyyy-MM-")));
//        return getRecordsImpl(String.format("select * from [VISIT_DATETIME_TABLE] where [DATETIME_IN] like '%s%%'",
//                Tools.dateTimeToString(date, "yyyy-MM-")));
//    }
//
//    public DateRecord getRecord(Date date){
//        Log.d(LOG_TAG, String.format("select * from [VISIT_DATETIME_TABLE] where [DATETIME_IN] like '%s%%'",
//                Tools.dateTimeToString(date, "yyyy-MM-dd")));
//
//        return getRecordsImpl(String.format("select * from [VISIT_DATETIME_TABLE] where [DATETIME_IN] like '%s%%'",
//                Tools.dateTimeToString(date,"yyyy-MM-dd"))).get(0);
//    }
//
//    public Date getLastDateIn(){
//        Date date = null;
//        try {
//            String query = "select max([DATETIME_IN]) as DATETIME_IN  from [VISIT_DATETIME_TABLE]";
//            Cursor cursor = db.rawQuery(query, null);
//            cursor.moveToFirst();
//            date = Tools.stringToDateTime(cursor.getString(cursor.getColumnIndex("DATETIME_IN")));
//            cursor.close();
//        }catch(Exception e){}
//
//        return date;
//    }
//
//    public Date getLastDateOut(){
//        Date date = null;
//        try {
//            String query = "select max([DATETIME_OUT]) as DATETIME_OUT from [VISIT_DATETIME_TABLE]";
//            Cursor cursor = db.rawQuery(query, null);
//            cursor.moveToFirst();
//            date = Tools.stringToDateTime(cursor.getString(cursor.getColumnIndex("DATETIME_OUT")));
//            cursor.close();
//        }catch(Exception e){}
//
//        return date;
//    }
//
//    private List<DateRecord> getRecordsImpl(String query){
//        List<DateRecord> recordList = new ArrayList<>();
//
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                recordList.add(getRecord(cursor));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return recordList;
//    }
//
//    public DateRecord getRecordById(long id){
//
//        Cursor cursor = db.rawQuery(String.format("select * from [VISIT_DATETIME_TABLE] where ID = '%s'", id), null);
//        DateRecord record = null;
//        if (cursor.moveToFirst())
//            record = getRecord(cursor);
//        cursor.close();
//        return record;
//    }
//
//    private DateRecord getRecord(Cursor cursor){
//        DateRecord record = new DateRecord();
//        record.id = cursor.getLong(cursor.getColumnIndex("ID"));
//        record.dateIn = Tools.stringToDateTime(cursor.getString(cursor.getColumnIndex("DATETIME_IN")));
//        record.dateOut = Tools.stringToDateTime(cursor.getString(cursor.getColumnIndex("DATETIME_OUT")));
//        record.isDayOff = cursor.getInt(cursor.getColumnIndex("IS_DAYOFF"));
//        record.isShortDay = cursor.getInt(cursor.getColumnIndex("IS_SHORTDAY"));
//        return record;
//    }
}
