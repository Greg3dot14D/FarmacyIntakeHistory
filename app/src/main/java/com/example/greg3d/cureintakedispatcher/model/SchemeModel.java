package com.example.greg3d.cureintakedispatcher.model;

import com.example.greg3d.cureintakedispatcher.framework.annotations.Name;

/**
 * Created by greg3d on 21.10.17.
 */
@Name("INTAKE_SCHEME_TABLE")
public class SchemeModel extends BaseModel{
    // "Id"
    @Name("ID")
    public Integer id;

    //  "Название"
    @Name("NAME")
    public String name;

    //  "Id лекарства"
    @Name("FARMACY_ID")
    public Integer farmacyId;

    //  "Продолжительность"
    @Name("DURATION")
    public Integer duration;

    //  "Сколько раз в день"
    @Name("INTAKE_IN_DAY_COUNT")
    public Integer intake_count;

}

