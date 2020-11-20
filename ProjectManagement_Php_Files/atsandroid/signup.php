<?php
include("config.php");
require 'sendmail.php';

$db = new DB_Connect();
$con = $db->connect();

$userName = $_POST['userName'];
$password = $_POST['password'];
$email = $_POST['email'];

$otp = rand(1000000, 10000000);

$subject = "Bluebase App OTP";

$message = "Dear ".$userName.",<br/><br/>

Welcome, to Bluebase! Please enter<br/> 
the OTP below to sign up to our services.<br/><br/>

OTP - ".$otp."<br/><br/>

We are thrilled to welcome you to Bluebase!<br/><br/>

Regards,<br/>
Bluebase Team";

$query = "SELECT count(*) as [count]
            FROM user_master 
            WHERE user_name = (?)";

$params = array($userName);

$stmt = sqlsrv_query($con, $query, $params);

$result = array("status"=>"false");

while($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
    if($row["count"] == 0){
        $query = "SELECT count(*) as [count] 
                    FROM signup 
                    WHERE user_name = (?)";

        $params = array($userName);

        sqlsrv_free_stmt($stmt);

        $stmt = sqlsrv_query($con, $query, $params);
        
        while($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
            if($row["count"] == 0){
                $query = "INSERT INTO signup 
                            (user_name, password, email, otp) VALUES 
                            ((?), (?), (?), (?))";

                $params = array($userName, $password, $email, $otp);

                sqlsrv_query($con, $query, $params);

                if(sendMail($email, $subject, $message)){
                    $result = array("status"=>"true");
                }
                
            }else{
                $query = "DELETE 
                            FROM signup 
                            WHERE user_name = (?)";

                $params = array($userName);

                sqlsrv_query($con, $query, $params);

                $query = "INSERT INTO signup 
                            (user_name, password, email, otp) VALUES 
                            ((?), (?), (?), (?))";

                $params = array($userName, $password, $email, $otp);

                sqlsrv_query($con, $query, $params);
                
                if(sendMail($email, $subject, $message)){
                    $result = array("status"=>"true");
                }
    
            }
        }
    }
}

sqlsrv_free_stmt($stmt);

echo json_encode([$result]);

sqlsrv_close($con);
?>