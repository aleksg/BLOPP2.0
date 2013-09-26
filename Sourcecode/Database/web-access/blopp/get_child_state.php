<?php
	header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';

	$id = $_GET['child_id'];
	
	$mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);
	
	$id = $mysqli->real_escape_string($id);
	$q = 'SELECT id, label'
		.' FROM `HEALTH_STATES` HS'
		.' WHERE HS.id IN ('
            .'SELECT health_state_id'
            .' FROM `CHILD_HEALTH_STATES` CHS'
            .' WHERE CHS.child_id='.$id
            .' AND CHS.applies_now=1'
        .')'
		.' LIMIT 0,1';
	
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
		'state' => $rows[0]
	));
	$mysqli->close();
?>