package com.example.greg3d.cureintakedispatcher.activities.cureedit.commands;

import com.example.greg3d.cureintakedispatcher.activities.cureedit.CureEditActivity;
import com.example.greg3d.cureintakedispatcher.commands.IDateCommand;
import com.example.greg3d.cureintakedispatcher.helpers.Tools;

import java.util.Calendar;

/**
 * Created by greg3d on 29.10.17.
 */

public class AddBuyDateCommand implements IDateCommand{
    @Override
    public void execute(Calendar calendar) {
        CureEditActivity.getInstance().getControls().buyDate_EditText.setText(Tools.dateToString(calendar.getTime()));
    }
}
