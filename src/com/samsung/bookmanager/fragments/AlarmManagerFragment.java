package com.samsung.bookmanager.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import com.samsung.bookmanager.R;
import com.samsung.bookmanager.activities.BookDetailInforActivity;
import com.samsung.bookmanager.common.adapter.ListReminderArrayAdapter;
import com.samsung.bookmanager.service.ReminderService;

/**
 * User: thinhdd
 * Date: 10/18/13
 * Time: 9:35 AM
 */
public class AlarmManagerFragment extends Fragment
{
    private ReminderService reminderService;
    private ListView lvReminderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.alarm_management_fragment_layout, null);
        reminderService = new ReminderService(getActivity());
        initComponents(view);
        initListView();
        return view;
    }

    public void initComponents(View view)
    {
        lvReminderList = (ListView) view.findViewById(R.id.alarm_manager_lvAlarmList);
    }

    public void initListView()
    {
        SimpleCursorAdapter mAdapter = ListReminderArrayAdapter.getSimpleCursorAdapter(getActivity(), reminderService.getCursor());
        lvReminderList.setAdapter(mAdapter);

        lvReminderList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long reminderId)
            {
                Intent intent = new Intent(getActivity(), BookDetailInforActivity.class);
                intent.putExtra("REMINDER", reminderService.findById((int) reminderId));
                startActivity(intent);
            }
        });
    }
}
