package com.samsung.bookmanager.fragments.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import com.samsung.bookmanager.R;
import com.samsung.bookmanager.entities.Book;

/**
 * User: anhnt
 * Date: 1/24/14
 * Time: 1:31 PM
 */
public class SetReminderDialogFragment extends DialogFragment
{
    private Book book;
    private TextView tvBookId;
    private TextView tvBookName;
    private TextView tvDate;
    private TextView tvTime;
    private Button btSetReminder;
    private Button btCancel;

    public SetReminderDialogFragment(Book book)
    {
        this.book = book;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.set_reminder_dialog_layout, container);
        initComponent(view);
        return view;
    }

    private void initComponent(View view)
    {

    }
}
