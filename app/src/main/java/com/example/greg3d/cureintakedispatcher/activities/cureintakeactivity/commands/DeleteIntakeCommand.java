package com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.commands;

import com.example.greg3d.cureintakedispatcher.activities.cureintakeactivity.CureIntakeActivity;
import com.example.greg3d.cureintakedispatcher.commands.ICommand;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.model.SchemeModel;

/**
 * Created by greg3d on 06.11.17.
 */

public class DeleteIntakeCommand implements ICommand{
    @Override
    public void execute() {
        SchemeModel model = new SchemeModel();
        model.id = Integer.valueOf(String.valueOf(CureIntakeActivity.getSelectedSchemeId()));
        DBHelper.getInstance().deleteRecord(model);
        CureIntakeActivity.refresh();
    }
}
