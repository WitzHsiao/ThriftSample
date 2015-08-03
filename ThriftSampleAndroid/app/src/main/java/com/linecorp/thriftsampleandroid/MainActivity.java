package com.linecorp.thriftsampleandroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.apache.thrift.TException;
import org.apache.thrift.async.TAsyncClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        EchoSrv.AsyncClient client = new EchoSrv.AsyncClient()

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                TSocket transport = new TSocket("10.0.2.2", 9090); // 10.0.2.2 is the ip which emulator to localhost by
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                EchoSrv.Client client = new EchoSrv.Client(protocol);
                try {
                    transport.open();
                    Log.d("WITZ", client.echo("hello"));
                    Log.d("WITZ", client.echo2times("hello"));
                } catch (TTransportException e) {
                    e.printStackTrace();
                } catch (TException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

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
