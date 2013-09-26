<?php
    # Returns all registered entries for the day
    # for a given child (id) during
    # the given month during the given year.
    
    # resultobject.days is a list of rows in JSON format with the fields:
    # id(int), reward(int), time(time), day_date(date),
    # child_id(int), medicine_id(int), health_state_id(int),
    # medical_plan_dose_id(int), pollen_state_id(int)
    
    # if medicical_plan_dose_id is set, the mediciation was planned,
    # otherwise it was a by-need medication

	header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';

    $mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);

	$id = $mysqli->real_escape_string($_GET['child_id']);
	$year = $mysqli->real_escape_string($_GET['year']);
	$month = $mysqli->real_escape_string($_GET['month']);
	
	if ($year == NULL)
        $year = date("Y");
    
    if ($month == NULL)
        $month = date("m");
	
	$q = "SELECT *"
		." FROM `DAY_MEDICINE_DOSES`"
		." WHERE `child_id`=$id"
		." AND YEAR(`day_date`)=$year"
		." AND MONTH(`day_date`)=$month"
		." LIMIT 0,100";
	
	$result = $mysqli->query($q);

	$rows = array();
	
	if ($result) {
		while ($r = $result->fetch_assoc()) {
			$rows[] = $r;
		}
		$result->close();
		$sqlsuccess = true;
	} else {
		$sqlsuccess = false;
	}

	print json_encode(array(
		'sqlsuccess' => $sqlsuccess,
		'child_id' => $id,
		'year' => $year,
		'month' => $month,
		'query' => $q,
		'days' => $rows
	));
	$mysqli->close();
?>