package com.yuanin.fuliclub.learnPart;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import com.adapter.adapter.DelegateAdapter;
import com.mvvm.base.AbsLifecycleActivity;
import com.next.easytitlebar.view.EasyTitleBar;
import com.yuanin.fuliclub.R;
import com.yuanin.fuliclub.base.ReturnResult;
import com.yuanin.fuliclub.config.ParamsKeys;
import com.yuanin.fuliclub.config.ParamsValues;
import com.yuanin.fuliclub.config.StaticMembers;
import com.yuanin.fuliclub.coursePart.KnobbleDetailsListAdapter;
import com.yuanin.fuliclub.coursePart.SelectPlaySpeedDialogFragment;
import com.yuanin.fuliclub.coursePart.bean.KnobbleDetailsInfoVo;
import com.yuanin.fuliclub.event.LoginOutEvent;
import com.yuanin.fuliclub.event.WechatPayUnusualeEvent;
import com.yuanin.fuliclub.event.WorkCommitEvent;
import com.yuanin.fuliclub.homePart.WebViewActivity;
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
import com.yuanin.fuliclub.util.PopupWindowUtils;
import com.yuanin.fuliclub.util.ToastUtils;
import com.yuanin.fuliclub.view.ObservableWebView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

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
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.tvKnobbleName)
    TextView tvKnobbleName;
    @BindView(R.id.tvCourseName)
    TextView tvCourseName;
    @BindView(R.id.tvPlaySpeed)
    TextView tvPlaySpeed;
    @BindView(R.id.courseIntro)
    RecyclerView courseIntro;
    @BindView(R.id.ll_Play_control)
    LinearLayout ll_Play_control;


    private KnobbleDetailsInfoVo detailsInfoVo;

    protected SharedPreferencesUtil sp;
    protected OrmUtil orm;

    private MusicPlayerManager musicPlayerManager;
    private PlayListManager playListManager;
    private KnobbleDetailsInfoVo currentSong;

    private WeakReference<CourseKnobbleDetailsActivity> weakReference;
    private boolean isBuy;


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
        isBuy = getIntent().getBooleanExtra(ParamsValues.COURSE_IS_BUYED, false);
        mViewModel.getCourseKnobbleDetailsLogin(String.valueOf(courseKnobbleId));

        sp = SharedPreferencesUtil.getInstance(getApplicationContext());
        orm = OrmUtil.getInstance(getApplicationContext());

        musicPlayerManager = MusicPlayerService.getMusicPlayerManager(getApplicationContext());
        playListManager = MusicPlayerService.getPlayListManager(getApplicationContext());
        playListManager.addPlayListListener(this);
        sbProgress.setOnSeekBarChangeListener(this);

        EventBus.getDefault().register(this);

//        currentSong = this.playListManager.getPlayData();
//        if (currentSong != null) {
//            setInitData(currentSong);
//        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void workCommit(WorkCommitEvent workCommitEvent){
        detailsInfoVo.setIsWork(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
                    detailsInfoVo.setBuyed(isBuy);
                    detailsInfoVo.setUserId(StaticMembers.USER_ID);

                    setKnobbleDetails(detailsInfoVo);

                    //TODO 设置数据
                   // detailsInfoVo.setMp3Url("https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/a1e7d4d2df3b47cf8b219e1c236d313d.mp3");
                    currentSong = this.playListManager.getPlayData();
                    if (currentSong != null) {
                        if (detailsInfoVo.getId().equals(currentSong.getId() )) {
                            setInitData(currentSong);
                        } else {
                            playListManager.play(detailsInfoVo);
                            //playListManager.pause();
                        }
                    } else {
                        playListManager.play(detailsInfoVo);
                        //playListManager.pause();
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

    @OnClick({R.id.llWriteNote, R.id.llDoHomeWork, R.id.iv_play_control, R.id.tvPlaySpeed, R.id.ivClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llWriteNote:
                if (isBuy) {
                    Intent intent = new Intent(this, WebViewActivity.class);
                    intent.putExtra(ParamsKeys.TYPE, ParamsValues.NOTE);
                    intent.putExtra(ParamsKeys.KNOBBLE_MLID, detailsInfoVo.getId());
                    startActivity(intent);
                } else {
                    ToastUtils.showToast("请先购买该课程。");
                }

                break;
            case R.id.llDoHomeWork:
                if (isBuy) {
                    Intent intent2 = new Intent(this, WebViewActivity.class);
                    intent2.putExtra(ParamsKeys.TYPE, ParamsValues.WORK);
                    intent2.putExtra(ParamsKeys.IS_WORK, String.valueOf(detailsInfoVo.getIsWork()));
                    intent2.putExtra(ParamsKeys.KNOBBLE_MLID, detailsInfoVo.getId());
                    startActivity(intent2);
                }else {
                    ToastUtils.showToast("请先购买该课程。");
                }

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
            case R.id.ivClose:
                ll_Play_control.setVisibility(View.GONE);
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
        tvPlaySpeed.setVisibility(View.GONE);
        ivClose.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPlaying(KnobbleDetailsInfoVo data) {
        ivPlayControl.setImageResource(R.drawable.selector_music_pause);
        tvPlaySpeed.setVisibility(View.VISIBLE);
        ivClose.setVisibility(View.GONE);
        tvPlaySpeed.setText(String.valueOf(musicPlayerManager.getPlaySpeed()) + "倍");
        currentSong = data;
    }

    @Override
    public void onPrepared(IjkMediaPlayer mediaPlayer, KnobbleDetailsInfoVo data) {
        setInitData(data);
        playOrPause();
    }

    @Override
    public void onCompletion(IjkMediaPlayer mediaPlayer) {

    }

    @Override
    public void onError(IjkMediaPlayer mp, int what, int extra) {

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
