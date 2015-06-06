package dftinc.appengine.smbcard;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.net.MalformedURLException;

import jcifs.Config;
import jcifs.smb.*;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.text_view1);


        //Custom Code-----------------------------------------------------------------------------//

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String server = "smb://172.16.11.185/Storage/";
        String username="joseph";
        String password="evidence spite";
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", username, password);

        SmbFile sFile = null;
        try {
            sFile = new SmbFile(server, auth);
            String[] files = sFile.list();
            for( int i = 0; i < files.length; i++ ) {
                tv.append( " " + files[i]+"\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SmbException e) {
            e.printStackTrace();
        }

        try {
            SmbFile[] files = sFile.listFiles();
        } catch (SmbException e) {
            e.printStackTrace();
        }

        //----------------------------------------------------------------------------------------//

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
