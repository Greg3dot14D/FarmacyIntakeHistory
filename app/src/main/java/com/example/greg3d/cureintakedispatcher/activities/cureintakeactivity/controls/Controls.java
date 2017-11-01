package com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.controls;

import android.widget.Button;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.framework.annotations.FindBy;

/**
 * Created by greg3d on 21.10.17.
 */

public class Controls {
    @FindBy(R.id.b_intake_done)
    public Button intake_Button;

    @FindBy(R.id.b_intake_canceled)
    public Button cancel_Button;

    @FindBy(R.id.b_add_intake)
    public Button add_Button;

    @FindBy(R.id.b_edit_intake)
    public Button edit_Button;

    @FindBy(R.id.b_del_intake)
    public Button del_Button;

}
