package com.deepakbaliga.getdone.activities;


import android.app.SharedElementCallback;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.SingleDateAndTimePicker;
import com.deepakbaliga.getdone.adapters.CategoriesAddTaskAdapter;
import com.deepakbaliga.getdone.baseClasses.GetDoneActivity;
import com.deepakbaliga.getdone.callback.ItemCallBack;
import com.deepakbaliga.getdone.customViews.RegularButton;
import com.deepakbaliga.getdone.dialog.SingleDateAndTimePickerDialog;
import com.deepakbaliga.getdone.model.Category;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateToDoActivity extends GetDoneActivity {


    @BindView(R.id.todo_edit_text)
    EditText todoEditText;

    @BindView(R.id.button_add_task)
    RegularButton addTaskButton;

    @BindView(R.id.close_button)
    ImageView closeButton;

    @BindView(R.id.categories_recyclerview)
    RecyclerView categoriesRecyclerView;

    @BindView(R.id.button_date_and_time)
    ImageView dateAndTimeButton;


    @BindView(R.id.button_reminder)
    ImageView reminderButton;

    @BindView(R.id.button_comment)
    ImageView commentButton;

    @BindView(R.id.button_attachment)
    ImageView attachmentButton;

    @BindView(R.id.button_voice_note)
    ImageView voiceNoteButton;

    @BindView(R.id.tools)
    LinearLayout toolsLayout;

    @BindView(R.id.line_one)
    View lineOne;

    @BindView(R.id.line_two)
    View lineTwo;

    @BindView(R.id.line_three)
    View lineThree;




    private SingleDateAndTimePickerDialog.Builder dateTimePicker;

    private LinkedList<Category> categories = new LinkedList<>();

    private CategoriesAddTaskAdapter categoriesAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_to_do);
        ButterKnife.bind(this);



        animateViews();
        init();
        initToolBar();

    }

    private void animateViews() {

        final Animation dropFromTopAnimation = AnimationUtils.loadAnimation(this, R.anim.drop_from_top);
        final Animation dropFromTopAnimationLate = AnimationUtils.loadAnimation(this, R.anim.drop_from_top_little_later);
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_slow);

        //Animate TodoEditText First
        todoEditText.setAnimation(dropFromTopAnimation);
        categoriesRecyclerView.setAnimation(dropFromTopAnimationLate);
        toolsLayout.setAnimation(dropFromTopAnimationLate);
        lineOne.setAnimation(fadeIn);
        lineTwo.setAnimation(fadeIn);
        lineThree.setAnimation(fadeIn);


    }

    private void initToolBar() {

        dateAndTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                 dateTimePicker = new SingleDateAndTimePickerDialog.Builder(CreateToDoActivity.this)
                        .bottomSheet()
                        .curved()
                        .listener(new SingleDateAndTimePickerDialog.Listener() {
                            @Override
                            public void onDateSelected(Date date, boolean isThereTime) {

                                if (isThereTime){


                                }else{


                                }


                            }
                        });

                dateTimePicker.display();
            }
        });

    }

    private void init() {


        categories.add(new Category((long)(Math.random()*1000000),"Personal", 43, R.drawable.icon_person, R.color.alizarin,false,0));
        categories.add(new Category((long)(Math.random()*1000000),"Work", 235, R.drawable.icon_work, R.color.amethyst,false,0));
        categories.add(new Category((long)(Math.random()*1000000),"Meet", 3, R.drawable.icon_happy_mood, R.color.peter_river,false,0));
        categories.add(new Category((long)(Math.random()*1000000),"Home", 113, R.drawable.icon_home, R.color.emerald,false,0));
        categories.add(new Category((long)(Math.random()*1000000),"School", 23, R.drawable.icon_school, R.color.sun_flower,false,0));
        categories.add(new Category((long)(Math.random()*1000000),"Earth", 0, R.drawable.icon_earth, R.color.green_sea,false,0));
        categories.add(new Category((long)(Math.random()*1000000),"Universe", 7, R.drawable.icon_star, R.color.clouds,false,0));

        categoriesAdapter = new CategoriesAddTaskAdapter(this, 0, categories, new ItemCallBack() {
            @Override
            public void onSelect(int position) {

                categoriesAdapter.notifyDataSetChanged();


            }
        });


        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        categoriesRecyclerView.setLayoutManager(linearLayoutManager);
        categoriesRecyclerView.setAdapter(categoriesAdapter);



        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                CreateToDoActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.close);
            }
        });


    }

    @Override
    public void onBackPressed() {

        Log.e("BACK", "Button Press");

        if(dateTimePicker!=null){
            if(!dateTimePicker.isClosed()){
                super.onBackPressed();
                addTaskButton.setText("");
                addTaskButton.setBackground(getDrawable(R.drawable.ripple_rectangle_corners));
            }else{
                dateTimePicker.close();
            }
        }else{

            super.onBackPressed();
            addTaskButton.setText("");
            addTaskButton.setBackground(getDrawable(R.drawable.ripple_rectangle_corners));
        }







    }



}
