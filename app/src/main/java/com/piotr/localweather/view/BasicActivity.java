package com.piotr.localweather.view;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.piotr.localweather.R;

/**
 * @author piotr on 08.08.16.
 */
public class BasicActivity extends AppCompatActivity {
    private static final String TAG = BasicActivity.class.getSimpleName();

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
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_map:
                openPreferredLocationInMap();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap() {
        showErrorToast();
        // FIXME: 13.03.17 Reimplement this feature
    }

    private void showErrorToast() {
        Toast.makeText(this, R.string.error_function_not_available, Toast.LENGTH_SHORT).show();
    }
}