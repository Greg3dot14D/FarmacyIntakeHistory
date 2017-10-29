package com.example.greg3d.cureintakedispatcher.activities.cureedit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.activities.cureedit.adapters.SpinnerAdapter;
import com.example.greg3d.cureintakedispatcher.activities.cureedit.controls.Controls;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cure_edit);

        controls = new Controls();


        Spinner spinner = (Spinner)findViewById(R.id.ce_Cure_Spinner);
        spinner.setAdapter(new SpinnerAdapter(this));
        spinner.setSelection(1, true);

//        // Настраиваем адаптер
//        ArrayAdapter<?> adapter =
//                ArrayAdapter.createFromResource(this, R.array.cures, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Вызываем адаптер


        //ActivityFactory.InitActivity(this, controls);
        //ActivityFactory.setListener(this, controls);
        //ActivityFactory.InitFonts(this,controls, CssManager.getEditButtonCss());
    }

    @Override
    public void onClick(View view) {

    }
}
