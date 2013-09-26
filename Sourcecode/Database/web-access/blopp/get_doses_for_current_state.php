<?php
    # returns a list of planned doses of medicines
    # that aren't taken today

    # checks if the doses have their medical_plan_dose_id in
    # the table DAY_MEDICINE_DOSES and excludes those that are

    # rows: [   id, 
    #           medical_plan_id,
    #           health_state_id,
    #           time,
    #           medicine_id,
    #           medicine_color,
    #           medicine_name
    #       ]

	header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';

	$id = $_GET['child_id'];
	
	$mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);
	
	$day_date = date('Y-m-d');
	
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
			."SELECT C.medical_plan_id"
			." FROM `CHILDREN` AS C"
			." WHERE C.id=$id"
		.")"
		." AND Mpd.id NOT IN ("
            ."SELECT DMD.medical_plan_dose_id"
            ." FROM `DAY_MEDICINE_DOSES` AS DMD"
            ." WHERE DMD.medical_plan_dose_id=Mpd.id"
            ." AND DMD.day_date='$day_date'"
        .")"
		." AND Mpd.medicine_id = M.id"
		." AND Mpd.health_state_id IN ("
			."SELECT HS.health_state_id"
			." FROM `CHILD_HEALTH_STATES` AS HS"
			." WHERE HS.child_id=$id"
			." AND HS.applies_now='1'"
		.")"
		." LIMIT 0,100";
	
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
		'rows' => $rows
	));
	$mysqli->close();
?>