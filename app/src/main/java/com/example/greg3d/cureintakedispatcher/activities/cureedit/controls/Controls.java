package com.example.greg3d.cureintakedispatcher.activities.cureedit.controls;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.constants.DateFormats;
import com.example.greg3d.cureintakedispatcher.elements.DoubleView;
import com.example.greg3d.cureintakedispatcher.elements.FormatedDateView;
import com.example.greg3d.cureintakedispatcher.framework.annotations.DateFormated;
import com.example.greg3d.cureintakedispatcher.framework.annotations.FindBy;
import com.example.greg3d.cureintakedispatcher.framework.containers.BaseControls;

/**
 * Created by greg3d on 21.10.17.
 */

public class Controls extends BaseControls{

    @FindBy(R.id.ce_CureName)
    public EditText name_EditText;

    @FindBy(R.id.ce_Value)
    public EditText volume_EditText;

    @FindBy(R.id.ce_Price)
    public DoubleView price_EditText;

    @FindBy(R.id.ce_PurchaseDate)
    @DateFormated(DateFormats.DATE_FORMAT)
    public FormatedDateView buyDate_DateText;

    @FindBy(R.id.ce_save)
    public Button save_Button;

    @FindBy(R.id.ce_cancel)
    public Button cancel_Button;


    public <T extends Activity> Controls(T activity) {
        super(activity);
    }
//
//    @FindBy(R.id.ch_Buy)
//    public Button buy_Button;
}
