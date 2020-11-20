<?php
include("config.php");

$db = new DB_Connect();
$con = $db->connect();

$userName = $_POST['userName'];

$query = "SELECT * 
            FROM user_master 
            WHERE user_name = (?)";

$params = array($userName);

$stmt = sqlsrv_query($con, $query, $params);

$result = array("status"=>"false");

while($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
    $result = array("status"=>"true", 
                    "name"=>$row["full_name"],
                    "userName"=>$row["user_name"],
                    "email"=>$row["email_id"],
                    "empId"=>$row["emp_id"],
                    "mobileNo"=>$row["mobile_no"],
                    "profile"=>$row["profile"]     
                );
}

sqlsrv_free_stmt($stmt);

echo json_encode([$result]);

sqlsrv_close($con);
?>