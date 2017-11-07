package com.example.greg3d.cureintakedispatcher.activities.curehistory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.cureedit.CureEditActivity;
import com.example.greg3d.cureintakedispatcher.activities.curehistory.adapters.CellAdapter;
import com.example.greg3d.cureintakedispatcher.activities.curehistory.commands.DeleteCureCommand;
import com.example.greg3d.cureintakedispatcher.activities.curehistory.controls.Controls;
import com.example.greg3d.cureintakedispatcher.activities.curehistoryall.CureHistoryAllActivity;
import com.example.greg3d.cureintakedispatcher.constants.State;
import com.example.greg3d.cureintakedispatcher.dialog.YesNoDialog;
import com.example.greg3d.cureintakedispatcher.fakes.Show;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.framework.factory.ViewFactory;
import com.example.greg3d.cureintakedispatcher.framework.helpers.ViewHelper;
import com.example.greg3d.cureintakedispatcher.helpers.ActivitiesManager;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.helpers.GridViewHelper;
import com.example.greg3d.cureintakedispatcher.model.FarmacyHistoryModel;

/**
 * Created by greg3d on 28.10.17.
 */

public class CureHistoryActivity extends Activity implements View.OnClickListener{

    private static final String LOG_TAG = "CureHistoryAllActivity";
    GridViewHelper gridView;

    private static CureHistoryActivity instance;
    public static CureHistoryActivity getInstance(){
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
                .setAdapter(new CellAdapter(this));

        controls = new Controls();
        ActivityFactory.InitActivity(this, controls);
        ActivityFactory.setListener(this, controls);
        //ActivityFactory.InitFonts(this,controls, CssManager.getEditButtonCss());
    }

    public CureHistoryActivity(){}

    public <T extends View.OnClickListener> CureHistoryActivity(T activity, View view){
        instance = this;
        this.view = view;
        gridView = new GridViewHelper(view, R.id.gvCureHistory)
                .setAdapter(new CellAdapter(view.getContext()));
        controls = new Controls();
        ViewFactory.InitView(view, controls);
        ActivityFactory.setListener(activity, controls);
    }

    @Override
    public void onClick(View view) {
        this.view = view;
        this.onClick(this, view);
    }

    public void onClick(Activity activity, View view){
        ViewHelper v = new ViewHelper(view);

        if(v.idEquals(controls.add_Button)) {
            CureEditActivity.state = State.ADD;
            ActivitiesManager.startCureEditActivity(activity, 0);
        }
        else if(v.idEquals(controls.buy_Button) && isSelected()) {
            CureEditActivity.state = State.BUY;
            CureEditActivity
                    .setModel(DBHelper.getRecordById(new FarmacyHistoryModel(),
                            getSelectedId()));
            ActivitiesManager.startCureEditActivity(activity,0);
        }
        else if(v.idEquals(controls.edit_Button) && isSelected()) {
            CureEditActivity.state = State.EDIT;
            CureEditActivity
                    .setModel(DBHelper.getRecordById(new FarmacyHistoryModel(),
                            getSelectedId()));
            ActivitiesManager.startCureEditActivity(activity,0);
        }
        else if(v.idEquals(controls.del_Button) && isSelected())
            new YesNoDialog(activity, new DeleteCureCommand(), "Удаляем пилюльку ?").show();
    }

    private boolean isSelected(){
        if(!this.gridView.isSelected()) {
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
        instance.gridView.setAdapter(new CellAdapter(instance.view.getContext()));
        CureHistoryAllActivity.refresh();
        instance.gridView.setUnSelected();
    }
}
