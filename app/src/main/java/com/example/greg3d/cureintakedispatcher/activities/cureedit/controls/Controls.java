package com.example.greg3d.cureintakedispatcher.activities.cureedit.controls;

import android.widget.Button;
import android.widget.EditText;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.framework.annotations.FindBy;

/**
 * Created by greg3d on 21.10.17.
 */

public class Controls {

    @FindBy(R.id.ce_CureName)
    public EditText name_EditText;

    @FindBy(R.id.ce_Value)
    public EditText volume_EditText;

    @FindBy(R.id.ce_Price)
    public EditText price_EditText;

    @FindBy(R.id.ce_PurchaseDate)
    public EditText buyDate_EditText;

    @FindBy(R.id.ce_save)
    public Button save_Button;

    @FindBy(R.id.ce_cancel)
    public Button cancel_Button;
//
//    @FindBy(R.id.ch_Buy)
//    public Button buy_Button;
}
