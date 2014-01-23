package com.samsung.bookmanager.fragments.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.*;
import android.widget.RadioGroup;
import com.samsung.bookmanager.R;
import com.samsung.bookmanager.entities.Book;
import com.samsung.bookmanager.service.BookService;

/**
 * User: Admin
 * Date: 1/21/14
 * Time: 9:29 PM
 */
public class MenuDialogFragment extends DialogFragment
{
    private BookService bookService;
    private Book book;

    public MenuDialogFragment(Book book)
    {
        this.book = book;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.menu_dialog_layout, container);
        bookService = new BookService(getActivity());
        initComponent(view);
        return view;
    }

    private void initComponent(View view)
    {
        RadioGroup rgMenuOption = (RadioGroup) view.findViewById(R.id.menu_dialog_rgMenu);
        rgMenuOption.setOnCheckedChangeListener(onCheckChangeListener);
    }

    private RadioGroup.OnCheckedChangeListener onCheckChangeListener = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId)
        {
            switch (checkedId)
            {
                case R.id.menu_dialog_rbSetReminder:

                    break;
                case R.id.menu_dialog_rbEdit:
                    break;
                case R.id.menu_dialog_rbDelete:
                    bookService.delete(book);
                    break;
            }
            dismiss();
        }
    };
}
