<?php
include("config.php");

$db = new DB_Connect();
$con = $db->connect();

$userName = $_POST["userName"];
$password = $_POST["password"];
 
$query = "SELECT * 
			FROM user_master 
			WHERE user_name = (?) AND password = (?)";

$params = array($userName, $password);
 
$stmt = sqlsrv_query($con, $query, $params);
 
$result = array("status"=>"false");

while($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
	$result = array("status"=>"true",
					"email"=>$row["email_id"],
					"userId"=>$row["user_id"]
				);
}

sqlsrv_free_stmt($stmt);
 
echo json_encode([$result]);
 
sqlsrv_close($con);
?>