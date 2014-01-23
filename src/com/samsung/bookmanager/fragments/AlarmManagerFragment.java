package com.samsung.bookmanager.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import com.samsung.bookmanager.R;

/**
 * User: thinhdd
 * Date: 10/18/13
 * Time: 9:35 AM
 */
public class AlarmManagerFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.alarm_management_fragment_layout, null);
    }
}
