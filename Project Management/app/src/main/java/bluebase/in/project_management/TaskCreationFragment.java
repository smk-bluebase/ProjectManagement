package bluebase.in.project_management;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_ENABLE_WRITE_AHEAD_LOGGING;

public class TaskCreationFragment extends Fragment {
    Context context;
    ProgressDialog progressDialog;

    UpdateLocal updateLocal;

    AutoCompleteTextView customerTextView;

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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
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



            SQLiteDatabase myDatabase = context.openOrCreateDatabase("project_management.sqlite", MODE_ENABLE_WRITE_AHEAD_LOGGING, null);
            Cursor resultSet1 = myDatabase.rawQuery("SELECT name FROM project_customer_master", null);
            resultSet1.moveToFirst();

            int i = 0;
            String[] customer = new String[resultSet1.getCount()];

            if(resultSet1.getCount() > 0) {
                while(i < resultSet1.getCount()){
                    customer[i] = resultSet1.getString(resultSet1.getColumnIndex("name"));
                    resultSet1.moveToNext();
                    i++;
                }
            }

            resultSet1.close();

            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.dropdown_menu_popup_item, customer);
            customerTextView.setAdapter(adapter);
            customerTextView.setKeyListener(null);

        }
    }

}
