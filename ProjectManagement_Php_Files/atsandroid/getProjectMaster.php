<?php
include("config.php");

$db = new DB_Connect();
$con = $db->connect();

$query = "SELECT *
            FROM project_master";

$stmt = sqlsrv_query($con, $query);

$result = array();

while($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
    $result[] = array("id"=>$row["id"],
                    "name"=>$row["name"],
                    "customerId"=>$row["customer_id"],
                    "description"=>$row["description"],
                    "dueDate"=>$row["due_date"],
                    "duration"=>$row["duration"],
                    "cost"=>$row["cost"],
                    "status"=>$row["status"],
                    "poNumber"=>$row["po_number"],
                    "poDetail"=>$row["po_detail"],
                    "createdBy"=>$row["created_by"],
                    "createdOn"=>$row["created_on"]
                );
}

sqlsrv_free_stmt($stmt);

echo json_encode($result);

sqlsrv_close($con);
?>