<?php
    # Inserts a new entry in the MEDICAL_PLAN_DOSES
    # table for the given child, health state a dose
    # of the given medicine at the given time.
    
    # Input:
    #   child_id: int
    #   health_state_id: int
    #   medicine_id: int
    #   time: String(hh:mm:ss)
    
    # Output:
    #   sqlsuccess: boolean
    #   id: int
    
    
	header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';
	
	$mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);

    $child_id = $mysqli->real_escape_string($_POST['child_id']);
    $health_state_id = $mysqli->real_escape_String($_POST['health_state_id']);
    $medicine_id = $mysqli->real_escape_string($_POST['medicine_id']);
    $time = $mysqli->real_escape_string($_POST['time']);
	
	$q = "INSERT INTO `MEDICAL_PLAN_DOSES` ("
            ."`id`, "
            ."`medical_plan_id`, "
            ."`health_state_id`, "
            ."`time`, "
            ."`medicine_id`)"
        ." SELECT "
            ."'', "
            ."C.medical_plan_id, "
            ."'$health_state_id', "
            ."'$time', "
            ."'$medicine_id'"
        ." FROM `CHILDREN` AS C"
        ." WHERE C.id='$child_id'";
	
	$sqlsuccess = $mysqli->query($q);
	$id = $mysqli->insert_id;
    
	print json_encode(array(
		"sqlsuccess" => $sqlsuccess,
		"q" => $q,
		"child_id" => $child_id,
		"health_state_id" => $health_state_id,
		"medicine_id" => $medicine_id,
		"time" => $time,
		"id" => $id
	));
	
	$mysqli->close();
?>