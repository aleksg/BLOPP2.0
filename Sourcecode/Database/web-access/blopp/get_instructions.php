<?php
	#header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';

	$id = $_GET['medicine_id'];
	
	$mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);
	$mysqli->set_charset("utf8");
	$id = $mysqli->real_escape_string($id);
	$q = "SELECT *"
		." FROM `MEDICINE_INSTRUCTIONS`"
		." WHERE id IN ("
            ."SELECT instructions_id"
            ." FROM `MEDICINES`"
            ." WHERE `id`='$id')"
		." LIMIT 0,1";
	
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
		'instructions' => $rows[0]
	));
?>