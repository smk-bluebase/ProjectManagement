<?php
include("config.php");

$db = new DB_Connect();
$con = $db->connect();

$otp = $_POST['otp'];
$password = $_POST['password'];

$query = "SELECT TOP(1) * 
            FROM forgot_password 
            WHERE otp = (?)";

$params = array($otp);

$stmt = sqlsrv_query($con, $query, $params);

$result = array("status"=>"false");

while($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
    $query = "UPDATE user_master 
                SET password = (?)
                WHERE user_name = (?)";

    $params = array($password, $row['user_name']);
    
    sqlsrv_query($con, $query, $params);

    $query = "DELETE 
                FROM forgot_password 
                WHERE user_name = (?)";

    $params = array($row['user_name']);

    sqlsrv_query($con, $query, $params);

    $result = array("status"=>"true");
}

sqlsrv_free_stmt($stmt);

echo json_encode([$result]);

sqlsrv_close($con);
?>