package com.example.greg3d.cureintakedispatcher.activities.editintake;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.CureIntakeActivity;
import com.example.greg3d.cureintakedispatcher.activities.editintake.adapters.SpinnerAdapter;
import com.example.greg3d.cureintakedispatcher.activities.editintake.controls.Controls;
import com.example.greg3d.cureintakedispatcher.constants.IntakeStatus;
import com.example.greg3d.cureintakedispatcher.constants.State;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.framework.helpers.ViewHelper;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.model.FarmacyModel;
import com.example.greg3d.cureintakedispatcher.model.HistoryRecordModel;
import com.example.greg3d.cureintakedispatcher.model.SchemeModel;

import java.util.Date;

/**
 * Created by greg3d on 01.11.17.
 */

public class EditIntakeActivity extends Activity implements View.OnClickListener {

    private static final String LOG = "EditIntakeActivity";

    private static EditIntakeActivity instance;

    public static EditIntakeActivity getInstance() {
        return instance;
    }

    private Controls controls;

    public Controls getControls() {
        return this.controls;
    }

    private static SchemeModel model;
    public static int state;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_intake);

        instance = this;
        controls = new Controls();

        spinner = (Spinner)findViewById(R.id.in_CureName_Spinner);
        spinner.setAdapter(new SpinnerAdapter(this));
        //spinner.setSelection(1, true);

        // Вызываем адаптер
        ActivityFactory.InitActivity(this, controls);
        ActivityFactory.setListener(this, controls);
        updateFieldsBySelectedRecord();
    }

    @Override
    public void onClick(View view) {
        ViewHelper v = new ViewHelper(view);

        if(v.idEquals(controls.save_Button)){
            if(state == State.ADD){
                DBHelper db = DBHelper.getInstance();

                Date lastDate = new Date();
                SchemeModel scheme = new SchemeModel();
                scheme.lastDate = lastDate;
                scheme.name = controls.schema_EditText.getText().toString();
                scheme.duration = Integer.valueOf(controls.duration_EditText.getText().toString());
                // TODO
                scheme.intake_count = Integer.valueOf(controls.intakeNum_EditText.getText().toString());
                scheme.farmacyId = ((FarmacyModel)spinner.getSelectedItem()).id;

                db.insertRecord(scheme);

                scheme = db.getLastRecord(scheme);

                HistoryRecordModel intakeModel = new HistoryRecordModel();
                intakeModel.schemeId = scheme.id;
                intakeModel.status = IntakeStatus.NEW;
                intakeModel.intakeNum = 0;
                intakeModel.intakeTime = lastDate;
                intakeModel.daysRemaind = Integer.valueOf(controls.duration_EditText.getText().toString());

                db.insertRecord(intakeModel);

                this.finish();
                CureIntakeActivity.refresh();
            } else if(state == State.EDIT && model != null){
                DBHelper db = DBHelper.getInstance();

                Date lastDate = new Date();
                SchemeModel scheme = new SchemeModel();
                scheme.id = CureIntakeActivity.getSelectedSchemeId();
                scheme.lastDate = lastDate;
                scheme.name = controls.schema_EditText.getText().toString();
                scheme.duration = Integer.valueOf(controls.duration_EditText.getText().toString());
                // TODO
                scheme.intake_count = Integer.valueOf(controls.intakeNum_EditText.getText().toString());
                scheme.farmacyId = ((FarmacyModel)spinner.getSelectedItem()).id;

                db.editRecord(scheme);

                HistoryRecordModel intakeModel = new HistoryRecordModel();
                intakeModel.id = CureIntakeActivity.getSelectedId();
                intakeModel.schemeId = scheme.id;
                intakeModel.status = IntakeStatus.NEW;
                //intakeModel.intakeNum = 0;
                intakeModel.intakeTime = lastDate;
                intakeModel.daysRemaind = Integer.valueOf(controls.duration_EditText.getText().toString());

                db.editRecord(intakeModel);

                this.finish();
                CureIntakeActivity.refresh();
            }
            return;
        }
        else if(v.idEquals(controls.cancel_Button)) {
            this.finish();
        }
    }

    public static void setModel(SchemeModel inModel){
        model = inModel;
    }

    // Заполняем поля на форме по значениям выбранной записи
    private void updateFieldsBySelectedRecord(){
        if(this.model != null){
            if(state == State.EDIT) {
                controls.schema_EditText.setText(model.name);
                controls.duration_EditText.setText(model.duration.toString());
                controls.intakeNum_EditText.setText(model.intake_count.toString());
                //controls.name_EditText.setText(model.name);
                //controls.volume_EditText.setText(model.volume);
            }
        }
    }
}