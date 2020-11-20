<?php
include("config.php");

$db = new DB_Connect();
$con = $db->connect();

$customerId = $_POST["customerId"];
$projectId = $_POST["projectId"];
$module = $_POST["module"];
$task = $_POST["task"];
$description = $_POST["description"];
$dueDate = $_POST["dueDate"];
$hours = $_POST["hours"];
$status = $_POST["status"];
$createdBy = $_POST["createdBy"];
$createdOn = date("Y-m-d h:i:s");

$query = "INSERT INTO task_master
            ([customer_id], [project_id], [module], [task],
            [description], [due_date], [hours], [status], [created_by], [created_on])
            VALUES ((?), (?), (?), (?), (?), (?), (?), (?), (?), (?))";

$params = array($customerId, $projectId, $module, $task, $description, $dueDate, $hours, $status, $createdBy, $createdOn);

$stmt = sqlsrv_query($con, $query, $params);

$result = array("status"=>"false");

if($stmt){
    $result = array("status"=>"true");
}

sqlsrv_free_stmt($stmt);

echo json_encode([$result]);

sqlsrv_close($con);
?>