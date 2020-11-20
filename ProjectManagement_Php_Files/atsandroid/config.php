<?php
define("SERVER_NAME", "");
define("UID", "");
define("PWD", "");
define("DB", "Bluebase_HRMS");

class DB_Connect {
    public function connect() {
        $connectionInfo = array( "Database"=>DB, "UID"=>UID, "PWD"=>PWD);
        $con = sqlsrv_connect(SERVER_NAME, $connectionInfo);
        return $con;
    }
}

// error_reporting(0);

?>