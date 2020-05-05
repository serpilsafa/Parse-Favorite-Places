package com.safa.fourquareapplication.viewmodel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ArrayAdapter;

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
    private MutableLiveData<Boolean> progressBar;

    public LiveData<List<PinArea>> getPinList(){
        if (pinList == null){
            pinList = new MutableLiveData<List<PinArea>>();
            progressBar = new MutableLiveData<Boolean>();

            loadPins();
        }
        return pinList;
    }

    public MutableLiveData<Boolean>  getStatusProgressBar(){
        return progressBar;
    }

    private void loadPins(){
        /*
        ArrayList<PinArea> pinListX = new ArrayList<PinArea>();
        pinListX.add(new PinArea("test","test", "test","test", "test","test"));
        pinList.setValue(pinListX);

         */


        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Pins");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    final ArrayList<PinArea> pinArea  = new ArrayList<PinArea>();
                    for (final ParseObject o : objects){

                        final PinArea pin = PinArea.instance();

                        ParseFile parseFile = (ParseFile) o.get("areaImage");
                        if(parseFile != null){
                            parseFile.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if (e == null && data != null){
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        pin.setAreaImage(bitmap);

                                        pin.setAreaName(o.getString("areaName"));
                                        pin.setAreaType(o.getString("areaType"));
                                        pin.setAreaAtmosphere(o.getString("areaAtmosphere"));
                                        pin.setAreaLatitude(o.getString("areaLatitude"));
                                        pin.setAreaLongitude(o.getString("areaLongitude"));
                                        // pin.setAreaImage(o.getString("areaName"));

                                        pinArea.add(pin);

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
