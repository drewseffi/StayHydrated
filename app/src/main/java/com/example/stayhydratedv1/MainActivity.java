package com.example.stayhydratedv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelUuid;

import java.util.Collections;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
    BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
    BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();

    ScanFilter scanFilter = new ScanFilter.Builder()
            .setServiceUuid(new ParcelUuid(UUID.fromString("YOUR_SERVICE_UUID_HERE")))
            .build();
    ScanSettings scanSettings = new ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .build();
    bluetoothLeScanner.startScan(Collections.singletonList(scanFilter), scanSettings, new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            BluetoothDevice device = result.getDevice();
            if (device.getName() != null && device.getName().equals("YOUR_DEVICE_NAME_HERE")) {
                // Connect to the device using BluetoothGatt
            }
        }
    });



    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    bluetoothAdapter.getBluetoothLeScanner().startScan(new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            // A BLE device has been found, you can connect to it and get data
        }
    });


}