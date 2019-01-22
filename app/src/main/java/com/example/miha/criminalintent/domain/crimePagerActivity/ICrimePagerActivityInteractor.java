package com.example.miha.criminalintent.domain.crimePagerActivity;

import com.example.miha.criminalintent.domain.model.Crime;

import java.util.List;

public interface ICrimePagerActivityInteractor {
    void getCrimes(Callback callback);
    interface Callback {
        void success(List<Crime> crime);
    }
    Integer getCurrentPosition(List<Crime> crime);
}
