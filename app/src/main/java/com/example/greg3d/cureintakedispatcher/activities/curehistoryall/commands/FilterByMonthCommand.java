package com.example.greg3d.cureintakedispatcher.activities.curehistoryall.commands;

import android.util.Log;

import com.example.greg3d.cureintakedispatcher.activities.curehistoryall.CureHistoryAllActivity;
import com.example.greg3d.cureintakedispatcher.commands.IDateCommand;

import java.util.Calendar;

/**
 * Created by greg3d on 06.11.17.
 */

public class FilterByMonthCommand implements IDateCommand {
    @Override
    public void execute(Calendar calendar) {
        CureHistoryAllActivity.setFilter(calendar.getTime());
        CureHistoryAllActivity.refresh();
    }
}
