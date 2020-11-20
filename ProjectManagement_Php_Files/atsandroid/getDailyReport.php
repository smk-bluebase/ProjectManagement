<?php
include("config.php");

$db = new DB_Connect();
$con = $db->connect();

$fromDate = $_POST["fromDate"];
$toDate = $_POST["toDate"];
$userId = $_POST["userId"];

$query = "SELECT um.full_name, m.name AS project, p.name, tm.task, ts.actual_date, ts.daily_status, 
            ts.start_time, ts.end_time, ts.remarks AS rem, ts.created_by, ts.approval_status,
            ts.total_hours, ps.remarks AS status, ds.name AS daily, tm.module, tm.description, ta.allocated_hour
            FROM task_master tm JOIN task_allocation_master ta 
            ON tm.id = ta.task_master_id JOIN timesheet ts 
            ON ts.task_master_id = ta.task_master_id AND ts.actual_date = ta.allocated_date AND ts.created_by = ta.allocated_user
            JOIN project_customer_master p ON p.id = tm.customer_id
            JOIN project_master m ON m.id = tm.project_id
            JOIN user_master um ON um.user_id = ts.created_by
            JOIN daily_status_master ds ON ds.id = ts.daily_status
            JOIN project_status_master ps ON ps.id = ts.status
            WHERE ts.actual_date BETWEEN (?) AND (?) AND um.user_id IN((?)) 
            ORDER BY um.full_name,ts.actual_date DESC";

$params = array($fromDate, $toDate, $userId);

$stmt = sqlsrv_query($con, $query, $params);

$result = array("status"=>"false");

while($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
    $result = array("0"=>$row["name"],
                    "1"=>$row["module"],
                    "2"=>$row["task"],
                    "3"=>$row["description"],
                    "4"=>$row["actual_date"],
                    "5"=>$row["allocated_hour"],
                    "6"=>$row["start_time"],
                    "7"=>$row["end_time"],
                    "8"=>$row["total_hours"],
                    "9"=>$row["rem"],
                    "10"=>$row["daily"],
                    "11"=>$row["status"],
                    "12"=>$row["full_name"],
                    "13"=>$row["approval_status"]
                );
}

sqlsrv_free_stmt($stmt);

echo json_encode([$result]);

sqlsrv_close($con);
?>