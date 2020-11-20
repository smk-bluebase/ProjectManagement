package bluebase.in.project_management;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_ENABLE_WRITE_AHEAD_LOGGING;

public class TaskCreationFragment extends Fragment {
    Context context;
    ProgressDialog progressDialog;

    UpdateLocal updateLocal;

    AutoCompleteTextView customerTextView;
    AutoCompleteTextView projectTextView;
    TextView dateTextView;
    final String[] dateValue = new String[1];
    EditText module;
    EditText task;
    EditText description;
    EditText hours;

    String urlTaskMaster = CommonUtils.IP + "/ATS/atsandroid/postTaskMaster.php";

    JsonObject jsonObject;
    int customerId;
    int projectId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taskcreation, container, false);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        height = (int) (height / 1.5);

        ImageView background = view.findViewById(R.id.background);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 200, height);
        background.setLayoutParams(layoutParams);

        context = getContext();

        customerTextView = view.findViewById(R.id.customer);
        projectTextView = view.findViewById(R.id.project);

        ImageView reconnectButton = view.findViewById(R.id.reconnectButton);
        reconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerTextView.setText("");
                projectTextView.setText("");
                module.setText("");
                task.setText("");
                description.setText("");
                dateTextView.setText("Date");
                hours.setText("");
            }
        });

        dateTextView = view.findViewById(R.id.date);

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String month;
                                if(monthOfYear + 1 < 10){
                                    month = "0" + (monthOfYear + 1);
                                }else{
                                    month = String.valueOf(monthOfYear + 1);
                                }

                                String dateValueStr = dayOfMonth + "-" + month + "-" + year;
                                dateTextView.setText("Date : " + dateValueStr);
                                dateValue[0] = year + "-" + month + "-" + dayOfMonth;
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        module = view.findViewById(R.id.module);
        task = view.findViewById(R.id.task);
        description = view.findViewById(R.id.description);
        hours = view.findViewById(R.id.hours);

        Button addTask = view.findViewById(R.id.addTask);

        addTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!customerTextView.getText().toString().equals("")) {
                    if(!projectTextView.getText().toString().equals("")) {
                        if(!module.getText().toString().equals("")) {
                            if(!task.getText().toString().equals("")) {
                                if(!description.getText().toString().equals("")) {
                                    if(!dateValue.equals("")) {
                                        if(!hours.getText().toString().equals("")) {
                                            progressDialog = new ProgressDialog(context);
                                            progressDialog.setCancelable(false);
                                            progressDialog.setMessage("Loading...");
                                            progressDialog.show();

                                            jsonObject = new JsonObject();
                                            jsonObject.addProperty("customerId", customerId);
                                            jsonObject.addProperty("projectId", projectId);
                                            jsonObject.addProperty("module", module.getText().toString());
                                            jsonObject.addProperty("task", task.getText().toString());
                                            jsonObject.addProperty("description", description.getText().toString());
                                            jsonObject.addProperty("dueDate", dateValue[0]);
                                            jsonObject.addProperty("hours", hours.getText().toString());
                                            jsonObject.addProperty("status", 0);
                                            jsonObject.addProperty("createdBy", ((MainActivity) getActivity()).getUserId());

                                            PostTask postTask = new PostTask(context);
                                            postTask.checkServerAvailability(2);
                                        }else {
                                            Toast.makeText(context, "Enter hours", Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        Toast.makeText(context, "Select Date", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(context, "Enter Description", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(context, "Enter Task", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context, "Enter Module", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context, "Select Project", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Select Customer", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        updateLocal = new UpdateLocal(context);
        updateLocal.checkServerAvailability(2);
    }

    private class UpdateLocal extends UpdateLocalDatabase {

        public UpdateLocal(Context context) {
            super(context);
        }

        public void serverAvailability(boolean isServerAvailable){
            if(isServerAvailable){
                updateLocal.updateProjectAndProjectCustomerMaster();
            }else {
                Toast.makeText(context, "Connection to the server \nnot Available", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }

        public void onPostUpdate() {
            progressDialog.dismiss();

            final SQLiteDatabase myDatabase = context.openOrCreateDatabase("project_management.sqlite", MODE_ENABLE_WRITE_AHEAD_LOGGING, null);
            Cursor resultSet1 = myDatabase.rawQuery("SELECT name FROM project_customer_master", null);
            resultSet1.moveToFirst();

            int i = 0;
            String[] customerArray = new String[resultSet1.getCount()];

            if(resultSet1.getCount() > 0) {
                while(i < resultSet1.getCount()){
                    customerArray[i] = resultSet1.getString(resultSet1.getColumnIndex("name"));
                    resultSet1.moveToNext();
                    i++;
                }
            }

            resultSet1.close();

            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(context, R.layout.dropdown_menu_popup_item, customerArray);
            customerTextView.setAdapter(adapter1);
            customerTextView.setKeyListener(null);
            customerTextView.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // Do Nothing!
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // Do Nothing!
                }

                @Override
                public void afterTextChanged(Editable s) {
                    projectTextView.setText("");
                    Cursor resultSet2 = myDatabase.rawQuery(String.format("SELECT pm.name, pm.customer_id FROM project_master AS pm WHERE EXISTS (SELECT * FROM project_customer_master AS pcm WHERE pcm.id = pm.customer_id AND pcm.name = \'%s\')", customerTextView.getText().toString()), null);
                    resultSet2.moveToFirst();

                    int i = 0;
                    String[] projectArray = new String[resultSet2.getCount()];

                    if(resultSet2.getCount() > 0) {
                        customerId = resultSet2.getInt(resultSet2.getColumnIndex("customer_id"));

                        while(i < resultSet2.getCount()){
                            projectArray[i] = resultSet2.getString(resultSet2.getColumnIndex("name"));
                            resultSet2.moveToNext();
                            i++;
                        }
                    }

                    resultSet2.close();

                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(context, R.layout.dropdown_menu_popup_item, projectArray);
                    projectTextView.setAdapter(adapter2);
                    projectTextView.setKeyListener(null);
                    customerTextView.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            // Do Nothing!
                        }

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            // Do Nothing!
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            Cursor resultSet3 = myDatabase.rawQuery(String.format("SELECT id FROM project_master WHERE name = \'%s\'", projectTextView.getText().toString()), null);
                            resultSet3.moveToFirst();

                            if(resultSet3.getCount() > 0) {
                                projectId = resultSet3.getInt(resultSet3.getColumnIndex("id"));
                            }

                            resultSet3.close();
                        }
                    });
                }
            });

        }
    }

    private class PostTask extends PostRequest{
        public PostTask(Context context){
            super(context);
        }

        public void serverAvailability(boolean isServerAvailable){
            if(isServerAvailable){
                super.postRequest(urlTaskMaster, jsonObject);
            }else {
                Toast.makeText(context, "Connection to the server \nnot Available", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }

        public void onFinish(JSONArray jsonArray){
            progressDialog.dismiss();

            try{
                JSONObject jsonObject = (JSONObject) jsonArray.get(0);

                if(jsonObject.getBoolean("status")){
                    Toast.makeText(context, "Task added Successfully", Toast.LENGTH_SHORT).show();
                    customerTextView.setText("");
                    projectTextView.setText("");
                    module.setText("");
                    task.setText("");
                    description.setText("");
                    dateTextView.setText("Date");
                    hours.setText("");
                }else {
                    Toast.makeText(context, "Failed to add Task", Toast.LENGTH_SHORT).show();
                }

            }catch(JSONException e){
                e.printStackTrace();
            }
        }
    }

}
