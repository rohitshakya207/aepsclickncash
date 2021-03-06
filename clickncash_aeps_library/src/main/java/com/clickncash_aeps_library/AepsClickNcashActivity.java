package com.clickncash_aeps_library;

import static com.aeps.aepslib.utils.Utils.createMultipleTransactionID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.aeps.aepslib.AepsActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class AepsClickNcashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeps_click_ncash);
        newAeps();
    }
    void newAeps() {
        Intent i = new Intent(AepsClickNcashActivity.this, AepsActivity.class);
        i.putExtra("agent_id", "RTP1");
        i.putExtra("developer_id", "RAINET TECHNOLOGY PRIVATE LIMITED300230");
        i.putExtra("password", "cbzjndvx7i");
//        i.putExtra("agent_id", ag_id);
//        i.putExtra("developer_id", developer_id);
//        i.putExtra("password", pass);
        i.putExtra("primary_color", R.color.indigo_A700);
        i.putExtra("accent_color", R.color.teal_200);
        i.putExtra("primary_dark_color", R.color.indigo_A700);
        i.putExtra("clientTransactionId", createMultipleTransactionID());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("bankVendorType", "ICICI");
        startActivityForResult(i, 300);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String message = "", status = "";
        if (data != null) {
            message = data.getStringExtra("message");
            status = data.getStringExtra("statusCode");
        }
        if (requestCode == 300 && resultCode == RESULT_OK) {
            try {

                JSONObject jsonTransactionJsonObject = new JSONObject(data.getStringExtra("data"));
                Log.d("Bankit AePs--", "Data-----" + new Gson().toJson(jsonTransactionJsonObject));
                //  showAlertDialog(message + jsonTransactionJsonObject.optString("bankName") + " " + jsonTransactionJsonObject.optString("bankrefrenceNo") + " " + jsonTransactionJsonObject.optString("service") + " " + jsonTransactionJsonObject.optString("stanNo") + " " + jsonTransactionJsonObject.optString("transactionAmount") + " " + jsonTransactionJsonObject.optString("transactionId") + " " + jsonTransactionJsonObject.optString("transactionNO") + " " + jsonTransactionJsonObject.optString("uidNo"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (requestCode == 400 && resultCode == RESULT_OK) {
            //  showAlertDialog(message + " " + status);
        } else {
            if (resultCode == RESULT_CANCELED) {
                if (!message.equalsIgnoreCase("")) {
                    try {
                        //showAlertDialog(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}