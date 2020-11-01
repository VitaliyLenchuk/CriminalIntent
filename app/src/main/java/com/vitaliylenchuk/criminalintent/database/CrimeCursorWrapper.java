package com.vitaliylenchuk.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.vitaliylenchuk.criminalintent.Crime;
import com.vitaliylenchuk.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super (cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int position = getInt(getColumnIndex(CrimeTable.Cols.POSITION));
        int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));
        String suspect = getString(getColumnIndex(CrimeTable.Cols.SUSPECT));
        int suspectid = getInt(getColumnIndex(CrimeTable.Cols.SUSPECTID));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        crime.setPosition(position);
        crime.setSuspect(suspect);
        crime.setSuspectId(suspectid);

        return crime;
    }
}
