<?php
include("config.php");
require 'sendmail.php';

$db = new DB_Connect();
$con = $db->connect();

$userName = $_POST['userName'];

$otp = rand(1000000, 10000000);

$subject = "Bluebase App Forgot Password OTP";

$message = "Dear ".$userName.",<br/><br/>

Looks like you have forgotten your password. Please enter<br/>
the OTP below to authenticate yourself.<br/><br/>

OTP - ".$otp."<br/><br/>

Continue with Bluebase!<br/><br/>

Regards,<br/>
Bluebase Team";

$query = "SELECT COUNT(*) as [count], um.email_id 
            FROM user_master as um 
            WHERE um.user_name = (?) GROUP BY um.email_id";

$params = array($userName);

$stmt = sqlsrv_query($con, $query, $params);

$result = array("status"=>"false");

while($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
    if($row['count'] == 1){
        $query = "INSERT INTO forgot_password 
                    ([user_name], [otp]) VALUES 
                    ((?), (?))";

        $params = array($userName, $otp);

        sqlsrv_query($con, $query, $params);

        if(sendMail($row['email_id'], $subject, $message)){
            $result = array("status"=>"true");
        }
    }
}

sqlsrv_free_stmt($stmt);

echo json_encode([$result]);

sqlsrv_close($con);
?>