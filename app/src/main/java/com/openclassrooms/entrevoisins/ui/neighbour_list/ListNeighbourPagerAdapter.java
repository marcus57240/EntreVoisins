package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mListTitles = new ArrayList<>();

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return mListTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
       return (CharSequence) mFragmentList.get(position);
    }

    public void AddFragment (Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mListTitles.add(title);
    }
}