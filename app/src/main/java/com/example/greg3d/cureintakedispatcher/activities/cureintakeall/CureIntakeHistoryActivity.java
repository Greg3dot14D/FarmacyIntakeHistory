package com.example.greg3d.cureintakedispatcher.activities.cureintakeall;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeall.adapters.CellIntakeHistoryAllAdapter;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeall.controls.Controls;
import com.example.greg3d.cureintakedispatcher.controller.CSVController;
import com.example.greg3d.cureintakedispatcher.fakes.Show;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.framework.factory.ViewFactory;
import com.example.greg3d.cureintakedispatcher.framework.helpers.ViewHelper;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.helpers.GridViewHelper;

public class CureIntakeHistoryActivity extends Activity implements View.OnClickListener{

    private static final String LOG = "CureIntakeActivity";
    GridViewHelper gridView;

    private static CureIntakeHistoryActivity instance;
    private static View view;
    public static CureIntakeHistoryActivity getInstance(){
        return instance;
    }

    private Controls controls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cure_intake);

        instance = this;
        new DBHelper(this);

        gridView = new GridViewHelper(this, R.id.gvIntakeAll)
                            .setAdapter(new CellIntakeHistoryAllAdapter(this));

        controls = new Controls();
        ActivityFactory.InitActivity(this, controls);
        ActivityFactory.setListener(this, controls);
        //ActivityFactory.InitFonts(this,controls, CssManager.getEditButtonCss());
    }

    public CureIntakeHistoryActivity(){}

    public <T extends View.OnClickListener> CureIntakeHistoryActivity(T activity, View view){
        instance = this;
        this.view = view;
        gridView = new GridViewHelper(view, R.id.gvIntakeAll)
                .setAdapter(new CellIntakeHistoryAllAdapter(view.getContext()));
        controls = new Controls();
        ViewFactory.InitView(view, controls);
        ActivityFactory.setListener(activity, controls);
    }

    public static void refresh(){
        instance.gridView.setAdapter(new CellIntakeHistoryAllAdapter(view.getContext()));
    }

    @Override
    public void onClick(View view) {
        this.view = view;
        this.onClick(this, view);
    }

    public void onClick(Activity activity, View view) {
        ViewHelper v = new ViewHelper(view);

        if(v.idEquals(controls.add_Button))
            CSVController.writeTablesToSD();
//            startIntakeImpl();
//        else if(v.idEquals(controls.intake_Button))
//            this.intakeImpl(IntakeStatus.INTAKED);
//        else if(v.idEquals(controls.cancel_Button))
//            this.intakeImpl(IntakeStatus.CANCELED);
//        if(v.idEquals(controls.add_Button)) {
//            EditIntakeActivity.state = State.ADD;
//            ActivitiesManager.startEditIntakeActivity(activity);
//        }
//        else if(v.idEquals(controls.del_Button)){
//            if(!isSelected())
//                return;
//            SchemeModel model = new SchemeModel();
//            model.id = Integer.valueOf(String.valueOf(getSelectedSchemeId()));
//            DBHelper.getInstance().deleteRecord(model);
//            refresh();
//        }
    }

//    private void startIntakeImpl(){
//        DBHelper db = DBHelper.getInstance();
//        Date lastDate = new Date();
//
//        HistoryRecordModel history = new HistoryRecordModel();
//        SchemeModel scheme = new SchemeModel();
//
//        history.id = getSelectedId();
//        history = db.getRecord(history);
//
//        scheme.id = history.schemeId;
//        scheme = db.getRecord(scheme);
//
//        history.intakeTime = lastDate;
//        history.intakeNum = 1;
//        history.daysRemaind = scheme.duration;
//
//        db.insertRecord(history);
//
//        scheme.lastDate = lastDate;
//        db.editRecord(scheme);
//        refresh();
//    }
//
//    private void intakeImpl(int status){
//        DBHelper db = DBHelper.getInstance();
//        Date lastDate = new Date();
//
//        HistoryRecordModel history = new HistoryRecordModel();
//        SchemeModel scheme = new SchemeModel();
//
//        history.id = getSelectedId();
//        history = db.getRecord(history);
//
//        scheme.id = history.schemeId;
//        scheme = db.getRecord(scheme);
//
//        history.intakeTime = lastDate;
//        history.intakeNum += 1;
//
//        if(history.intakeNum > scheme.intake_count){
//            history.intakeNum = 1;
//            history.daysRemaind -= 1;
//        }
//        history.status = status;
//        db.editRecord(history);
//
//        scheme.lastDate = lastDate;
//        db.editRecord(scheme);
//        refresh();
//    }

    public static int getSelectedId(){
        return Integer.valueOf(((TextView)instance.gridView.getView().findViewById(R.id.c_id)).getText().toString());
    }

    public static int getSelectedSchemeId(){
        return Integer.valueOf(((TextView)instance.gridView.getView().findViewById(R.id.c_schemeId)).getText().toString());
    }

    private boolean isSelected(){
        try {
            getSelectedId();
        }catch(Exception e){
            Show.show(view.getContext(), "Запись не выбрана");
            return false;
        }
        return true;
    }
}
