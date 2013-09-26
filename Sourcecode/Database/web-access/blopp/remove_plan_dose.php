<?php
    # Deletes the entry in the table MEDICAL_PLAN_DOSES
    # which correspons to the given id.
    
    # Input:
    #   id: int
    
    # Output:
    #   sqlsuccess: boolean
    #   num_deleted: int (should be 1 or 0)
    
	header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';
	
	$mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);

    $id = $mysqli->real_escape_string($_POST['id']);
	
	$q = "DELETE FROM `MEDICAL_PLAN_DOSES`"
        ." WHERE id='$id'";
	
	$sqlsuccess = $mysqli->query($q);
	$num_deleted = $mysqli->affected_rows;
    
	print json_encode(array(
		"sqlsuccess" => $sqlsuccess,
		"q" => $q,
		"id" => $id,
		"num_deleted" => $num_deleted
	));
	
	$mysqli->close();
?>