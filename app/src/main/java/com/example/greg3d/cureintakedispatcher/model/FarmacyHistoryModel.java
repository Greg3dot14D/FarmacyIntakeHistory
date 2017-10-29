package com.example.greg3d.cureintakedispatcher.model;

import com.example.greg3d.cureintakedispatcher.framework.annotations.Name;

import java.util.Date;

/**
 * Created by greg3d on 28.10.17.
 *
 * История покупок лекарств
 *
 */
@Name("FARMACY_HISTORY_TABLE")
public class FarmacyHistoryModel extends BaseModel{
    @Name("ID")
    public Integer id;

    // "Id лекарства"
    @Name("FARMACY_ID")
    public Integer farmacyId;

    //  "Название"
    @Name("NAME")
    public String name;

    //  "Объем"
    @Name("VOLUME")
    public String volume;

    //  "Цена"
    @Name("PRICE")
    public Double price;

    //  "Дата приобретения"
    @Name("PURCHASE_DATE")
    public Date purchaseDate;
}
