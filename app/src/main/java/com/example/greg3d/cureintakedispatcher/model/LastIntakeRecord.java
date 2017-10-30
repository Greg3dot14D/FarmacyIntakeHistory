package com.example.greg3d.cureintakedispatcher.model;

import com.example.greg3d.cureintakedispatcher.framework.annotations.Name;

import java.util.Date;

/**
 * Created by greg3d on 21.10.17.
 */

public class LastIntakeRecord extends BaseModel{

    @Name("Запись выбранна")
    public Boolean selected;

    @Name("Название схемы")
    public String schemeName;

    @Name("Принято")
    public String currentIntake;

    @Name("Последний прием")
    public String lastIntake;

    @Name("Осталось дней")
    public String dayBalance;

    //  "Дата приобретения"
    @Name("LAST_DATE")
    public Date lastDate;

    // "deleted = 1 - запись удалена"
    @Name("DELETED")
    public Integer deleted = 0;
}
