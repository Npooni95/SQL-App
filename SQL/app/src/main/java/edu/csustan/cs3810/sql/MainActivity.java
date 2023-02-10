package edu.csustan.cs3810.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Container container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        container = new Container (this);
        setContentView(R.layout.activity_main);
    }

    public void runQuery (View v) {
        // run query
        EditText inputQuery = findViewById(R.id.inputQuery);
        String query = inputQuery.getText().toString();
        String result = container.runQuery(query);

        // show result to message label
        TextView lblMsg = findViewById(R.id.lblMsg);
        lblMsg.setText(result);
    }
}