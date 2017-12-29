package com.qr.demo;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.qr.demo.Label.PrintLabel;
import com.qr.demo.activity.BaseActivity;
import com.qr.demo.activity.DeviceListActivity;
import com.qr.demo.activity.LabelListActivity;
import com.qr.demo.db.DBManager;
import com.qr.print.*;

import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    private PrintPP_CPCL printPP_cpcl;

    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    private String address = "";
    private String name = "";
    private BluetoothAdapter mBluetoothAdapter = null;
    private int interval;

    private MyApplication myApplication;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);

        //obtain the local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //If the Bluetooth adapter is not supported,programmer is over
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        myApplication = (MyApplication) getApplication();

        printPP_cpcl = new PrintPP_CPCL();
        myApplication.setPrintPP_cpcl(printPP_cpcl);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
    }

    @OnClick(R.id.connentprint)
    public void connentprintOnclicked(View v) {
        Intent serverIntent = new Intent(MainActivity.this, DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
    }

    @OnClick(R.id.passengerrecord)
    public void passengerrecordOnClicked(View v) {
        startActivity(new Intent(this, LabelListActivity.class).putExtra("type", "passengerrecord"));
    }

    @OnClick(R.id.traintelegram)
    public void traintelegramOnClicked(View v) {
        startActivity(new Intent(this, LabelListActivity.class).putExtra("type", "traintelegram"));
    }


    @Override
    public void onStart() {
        super.onStart();
        // If BT is not on, request that it be enabled
        // setupChat() will then be called during onActivityRe//sultsetupChat
//        if (!mBluetoothAdapter.isEnabled()) {
//            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
//        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {
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
//                            mTitle.setText(R.string.title_connected_to);
//                            mTitle.append(name);

                        } else {
                            myApplication.setConnected(false);
                        }
                    }

                }
                break;
            case REQUEST_ENABLE_BT:

        }
    }


    private void ensureDiscoverable() {
        if (mBluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }
}


