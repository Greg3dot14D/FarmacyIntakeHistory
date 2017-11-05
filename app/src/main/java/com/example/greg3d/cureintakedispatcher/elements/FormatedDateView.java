package com.example.greg3d.cureintakedispatcher.elements;

import android.widget.TextView;

import com.example.greg3d.cureintakedispatcher.constants.DateFormats;
import com.example.greg3d.cureintakedispatcher.framework.annotations.SetView;
import com.example.greg3d.cureintakedispatcher.framework.interfaces.IDateFormated;
import com.example.greg3d.cureintakedispatcher.framework.interfaces.ISetView;
import com.example.greg3d.cureintakedispatcher.helpers.Tools;

import java.util.Date;

/**
 *
 * Created by greg3d on 05.11.17.
 *
 */
@SetView(TextView.class)
public class FormatedDateView extends BaseElement implements IDateFormated, ISetView{

    private String dateFormat = DateFormats.DATE_TIME_FORMAT;

    public FormatedDateView(Object view){
        super(view);
    }

    public void setDate(Date date){
        this.view.setText(Tools.dateTimeToString(date, this.dateFormat));
    }

    public Date getDate(){
        return Tools.stringToDate(this.view.getText().toString(), this.dateFormat);
    }

    @Override
    public String getDateFormat() {
        return this.getDateFormat();
    }

    @Override
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }


}
