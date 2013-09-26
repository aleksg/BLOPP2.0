<?php
	header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';

	$name = $_POST['name'];
	$persnum = $_POST['persnum'];
	$availstates = $_POST['states'];
	
	$mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);
    
    $medical_plan_q = "INSERT INTO `MEDICAL_PLANS` (`id`, `label`)"
                     ." VALUES ("
                        ."'', '".$name."'"
                     .")";
    $mysqli->query($medical_plan_q);
    $medical_plan_id = $mysqli->insert_id;
    
    $q = "INSERT INTO `CHILDREN` (`id`, `name`, `pers_num`, `medical_plan_id`, `credits`)"
        ." VALUES ("
            ."'', "
            ."'".$name."', "
            ."'".$persnum."', "
            ."'".$medical_plan_id."', "
            ."'0'"
        .")";
    $mysqli->query($q);
    $child_id = $mysqli->insert_id;
    
    $all_states_q = "SELECT id FROM `HEALTH_STATES`";
    $result = $mysqli->query($all_states_q);
    
    $all_state_ids = array();
    
    if ($result) {
        while ($r = $result->fetch_assoc()) {
            $all_state_ids[] = $r["id"];
        }
    }
    
    # first state applies now and is default!
	$state_queries = array();
    foreach ($availstates as $state_id) {
        if (in_array($state_id, $all_state_ids)) {
            $state_q = "INSERT INTO `CHILD_HEALTH_STATES` (`child_id`, `health_state_id`, `applies_now`, `default`)"
                        ." VALUES ("
                            ."'".$child_id."', "
                            ."'".$state_id."', "
                            ."'".($state_id == $availstates[0] ? 1 : 0)."'"
							."'".($state_id == $availstates[0] ? 1 : 0)."'"
                        .")";
            $mysqli->query($state_q);
            $state_queries[] = $state_q;
        }
    }

	print json_encode(array(
		"post" => $_POST,
		"q" => $q,
		"medical_plan_q" => $medical_plan_q,
		"medical_plan_id" => $medical_plan_id,
		"child_id" => $child_id,
		"state_queries" => $state_queries,
		"all_state_ids" => $all_state_ids
	));
	
	$mysqli->close();
?>