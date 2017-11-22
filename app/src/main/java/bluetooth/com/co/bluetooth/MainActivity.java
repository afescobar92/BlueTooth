package bluetooth.com.co.bluetooth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Button btnOn,btnOff,btnSearch;
    private ListView lstSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        btnOn = findViewById(R.id.btnOn);
        btnOff = findViewById(R.id.btnOff);;
        btnSearch = findViewById(R.id.btnSearch);
        lstSearch = findViewById(R.id.lstSearch);
    }
}
