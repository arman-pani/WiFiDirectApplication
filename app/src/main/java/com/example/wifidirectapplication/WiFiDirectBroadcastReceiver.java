package com.example.wifidirectapplication;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.widget.Toast;

import androidx.core.content.ContextCompat;


public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {
    private final WifiP2pManager manager;
    private final Channel channel;
    private final MainActivity activity;

    public WiFiDirectBroadcastReceiver(
            WifiP2pManager manager,
            Channel channel,
            MainActivity activity
    ) {
        this.manager = manager;
        this.channel = channel;
        this.activity = activity;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)){
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                Toast.makeText(context, "WiFi P2P is Enabled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "WiFi P2P is Disabled", Toast.LENGTH_SHORT).show();
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                if (manager != null){
                    manager.requestPeers(channel, activity);
                }
            } else {
                Toast.makeText(context, "Location permission is required to get peer list", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
