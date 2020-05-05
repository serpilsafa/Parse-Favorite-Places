package com.safa.fourquareapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.safa.fourquareapplication.R;
import com.safa.fourquareapplication.adapter.PinListRecycleViewAdapter;
import com.safa.fourquareapplication.model.PinArea;
import com.safa.fourquareapplication.viewmodel.PinViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListPinActivity extends AppCompatActivity {

    private PinViewModel model;
    private RecyclerView recyclerView;
    private PinListRecycleViewAdapter adapter;
    private ArrayList<PinArea> pinAreaArrayList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pin);

        model = new ViewModelProvider(this).get(PinViewModel.class);
        pinAreaArrayList = new ArrayList<PinArea>();

        progressBar = findViewById(R.id.progress_circular);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PinListRecycleViewAdapter(pinAreaArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.GONE);

        dataChanged();



    }

    private void dataChanged(){
        model.getPinList().observe(this, new Observer<List<PinArea>>() {
            @Override
            public void onChanged(List<PinArea> pinAreas) {
                adapter.updateView(pinAreas);
            }
        });



        model.getStatusProgressBar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAddPin:
                goActivity(AddPinActivity.class);
                break;
            case R.id.menuSignOut:
                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            goActivity(LoginActivity.class);
                        }
                    }
                });
        }

        return super.onOptionsItemSelected(item);
    }

    private void goActivity(Class activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();

    }
}
