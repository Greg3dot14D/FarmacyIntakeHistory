package com.example.greg3d.cureintakedispatcher.activities.flipper.commands;

import com.example.greg3d.cureintakedispatcher.activities.curehistory.CureHistoryActivity;
import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.CureIntakeActivity;
import com.example.greg3d.cureintakedispatcher.commands.ICommand;
import com.example.greg3d.cureintakedispatcher.controller.CSVController;

/**
 * Created by greg3d on 06.11.17.
 */

public class ImportFilesCommand implements ICommand{
    @Override
    public void execute() {
        CSVController.readTablesFromSD();
        CureHistoryActivity.refresh();
        CureIntakeActivity.refresh();
    }
}
