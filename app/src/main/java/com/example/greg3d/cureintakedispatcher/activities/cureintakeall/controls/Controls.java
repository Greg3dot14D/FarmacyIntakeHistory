package com.example.greg3d.cureintakedispatcher.activities.cureintakeall.controls;

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
    @FindBy(R.id.ia_add_intake)
    public Button add_Button;

    @FindBy(R.id.ia_edit_intake)
    public Button edit_Button;

    @FindBy(R.id.ia_Filter)
    @DateFormated(DateFormats.DATE_FORMAT)
    public FormatedDateView filter_DateView;
}
