<?php
    # Deletes the entry in the table MEDICAL_PLAN_DOSES
    # which correspons to the given id.
    
    # Input:
    #   time: sqltime
    #   medicine_id: int
    #   child_id: int
    
    # Output:
    #   sqlsuccess: boolean
    #   num_deleted: int (should be 1 or 0)
    
	header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';
	
	$mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);

    $time = $mysqli->real_escape_string($_POST['time']);
    $medicine_id = $_POST['medicine_id'];
    $health_state_id = $_POST['health_state_id'];
    $child_id = $_POST['child_id'];
	
	$q = "DELETE FROM `MEDICAL_PLAN_DOSES`"
        ." WHERE `time`='$time'"
        ." AND `medicine_id`='$medicine_id'"
        ." AND `health_state_id`='$health_state_id'"
        ." AND `medical_plan_id` IN (SELECT mp.id FROM `MEDICAL_PLANS` AS mp WHERE mp.child_id='$child_id')";
	
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