package com.example.greg3d.cureintakedispatcher.model;

import com.example.greg3d.cureintakedispatcher.framework.annotations.Name;

import java.util.Date;

/**
 * Created by greg3d on 16.10.17.
 */
@Name("INTAKE_HISTORY_TABLE")
public class HistoryRecordModel extends BaseModel{

    //  "Id"
    @Name("ID")
    public Integer id;

    //  "Id схемы"
    @Name("SCHEME_ID")
    public Integer schemeId;

    //  "Приняли 1 / Пропустили 0"
    @Name("STATUS")
    public Integer status;

    //  "Время приема"
    @Name("INTAKE_TIME")
    public Date intakeTime;

    //  "Номер приема за день"
    @Name("INTAKE_NUM")
    public Integer intakeNum;
}
