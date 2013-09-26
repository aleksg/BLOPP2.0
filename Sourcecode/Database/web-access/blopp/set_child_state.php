<?php
    # Changes the current state of a child, and creates an
    # entry in the CHILDREN_LOG_DAYS table with the new state
    # for the current day (calculated by this script).
    
    # Input:
    #   child_id
    #   state_id
    
    # Output:
    #   sqlsuccess

	header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';

	$child_id = $_POST['child_id'];
	$state_id = $_POST['state_id'];
	
	$day_date = date('Y-m-d');
	$pollen_state_id = 1;
	
	$mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);
	
	$child_id = $mysqli->real_escape_string($child_id);
	$state_id = $mysqli->real_escape_string($state_id);
        
    $update_q = "UPDATE `CHILD_HEALTH_STATES`"
        ." SET applies_now = IF(health_state_id = '$state_id', 1, 0)"
        ." WHERE child_id='$child_id'";
	
	$sqlsuccess = $mysqli->query($update_q);
	
	$insert_q = "REPLACE INTO `CHILDREN_LOG_DAYS` ("
            ."`date`, "
            ."`child_id`, "
            ."`pollen_state_id`, "
            ."`health_state_id`) "
        ." VALUES ("
            ."'$day_date', "
            ."'$child_id', "
            ."'$pollen_state_id', "
            ."'$state_id')";
            
    $sqlsuccess = $sqlsuccess and $mysqli->query($insert_q);

	print json_encode(array(
		'sqlsuccess' => $sqlsuccess,
		'child_id' => $child_id,
		'state_id' => $state_id,
		'update_query' => $update_q,
		'insert_query' => $insert_q
	));
	
	$mysqli->close();
?>