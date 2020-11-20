package bluebase.in.project_management;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DayWiseReportFragment extends Fragment {
    Context context;
    ProgressDialog progressDialog;
    TextView fromDate;
    TextView toDate;
    AutoCompleteTextView employeeTextView;
    JsonObject jsonObject;

    String urlEmployeeList = CommonUtils.IP + "/ATS/atsandroid/getEmployeeList.php";

    String[] fromDateValue = new String[1];
    String[] toDateValue = new String[1];
    String selectedUserId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daywise_report, container, false);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        height = (int) (height / 1.5);

        ImageView background = view.findViewById(R.id.background);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 200, height);
        background.setLayoutParams(layoutParams);

        context = getContext();

        fromDate = view.findViewById(R.id.fromDate);

        fromDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
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
                                fromDate.setText("From Date " + dateValueStr);
                                fromDateValue[0] = year + "-" + month + "-" + dayOfMonth;
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        toDate = view.findViewById(R.id.toDate);

        toDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
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
                                toDate.setText("From Date " + dateValueStr);
                                toDateValue[0] = year + "-" + month + "-" + dayOfMonth;
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        employeeTextView = view.findViewById(R.id.employee);

        Button report = view.findViewById(R.id.report);

        report.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                jsonObject = new JsonObject();
                jsonObject.addProperty("fromDate", fromDateValue[0]);
                jsonObject.addProperty("toDate", toDateValue[0]);
                jsonObject.addProperty("userId", selectedUserId);


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

        PostEmployeeList postEmployeeList = new PostEmployeeList(context);
        postEmployeeList.checkServerAvailability(2);
    }

    private class PostEmployeeList extends PostRequest{
        public PostEmployeeList(Context context){
            super(context);
        }

        public void serverAvailability(boolean isServerAvailable){
            if(isServerAvailable){
                super.postRequest(urlEmployeeList, new JsonObject());
            }else {
                Toast.makeText(context, "Connection to the server \nnot Available", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }

        public void onFinish(JSONArray jsonArray){
            progressDialog.dismiss();
            final String[] employeeArray = new String[jsonArray.length()];
            final Map<String, String> employee = new HashMap<>();

            try{
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                    employeeArray[i] = jsonObject.getString("userName");
                    employee.put(jsonObject.getString("userName"), jsonObject.getString("userId"));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.dropdown_menu_popup_item, employeeArray);
                employeeTextView.setAdapter(adapter);
                employeeTextView.setKeyListener(null);
                employeeTextView.addTextChangedListener(new TextWatcher() {

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
                       selectedUserId = employee.get(employeeTextView.getText().toString());
                    }
                });

            }catch(JSONException e){
                e.printStackTrace();
            }
        }
    }

}
