package com.example.greg3d.cureintakedispatcher.activities.cureedit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.cureedit.adapters.SpinnerAdapter;
import com.example.greg3d.cureintakedispatcher.activities.cureedit.commands.AddBuyDateCommand;
import com.example.greg3d.cureintakedispatcher.activities.cureedit.controls.Controls;
import com.example.greg3d.cureintakedispatcher.activities.curehistory.CureHistoryActivity;
import com.example.greg3d.cureintakedispatcher.dialog.DatePickerDialogImpl;
import com.example.greg3d.cureintakedispatcher.fakes.Show;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.framework.helpers.ViewHelper;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.helpers.Tools;
import com.example.greg3d.cureintakedispatcher.model.DateFormat;
import com.example.greg3d.cureintakedispatcher.model.FarmacyHistoryModel;
import com.example.greg3d.cureintakedispatcher.model.FarmacyModel;

import java.util.Calendar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cure_edit);

        this.instance = this;
        controls = new Controls();

        Spinner spinner = (Spinner)findViewById(R.id.ce_Cure_Spinner);
        spinner.setAdapter(new SpinnerAdapter(this));
        spinner.setSelection(1, true);

        // Вызываем адаптер
        ActivityFactory.InitActivity(this, controls);
        ActivityFactory.setListener(this, controls);
        //ActivityFactory.InitFonts(this,controls, CssManager.getEditButtonCss());
    }

    @Override
    public void onClick(View view) {
        ViewHelper v = new ViewHelper(view);

        if(v.idEquals(controls.buyDate_EditText))
            new DatePickerDialogImpl(this, Calendar.getInstance().getTime(), new AddBuyDateCommand()).show();
        else if(v.idEquals(controls.save_Button)) {
            FarmacyHistoryModel model = new FarmacyHistoryModel();

            model.name = controls.name_EditText.getText().toString();
            model.price = Double.valueOf(controls.price_EditText.getText().toString());
            model.volume = controls.volume_EditText.getText().toString();
            model.purchaseDate = Tools.stringToDate(controls.volume_EditText.getText().toString(), DateFormat.DATE);
            addNewRecord(model);
        }
        else if(v.idEquals(controls.cancel_Button))
            Show.show(this, String.valueOf(CureHistoryActivity.getInstance().getSelectedId()));
    }

    private void addNewRecord(FarmacyHistoryModel model){
        DBHelper db = DBHelper.getInstance();
        FarmacyModel fModel = new FarmacyModel();
        fModel.volume = model.volume;
        fModel.name = model.name;
        db.insertRecord(fModel);

        fModel = db.getLastRecord(fModel);
        model.farmacyId = fModel.id;

        db.insertRecord(model);
    }
}
