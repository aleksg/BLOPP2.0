<?php
	header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';

	$id = $_GET['child_id'];
	
	$mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);
	
	$id = $mysqli->real_escape_string($id);
	$q = "SELECT "
			."Mpd.id, "
			."Mpd.medical_plan_id, "
			."Mpd.health_state_id, "
			."Mpd.time, "
			."Mpd.medicine_id, "
			."M.color AS 'medicine_color', "
			."M.name AS 'medicine_name'"
		." FROM `MEDICAL_PLAN_DOSES` AS Mpd,"
		." `MEDICINES` AS M"
		." WHERE Mpd.medical_plan_id IN ("
			."SELECT medical_plan_id"
			." FROM `CHILDREN` C"
			." WHERE C.id=".$id
		.')'
		." AND Mpd.medicine_id = M.id"
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
		'query' => $q,
		'rows' => $rows
	));
	$mysqli->close();
?>