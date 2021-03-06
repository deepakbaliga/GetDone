package com.deepakbaliga.getdone.activities;


import android.Manifest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.os.Environment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.deepakbaliga.getdone.R;
import com.deepakbaliga.getdone.adapters.CategoriesAddTaskAdapter;
import com.deepakbaliga.getdone.adapters.PictureTileAdapter;
import com.deepakbaliga.getdone.callback.CallBackSubTasks;
import com.deepakbaliga.getdone.callback.ItemCallBack;
import com.deepakbaliga.getdone.customViews.RegularButton;
import com.deepakbaliga.getdone.customViews.RegularTextView;
import com.deepakbaliga.getdone.customViews.ThinTextView;
import com.deepakbaliga.getdone.dialog.SingleDateAndTimePickerDialog;
import com.deepakbaliga.getdone.fragments.SubTasksFragment;
import com.deepakbaliga.getdone.model.Category;
import com.deepakbaliga.getdone.model.RealmUri;
import com.deepakbaliga.getdone.model.SubTask;
import com.deepakbaliga.getdone.model.Task;
import com.deepakbaliga.getdone.utilities.Pop;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder;
import cafe.adriel.androidaudiorecorder.model.AudioChannel;
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate;
import cafe.adriel.androidaudiorecorder.model.AudioSource;
import gun0912.tedbottompicker.TedBottomPicker;
import io.realm.Realm;
import io.realm.RealmList;

public class CreateToDoActivity extends FragmentActivity {


    @BindView(R.id.todo_edit_text)
    EditText todoEditText;

    @BindView(R.id.button_add_task)
    RegularButton addTaskButton;

    @BindView(R.id.close_button)
    ImageView closeButton;

    @BindView(R.id.categories_recyclerview)
    RecyclerView categoriesRecyclerView;

    @BindView(R.id.images_recyclerview)
    RecyclerView tilesRecyclerView;

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

    @BindView(R.id.day_textview)
    ThinTextView dayTextView;

    @BindView(R.id.time_textview)
    RegularTextView timeTextView;

    @BindView(R.id.recorded_audio)
    LinearLayout recordedAudio;


    private String filePath;
    private boolean isAudioPresent = false;


    private boolean setReminder = false;
    private boolean setDate     = false;
    private boolean setTime     = false;

    private Date dateSelected;

    private SingleDateAndTimePickerDialog.Builder dateTimePicker;

    private int categorySelected = 0;
    private LinkedList<Category> categories = new LinkedList<>();
    private ArrayList<Uri> fileUriList = new ArrayList<>();
    private LinkedList<SubTask> subTasks = new LinkedList<>();

    private CategoriesAddTaskAdapter categoriesAdapter;


    private PictureTileAdapter pictureTileAdapter;

    private LinearLayoutManager linearLayoutManagerCategories, linearLayoutManagerTiles;


    private boolean isOpenSubTask = false;
    FragmentTransaction transaction;








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

                                setTime = isThereTime;

                                setDate = true;

                                dateSelected = date;

                                dateAndTimeButton.setColorFilter(getResources().getColor(R.color.colorPrimary));
                                final Animation vibrateAnimation = AnimationUtils.loadAnimation(CreateToDoActivity.this, R.anim.vibrate);
                                dateAndTimeButton.startAnimation(vibrateAnimation);

                                final Animation riseFromBottom = AnimationUtils.loadAnimation(CreateToDoActivity.this, R.anim.rise_from_bottom_little_later);


                                String pattern = "dd MMMM";
                                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                                dayTextView.setVisibility(View.VISIBLE);
                                dayTextView.setText(dateFormat.format(date));
                                dayTextView.setAnimation(riseFromBottom);


                                //TimeFormat
                                dateFormat = new SimpleDateFormat("hh:mm a");



                                if (isThereTime){

                                timeTextView.setText(dateFormat.format(date));
                                timeTextView.setVisibility(View.VISIBLE);
                                timeTextView.setAnimation(riseFromBottom);

                                }else{
                                    timeTextView.setText("");
                                    timeTextView.setVisibility(View.INVISIBLE);

                                }


                            }
                        });

                dateTimePicker.display();
            }
        });


        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setReminder = !setReminder;

                if(setReminder){

                    reminderButton.setColorFilter(getResources().getColor(R.color.colorPrimary));
                    final Animation vibrateAnimation = AnimationUtils.loadAnimation(CreateToDoActivity.this, R.anim.vibrate);
                    reminderButton.startAnimation(vibrateAnimation);

                }else{
                    reminderButton.setColorFilter(getResources().getColor(R.color.light_grey));

                }
            }
        });

        attachmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                        TedBottomPicker bottomSheetDialogFragment = new TedBottomPicker.Builder(CreateToDoActivity.this)
                                .setOnMultiImageSelectedListener(new TedBottomPicker.OnMultiImageSelectedListener() {
                                    @Override
                                    public void onImagesSelected(ArrayList<Uri> uriList) {

                                        fileUriList  = uriList;


                                        if(fileUriList.size()>0){

                                            attachmentButton.setColorFilter(getResources().getColor(R.color.colorPrimary));
                                            final Animation vibrateAnimation = AnimationUtils.loadAnimation(CreateToDoActivity.this, R.anim.vibrate);
                                            attachmentButton.startAnimation(vibrateAnimation);

                                            pictureTileAdapter.setUriList(uriList);
                                            pictureTileAdapter.notifyDataSetChanged();

                                        }else{
                                            attachmentButton.setColorFilter(getResources().getColor(R.color.light_grey));

                                        }



                                    }
                                })
                                .setPeekHeight(1600)
                                .showTitle(true)
                                .setTitle("")
                                .setCompleteButtonText("Done")
                                .setEmptySelectionText("Select Pictures ( Max Limit 10 )")
                                .setSelectMaxCount(10)
                                .setSelectMaxCountErrorText("Maximum Limit of 10 Reached.")
                                .create();

                        bottomSheetDialogFragment.show(getSupportFragmentManager());

                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Toast.makeText(CreateToDoActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }


                };

                new TedPermission(CreateToDoActivity.this)
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                        .check();








            }
        });


        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isOpenSubTask = true;



                 transaction = getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("idontcare")
                         .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .add(R.id.activity_create_to_do, SubTasksFragment.getInstance(subTasks, new CallBackSubTasks() {
                            @Override
                            public void onResult(LinkedList<SubTask> subTaskList) {
                                subTasks = subTaskList;


                                if(subTasks.size()>0){
                                    commentButton.setColorFilter(getResources().getColor(R.color.colorPrimary));
                                    final Animation vibrateAnimation = AnimationUtils.loadAnimation(CreateToDoActivity.this, R.anim.vibrate);
                                    commentButton.startAnimation(vibrateAnimation);
                                }else{
                                    commentButton.setColorFilter(getResources().getColor(R.color.light_grey));
                                }

                            }
                        }), "createSubTask");

                transaction.commit();


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

                categorySelected = position;


            }
        });

        pictureTileAdapter = new PictureTileAdapter(this, new ArrayList<Uri>(), new ItemCallBack() {
            @Override
            public void onSelect(int position) {

                fileUriList.remove(position);
                pictureTileAdapter.notifyDataSetChanged();

                if(fileUriList.size()<=0){
                    attachmentButton.setColorFilter(getResources().getColor(R.color.light_grey));
                }

            }
        });

        linearLayoutManagerCategories = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManagerTiles = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        categoriesRecyclerView.setLayoutManager(linearLayoutManagerCategories);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        tilesRecyclerView.setLayoutManager(linearLayoutManagerTiles);
        tilesRecyclerView.setAdapter(pictureTileAdapter);



        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
                CreateToDoActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.close);
            }
        });

        voiceNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
                        String format = simpleDateFormat.format(new Date());
                        Log.d("MainActivity", "Current Timestamp: " + format);

                         filePath = Environment.getExternalStorageDirectory() + "/"+format+"_recorded_audio.wav";
                        Log.e("TAG", "onPermissionGranted: "+ filePath);
                        int color = getResources().getColor(R.color.colorAccent);
                        int requestCode = 0;
                        AndroidAudioRecorder.with(CreateToDoActivity.this)

                                .setFilePath(filePath)
                                .setColor(color)
                                .setRequestCode(requestCode)
                                .setSource(AudioSource.MIC)
                                .setChannel(AudioChannel.STEREO)
                                .setSampleRate(AudioSampleRate.HZ_48000)
                                .setAutoStart(false)
                                .setKeepDisplayOn(true)
                                .record();


                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Pop.show(CreateToDoActivity.this, "Permission Denied\n" + deniedPermissions.toString());
                    }


                };


                new TedPermission(CreateToDoActivity.this)
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();


            }
        });

        recordedAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAudioPresent = false;


                Animation animation = AnimationUtils.loadAnimation(CreateToDoActivity.this, android.R.anim.slide_out_right);
                recordedAudio.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        recordedAudio.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                voiceNoteButton.setColorFilter(getResources().getColor(R.color.light_grey));


            }
        });




    }

    @Override
    public void onBackPressed() {

        if(isOpenSubTask){

            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).
                    remove(getSupportFragmentManager().findFragmentById(R.id.activity_create_to_do)).commit();
            isOpenSubTask = false;
            return;
        }

        if(dateTimePicker!=null){
            if(!dateTimePicker.isClosed()){

                super.onBackPressed();
                addTaskButton.setText("");
                addTaskButton.setBackground(getDrawable(R.drawable.ripple_rectangle_corners));
                return;

            }else{
                dateTimePicker.close();
                dateTimePicker=null;
                return;
            }
        }



            super.onBackPressed();
            addTaskButton.setText("");
            addTaskButton.setBackground(getDrawable(R.drawable.ripple_rectangle_corners));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                recordedAudio.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(CreateToDoActivity.this, android.R.anim.slide_in_left);
                recordedAudio.startAnimation(animation);

                voiceNoteButton.setColorFilter(getResources().getColor(R.color.colorPrimary));
                final Animation vibrateAnimation = AnimationUtils.loadAnimation(CreateToDoActivity.this, R.anim.vibrate);
                voiceNoteButton.startAnimation(vibrateAnimation);

                isAudioPresent = true;


            } else if (resultCode == RESULT_CANCELED) {


            }
        }

    }

    public void addTask(View view){


        if(isValid()){


            final Realm realm = Realm.getDefaultInstance();



            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {


                    Task task = bgRealm.createObject(Task.class);


                    task.setId(0l);

                    task.setTask(todoEditText.getText().toString().trim());



                    Category category = bgRealm.createObject(Category.class);

                    category.setCategoryTitle(categories.get(categorySelected).getCategoryTitle());
                    category.setColor(categories.get(categorySelected).getColor());
                    category.setDeletable(categories.get(categorySelected).isDeletable());
                    category.setIcon(categories.get(categorySelected).getIcon());
                    category.setRemainingTasks(categories.get(categorySelected).getRemainingTasks());
                    category.setTotalTasks(categories.get(categorySelected).getTotalTasks());

                    task.setCategory(category);



                    task.setTimeSet(setTime);
                    task.setDate(dateSelected);
                    task.setReminderSet(setReminder);


                    if(subTasks.size()>0){
                        task.setSubTaskSet(true);
                    }else{
                        task.setSubTaskSet(false);
                    }




                    task.setSubTasks(SubTask.getRealList(bgRealm, subTasks));


                    if(fileUriList.size()>0){
                        task.setAttachmentSet(true);
                    }else{
                        task.setAttachmentSet(false);
                    }


                    task.setImages(RealmUri.getRealList(bgRealm, fileUriList));


                    task.setAudioSet(isAudioPresent);
                    task.setAudioUri(filePath);

                    task.setDateCreated(new Date());


                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {

                    Pop.show(CreateToDoActivity.this, "Cool!");
                    CreateToDoActivity.this.finish();

                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    Pop.show(CreateToDoActivity.this, "Something went wrong !\n"+error.getLocalizedMessage());


                }
            });




        }

    }

    private boolean isValid() {

        if(todoEditText.getText().length()<=0){
            Pop.show(CreateToDoActivity.this, "Come on dude! Add something in ToDo.");
            return false;
        }else if(setReminder==true && setDate==false){
            Pop.show(CreateToDoActivity.this, "You need to select a date!");
            return false;
        }else if(!setDate){
            Pop.show(CreateToDoActivity.this, "You need to select a date!");
            return false;
        }

        return true;

    }
}
