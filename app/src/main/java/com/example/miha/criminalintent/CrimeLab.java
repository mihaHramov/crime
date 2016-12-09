package com.example.miha.criminalintent;

import android.content.Context;
import android.util.Log;

import com.example.miha.criminalintent.model.Crime;

import java.util.ArrayList;
import java.util.UUID;

public class CrimeLab {

    private ArrayList<Crime> mCrimes;
    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";
    private CriminalIntentJSONSerializer mSerializer;
    private static CrimeLab sCrimeLab;
    private Context mAppContext;

    public void addCrime(Crime c) {
        mCrimes.add(c);
    }

    public void deleteCrime(Crime c) {
        mCrimes.remove(c);
    }

    public boolean saveCrimes() {
        mSerializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME);
        try {
            mSerializer.saveCrimes(mCrimes);
            Log.d(TAG, "crimes saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes: ", e);
            return false;
        }
    }

    private CrimeLab(Context appContext) {//генератор преступлений
        mAppContext = appContext;
        mSerializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME);
        try {
            mCrimes = mSerializer.loadCrimes();
        } catch (Exception e) {
            mCrimes = new ArrayList<Crime>();
            Log.e(TAG, "Error loading crimes: ", e);
        }
        if(mCrimes.size()==0){
            Log.d("mihaHramov","pizdec");
        }
    }

    public static CrimeLab get(Context c) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime c : mCrimes) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }
}