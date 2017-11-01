package com.example.greg3d.cureintakedispatcher.activities.editintake.controls;

import android.widget.Button;
import android.widget.EditText;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.framework.annotations.FindBy;

/**
 * Created by greg3d on 21.10.17.
 */

public class Controls {

    @FindBy(R.id.in_SchemName)
    public EditText schema_EditText;

    @FindBy(R.id.in_Duration)
    public EditText duration_EditText;

    @FindBy(R.id.in_Intake_Num)
    public EditText intakeNum_EditText;

    @FindBy(R.id.in_save_button)
    public Button save_Button;

    @FindBy(R.id.in_cancel_button)
    public Button cancel_Button;
}
