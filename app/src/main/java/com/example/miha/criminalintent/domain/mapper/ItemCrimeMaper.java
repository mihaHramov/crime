package com.example.miha.criminalintent.domain.mapper;

import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.ItemCrime;

import rx.functions.Func1;

public class ItemCrimeMaper  implements Func1<Crime, ItemCrime> {
    @Override
    public ItemCrime call(Crime crime) {
        ItemCrime itemCrime = new ItemCrime();
        itemCrime.setCrime(crime);
        return itemCrime;
    }
}
