package com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.controls;

import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.R;
import com.example.greg3d.cureintakedispatcher.constants.DateFormats;
import com.example.greg3d.cureintakedispatcher.elements.FormatedDateView;
import com.example.greg3d.cureintakedispatcher.elements.IntegerView;
import com.example.greg3d.cureintakedispatcher.framework.annotations.DateFormated;
import com.example.greg3d.cureintakedispatcher.framework.annotations.FindBy;

/**
 * Created by greg3d on 21.10.17.
 */

public class LastIntakeView {
        @FindBy(R.id.c_id)
        public IntegerView id_IntegerView;

        @FindBy(R.id.c_schemeId)
        public IntegerView schemeId_IntegerView;

        @FindBy(R.id.c_Scheme)
        public TextView scheme_TextView;

        @FindBy(R.id.c_CurrentIntake)
        public TextView currentIntake_TextView;

        @FindBy(R.id.c_IntakeStatus)
        public TextView intakeStatus_TextView;

        @FindBy(R.id.c_IntakeDate)
        @DateFormated(DateFormats.SIMPLE_DATE_TIME_FORMAT)
        // TODO - Date to Long
        //public TextView intakeDate_TextView;
        public FormatedDateView intakeDate_TextView;

        @FindBy(R.id.c_BalanceDays)
        public IntegerView balanceDays_IntegerView;
}
