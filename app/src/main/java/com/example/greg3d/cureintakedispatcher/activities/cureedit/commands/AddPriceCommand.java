package com.example.greg3d.cureintakedispatcher.activities.cureedit.commands;

import com.example.greg3d.cureintakedispatcher.activities.cureedit.CureEditActivity;
import com.example.greg3d.cureintakedispatcher.commands.IObjectCommand;

/**
 * Created by greg3d on 30.10.17.
 */

public class AddPriceCommand implements IObjectCommand{
    @Override
    public void execute(Object object) {
        CureEditActivity.getControls().price_EditText.setValue(object);
    }
}
