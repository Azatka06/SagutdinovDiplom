package com.example.diplom.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diplom.App;
import com.example.diplom.AppConstants;
import com.example.diplom.models.Note;
import com.sagutdinov.diplom.R;

import java.util.Calendar;
import java.util.Date;

import static com.example.diplom.App.getNoteHolder;

public class AddActivity extends AppCompatActivity {
    private String creatorName;
    private String noteId;
    private Date selectedDate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
    }

    private void init() {
        final TextView headText = findViewById(R.id.headText);
        final TextView bodyText = findViewById(R.id.bodyText);
        final Button btnSave = findViewById(R.id.btnSave);
        final CalendarView deadlineSelector=findViewById(R.id.deadlineSelector);
        deadlineSelector.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar=Calendar.getInstance();
                calendar.set(year,month,dayOfMonth);
                selectedDate = calendar.getTime();
                view.setDate(selectedDate.getTime(),true,true);
            }
        });
        creatorName = getIntent().getExtras().getString(AppConstants.name);
        if (getIntent().getExtras().containsKey(AppConstants.noteId)) {
            noteId=getIntent().getExtras().getString(AppConstants.noteId);
            final Note noteText= App.getNoteHolder().getNote(noteId);
            headText.setText(noteText.getHeadText());
            bodyText.setText(noteText.getBodyText());
            deadlineSelector.setDate(noteText.getDate(),true,true);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    App.getNoteHolder().udpateNote(headText.getText().toString(),bodyText.getText().toString(),noteId,selectedDate);
                    finish();
                }
            });
        } else {
            selectedDate=new Date(deadlineSelector.getDate());
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    App.getNoteHolder().addNote(headText.getText().toString(), bodyText.getText().toString(), creatorName,selectedDate);
                    finish();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getIntent().getExtras().containsKey(AppConstants.noteId)) {
            getMenuInflater().inflate(R.menu.add_menu, menu);
        }
        return true;
    }

    public void onDel(MenuItem item) {
        getNoteHolder().deleteNote(noteId);
        finish();
    }
}
