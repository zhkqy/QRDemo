package com.qr.demo.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qr.demo.Label.DianBaoLabel;
import com.qr.demo.Label.DianBaoLabel2;
import com.qr.demo.Label.keYunRecordLabel;
import com.qr.demo.Label.keYunRecordLabel2;
import com.qr.demo.MainActivity;
import com.qr.demo.MyApplication;
import com.qr.demo.R;
import com.qr.demo.model.PrintModel;
import com.qr.print.PrintPP_CPCL;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sun on 2018/1/3.
 */

public class PrintActivity extends BaseActivity {

    private boolean isSending = false;
    private int interval;
    private PrintModel printModel;

//    蓝牙

    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private String address = "";
    private String name = "";
    private BluetoothAdapter mBluetoothAdapter = null;
    private MyApplication myApplication;
    private PrintPP_CPCL printPP_cpcl;

    @BindView(R.id.title_right_text)
    TextView title_right_text;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_print);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initUI() {
        //obtain the local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //If the Bluetooth adapter is not supported,programmer is over
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        myApplication = (MyApplication) getApplication();
        printPP_cpcl = myApplication.getPrintPP_cpcl();
        if (myApplication.isConnected()) {
            title_right_text.setText(myApplication.getConnetName());
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        printModel = (PrintModel) getIntent().getSerializableExtra("data");
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }


    @OnClick(R.id.connentprint)
    public void connentprintOnclicked(View v) {
        Intent serverIntent = new Intent(this, DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
    }

    @OnClick(R.id.printOne)
    public void printOneOnclicked(View v) {
        final PrintPP_CPCL printPP_cpcl = ((MyApplication) getApplication()).getPrintPP_cpcl();
        if (!isSending) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    isSending = true;
                    if (((MyApplication) getApplication()).isConnected()) {

                        if (printModel != null) {
                            if (printModel.recordThing.contains("电报")) {
                                DianBaoLabel pl = new DianBaoLabel(printPP_cpcl);
                                pl.Lable(printModel.saveRecordThing, printModel.saveZhusongDianBao,
                                        printModel.saveChaosongDianBao, printModel.savedescription);
                            } else {
                                keYunRecordLabel pl = new keYunRecordLabel(printPP_cpcl);
                                pl.Lable(printModel.saveRecordThing, printModel.saveConnectStation,
                                        printModel.savedescription);
                            }
                        }
                    }
                    try {
                        interval = 0;
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isSending = false;
                }
            }).start();
        }
    }

    @OnClick(R.id.printTwo)
    public void printTwoOnclicked(View v) {
        final PrintPP_CPCL printPP_cpcl = ((MyApplication) getApplication()).getPrintPP_cpcl();
        if (!isSending) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    isSending = true;
                    if (((MyApplication) getApplication()).isConnected()) {

                        if (printModel != null) {
                            if (printModel.recordThing.contains("电报")) {
                                DianBaoLabel2 pl = new DianBaoLabel2(printPP_cpcl);
                                pl.Lable(printModel.saveRecordThing, printModel.saveZhusongDianBao,
                                        printModel.saveChaosongDianBao, printModel.savedescription);
                            } else {
                                keYunRecordLabel2 pl = new keYunRecordLabel2(printPP_cpcl);
                                pl.Lable(printModel.saveRecordThing, printModel.saveConnectStation,
                                        printModel.savedescription);
                            }
                        }
                    }
                    try {
                        interval = 0;
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isSending = false;
                }
            }).start();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {

        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (myApplication.isConnected() & (printPP_cpcl != null)) {
                                printPP_cpcl.disconnect();
                                myApplication.setConnected(false);
                            }
                            String sdata = data.getExtras()
                                    .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                            address = sdata.substring(sdata.length() - 17);
                            name = sdata.substring(0, (sdata.length() - 17));
                            if (!myApplication.isConnected()) {
                                if (printPP_cpcl.connect(name, address)) {
                                    myApplication.setConnected(true);

                                    PrintActivity.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (title_right_text != null) {
                                                title_right_text.setText(name);
                                                myApplication.setConnetName(name);
                                            }
                                        }
                                    });

                                } else {
                                    myApplication.setConnected(false);
                                }
                            }
                        }
                    }).start();

                }
                break;
            case REQUEST_ENABLE_BT:

        }
    }


}
