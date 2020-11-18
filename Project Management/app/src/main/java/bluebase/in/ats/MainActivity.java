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

import java.util.Deque;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Context context = this;
    String userName;
    String email;
    private DrawerLayout drawer;
    private Deque<String> recentActivityQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
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

            for(int i = 0; i < 12; i++){
                recentActivityQueue.add(CommonUtils.fragmentArray[i]);
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
                        .addToBackStack(CommonUtils.fragmentArray[0])
                        .replace(R.id.fragment_container, new ProfileFragment(), CommonUtils.fragmentArray[0])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[0])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[0]);
                break;

            case R.id.nav_change_password:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[1])
                        .replace(R.id.fragment_container, new ChangePasswordFragment(), CommonUtils.fragmentArray[1])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[1])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[1]);
                break;

            case R.id.nav_task_creation:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[2])
                        .replace(R.id.fragment_container, new TaskCreationFragment(), CommonUtils.fragmentArray[2])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[2]))recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[2]);
                break;

            case R.id.nav_report:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[3])
                        .replace(R.id.fragment_container, new ReportFragment(), CommonUtils.fragmentArray[3])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[3])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[3]);
                break;

            case R.id.nav_task_allocation:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[4])
                        .replace(R.id.fragment_container, new TaskAllocationFragment(), CommonUtils.fragmentArray[4])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[4])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[4]);
                break;

            case R.id.nav_time_sheet:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[5])
                        .replace(R.id.fragment_container, new TimeSheetFragment(), CommonUtils.fragmentArray[5])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[5])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[5]);
                break;

            case R.id.nav_time_sheet_timer:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[6])
                        .replace(R.id.fragment_container, new TimeSheetTimerFragment(), CommonUtils.fragmentArray[6])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[6])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[6]);
                break;

            case R.id.nav_daily_report:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[7])
                        .replace(R.id.fragment_container, new DailyReportFragment(), CommonUtils.fragmentArray[7])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[7])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[7]);
                break;

            case R.id.nav_daily_allocated_tasks:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[8])
                        .replace(R.id.fragment_container, new DailyAllocatedTasksFragment(), CommonUtils.fragmentArray[8])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[8])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[8]);
                break;

            case R.id.nav_daily_project_report:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[9])
                        .replace(R.id.fragment_container, new DailyProjectReportFragment(), CommonUtils.fragmentArray[9])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[9])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[9]);
                break;

            case R.id.nav_projectwise_report:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[10])
                        .replace(R.id.fragment_container, new ProjectWiseReportFragment(), CommonUtils.fragmentArray[10])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[10])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[10]);
                break;

            case R.id.nav_userwise_report:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[11])
                        .replace(R.id.fragment_container, new UserWiseReportFragment(), CommonUtils.fragmentArray[11])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[11])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[11]);
                break;

            case R.id.nav_customer_master:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[12])
                        .replace(R.id.fragment_container, new CustomerFragment(), CommonUtils.fragmentArray[12])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[12])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[12]);
                break;

            case R.id.nav_time_sheet_approval:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[13])
                        .replace(R.id.fragment_container, new TimeSheetApprovalFragment(), CommonUtils.fragmentArray[13])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[13])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[13]);
                break;

            case R.id.nav_project_master:
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(CommonUtils.fragmentArray[14])
                        .replace(R.id.fragment_container, new ProjectMasterFragment(), CommonUtils.fragmentArray[14])
                        .commit();
                if(!recentActivityQueue.remove(CommonUtils.fragmentArray[14])) recentActivityQueue.removeLast();
                recentActivityQueue.addFirst(CommonUtils.fragmentArray[14]);
                break;

        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void setRecentActivityQueue(Deque<String> recentActivityQueue) {
        this.recentActivityQueue = recentActivityQueue;
    }

    public Deque<String> getRecentActivityQueue(){
        return recentActivityQueue;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
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