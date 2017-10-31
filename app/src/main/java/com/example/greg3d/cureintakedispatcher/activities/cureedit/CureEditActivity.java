package com.example.greg3d.cureintakedispatcher.activities.cureedit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.cureedit.commands.AddBuyDateCommand;
import com.example.greg3d.cureintakedispatcher.activities.cureedit.commands.AddPriceCommand;
import com.example.greg3d.cureintakedispatcher.activities.cureedit.controls.Controls;
import com.example.greg3d.cureintakedispatcher.activities.curehistory.CureHistoryActivity;
import com.example.greg3d.cureintakedispatcher.dialog.DatePickerDialogImpl;
import com.example.greg3d.cureintakedispatcher.dialog.calc.CalcDialog;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.framework.helpers.ViewHelper;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.helpers.Tools;
import com.example.greg3d.cureintakedispatcher.model.DateFormat;
import com.example.greg3d.cureintakedispatcher.model.FarmacyHistoryModel;
import com.example.greg3d.cureintakedispatcher.model.FarmacyModel;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by greg3d on 28.10.17.
 */

public class CureEditActivity extends Activity implements View.OnClickListener{

    private static final String LOG = "CureEditActivity";

    private static CureEditActivity instance;
    public static CureEditActivity getInstance(){
        return instance;
    }

    private Controls controls;
    public Controls getControls(){return this.controls;}

    private static FarmacyHistoryModel model;
    public static int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cure_edit);

        instance = this;
        controls = new Controls();

//        Spinner spinner = (Spinner)findViewById(R.id.ce_Cure_Spinner);
//        spinner.setAdapter(new SpinnerAdapter(this));
//        spinner.setSelection(1, true);

        // Вызываем адаптер
        ActivityFactory.InitActivity(this, controls);
        ActivityFactory.setListener(this, controls);
        //ActivityFactory.InitFonts(this,controls, CssManager.getEditButtonCss());

        this.updateFieldsBySelectedRecord();
    }

    public static void setModel(FarmacyHistoryModel inModel){
        model = inModel;
    }

//    // Вызывается перед тем, как Активность перестает быть "видимой".
//    @Override
//    public void onStop(){
//        super.onStop();
//        model = null;
//    }

    @Override
    public void onClick(View view) {
        ViewHelper v = new ViewHelper(view);

        if(v.idEquals(controls.buyDate_EditText))
            new DatePickerDialogImpl(this, Calendar.getInstance().getTime(), new AddBuyDateCommand()).show();
        else if(v.idEquals(controls.save_Button)) {
            FarmacyHistoryModel fhModel = new FarmacyHistoryModel();

            fhModel.name = controls.name_EditText.getText().toString();
            fhModel.price = Double.valueOf(controls.price_EditText.getText().toString());
            fhModel.volume = controls.volume_EditText.getText().toString();
            fhModel.purchaseDate = Tools.stringToDate(controls.buyDate_EditText.getText().toString(), DateFormat.DATE);
            updateRecord(fhModel);

            CureHistoryActivity.refresh();
            this.finish();
        }
        else if(v.idEquals(controls.cancel_Button))
            this.finish();
            //Show.show(this, String.valueOf(CureHistoryActivity.getSelectedId()));
        else if(v.idEquals(controls.price_EditText)){
            CalcDialog.show (this, new AddPriceCommand());
        }
    }

    // Заполняем поля на форме по значениям выбранной записи
    private void updateFieldsBySelectedRecord(){
        if(this.model != null){
            if(state == State.BUY)
                controls.buyDate_EditText.setText(Tools.dateToString(new Date()));
            else
                controls.buyDate_EditText.setText(Tools.dateToString(model.purchaseDate));
            controls.price_EditText.setText(model.price.toString());
            controls.name_EditText.setText(model.name);
            controls.volume_EditText.setText(model.volume);
        }
    }

    private void updateRecord(FarmacyHistoryModel model){
        DBHelper db = DBHelper.getInstance();

        FarmacyModel fModel = new FarmacyModel();
        fModel.volume = model.volume;
        fModel.name = model.name;
        fModel.lastDate = model.purchaseDate;

        if(this.model != null){
            model.farmacyId = this.model.farmacyId;
            fModel.id = this.model.farmacyId;
        }

        switch(state){
            case State.BUY:
                db.updateRecord(fModel);
                db.insertRecord(model);
                break;
            case State.ADD:
                db.insertRecord(fModel);
                fModel = db.getLastRecord(fModel);
                model.farmacyId = fModel.id;
                db.insertRecord(model);
                break;
            case State.EDIT:
                fModel.id = this.model.farmacyId;
                db.updateRecord(fModel);

                model.id = this.model.id;
                model.farmacyId = this.model.farmacyId;
                db.updateRecord(model);
                break;
        }
    }
}
