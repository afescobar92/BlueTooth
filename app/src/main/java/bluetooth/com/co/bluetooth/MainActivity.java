package bluetooth.com.co.bluetooth;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button btnOn,btnOff,btnSearch;
    private ListView lstSearch;

    private BluetoothAdapter bluetoothAdapter;
    private ArrayAdapter<String> arrayAdapter;

    private Handler handler;
    private BluetoothSocket bluetoothSocket;

    private final static int REQUEST_ENABLE_BT = 1;
    private final static int MESSAGE_READ = 2;
    private final static int CONNECTION_STATUS = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAdapters();
        requestPermissions();
        initHandler();
        validateBlueToothDevice();
    }

    private void initView(){
        btnOn = findViewById(R.id.btnOn);
        btnOff = findViewById(R.id.btnOff);;
        btnSearch = findViewById(R.id.btnSearch);
        lstSearch = findViewById(R.id.lstSearch);
    }

    private void initAdapters(){
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        lstSearch.setAdapter(arrayAdapter);

    }

    @SuppressLint("HandlerLeak")
    private void initHandler() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == MESSAGE_READ){
                    String messageRead = "";
                    try {
                        messageRead = new String(msg.obj.toString().getBytes(),"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        Log.e(TAG,e.getMessage());
                    }
                    Log.i(TAG,messageRead);
                }

                if(msg.what == CONNECTION_STATUS){
                    if(msg.arg1 == 1){
                        Log.i(TAG,"Connect device: "+msg.obj);
                    }else{
                        Log.i(TAG,"Failure connect device: ");
                    }
                }

            }
        };

    }

    private void requestPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);
        }
    }

    private void validateBlueToothDevice(){
        if(bluetoothAdapter == null){
            Toast.makeText(this, "Bluetooth device not supported", Toast.LENGTH_LONG).show();
        }else{
            btnOn.setEnabled(true);
            btnOff.setEnabled(true);
            btnSearch.setEnabled(true);
        }
    }

    public void on(View view) {
        if(!bluetoothAdapter.isEnabled()){
            Intent enabledBlueToothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enabledBlueToothIntent,REQUEST_ENABLE_BT);
            Toast.makeText(this, "BlueTooth activated", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "BlueTooth already activate", Toast.LENGTH_LONG).show();
        }
    }

    public void off(View view) {

    }

    public void search(View view) {

    }
}
