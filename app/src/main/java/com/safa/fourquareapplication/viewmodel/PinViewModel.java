package com.safa.fourquareapplication.viewmodel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.safa.fourquareapplication.model.PinArea;

import java.util.ArrayList;
import java.util.List;

public class PinViewModel extends ViewModel {
    private MutableLiveData<List<PinArea>> pinList;
    private MutableLiveData<Boolean> progressBar = new MutableLiveData<Boolean>();

    public LiveData<List<PinArea>> getPinList(){
        if (pinList == null){
            pinList = new MutableLiveData<List<PinArea>>();

        }
        loadPins();
        return pinList;
    }

    public MutableLiveData<Boolean>  getStatusProgressBar(){
        return progressBar;
    }

    private void loadPins(){

        final ArrayList<PinArea> pinArea  = new ArrayList<PinArea>();
        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Pins");
       // parseQuery.orderByAscending("createAt");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> objects, ParseException e) {
                if (e == null){
                    final int[] i = {0};
                    for (final ParseObject o : objects){
                        ParseFile parseFile = (ParseFile) o.get("areaImage");

                        if(parseFile != null){
                            parseFile.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e == null && data != null){
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                                        String id = o.getObjectId();
                                        String name = o.getString("areaName");
                                        String type =o.getString("areaType");
                                        String atmosphere = o.getString("areaAtmosphere");
                                        String latitude = o.getString("areaLatitude");
                                        String longitude = o.getString("areaLongitude");

                                        pinArea.add(new PinArea(name,type,atmosphere,latitude,longitude,bitmap,id));

                                        pinList.setValue(pinArea);
                                        progressBar.setValue(true);

                                    }

                                }
                            });
                        }

                    }

                }

            }
        });




    }


}
