package com.example.greg3d.cureintakedispatcher.activities.curehistory.controls;

import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.framework.annotations.FindBy;

/**
 * Created by greg3d on 21.10.17.
 */

public class CureHistoryView {
        @FindBy(R.id.ch_CureName)
        public TextView cureName_TextView;

        @FindBy(R.id.ch_Valume)
        public TextView cureValume_TextView;

        @FindBy(R.id.ch_Price)
        public TextView curePrice_TextView;

        @FindBy(R.id.ch_PurchaseDate)
        public TextView purchaseDate_TextView;
}
