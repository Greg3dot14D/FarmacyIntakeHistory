package com.example.greg3d.cureintakedispatcher.model;

import com.example.greg3d.cureintakedispatcher.framework.annotations.Name;

import java.util.Date;

/**
 * Created by greg3d on 15.10.17.
 *
 * Описание лекарства
 *
 */
@Name("FARMACY_TABLE")
public class FarmacyModel extends BaseModel{

    @Name("ID")
    public Integer id;

    //  "Название"
    @Name("NAME")
    public String name;

    //  "Объем"
    @Name("VOLUME")
    public String volume;

    //  "Дата приобретения"
    @Name("LAST_DATE")
    public Date lastDate;

    // "deleted = 1 - запись удалена"
    @Name("DELETED")
    public Integer deleted = 0;
}
