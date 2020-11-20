<?php
include("config.php");

$db = new DB_Connect();
$con = $db->connect();

$userName = $_POST['userName'];
$currentPassword = $_POST['currentPassword'];
$newPassword = $_POST['newPassword'];

$query = "SELECT [password]
            FROM user_master
            WHERE user_name = (?)";

$params = array($userName);

$stmt = sqlsrv_query($con, $query, $params);

$result = array("status"=>"false");

while($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
    if($currentPassword == $row['password']){
        $query = "UPDATE user_master
                    SET [password] = (?)
                    WHERE user_name = (?)";
    
        $params = array($newPassword, $userName);

        sqlsrv_query($con, $query, $params);

        $result = array("status"=>"true");
    }
}

sqlsrv_free_stmt($stmt);

echo json_encode([$result]);

sqlsrv_close($con);
?>