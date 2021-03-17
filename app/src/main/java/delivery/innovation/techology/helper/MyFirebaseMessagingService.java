package delivery.innovation.techology.helper;


import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import delivery.innovation.techology.activity.OrderDetailActivity;
import delivery.innovation.techology.model.WalletHistory;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived ( RemoteMessage remoteMessage ) {
        if (remoteMessage.getData ().size () > 0) {
            try {
                JSONObject json = new JSONObject (remoteMessage.getData ().toString ());
//                System.out.println ("=====n_response " + json.toString ());
                sendPushNotification (json);
            } catch (Exception e) {
                Log.e (TAG,"Exception: " + e.getMessage ());
            }
        }
    }

    private void sendPushNotification ( JSONObject json ) {
        try {

            JSONObject data = json.getJSONObject (Constant.DATA);

            String title = data.getString ("title");
            String message = data.getString ("message");
            String imageUrl = data.getString ("image");

            String type = data.getString ("type");
            String id = data.getString ("id");

            Intent intent = null;


            if (type.equals ("delivery_boys")) {
                intent = new Intent (getApplicationContext (),OrderDetailActivity.class);
                intent.putExtra ("order_id",id);
            } else {
                intent = new Intent (getApplicationContext (),WalletHistory.class);
            }

            MyNotificationManager mNotificationManager = new MyNotificationManager (getApplicationContext ());
            //Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            if (imageUrl.equals ("null") || imageUrl.equals ("")) {
                mNotificationManager.showSmallNotification (title,message,intent);
            } else {
                mNotificationManager.showBigNotification (title,message,imageUrl,intent);
            }


        } catch (JSONException e) {
            Log.e (TAG,"Json Exception: " + e.getMessage ());
        } catch (Exception e) {
            Log.e (TAG,"Exception: " + e.getMessage ());
        }
    }

    @Override
    public void onNewToken ( String s ) {
        super.onNewToken (s);
        AppController.getInstance ().setDeviceToken (s);
        //MainActivity.UpdateToken(s);
    }

}
