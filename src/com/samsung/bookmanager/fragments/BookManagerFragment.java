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
import com.samsung.bookmanager.common.adapter.ListArrayAdapter;
import com.samsung.bookmanager.fragments.dialog.MenuDialogFragment;
import com.samsung.bookmanager.service.BookService;

public class BookManagerFragment extends Fragment
{
    private BookService bookService;
    private ListView lvBookList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.book_management_fragment_layout, null);
        bookService = new BookService(getActivity());
        initComponents(view);
        initListView();
        return view;
    }

    public void initComponents(View view)
    {
        lvBookList = (ListView) view.findViewById(R.id.book_manager_lvBookList);
    }

    public void initListView()
    {
        SimpleCursorAdapter mAdapter = ListArrayAdapter.getSimpleCursorAdapter(getActivity(), bookService.getCursor());
        lvBookList.setAdapter(mAdapter);

        lvBookList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long bookId)
            {
                Intent intent = new Intent(getActivity(), BookDetailInforActivity.class);
                intent.putExtra("BOOK", bookService.findById((int) bookId));
                startActivity(intent);
            }
        });

        lvBookList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long bookId)
            {
                MenuDialogFragment menuDialogFragment = new MenuDialogFragment(bookService.findById((int) bookId));
                menuDialogFragment.setRetainInstance(true);
                menuDialogFragment.show(getActivity().getFragmentManager(), "MenuDialogFragment");
                return true;
            }
        });
    }
}
