<?php
    # Returns result: true if dose is taken,
    # otherwise false
    
	header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';

	$id = $_GET['dose_id'];
	
	$day_date = date('Y-m-d');
	
	$mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);
	
	$id = $mysqli->real_escape_string($id);
	$q = "SELECT `id`"
		." FROM `DAY_MEDICINE_DOSES`"
		." WHERE `medical_plan_dose_id`='$id'"
		." AND `day_date`='$day_date'"
		." LIMIT 0,1";
	
	$result = $mysqli->query($q);
	if ($result) {
        $sqlsuccess = true;
	} else {
        $sqlsuccess = false;
	}

	print json_encode(array(
		'sqlsuccess' => $sqlsuccess,
		'dose_id' => $id,
		'day_date' => $day_date,
		'query' => $q,
		'result' => $result->num_rows > 0
	));
?>