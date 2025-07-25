package com.example.wifidirectapplication;

import android.net.wifi.p2p.WifiP2pDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PeerAdapter extends RecyclerView.Adapter<PeerAdapter.PeerViewHolder> {
    private List<WifiP2pDevice> devices;

    public void setDevices(List<WifiP2pDevice> devices) {
        this.devices = devices != null ? devices : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PeerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_peer, parent, false);
        return new PeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeerViewHolder holder, int position) {
        WifiP2pDevice device = devices.get(position);
        holder.nameTextView.setText(device.deviceName);
        holder.statusTextView.setText(getDeviceStatus(device.status));
    }

    @Override
    public int getItemCount() {
        return devices == null ? 0 : devices.size();
    }

    static class PeerViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView statusTextView;

        public PeerViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.device_name);
            statusTextView = itemView.findViewById(R.id.device_status);
        }
    }

    private String getDeviceStatus(int deviceStatus) {
        switch (deviceStatus) {
            case WifiP2pDevice.CONNECTED:
                return "Connected";
            case WifiP2pDevice.INVITED:
                return "Invited";
            case WifiP2pDevice.FAILED:
                return "Failed";
            case WifiP2pDevice.AVAILABLE:
                return "Available";
            case WifiP2pDevice.UNAVAILABLE:
                return "Unavailable";
            default:
                return "Unknown";
        }
    }
}

