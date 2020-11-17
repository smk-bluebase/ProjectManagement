package bluebase.in.ats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Context context = this;
    String email;
    private DrawerLayout drawer;
    public Queue<String> recentActivityQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        Toolbar toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        recentActivityQueue = new LinkedList<>();

        drawer = findViewById(R.id.patient_drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        TextView emailTextView = headerView.findViewById(R.id.email);
        emailTextView.setText(email);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ImageView backButton = headerView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        if(savedInstanceState == null) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("homeFragment")
                    .replace(R.id.fragment_container, new HomeFragment(), "homeFragment")
                    .commit();
            navigationView.setCheckedItem(R.id.nav_home);

            String[] initializeArray = {"taskCreationFragment",
                                        "reportFragment",
                                        "taskAllocationFragment",
                                        "timeSheetFragment",
                                        "timeSheetTimerFragment",
                                        "dailyReportFragment",
                                        "dailyAllocatedTasksFragment",
                                        "dailyProjectReportFragment",
                                        "projectwiseReportFragment"};

            for(int i = 0; i < initializeArray.length; i++){
                recentActivityQueue.add(initializeArray[i]);
            }
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("homeFragment")
                        .replace(R.id.fragment_container, new HomeFragment(), "homeFragment")
                        .commit();
                break;

            case R.id.nav_profile:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("profileFragment")
                        .replace(R.id.fragment_container, new ProfileFragment(), "profileFragment")
                        .commit();
                if(!recentActivityQueue.remove("profileFragment")) recentActivityQueue.remove();
                recentActivityQueue.add("profileFragment");
                break;

            case R.id.nav_task_creation:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("taskCreationFragment")
                        .replace(R.id.fragment_container, new TaskCreationFragment(), "taskCreationFragment")
                        .commit();
                if(!recentActivityQueue.remove("taskCreationFragment"))recentActivityQueue.remove();
                recentActivityQueue.add("taskCreationFragment");
                break;

            case R.id.nav_report:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("reportFragment")
                        .replace(R.id.fragment_container, new ReportFragment(), "reportFragment")
                        .commit();
                if(!recentActivityQueue.remove("reportFragment")) recentActivityQueue.remove();
                recentActivityQueue.add("reportFragment");
                break;

            case R.id.nav_task_allocation:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("taskAllocationFragment")
                        .replace(R.id.fragment_container, new TaskAllocationFragment(), "taskAllocationFragment")
                        .commit();
                if(!recentActivityQueue.remove("taskAllocationFragment")) recentActivityQueue.remove();
                recentActivityQueue.add("taskAllocationFragment");
                break;

            case R.id.nav_time_sheet:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("timeSheetFragment")
                        .replace(R.id.fragment_container, new TimeSheetFragment(), "timeSheetFragment")
                        .commit();
                if(!recentActivityQueue.remove("timeSheetFragment")) recentActivityQueue.remove();
                recentActivityQueue.add("timeSheetFragment");
                break;

            case R.id.nav_time_sheet_timer:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("timeSheetTimeFragment")
                        .replace(R.id.fragment_container, new TimeSheetTimerFragment(), "timeSheetTimerFragment")
                        .commit();
                if(!recentActivityQueue.remove("timeSheetTimeFragment")) recentActivityQueue.remove();
                recentActivityQueue.add("timeSheetTimeFragment");
                break;

            case R.id.nav_daily_report:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("dailyReportFragment")
                        .replace(R.id.fragment_container, new DailyReportFragment(), "dailyReportFragment")
                        .commit();
                if(!recentActivityQueue.remove("dailyReportFragment")) recentActivityQueue.remove();
                recentActivityQueue.add("dailyReportFragment");
                break;

            case R.id.nav_daily_allocated_tasks:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("dailyAllocationTasksFragment")
                        .replace(R.id.fragment_container, new DailyAllocatedTasksFragment(), "dailyAllocatedTasksFragment")
                        .commit();
                if(!recentActivityQueue.remove("dailyAllocationTasksFragment")) recentActivityQueue.remove();
                recentActivityQueue.add("dailyAllocationTasksFragment");
                break;

            case R.id.nav_daily_project_report:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("dailyProjectReportFragment")
                        .replace(R.id.fragment_container, new DailyProjectReportFragment(), "dailyProjectReportFragment")
                        .commit();
                if(!recentActivityQueue.remove("dailyProjectReportFragment")) recentActivityQueue.remove();
                recentActivityQueue.add("dailyProjectReportFragment");
                break;

            case R.id.nav_projectwise_report:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("projectwiseReportFragment")
                        .replace(R.id.fragment_container, new ProjectWiseReportFragment(), "projectwiseReportFragment")
                        .commit();
                if(!recentActivityQueue.remove("projectwiseReportFragment")) recentActivityQueue.remove();
                recentActivityQueue.add("projectwiseReportFragment");
                break;

            case R.id.nav_userwise_report:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("userwiseReportFragment")
                        .replace(R.id.fragment_container, new UserWiseReportFragment(), "userwiseReportFragment")
                        .commit();
                if(!recentActivityQueue.remove("userwiseReportFragment")) recentActivityQueue.remove();
                recentActivityQueue.add("userwiseReportFragment");
                break;

            case R.id.nav_customer_master:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("customerMasterFragment")
                        .replace(R.id.fragment_container, new CustomerFragment(), "customerMasterFragment")
                        .commit();
                if(!recentActivityQueue.remove("customerMasterFragment")) recentActivityQueue.remove();
                recentActivityQueue.add("customerMasterFragment");
                break;

            case R.id.nav_time_sheet_approval:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("timeSheetApprovalFragment")
                        .replace(R.id.fragment_container, new TimeSheetApprovalFragment(), "timeSheetApprovalFragment")
                        .commit();
                if(!recentActivityQueue.remove("timeSheetApprovalFragment")) recentActivityQueue.remove();
                recentActivityQueue.add("timeSheetApprovalFragment");
                break;

            case R.id.nav_project_master:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack("projectMasterFragment")
                        .replace(R.id.fragment_container, new ProjectMasterFragment(), "projectMasterFragment")
                        .commit();
                if(!recentActivityQueue.remove("projectMasterFragment")) recentActivityQueue.remove();
                recentActivityQueue.add("projectMasterFragment");
                break;

        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public Queue<String> getRecentActivityQueue(){
        return recentActivityQueue;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            int fragmentsInStack = getSupportFragmentManager().getBackStackEntryCount();

            if (fragmentsInStack > 1) {
                getSupportFragmentManager().popBackStack();
            }else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setTitle("ATS");
                alertDialogBuilder.setMessage("Do you want to logout?");
                alertDialogBuilder.setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        });

                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
    }

}