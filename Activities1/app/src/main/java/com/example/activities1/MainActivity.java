package com.example.activities1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* Class name for Log tag */
    private static final String TAG_ACTIVITY = MainActivity.class.getSimpleName();
    /* Unique tag required for the intent extra */
    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.MESSAGE";
    /* Unique tag for the intent reply */
    public static final int TEXT_REQUEST = 1;

    /* EditText view for the message */
    private EditText mMessage = null;
    /* TextView for the reply header */
    private TextView mReplyHead = null;
    /* TextView for the reply body */
    private TextView mReply = null;

    /**
     * Initialize the activity.
     * @param savedInstanceState The current state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* initialize all the view variables */
        mMessage = (EditText) findViewById(R.id.editText_main);
        mReplyHead = (TextView) findViewById(R.id.text_header_reply);
        mReply = (TextView) findViewById(R.id.text_message_reply);
    }

    /**
     * Handle the onClick for the "Send" button.  Gets the value
     * of the main EditText, creates an intent, and launches the
     * second activity with that intent.
     *
     * The return intent from the second activity is onActivityResult().
     * @param view The view (Button) that was clicked.
     */
    public void launchSecondActivity(View view) {
        Log.d(TAG_ACTIVITY, "Button clicked!");

        Intent intent = new Intent(this, SecondActivity.class);
        String message = "";
        if (mMessage != null) {
            message = mMessage.getText().toString();
        }

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    /**
     * Handle the data in the return intent from SecondActivity.
     *
     * @param requestCode Code for the SecondActivity request.
     * @param resultCode Code that comes back from SecondActivity.
     * @param data Intent data sent back from SecondActivity.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);

                if (mReplyHead != null) {
                    mReplyHead.setVisibility(View.VISIBLE);
                }

                if (mReply != null) {
                    mReply.setText(reply);
                    mReply.setVisibility(View.VISIBLE);
                }

            }
        }
    }
}
