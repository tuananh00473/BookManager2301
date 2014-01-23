package com.samsung.bookmanager.fragments.dialog;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.*;
import android.widget.ImageView;
import android.widget.RadioGroup;
import com.samsung.bookmanager.R;
import com.samsung.bookmanager.common.AppSetting;

import java.io.File;

/**
 * User: Admin
 * Date: 1/21/14
 * Time: 9:29 PM
 */
public class EditBookAvatarDialogFragment extends DialogFragment
{
    public EditBookAvatarDialogFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.edit_book_avatar_dialog_layout, container);
        initComponent(view);
        return view;
    }

    private void initComponent(View view)
    {
        RadioGroup rgEditBookAvatar = (RadioGroup) view.findViewById(R.id.edit_book_avatar_rgEditBookAvatar);
        rgEditBookAvatar.setOnCheckedChangeListener(onCheckChangeListener);
    }

    private RadioGroup.OnCheckedChangeListener onCheckChangeListener = new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId)
        {
            switch (checkedId)
            {
                case R.id.edit_book_avatar_rbTakePhoto:
                    takeImage();
                    break;
                case R.id.edit_book_avatar_rbChoosePhoto:
                    chooseImage();
                    break;
                case R.id.edit_book_avatar_rbDeletePhoto:
                    deleteImage();
                    break;
            }
            dismiss();
        }
    };

    private void takeImage()
    {
//        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        getActivity().startActivityForResult(cameraIntent, AppSetting.PICK_FROM_CAMERA);
        String path = Environment.DIRECTORY_DCIM + "/img" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        File file = new File(path);
        Uri outputFileUri = Uri.fromFile(file);
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, AppSetting.PICK_FROM_CAMERA);
    }

    private void chooseImage()
    {
        Intent pickIntent = new Intent();
        pickIntent.setType("image/*");
        pickIntent.setAction(Intent.ACTION_GET_CONTENT);

        getActivity().startActivityForResult(Intent.createChooser(pickIntent, "Select Picture"), AppSetting.PICK_FROM_FILE);
    }

    private void deleteImage()
    {
        ((ImageView) getActivity().findViewById(R.id.new_book_ivAvatar)).setImageResource(R.drawable.book_avatar_detail);
    }
}
