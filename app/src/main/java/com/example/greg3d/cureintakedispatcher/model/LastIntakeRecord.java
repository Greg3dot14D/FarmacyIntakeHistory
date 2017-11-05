package com.example.greg3d.cureintakedispatcher.model;

import com.example.greg3d.cureintakedispatcher.framework.annotations.Name;

import java.util.Date;

/**
 * Created by greg3d on 21.10.17.
 */

public class LastIntakeRecord extends BaseModel{

    @Name("ID")
    public Integer id;

    @Name("SCHEME_ID")
    public Integer schemeId;

    @Name("Запись выбранна")
    public Boolean selected;

    @Name("Название схемы")
    public String schemeName;

    @Name("Принято")
    public String currentIntake;

    @Name("Последний прием")
    public String lastIntakeStatus;

    @Name("Последний прием")
    public Date lastIntakeDate;

    @Name("Осталось дней")
    public String daysRemaind;

    //  "Дата приобретения"
    @Name("LAST_DATE")
    public Date lastDate;

    // "deleted = 1 - запись удалена"
    @Name("DELETED")
    public Integer deleted = 0;
}
