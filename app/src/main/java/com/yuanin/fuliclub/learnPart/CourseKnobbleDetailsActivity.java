package com.yuanin.fuliclub.learnPart;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.adapter.adapter.DelegateAdapter;
import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.coursePart.KnobbleDetailsListAdapter;
import com.yuanin.fuliclub.coursePart.SelectPlaySpeedDialogFragment;
import com.yuanin.fuliclub.coursePart.bean.KnobbleDetailsInfoVo;
import com.yuanin.fuliclub.loginRegister.LoginActivity;
import com.yuanin.fuliclub.musicPlay.MusicPlayerManager;
import com.yuanin.fuliclub.musicPlay.MusicPlayerService;
import com.yuanin.fuliclub.musicPlay.OnMusicPlayerListener;
import com.yuanin.fuliclub.musicPlay.OrmUtil;
import com.yuanin.fuliclub.musicPlay.PlayListListener;
import com.yuanin.fuliclub.musicPlay.PlayListManager;
import com.yuanin.fuliclub.musicPlay.SharedPreferencesUtil;
import com.yuanin.fuliclub.musicPlay.TimeUtil;
import com.yuanin.fuliclub.util.AdapterPool;
import com.yuanin.fuliclub.util.ToastUtils;
import com.yuanin.fuliclub.view.ObservableWebView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseKnobbleDetailsActivity extends AbsLifecycleActivity<CourseViewModel> implements OnMusicPlayerListener, PlayListListener, SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.titleBar)
    EasyTitleBar titleBar;
    @BindView(R.id.llWriteNote)
    LinearLayout llWriteNote;
    @BindView(R.id.llDoHomeWork)
    LinearLayout llDoHomeWork;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.sb_progress)
    SeekBar sbProgress;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.iv_play_control)
    ImageView ivPlayControl;
    @BindView(R.id.tvKnobbleName)
    TextView tvKnobbleName;
    @BindView(R.id.tvCourseName)
    TextView tvCourseName;
    @BindView(R.id.tvPlaySpeed)
    TextView tvPlaySpeed;
    @BindView(R.id.courseIntro)
    RecyclerView courseIntro;


    private KnobbleDetailsInfoVo detailsInfoVo;

    protected SharedPreferencesUtil sp;
    protected OrmUtil orm;

    private MusicPlayerManager musicPlayerManager;
    private PlayListManager playListManager;
    private KnobbleDetailsInfoVo currentSong;

    private WeakReference<CourseKnobbleDetailsActivity> weakReference;


    @Override
    protected int getScreenMode() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_knobble_details;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        weakReference = new WeakReference<>(this);

        loadManager.showSuccess();
        titleBar.setBackImageRes(R.drawable.black);
        titleBar.getBackLayout().setOnClickListener(v -> finish());

        int courseKnobbleId = getIntent().getIntExtra("courseKnobbleId", 0);
        mViewModel.getCourseKnobbleDetailsLogin(String.valueOf(courseKnobbleId));

        sp = SharedPreferencesUtil.getInstance(getApplicationContext());
        orm = OrmUtil.getInstance(getApplicationContext());

        musicPlayerManager = MusicPlayerService.getMusicPlayerManager(getApplicationContext());
        playListManager = MusicPlayerService.getPlayListManager(getApplicationContext());
        playListManager.addPlayListListener(this);
        sbProgress.setOnSeekBarChangeListener(this);

    }

    public void setInitData(KnobbleDetailsInfoVo data) {
        sbProgress.setMax((int) data.getDuration());
        sbProgress.setProgress(sp.getLastSongProgress());
        tvStartTime.setText(TimeUtil.formatMSTime((int) sp.getLastSongProgress()));
        tvEndTime.setText(TimeUtil.formatMSTime((int) data.getDuration()));

        //rv.setAlbumUri(data.getBanner());
        tvKnobbleName.setText(data.getClassHourName());
        tvCourseName.setText(data.getCourseName());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void dataObserver() {
        super.dataObserver();

        registerSubscriber(CourseRepository.EVENT_KEY_KNOBBLE_DETAILS_LOGIN, ReturnResult.class).observe(this, returnResult -> {
            if (returnResult != null) {
                if (returnResult.isSuccess()) {
                    detailsInfoVo = (KnobbleDetailsInfoVo) returnResult.getData();

                    setKnobbleDetails(detailsInfoVo);

                    //TODO 设置数据
                   // detailsInfoVo.setMp3Url("https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/a1e7d4d2df3b47cf8b219e1c236d313d.mp3");
                    currentSong = this.playListManager.getPlayData();
                    if (currentSong != null) {
                        if (detailsInfoVo.getId().equals(currentSong.getId())) {
                            setInitData(currentSong);
                        } else {
                            playListManager.play(detailsInfoVo);
                        }
                    } else {
                        playListManager.play(detailsInfoVo);
                    }

                    //setInitData(currentSong);

                } else {
                    ToastUtils.showToast(returnResult.getMessage());
                }
            }
        });
    }

    private void setKnobbleDetails(KnobbleDetailsInfoVo detailsInfoVo) {
        KnobbleDetailsListAdapter adapter = new KnobbleDetailsListAdapter(this, detailsInfoVo.getChildDetailList());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        courseIntro.setAdapter(adapter);
        courseIntro.setLayoutManager(layoutManager);
    }

    @OnClick({R.id.llWriteNote, R.id.llDoHomeWork, R.id.iv_play_control, R.id.tvPlaySpeed})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llWriteNote:
                break;
            case R.id.llDoHomeWork:
                break;
            case R.id.iv_play_control:
                playOrPause();
                break;
            case R.id.tvPlaySpeed:
                SelectPlaySpeedDialogFragment.show(getSupportFragmentManager(), new SelectPlaySpeedDialogFragment.OnSelectPlaySpeedListener() {
                    @Override
                    public void selectPlaySpeed(String speed) {
                        tvPlaySpeed.setText(speed);
                        musicPlayerManager.setPlaySpeed(Float.valueOf(speed.substring(0,speed.length() - 1)));
                    }
                });
                break;
        }
    }

    private void playOrPause() {
        if (musicPlayerManager.isPlaying()) {
            pause();
        } else {
            play();
        }

    }

    private void play() {
        playListManager.resume();
    }

    private void pause() {
        playListManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        musicPlayerManager.addOnMusicPlayerListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        musicPlayerManager.removeOnMusicPlayerListener(this);
    }


    @Override
    public void onProgress(long progress, long total) {
        tvStartTime.setText(TimeUtil.formatMSTime((int) progress));
        sbProgress.setProgress((int) progress);
    }

    @Override
    public void onPaused(KnobbleDetailsInfoVo data) {
        ivPlayControl.setImageResource(R.drawable.selector_music_play);
    }

    @Override
    public void onPlaying(KnobbleDetailsInfoVo data) {
        ivPlayControl.setImageResource(R.drawable.selector_music_pause);
        currentSong = data;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer, KnobbleDetailsInfoVo data) {
        setInitData(data);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onError(MediaPlayer mp, int what, int extra) {

    }

    @Override
    public void onDataReady(KnobbleDetailsInfoVo song) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            musicPlayerManager.seekTo(progress);
            if (!musicPlayerManager.isPlaying()) {
                musicPlayerManager.resume();
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
