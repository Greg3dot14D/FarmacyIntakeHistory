package com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.commands;

import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.CureIntakeActivity;
import com.example.greg3d.cureintakedispatcher.commands.ICommand;
import com.example.greg3d.cureintakedispatcher.constants.IntakeStatus;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.model.HistoryRecordModel;
import com.example.greg3d.cureintakedispatcher.model.SchemeModel;

import java.util.Date;

/**
 * Created by greg3d on 06.11.17.
 */

public class StartIntakeCommand implements ICommand{
    @Override
    public void execute() {
        DBHelper db = DBHelper.getInstance();
        Date lastDate = new Date();

        HistoryRecordModel history = new HistoryRecordModel();
        SchemeModel scheme = new SchemeModel();

        history.id = CureIntakeActivity.getSelectedId();
        history = db.getRecord(history);

        scheme.id = history.schemeId;
        scheme = db.getRecord(scheme);

        history.intakeTime = lastDate;
        history.intakeNum = 1;
        history.status = IntakeStatus.INTAKED;
        history.daysRemaind = scheme.duration;

        db.insertRecord(history);

        scheme.lastDate = lastDate;
        db.editRecord(scheme);
        CureIntakeActivity.refresh();
    }
}
