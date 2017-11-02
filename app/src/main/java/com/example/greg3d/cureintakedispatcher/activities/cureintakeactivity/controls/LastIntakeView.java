package com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.controls;

import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.framework.annotations.FindBy;

/**
 * Created by greg3d on 21.10.17.
 */

public class LastIntakeView {
        @FindBy(R.id.c_id)
        public TextView id_TextView;

        @FindBy(R.id.c_schemeId)
        public TextView schemeId_TextView;

        @FindBy(R.id.c_Scheme)
        public TextView scheme_TextView;

        @FindBy(R.id.c_CurrentIntake)
        public TextView currentIntake_TextView;

        @FindBy(R.id.c_IntakeDate)
        public TextView intakeDate_TextView;

        @FindBy(R.id.c_BalanceDays)
        public TextView balanceDays_TextView;
}
