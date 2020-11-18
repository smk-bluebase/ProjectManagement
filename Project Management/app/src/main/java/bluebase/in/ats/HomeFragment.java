package bluebase.in.ats;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class HomeFragment extends Fragment {
    Context context;
    private HashMap<String, Integer> nameAndImageMap;
    private HashMap<String, HashMap<String, Integer>> fragmentMap;
    Deque<String> recentActivityQueue;
    private String[] recentActivity;

    HashMap<String, Integer> temp;
    Map.Entry<String, Integer> entry;

    private String[] displayName = {"Profile",
                                    "Change\nPassword",
                                    "Task\nCreation",
                                    "Report",
                                    "Task\nAllocation",
                                    "Time\nSheet",
                                    "Time\nSheet\nTimer",
                                    "Daily\nReport",
                                    "Daily\nAllocated\nTasks",
                                    "Daily\nProject\nReport",
                                    "Project\nWise\nReport",
                                    "User\nWise\nReport",
                                    "Customer\nMaster",
                                    "Time\nSheet\nApproval",
                                    "Project\nMaster"};

    private Integer[] imageId = { R.drawable.profile,
                                    R.drawable.change_password,
                                    R.drawable.task_creation,
                                    R.drawable.report,
                                    R.drawable.task_allocation,
                                    R.drawable.time_sheet,
                                    R.drawable.time_sheet_timer,
                                    R.drawable.daily_report,
                                    R.drawable.daily_allocated_tasks,
                                    R.drawable.daily_project_report,
                                    R.drawable.projectwise_report,
                                    R.drawable.userwise_report,
                                    R.drawable.customer_master,
                                    R.drawable.timesheet_approval,
                                    R.drawable.project_master};

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

        context = getActivity().getApplicationContext();

        fragmentMap = new HashMap<>();

        for(int i = 0; i < 14; i++){
            nameAndImageMap = new HashMap<>();
            nameAndImageMap.put(displayName[i], imageId[i]);
            fragmentMap.put(CommonUtils.fragmentArray[i], nameAndImageMap);
        }

        recentActivity = new String[12];
        recentActivityQueue = ((MainActivity) getActivity()).getRecentActivityQueue();
        int i = 0;
        for(String fragment : recentActivityQueue){
            recentActivity[i] = fragment;
            i++;
        }

        // Grid1
        temp = fragmentMap.get(recentActivity[0]);
        entry = temp.entrySet().iterator().next();
        LinearLayout grid1 = view.findViewById(R.id.grid1);

        ImageView grid1ImageView = view.findViewById(R.id.grid1ImageView);
        grid1ImageView.setImageResource(entry.getValue());
        TextView grid1TextView = view.findViewById(R.id.grid1TextView);
        grid1TextView.setText(entry.getKey());

        grid1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectFragment(recentActivity[0]);
            }
        });

        // Grid2
        temp = fragmentMap.get(recentActivity[1]);
        entry = temp.entrySet().iterator().next();
        LinearLayout grid2 = view.findViewById(R.id.grid2);

        ImageView grid2ImageView = view.findViewById(R.id.grid2ImageView);
        grid2ImageView.setImageResource(entry.getValue());
        TextView grid2TextView = view.findViewById(R.id.grid2TextView);
        grid2TextView.setText(entry.getKey());

        grid2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectFragment(recentActivity[1]);
            }
        });

        // Grid3
        temp = fragmentMap.get(recentActivity[2]);
        entry = temp.entrySet().iterator().next();
        LinearLayout grid3 = view.findViewById(R.id.grid3);

        ImageView grid3ImageView = view.findViewById(R.id.grid3ImageView);
        grid3ImageView.setImageResource(entry.getValue());
        TextView grid3TextView = view.findViewById(R.id.grid3TextView);
        grid3TextView.setText(entry.getKey());

        grid3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectFragment(recentActivity[2]);
            }
        });

        // Grid4
        temp = fragmentMap.get(recentActivity[3]);
        entry = temp.entrySet().iterator().next();
        LinearLayout grid4 = view.findViewById(R.id.grid4);

        ImageView grid4ImageView = view.findViewById(R.id.grid4ImageView);
        grid4ImageView.setImageResource(entry.getValue());
        TextView grid4TextView = view.findViewById(R.id.grid4TextView);
        grid4TextView.setText(entry.getKey());

        grid4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectFragment(recentActivity[3]);
            }
        });

        // Grid5
        temp = fragmentMap.get(recentActivity[4]);
        entry = temp.entrySet().iterator().next();
        LinearLayout grid5 = view.findViewById(R.id.grid5);

        ImageView grid5ImageView = view.findViewById(R.id.grid5ImageView);
        grid5ImageView.setImageResource(entry.getValue());
        TextView grid5TextView = view.findViewById(R.id.grid5TextView);
        grid5TextView.setText(entry.getKey());

        grid5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectFragment(recentActivity[4]);
            }
        });

        // Grid6
        temp = fragmentMap.get(recentActivity[5]);
        entry = temp.entrySet().iterator().next();
        LinearLayout grid6 = view.findViewById(R.id.grid6);

        ImageView grid6ImageView = view.findViewById(R.id.grid6ImageView);
        grid6ImageView.setImageResource(entry.getValue());
        TextView grid6TextView = view.findViewById(R.id.grid6TextView);
        grid6TextView.setText(entry.getKey());

        grid6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectFragment(recentActivity[5]);
            }
        });

        // Grid7
        temp = fragmentMap.get(recentActivity[6]);
        entry = temp.entrySet().iterator().next();
        LinearLayout grid7 = view.findViewById(R.id.grid7);

        ImageView grid7ImageView = view.findViewById(R.id.grid7ImageView);
        grid7ImageView.setImageResource(entry.getValue());
        TextView grid7TextView = view.findViewById(R.id.grid7TextView);
        grid7TextView.setText(entry.getKey());

        grid7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectFragment(recentActivity[6]);
            }
        });

        // Grid8
        temp = fragmentMap.get(recentActivity[7]);
        entry = temp.entrySet().iterator().next();
        LinearLayout grid8 = view.findViewById(R.id.grid8);

        ImageView grid8ImageView = view.findViewById(R.id.grid8ImageView);
        grid8ImageView.setImageResource(entry.getValue());
        TextView grid8TextView = view.findViewById(R.id.grid8TextView);
        grid8TextView.setText(entry.getKey());

        grid8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectFragment(recentActivity[7]);
            }
        });

        // Grid9
        temp = fragmentMap.get(recentActivity[8]);
        entry = temp.entrySet().iterator().next();
        LinearLayout grid9 = view.findViewById(R.id.grid9);

        ImageView grid9ImageView = view.findViewById(R.id.grid9ImageView);
        grid9ImageView.setImageResource(entry.getValue());
        TextView grid9TextView = view.findViewById(R.id.grid9TextView);
        grid9TextView.setText(entry.getKey());

        grid9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectFragment(recentActivity[8]);
            }
        });

        // Grid10
        temp = fragmentMap.get(recentActivity[9]);
        entry = temp.entrySet().iterator().next();
        LinearLayout grid10 = view.findViewById(R.id.grid10);

        ImageView grid10ImageView = view.findViewById(R.id.grid10ImageView);
        grid10ImageView.setImageResource(entry.getValue());
        TextView grid10TextView = view.findViewById(R.id.grid10TextView);
        grid10TextView.setText(entry.getKey());

        grid10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectFragment(recentActivity[9]);
            }
        });

        // Grid11
        temp = fragmentMap.get(recentActivity[10]);
        entry = temp.entrySet().iterator().next();
        LinearLayout grid11 = view.findViewById(R.id.grid11);

        ImageView grid11ImageView = view.findViewById(R.id.grid11ImageView);
        grid11ImageView.setImageResource(entry.getValue());
        TextView grid11TextView = view.findViewById(R.id.grid11TextView);
        grid11TextView.setText(entry.getKey());

        grid11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectFragment(recentActivity[10]);
            }
        });

        // Grid12
        temp = fragmentMap.get(recentActivity[11]);
        entry = temp.entrySet().iterator().next();
        LinearLayout grid12 = view.findViewById(R.id.grid12);

        ImageView grid12ImageView = view.findViewById(R.id.grid12ImageView);
        grid12ImageView.setImageResource(entry.getValue());
        TextView grid12TextView = view.findViewById(R.id.grid12TextView);
        grid12TextView.setText(entry.getKey());

        grid12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                selectFragment(recentActivity[11]);
            }
        });

        return view;
    }

    private void selectFragment(String fragment){

        switch(fragment){
            case "profileFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[0])
                        .replace(R.id.fragment_container, new ProfileFragment(), CommonUtils.fragmentArray[0])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[0])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[0]);
                break;

            case "changePasswordFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[1])
                        .replace(R.id.fragment_container, new ChangePasswordFragment(), CommonUtils.fragmentArray[1])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[1])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[1]);
                break;

            case "taskCreationFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[2])
                        .replace(R.id.fragment_container, new TaskCreationFragment(), CommonUtils.fragmentArray[2])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[2]))recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[2]);
                break;

            case "reportFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[3])
                        .replace(R.id.fragment_container, new ReportFragment(), CommonUtils.fragmentArray[3])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[3])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[3]);
                break;

            case "taskAllocationFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[4])
                        .replace(R.id.fragment_container, new TaskAllocationFragment(), CommonUtils.fragmentArray[4])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[4])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[4]);
                break;

            case "timeSheetFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[5])
                        .replace(R.id.fragment_container, new TimeSheetFragment(), CommonUtils.fragmentArray[5])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[5])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[5]);
                break;

            case "timeSheetTimerFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[6])
                        .replace(R.id.fragment_container, new TimeSheetTimerFragment(), CommonUtils.fragmentArray[6])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[6])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[6]);
                break;

            case "dailyReportFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[7])
                        .replace(R.id.fragment_container, new DailyReportFragment(), CommonUtils.fragmentArray[7])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[7])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[7]);
                break;

            case "dailyAllocatedTasksFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[8])
                        .replace(R.id.fragment_container, new DailyAllocatedTasksFragment(), CommonUtils.fragmentArray[8])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[8])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[8]);
                break;

            case "dailyProjectReportFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[9])
                        .replace(R.id.fragment_container, new DailyProjectReportFragment(), CommonUtils.fragmentArray[9])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[9])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[9]);
                break;

            case "projectwiseReportFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[10])
                        .replace(R.id.fragment_container, new ProjectWiseReportFragment(), CommonUtils.fragmentArray[10])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[10])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[10]);
                break;

            case "userwiseReportFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[11])
                        .replace(R.id.fragment_container, new UserWiseReportFragment(), CommonUtils.fragmentArray[11])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[11])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[11]);
                break;

            case "customerMasterFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[12])
                        .replace(R.id.fragment_container, new CustomerFragment(), CommonUtils.fragmentArray[12])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[12])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[12]);
                break;

            case "timeSheetApprovalFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[13])
                        .replace(R.id.fragment_container, new TimeSheetApprovalFragment(), CommonUtils.fragmentArray[13])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[13])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[13]);
                break;

            case "projectMasterFragment":
                getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[14])
                        .replace(R.id.fragment_container, new ProjectMasterFragment(), CommonUtils.fragmentArray[14])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[14])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[14]);
                break;

        }

        ((MainActivity) getActivity()).setRecentActivityQueue(recentActivityQueue);
    }

}
