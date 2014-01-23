package com.samsung.bookmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import com.samsung.bookmanager.R;
import com.samsung.bookmanager.fragments.AlarmManagerFragment;
import com.samsung.bookmanager.fragments.BookManagerFragment;

/**
 * User: Admin
 * Date: 1/17/14
 * Time: 10:44 PM
 */
public class MainManagerActivity extends FragmentActivity
{
    public static final int BOOK_MANAGER = 0;
    public static final int ALARM_MANAGER = 1;

    private Button btBookManager;
    private Button btAlarmManager;

    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.btBookManager:
                    doShowBookManagerFragment();
                    break;
                case R.id.btAlarmManager:
                    doShowAlarmManagerFragment();
                    break;
            }
        }
    };

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_manager_layout);
        setUpView();
        setUpAction();
        doShowBookManagerFragment();
    }

    private void setUpView()
    {
        btBookManager = (Button) findViewById(R.id.btBookManager);
        btAlarmManager = (Button) findViewById(R.id.btAlarmManager);
    }

    private void setUpAction()
    {
        btBookManager.setOnClickListener(onClickListener);
        btAlarmManager.setOnClickListener(onClickListener);
    }

    private void doShowBookManagerFragment()
    {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fContent, new BookManagerFragment(), "BookManagerFragment");
        ft.commit();
        changeTabState(BOOK_MANAGER);
    }

    private void doShowAlarmManagerFragment()
    {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fContent, new AlarmManagerFragment(), "AlarmManagerFragment");
        ft.commit();
        changeTabState(ALARM_MANAGER);
    }

    private void changeTabState(int state)
    {
        switch (state)
        {
            case BOOK_MANAGER:
                btBookManager.setBackgroundResource(R.drawable.bg_button_select_group2item);
                btAlarmManager.setBackgroundResource(R.drawable.bg_button_unselect_group2item);
                break;
            case ALARM_MANAGER:
                btAlarmManager.setBackgroundResource(R.drawable.bg_button_select_group2item);
                btBookManager.setBackgroundResource(R.drawable.bg_button_unselect_group2item);
                break;
        }
    }

    public void addNewBook(View view)
    {
        startActivity(new Intent(this, AddBookActivity.class));
    }

    public void back(View view)
    {
        finish();
    }
}