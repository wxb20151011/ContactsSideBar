package sidebar.eastaeon.com.contactssidebar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements ContactsSideBar.OnTouchingLetterChangedListener {

    private ContactsSideBar mSideBar;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSideBar = (ContactsSideBar)findViewById(R.id.sidebar);
        mSideBar.setOnTouchingLetterChangedListener(this);
        tv = (TextView) findViewById(R.id.tv_select);
    }

    @Override
    public void onTouchingLetterChanged(int s) {
        String text = mSideBar.letters[s];
        tv.setText(text);

    }
}
