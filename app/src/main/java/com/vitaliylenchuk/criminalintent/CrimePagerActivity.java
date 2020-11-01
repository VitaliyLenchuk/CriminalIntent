package com.vitaliylenchuk.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity
    implements CrimeFragment.Callbacks {
    private static final String EXTRA_CRIME_ID = "com.vitaliylenchuk.criminalintent.crime_id";
    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private Button mToFirstButton;
    private Button mToLastButton;


    public static Intent newIntent(Context packageContext, UUID crimeID) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeID);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeID = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position){
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        mToFirstButton = (Button) findViewById(R.id.to_first);
        mToLastButton = (Button) findViewById(R.id.to_last);
        mToFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
                mToFirstButton.setEnabled(false);
                mToLastButton.setEnabled(true);
            }
        });
        mToLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mCrimes.size());
                mToFirstButton.setEnabled(true);
                mToLastButton.setEnabled(false);
            }
        });
        for (int i=0; i<mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeID)){
                mViewPager.setCurrentItem(i);
                if (i==0) mToFirstButton.setEnabled(false);
                else mToFirstButton.setEnabled(true);
                if (i==mCrimes.size()) mToLastButton.setEnabled(false);
                else mToLastButton.setEnabled(true);
                break;
            }
        }
    }

    @Override
    public void onCrimeUpdated(Crime crime){

    }

}
