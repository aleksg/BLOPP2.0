<?php
    # Adds an entry to DAY_MEDICINE_DOSES with the log information
    # about a medication dose taken. Only adds if the planned dose
    # hasn't been taken that day already. Calculates the reward
    # based on health state of the child. Adds that reward to the
    # child's total credits.
    
    # Input:
    #   child_id: int
    #   medicine_id: int
    #   time: String(hh:mm:ss)
    #   day_date: String(YYYY-mm-dd)
    #   health_state_id: int
    #   medical_plan_dose_id: int
    
    # Output:
    #   reward: int
    #   unique: bool

	header('Content-Type: application/json; charset=utf-8;');
	
	include 'dbconfig.php';

	$mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);

	$child_id = $mysqli->real_escape_string($_POST['child_id']);
	$medicine_id = $mysqli->real_escape_string($_POST['medicine_id']);
	$time = $mysqli->real_escape_string($_POST['time'] ? $_POST['time'] : "00:00:01");
	$day_date = $mysqli->real_escape_string($_POST['day_date']);
	$health_state_id = $mysqli->real_escape_string($_POST['health_state_id']);
	$medical_plan_dose_id = $mysqli->real_escape_string($_POST['medical_plan_dose_id']); # can be null: means dose wasn't planned
    
	# check if there exists an entry for this day and medical_plan_dose_id
	$unique = true;
	if ($medical_plan_dose_id) {
		$check_unique_q = "SELECT *"
			." FROM `DAY_MEDICINE_DOSES`"
			." WHERE `medical_plan_dose_id`='$medical_plan_dose_id'"
			." AND `day_date`='$day_date'";
		
		$cu_result = $mysqli->query($check_unique_q);
		if ($cu_result->num_rows > 0)
			$unique = false;
	}
	
	if ($unique) {

	# calculate reward
	$reward_q = "SELECT reward"
				." FROM `HEALTH_STATES`"
				." WHERE `id`='$health_state_id'"
				." LIMIT 1";
	
	$result = $mysqli->query($reward_q);

	while ($r = $result->fetch_assoc()) {
		$reward = $r["reward"];
	};

	# find pollen state
	$pollen_state_id = NULL;
	
	$q = "INSERT INTO `DAY_MEDICINE_DOSES` ("
			."`id`, "
			."`reward`, "
			."`time`, "
			."`day_date`, "
			."`child_id`, "
			."`medicine_id`, "
			."`health_state_id`, "
			."`medical_plan_dose_id`, "
			."`pollen_state_id`)"
		." VALUES ("
			."'', "
			."'$reward', "
			."'$time', "
			."'$day_date', "
			."'$child_id', "
			."'$medicine_id', "
			."'$health_state_id', "
			."'$medical_plan_dose_id', "
			."'$pollen_state_id')";
			
    $sqlsuccess = $mysqli->query($q);
			
    $add_reward_q = "UPDATE `CHILDREN`"
                    ." SET `credits` = `credits` + '$reward'"
                    ." WHERE `id`='$child_id'";
    
    $sqlsuccess = $sqlsuccess and $mysqli->query($add_reward_q);

	}
    
	print json_encode(array(
		"sqlsuccess" => $sqlsuccess,
		"post" => $_POST,
		"q" => $q,
		"reward" => $reward,
		"unique" => $unique
	));
	
	$mysqli->close();
?>