<?php
include("config.php");

$db = new DB_Connect();
$con = $db->connect();

$query = "SELECT * 
            FROM project_customer_master";

$stmt = sqlsrv_query($con, $query);

$result = array();

while($row = sqlsrv_fetch_array($stmt, SQLSRV_FETCH_ASSOC)){
    $result[] = array("id"=>$row["id"],
                    "name"=>$row["name"],
                    "address"=>$row["address"],
                    "inCharge"=>$row["in_charge"],
                    "gstNumber"=>$row["gst_number"],
                    "contactNumber"=>$row["contact_number"],
                    "status"=>$row["status"],
                    "createdBy"=>$row["created_by"],
                    "createdOn"=>$row["created_on"]
                );
}

sqlsrv_free_stmt($stmt);

echo json_encode($result);

sqlsrv_close($con);
?>