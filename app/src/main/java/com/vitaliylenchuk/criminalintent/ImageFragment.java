package com.vitaliylenchuk.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;

import com.vitaliylenchuk.criminalintent.database.PictureUtils;

import java.io.File;

public class ImageFragment extends DialogFragment {

    private static final String ARG_FILE = "fileName";
    private File mPhotoFile;
    private ImageView mPhotoView;

    public static ImageFragment newInstance(File fileName) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_FILE, fileName);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //@NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mPhotoFile = (File) getArguments().getSerializable(ARG_FILE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.diaolog_image, null);
        mPhotoView = (ImageView) layout.findViewById(R.id.image_view);
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }

        builder.setView(layout);
        builder.setTitle(R.string.image_tittle);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        return builder.create();
    }
}
