package com.example.greg3d.cureintakedispatcher.activities.curehistoryall;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.curehistoryall.adapters.CellAdapter;
import com.example.greg3d.cureintakedispatcher.activities.curehistoryall.commands.FilterByMonthCommand;
import com.example.greg3d.cureintakedispatcher.activities.curehistoryall.controls.Controls;
import com.example.greg3d.cureintakedispatcher.controller.DBController;
import com.example.greg3d.cureintakedispatcher.dialog.DatePickerDialogImpl;
import com.example.greg3d.cureintakedispatcher.fakes.Show;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.framework.factory.ViewFactory;
import com.example.greg3d.cureintakedispatcher.framework.helpers.ViewHelper;
import com.example.greg3d.cureintakedispatcher.helpers.GridViewHelper;
import com.example.greg3d.cureintakedispatcher.model.FarmacyHistoryModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by greg3d on 28.10.17.
 */

public class CureHistoryAllActivity extends Activity implements View.OnClickListener{

    private static final String LOG_TAG = "CureHistoryAllActivity";
    GridViewHelper gridView;

    private static CureHistoryAllActivity instance;
    public static CureHistoryAllActivity getInstance(){
        return instance;
    }
    private View view;
    private Controls controls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cure_history);

        instance = this;
        gridView = new GridViewHelper(this, R.id.gvCureHistory)
                .setAdapter(new CellAdapter(this, getfilteredByMonthRecords(new Date())));

        controls = new Controls();
        ActivityFactory.InitActivity(this, controls);
        ActivityFactory.setListener(this, controls);
        //ActivityFactory.InitFonts(this,controls, CssManager.getEditButtonCss());
        controls.filter_DateView.setDate(new Date());
    }

    public CureHistoryAllActivity(){}

    public <T extends View.OnClickListener> CureHistoryAllActivity(T activity, View view){
        instance = this;
        this.view = view;
        gridView = new GridViewHelper(view, R.id.gvCureHistoryAll)
                .setAdapter(new CellAdapter(view.getContext(), getfilteredByMonthRecords(new Date())));
        controls = new Controls();
        ViewFactory.InitView(view, controls);
        //ActivityFactory.InitActivity(view, controls);
        ActivityFactory.setListener(activity, controls);
        controls.filter_DateView.setDate(new Date());
    }

    @Override
    public void onClick(View view) {
        this.view = view;
        this.onClick(this, view);
    }

    public void onClick(Activity activity, View view){
        ViewHelper v = new ViewHelper(view);
//        if(v.idEquals(controls.edit_Button)) {
//            if(!isSelected())
//                return;
//            CureEditActivity.state = State.EDIT;
//            CureEditActivity
//                    .setModel(DBHelper.getRecordById(new FarmacyHistoryModel(),
//                            getSelectedId()));
//            ActivitiesManager.startCureEditActivity(this,0);
//        }
//        else if(v.idEquals(controls.del_Button)) {
//            if (!isSelected())
//                return;
//            FarmacyModel model = new FarmacyModel();
//            model.id = getSelectedFarmacyId();
//            DBHelper.getInstance().deleteRecord(model);
//            refresh();
//        }
//        else
            if(v.idEquals(controls.filter_DateView)){
            new DatePickerDialogImpl(activity, getFilter(), new FilterByMonthCommand()).show();
        }
    }

    private boolean isSelected(){
        try {
            getSelectedFarmacyId();
        }catch(Exception e){
            Show.show(view.getContext(), "Запись не выбрана");
            return false;
        }
        return true;
    }

    public static int getSelectedFarmacyId(){
        return Integer.valueOf(((TextView)instance.gridView.getView().findViewById(R.id.ch_farmacyId)).getText().toString());
    }

    public static int getSelectedId(){
        return Integer.valueOf(((TextView)instance.gridView.getView().findViewById(R.id.ch_id)).getText().toString());
    }

    public static void refresh(){
        instance.gridView.setAdapter(new CellAdapter(instance.view.getContext(), getfilteredByMonthRecords(instance.getFilter())));
    }

    public static List<FarmacyHistoryModel> getfilteredByMonthRecords(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        Date endDate = calendar.getTime();

        Log.d("DDD", "start" + startDate);
        Log.d("DDD", "end" + endDate);

        return DBController.getFarmacyHistoryRecordsByDate(startDate, endDate);
    }

    public static void setFilter(Date date){
        instance.controls.filter_DateView.setDate(date);
    }

    private Date getFilter(){
        return instance.controls.filter_DateView.getDate();
    }
}
