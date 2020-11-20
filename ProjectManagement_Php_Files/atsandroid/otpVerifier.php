<?php
include("config.php");

$db = new DB_Connect();
$con = $db->connect();

$otp = $_POST['otp'];

$query = "SELECT TOP(1) * 
            FROM signup 
            WHERE otp = (?)";

$params = array($otp);

$stmt = sqlsrv_query($con, $query, $params);

$result = array("status"=>"false");

while($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
    $query = "INSERT INTO user_master 
                ([user_name], [password], [email_id]) VALUES 
                ((?), (?), (?))";

    $params = array($row['user_name'], $row['password'], $row['email']);

    sqlsrv_query($con, $query, $params);

    $query = "DELETE 
                FROM signup 
                WHERE user_name = (?)";

    $params = array($row['user_name']);

    sqlsrv_query($con, $query, $params);

    $result = array("status"=>"true");
}

sqlsrv_free_stmt($stmt);

echo json_encode([$result]);

sqlsrv_close($con);
?>