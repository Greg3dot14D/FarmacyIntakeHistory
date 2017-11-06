package com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.adapters.CellAdapter;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.commands.CancelIntakeCommand;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.commands.ConfirmIntakeCommand;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.commands.DeleteIntakeCommand;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.commands.StartIntakeCommand;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.controls.Controls;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeall.CureIntakeHistoryActivity;
import com.example.greg3d.cureintakedispatcher.activities.editintake.EditIntakeActivity;
import com.example.greg3d.cureintakedispatcher.constants.IntakeStatus;
import com.example.greg3d.cureintakedispatcher.constants.State;
import com.example.greg3d.cureintakedispatcher.dialog.YesNoDialog;
import com.example.greg3d.cureintakedispatcher.fakes.Show;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.framework.factory.ViewFactory;
import com.example.greg3d.cureintakedispatcher.framework.helpers.ViewHelper;
import com.example.greg3d.cureintakedispatcher.helpers.ActivitiesManager;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.helpers.GridViewHelper;
import com.example.greg3d.cureintakedispatcher.model.HistoryRecordModel;
import com.example.greg3d.cureintakedispatcher.model.LastIntakeRecord;
import com.example.greg3d.cureintakedispatcher.model.SchemeModel;

import java.util.Date;

public class CureIntakeActivity <T extends LastIntakeRecord> extends Activity implements View.OnClickListener{

    private static final String LOG = "CureIntakeActivity";
    GridViewHelper gridView;

    private static CureIntakeActivity instance;
    private static View view;
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

    public CureIntakeActivity(){}

    public <T extends View.OnClickListener> CureIntakeActivity(T activity, View view){
        instance = this;
        this.view = view;
        gridView = new GridViewHelper(view, R.id.gvBase)
                .setAdapter(new CellAdapter(view));
        controls = new Controls();
        ViewFactory.InitView(view, controls);
        ActivityFactory.setListener(activity, controls);
    }

    public static void refresh(){
        instance.gridView.setAdapter(new CellAdapter(view));
        CureIntakeHistoryActivity.refresh();
        instance.gridView.setUnSelected();
    }

    @Override
    public void onClick(View view) {
        this.view = view;
        this.onClick(this, view);
    }

    public void onClick(Activity activity, View view) {
        ViewHelper v = new ViewHelper(view);

        if(v.idEquals(controls.start_Button) && isSelected())
            //startIntakeImpl();
            new YesNoDialog(activity, new StartIntakeCommand(), "Начинаем курс по новой ?").show();
        else if(v.idEquals(controls.intake_Button) && isSelected())
            //this.intakeImpl(IntakeStatus.INTAKED);
            new YesNoDialog(activity, new ConfirmIntakeCommand(), "Принимаем пилюльку ?").show();
        else if(v.idEquals(controls.cancel_Button) && isSelected())
            //this.intakeImpl(IntakeStatus.CANCELED);
            new YesNoDialog(activity, new CancelIntakeCommand(), "Пропускаем пилюльку ?").show();
        else if(v.idEquals(controls.add_Button)) {
            EditIntakeActivity.state = State.ADD;
            ActivitiesManager.startEditIntakeActivity(activity);
        }
        else if(v.idEquals(controls.edit_Button) && isSelected()) {
            EditIntakeActivity.state = State.EDIT;
            ActivitiesManager.startEditIntakeActivity(activity);
            EditIntakeActivity
                    .setModel(DBHelper.getRecordById(new SchemeModel(),
                            getSelectedSchemeId()));
        }
        else if(v.idEquals(controls.del_Button) && isSelected())
            new YesNoDialog(activity, new DeleteIntakeCommand(), "Удаляем схемку ?").show();
    }

    private void startIntakeImpl(){
        DBHelper db = DBHelper.getInstance();
        Date lastDate = new Date();

        HistoryRecordModel history = new HistoryRecordModel();
        SchemeModel scheme = new SchemeModel();

        history.id = getSelectedId();
        history = db.getRecord(history);

        scheme.id = history.schemeId;
        scheme = db.getRecord(scheme);

        history.intakeTime = lastDate;
        history.intakeNum = 1;
        history.status = IntakeStatus.INTAKED;
        history.daysRemaind = scheme.duration;

        db.insertRecord(history);

        scheme.lastDate = lastDate;
        db.editRecord(scheme);
        refresh();
    }

    public static void intakeImpl(int status){
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
            if(history.daysRemaind < 1)
                history.daysRemaind = scheme.duration;
        }
        history.status = status;
        //db.editRecord(history);
        db.insertRecord(history);

        scheme.lastDate = lastDate;
        db.editRecord(scheme);
        refresh();
    }

    public static int getSelectedId(){
        //return ((LastIntakeRecord)instance.gridView.getSelectedObject()).id;
        return Integer.valueOf(((TextView)instance.gridView.getView().findViewById(R.id.c_id)).getText().toString());
    }

    public static int getSelectedSchemeId(){
        //return ((LastIntakeRecord)instance.gridView.getSelectedObject()).schemeId;
        return Integer.valueOf(((TextView)instance.gridView.getView().findViewById(R.id.c_schemeId)).getText().toString());
    }

    private boolean isSelected(){
        if(!this.gridView.isSelected()) {
            Show.show(view.getContext(), "Запись не выбрана");
            return false;
        }
        return true;
    }
}
