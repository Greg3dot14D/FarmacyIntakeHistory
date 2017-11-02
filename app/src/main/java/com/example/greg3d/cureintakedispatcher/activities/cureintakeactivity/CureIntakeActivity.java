package com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.adapters.CellAdapter;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.controls.Controls;
import com.example.greg3d.cureintakedispatcher.activities.editintake.EditIntakeActivity;
import com.example.greg3d.cureintakedispatcher.constants.IntakeStatus;
import com.example.greg3d.cureintakedispatcher.constants.State;
import com.example.greg3d.cureintakedispatcher.fakes.Show;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.framework.helpers.ViewHelper;
import com.example.greg3d.cureintakedispatcher.helpers.ActivitiesManager;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.helpers.GridViewHelper;
import com.example.greg3d.cureintakedispatcher.model.HistoryRecordModel;
import com.example.greg3d.cureintakedispatcher.model.SchemeModel;

import java.util.Date;

public class CureIntakeActivity extends Activity implements View.OnClickListener{

    private static final String LOG = "CureIntakeActivity";
    GridViewHelper gridView;

    private static CureIntakeActivity instance;
    public static CureIntakeActivity getInstance(){
        return instance;
    }

    private Controls controls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cure_intake);

        instance = this;
        new DBHelper(this);

        gridView = new GridViewHelper(this, R.id.gvBase)
                            .setAdapter(new CellAdapter(this));

        controls = new Controls();
        ActivityFactory.InitActivity(this, controls);
        ActivityFactory.setListener(this, controls);
        //ActivityFactory.InitFonts(this,controls, CssManager.getEditButtonCss());
    }

//    private View view;
//    private void setLastView(View view){
//        this.view = view;
//    }
//
//    private void resetSelect(){
//        if(this.view == null)
//            return;
//        this.view.setBackgroundColor(Colors.DEFAULT_CELL_COLOR);
//    }
//
//    private void setSelect(View view){
//        this.view = view;
//        this.view.setBackgroundColor(Colors.SELECTED_CELL_COLOR);
//    }

    public static void refresh(){
        instance.gridView.setAdapter(new CellAdapter(instance));
    }

    @Override
    public void onClick(View view) {
        ViewHelper v = new ViewHelper(view);

        //CSVController.writeTablesToSD();

        if(v.idEquals(controls.edit_Button))
            ActivitiesManager.startCureHistoryActivity(this);
        else if(v.idEquals(controls.intake_Button))
            this.intakeImpl(IntakeStatus.INTAKED);
        else if(v.idEquals(controls.cancel_Button))
            this.intakeImpl(IntakeStatus.CANCELED);
        else if(v.idEquals(controls.add_Button)) {
            EditIntakeActivity.state = State.ADD;
            ActivitiesManager.startEditIntakeActivity(this);
        }
        else if(v.idEquals(controls.del_Button)){
            if(!isSelected())
                return;
            SchemeModel model = new SchemeModel();
            model.id = Integer.valueOf(String.valueOf(getSelectedSchemeId()));
            DBHelper.getInstance().deleteRecord(model);
            refresh();
        }
    }

    private void intakeImpl(int status){
        DBHelper db = DBHelper.getInstance();
        Date lastDate = new Date();

        HistoryRecordModel history = new HistoryRecordModel();
        SchemeModel scheme = new SchemeModel();

        history.id = getSelectedId();
        history = db.getRecord(history);

        scheme.id = history.schemeId;
        scheme = db.getRecord(scheme);

        history.intakeTime = lastDate;
        history.intakeNum += 1;

        if(history.intakeNum > scheme.intake_count){
            history.intakeNum = 1;
            history.daysRemaind -= 1;
        }
        history.status = status;
        db.editRecord(history);

        scheme.lastDate = lastDate;
        db.editRecord(scheme);
        refresh();
    }

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
            Show.show(this, "Запись не выбрана");
            return false;
        }
        return true;
    }
}
