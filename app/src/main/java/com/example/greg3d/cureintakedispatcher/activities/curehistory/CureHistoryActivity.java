package com.example.greg3d.cureintakedispatcher.activities.curehistory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.curehistory.adapters.CellAdapter;
import com.example.greg3d.cureintakedispatcher.activities.curehistory.controls.Controls;
import com.example.greg3d.cureintakedispatcher.fakes.Show;
import com.example.greg3d.cureintakedispatcher.framework.factory.ActivityFactory;
import com.example.greg3d.cureintakedispatcher.framework.helpers.ViewHelper;
import com.example.greg3d.cureintakedispatcher.helpers.ActivitiesManager;
import com.example.greg3d.cureintakedispatcher.helpers.GridViewHelper;

/**
 * Created by greg3d on 28.10.17.
 */

public class CureHistoryActivity extends Activity implements View.OnClickListener{

    private static final String LOG = "CureHistoryActivity";
    GridViewHelper gridView;

    private static CureHistoryActivity instance;
    public static CureHistoryActivity getInstance(){
        return instance;
    }

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

    @Override
    public void onClick(View view) {
        ViewHelper v = new ViewHelper(view);

        if(v.idEquals(controls.add_Button))
            ActivitiesManager.startCureEditActivity(this,this.gridView.getSelectedId());
        if(v.idEquals(controls.buy_Button))
            Show.show(this, String.valueOf(this.gridView.getSelectedId()));

    }
}
