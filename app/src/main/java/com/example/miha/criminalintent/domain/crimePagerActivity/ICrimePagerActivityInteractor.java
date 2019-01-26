package com.example.miha.criminalintent.domain.crimePagerActivity;

import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.ItemCrime;

import java.util.List;

public interface ICrimePagerActivityInteractor {
    void sendCrime(Crime crime,OnSendComplete sendComplete,OnSendFailure failure);

    interface OnSendComplete {
        void call(String string);
    }

    interface OnSendFailure {
        void call(String string);
    }

    void getCrimes(Callback callback);

    interface Callback {
        void success(List<ItemCrime> crime);
    }

    Integer getCurrentPosition(List<ItemCrime> crime);
}
