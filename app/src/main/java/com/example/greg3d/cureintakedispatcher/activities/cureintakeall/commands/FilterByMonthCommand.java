package com.example.greg3d.cureintakedispatcher.activities.cureintakeall.commands;

import com.example.greg3d.cureintakedispatcher.activities.curehistoryall.CureHistoryAllActivity;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeall.CureIntakeHistoryActivity;
import com.example.greg3d.cureintakedispatcher.commands.IDateCommand;

import java.util.Calendar;

/**
 * Created by greg3d on 06.11.17.
 */

public class FilterByMonthCommand implements IDateCommand {
    @Override
    public void execute(Calendar calendar) {
        CureIntakeHistoryActivity.setFilter(calendar.getTime());
        CureIntakeHistoryActivity.refresh();
    }
}
