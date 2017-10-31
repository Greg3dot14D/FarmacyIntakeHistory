package com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.adapters.CellAdapter;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.controls.Controls;
import com.example.greg3d.cureintakedispatcher.controller.CSVController;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.framework.helpers.ViewHelper;
import com.example.greg3d.cureintakedispatcher.helpers.ActivitiesManager;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.helpers.GridViewHelper;

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

        if(v.idEquals(controls.cancel_Button)){
            CSVController.writeTablesToSD();
            //this.show();
            //CalcDialog d = new CalcDialog().setCommand(new Command());
            //d.show(getFragmentManager(), "test");

            //d.getDialog().show();

            //Show.show(this, d.getResult());

        }

        if(v.idEquals(controls.intake_Button))
            //CSVController.readTablesFromSD();
            ActivitiesManager.startCureHistoryActivity(this);
    }
}
