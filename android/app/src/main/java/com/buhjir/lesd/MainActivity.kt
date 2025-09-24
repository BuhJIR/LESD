package com.buhjir.lesd

import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var deviceListView: ListView
    private lateinit var effectSpinner: Spinner
    private lateinit var connectBtn: Button
    private lateinit var disconnectBtn: Button
    private val bluetoothHelper = BluetoothHelper(this)
    private lateinit var deviceAdapter: DeviceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        deviceListView = findViewById(R.id.deviceListView)
        effectSpinner = findViewById(R.id.effectSpinner)
        connectBtn = findViewById(R.id.connectBtn)
        disconnectBtn = findViewById(R.id.disconnectBtn)

        deviceAdapter = DeviceAdapter(this, bluetoothHelper.getPairedDevices())
        deviceListView.adapter = deviceAdapter

        effectSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            Effects.list()
        )

        connectBtn.setOnClickListener {
            val pos = deviceListView.checkedItemPosition
            if (pos >= 0) {
                val device = deviceAdapter.getItem(pos)
                bluetoothHelper.connectToDevice(device)
            }
        }

        disconnectBtn.setOnClickListener {
            bluetoothHelper.disconnect()
        }

        effectSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
                Effects.apply(effectSpinner.selectedItem as String, bluetoothHelper)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}