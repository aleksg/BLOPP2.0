<?php
    # Gets all columns in the CHILDREN table for a given ID.
    
    # Input:
    #   child_id
    
    # Output:
    #   information
    #       id
    #       name
    #       pers_num
    #       medical_plan_id
    #       credits
    #       location_latitude
    #       location_altitude
    
	header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';

	$id = $_GET['child_id'];
	
	$mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);
	
	$id = $mysqli->real_escape_string($id);
	$q = 'SELECT *'
		.' FROM `CHILDREN`'
		.' WHERE id='.$id
		.' LIMIT 0,1';
	
	$result = $mysqli->query($q);

	$rows = array();
	
	if ($result) {
		while ($r = $result->fetch_assoc()) {
			$rows[] = $r;
		}
		$sqlsuccess = true;
	} else {
		$sqlsuccess = false;
	}

	print json_encode(array(
		'sqlsuccess' => $sqlsuccess,
		'child_id' => $id,
		'query' => $q,
		'information' => $rows[0]
	));
?>