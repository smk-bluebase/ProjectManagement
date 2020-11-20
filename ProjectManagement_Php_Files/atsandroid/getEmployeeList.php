<?php
include("config.php");

$db = new DB_Connect();
$con = $db->connect();

$query = "SELECT user_id, user_name 
			FROM user_master 
			WHERE status = 0";
 
$stmt = sqlsrv_query($con, $query);
 
$result = array();

while($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
    $result[] = array("userId"=>$row["user_id"],
                    "userName"=>$row["user_name"]
				);
}

sqlsrv_free_stmt($stmt);
 
echo json_encode($result);
 
sqlsrv_close($con);
?>