package com.example.greg3d.cureintakedispatcher.activities.curehistoryall.controls;

import android.widget.Button;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.constants.DateFormats;
import com.example.greg3d.cureintakedispatcher.elements.FormatedDateView;
import com.example.greg3d.cureintakedispatcher.framework.annotations.DateFormated;
import com.example.greg3d.cureintakedispatcher.framework.annotations.FindBy;

/**
 * Created by greg3d on 21.10.17.
 */

public class Controls {
    @FindBy(R.id.cha_delCure)
    public Button del_Button;

    @FindBy(R.id.cha_EditCure)
    public Button edit_Button;

    @FindBy(R.id.cha_Filter)
    @DateFormated(DateFormats.DATE_FORMAT)
    public FormatedDateView filter_DateView;
}
