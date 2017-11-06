package com.example.greg3d.cureintakedispatcher.activities.curehistory.commands;

import com.example.greg3d.cureintakedispatcher.activities.curehistory.CureHistoryActivity;
import com.example.greg3d.cureintakedispatcher.commands.ICommand;
import com.example.greg3d.cureintakedispatcher.helpers.DBHelper;
import com.example.greg3d.cureintakedispatcher.model.FarmacyModel;

/**
 * Created by greg3d on 06.11.17.
 */

public class DeleteCureCommand implements ICommand{
    @Override
    public void execute() {
        FarmacyModel model = new FarmacyModel();
        model.id = CureHistoryActivity.getSelectedFarmacyId();
        DBHelper.getInstance().deleteRecord(model);
        CureHistoryActivity.refresh();
    }
}
