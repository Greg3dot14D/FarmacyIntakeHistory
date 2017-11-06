package com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.commands;

import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.CureIntakeActivity;
import com.example.greg3d.cureintakedispatcher.commands.ICommand;
import com.example.greg3d.cureintakedispatcher.constants.IntakeStatus;

/**
 * Created by greg3d on 06.11.17.
 */

public class CancelIntakeCommand implements ICommand{
    @Override
    public void execute() {
        CureIntakeActivity.intakeImpl(IntakeStatus.CANCELED);
    }
}
