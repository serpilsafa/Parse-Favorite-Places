package com.safa.fourquareapplication.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.util.List;

public class Util {

    @BindingAdapter({"android:bitmap"})
    public static void setImageBitmap(final ImageView imageView, Bitmap bitmap){
        imageView.setImageBitmap(bitmap);

    }

}
