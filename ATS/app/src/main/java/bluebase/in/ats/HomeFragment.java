package bluebase.in.ats;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private HashMap<String, Integer> imageHashMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        height = (int) (height / 1.5);

        ImageView background = view.findViewById(R.id.background);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 200, height);
        background.setLayoutParams(layoutParams);

        imageHashMap = new HashMap<String, Integer>();
        imageHashMap.put("profileFragment", R.drawable.profile);
        imageHashMap.put("taskCreationFragment", R.drawable.task_creation);
        imageHashMap.put("reportFragment", R.drawable.report);
        imageHashMap.put("taskAllocationFragment", R.drawable.task_allocation);
        imageHashMap.put("timeSheetFragment", R.drawable.time_sheet);
        imageHashMap.put("timeSheetTimerFragment", R.drawable.time_sheet_timer);
        imageHashMap.put("dailyReportFragment", R.drawable.daily_report);
        imageHashMap.put("dailyAllocatedTasksFragment", R.drawable.daily_allocated_tasks);
        imageHashMap.put("dailyProjectReportFragment", R.drawable.daily_project_report);
        imageHashMap.put("projectwiseReportFragment", R.drawable.projectwise_report);
        imageHashMap.put("userwiseReportFragment", R.drawable.userwise_report);
        imageHashMap.put("customerMasterFragment", R.drawable.customer_master);
        imageHashMap.put("timeSheetApprovalFragment", R.drawable.timesheet_approval);
        imageHashMap.put("projectMasterFragment", R.drawable.project_master);



        return view;
    }

}
