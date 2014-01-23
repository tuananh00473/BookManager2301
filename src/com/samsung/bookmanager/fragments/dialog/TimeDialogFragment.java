package com.samsung.bookmanager.fragments.dialog;

import android.app.*;
import android.content.Context;
import android.os.Bundle;
import android.widget.TimePicker;
import com.samsung.bookmanager.fragments.action_interface.TimeDialogFragmentListener;

import java.util.Calendar;

public class TimeDialogFragment extends DialogFragment
{

    public static String TAG = "TimeDialogFragment";
    static Context mContext; //I guess hold the context that called it. Needed when making a DatePickerDialog. I guess its needed when conncting the fragment with the context
    static int mHour;
    static int mMinute;
    static int mSecond;
    static TimeDialogFragmentListener mListener;

    public static TimeDialogFragment newInstance(Context context, TimeDialogFragmentListener listener, Calendar now)
    {
        TimeDialogFragment dialog = new TimeDialogFragment();
        mContext = context;
        mListener = listener;

        mHour = now.get(Calendar.HOUR);
        mMinute = now.get(Calendar.MINUTE);
        mSecond = now.get(Calendar.SECOND);
        /*I dont really see the purpose of the below*/
        Bundle args = new Bundle();
        args.putString("title", "Set Time");
        dialog.setArguments(args);//setArguments can only be called before fragment is attached to an activity, so right after the fragment is created


        return dialog;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new TimePickerDialog(mContext, mTimeSetListener, mHour, mMinute, true);
    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener()
    {

        @Override
        public void onTimeSet(TimePicker view, int hour, int minute)
        {
            mHour = hour;
            mMinute = minute;
            mSecond = 0;

            mListener.updateChangedTime(hour, minute, mSecond);
        }
    };


}
