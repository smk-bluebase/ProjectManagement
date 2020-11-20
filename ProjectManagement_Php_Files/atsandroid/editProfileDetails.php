<?php
include("config.php");

$db = new DB_Connect();
$con = $db->connect();

$name = $_POST['name'];
$userName = $_POST['userName'];
$originalUserName = $_POST['originalUserName'];
$emailId = $_POST['emailId'];
$empId = $_POST['empId'];
$mobileNo = $_POST['mobileNo'];
$profile = $_POST['profile'];

$result = array("status"=>"false");

if($userName == $originalUserName){
    $query = "UPDATE user_master
                SET [full_name] = (?), [email_id] = (?), [emp_id] = (?),
                [mobile_no] = (?), [profile] = (?)
                WHERE user_name = (?)";
    
    $params = array($name, $emailId, $empId, $mobileNo, $profile, $userName);

    sqlsrv_query($con, $query, $params);

    $result = array("status"=>"true");
}else {
    $query = "SELECT count(*) as count
            FROM user_master
            WHERE user_name = (?)";

    $params = array($userName);

    $stmt = sqlsrv_query($con, $query, $params);

    while($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
        if($row["count"] == 0){
            $query = "UPDATE user_master
                        SET [full_name] = (?), [user_name] = (?), [email_id] = (?),
                        [emp_id] = (?), [mobile_no] = (?), [profile] = (?)
                        WHERE user_name = (?)";
    
            $params = array($name, $userName, $emailId, $empId, $mobileNo, $profile, $originalUserName);
    
            sqlsrv_query($con, $query, $params);
    
            $result = array("status"=>"true");
        }
    }

}

echo json_encode([$result]);

sqlsrv_close($con);
?>