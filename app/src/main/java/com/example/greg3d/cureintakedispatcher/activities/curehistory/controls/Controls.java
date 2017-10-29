package com.example.greg3d.cureintakedispatcher.activities.curehistory.controls;

import android.widget.Button;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.framework.annotations.FindBy;

/**
 * Created by greg3d on 21.10.17.
 */

public class Controls {
    @FindBy(R.id.ch_AddCure)
    public Button add_Button;

    @FindBy(R.id.ch_EditCure)
    public Button edit_Button;

    @FindBy(R.id.ch_Buy)
    public Button buy_Button;
}
