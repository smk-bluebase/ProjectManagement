package bluebase.in.project_management;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import androidx.appcompat.app.AlertDialog;

public abstract class UpdateLocalDatabase {

    private String urlProjectCustomerMaster = CommonUtils.IP + "/ATS/atsandroid/getProjectCustomerMaster.php";
    private String urlProjectMaster = CommonUtils.IP + "/ATS/atsandroid/getProjectMaster.php";

    Context context;
    DataBaseHelper myDatabase;
    Boolean[] progress1 = new Boolean[2];

    int progressStatus = 0;
    private Handler handler = new Handler();

    public UpdateLocalDatabase(Context context){
        this.context = context;
        myDatabase = new DataBaseHelper(context);
    }

    public void checkServerAvailability(int time) {
        AsyncCheckAvailability asyncCheckAvailability = new AsyncCheckAvailability();
        asyncCheckAvailability.execute(String.valueOf(time));
    }

    public void updateProjectAndProjectCustomerMaster(){
        Arrays.fill(progress1, false);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("Project Management");
        alertDialogBuilder.setMessage("Updating Local Database...");

        LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.progress_dialogs, null);
        alertDialogBuilder.setView(dialogView);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        final ProgressBar progressBar = dialogView.findViewById(R.id.progressBar);
        Drawable progressDrawable = progressBar.getProgressDrawable().mutate();
        progressDrawable.setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
        progressBar.setProgressDrawable(progressDrawable);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                boolean isProgress = true;
                boolean isUpdateSuccessful = true;
                progressBar.setProgress(progressStatus);
                while(i < 2){
                    try {
                        if (isUpdateSuccessful){
                            switch (i) {
                                case 0:
                                   PostProjectCustomerMaster postProjectCustomerMaster = new PostProjectCustomerMaster(context);
                                   postProjectCustomerMaster.checkServerAvailability(2);
                                    break;
                                case 1:
                                    PostProjectMaster postProjectMaster = new PostProjectMaster(context);
                                    postProjectMaster.checkServerAvailability(2);
                                    break;
                                default:
                                    break;
                            }
                            isUpdateSuccessful = false;
                        }

                        if (progress1[i]) {
                            if(isProgress){
                                progressStatus += 50;
                                isProgress = false;
                            }else{
                                progressStatus += 50;
                                isProgress = true;
                            }
                            progressBar.setProgress(progressStatus);
                            isUpdateSuccessful = true;
                            i++;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                alertDialog.dismiss();
                handler.post(new Runnable() {
                    public void run() {
                        onPostUpdate();
                    }
                });
            }
        }).start();
    }

    private class PostProjectCustomerMaster extends PostRequest{
        public PostProjectCustomerMaster(Context context){
            super(context);
        }

        public void serverAvailability(boolean isServerAvailable){
            if(isServerAvailable){
                myDatabase.deleteProjectCustomerMaster();
                super.postRequest(urlProjectCustomerMaster, new JsonObject());
            }else{
                Toast.makeText(context, "Connection to the server \nnot Available", Toast.LENGTH_SHORT).show();
            }
        }

        public void onFinish(JSONArray jsonArray){
            try{
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                    int id;
                    String name;
                    String address;
                    String inCharge;
                    String gstNumber;
                    int contactNumber;
                    int status;
                    int createdBy;
                    String createdOn;

                    id = jsonObject.getInt("id");
                    name = jsonObject.getString("name");
                    address = jsonObject.getString("address");
                    inCharge = jsonObject.getString("inCharge");
                    gstNumber = jsonObject.getString("gstNumber");
                    contactNumber = jsonObject.getInt("contactNumber");
                    status = jsonObject.getInt("status");
                    createdBy = jsonObject.getInt("createdBy");
//                    createdOn = jsonObject.getString("createdOn");
                    createdOn = "";

                    myDatabase.insertProjectCustomerMaster(id, name, address, inCharge, gstNumber, contactNumber, status, createdBy, createdOn);
                }

            }catch(JSONException e){
                e.printStackTrace();
            }

            progress1[0] = true;
        }
    }

    private class PostProjectMaster extends PostRequest{
        public PostProjectMaster(Context context){
            super(context);
        }

        public void serverAvailability(boolean isServerAvailable){
            if(isServerAvailable){
                super.postRequest(urlProjectMaster, new JsonObject());
            }else {
                Toast.makeText(context, "Connection to the server \nnot Available", Toast.LENGTH_SHORT).show();
            }
        }

        public void onFinish(JSONArray jsonArray){
            try{
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                    int id;
                    String name;
                    String customerId;
                    String description;
                    String dueDate;
                    int duration;
                    int cost;
                    int status;
                    int poNumber;
                    String poDetail;
                    int createdBy;
                    String createdOn;

                    id = jsonObject.getInt("id");
                    name = jsonObject.getString("name");
                    customerId = jsonObject.getString("customerId");
                    description = jsonObject.getString("description");
                    dueDate = jsonObject.getString("dueDate");
                    duration = jsonObject.getInt("duration");
                    cost = jsonObject.getInt("cost");
                    status = jsonObject.getInt("status");
                    poNumber = jsonObject.getInt("poNumber");
                    poDetail = jsonObject.getString("poDetail");
                    createdBy = jsonObject.getInt("createdBy");
                    createdOn = jsonObject.getString("createdOn");
                    createdOn = "";

                    myDatabase.insertProjectMaster(id, name, customerId, description, dueDate, duration, cost, status, poNumber, poDetail, createdBy, createdOn);
                }

            }catch(JSONException e){
                e.printStackTrace();
            }

            progress1[1] = true;
        }
    }

    private class AsyncCheckAvailability extends AsyncTask<String, Boolean, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL(CommonUtils.IP);
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    int time = Integer.parseInt(strings[0]);
                    urlc.setConnectTimeout(time * 1000);
                    urlc.connect();
                    if (urlc.getResponseCode() == 200) {
                        Log.wtf("Connection", "Success !");
                        return true;
                    } else {
                        return false;
                    }
                } catch (MalformedURLException e1) {
                    return false;
                } catch (IOException e) {
                    return false;
                }
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean isServerAvailable) {
            serverAvailability(isServerAvailable);
        }

    }

    public abstract void serverAvailability(boolean isServerAvailable);

    public abstract void onPostUpdate();

    protected void finalize(){
        myDatabase.close();
    }

}