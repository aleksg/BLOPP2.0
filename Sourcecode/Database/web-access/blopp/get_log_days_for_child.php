<?php
    # Returns all registered entries for the day
    # for a given child (id) during
    # the given month during the given year.
    
    # returns a list of doses grouped on day_date with an additional
    # health_state_id parameter for each day
    
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
		." WHERE `child_id`='$id'"
		." AND YEAR(`day_date`)='$year'"
		." AND MONTH(`day_date`)='$month'"
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
	
	$statuses_q = 
         "SELECT "
            ."`date`, "
            ."`child_id`, "
            ."`health_state_id`"
        ." FROM `CHILDREN_LOG_DAYS`"
        ." WHERE `child_id`='$id'"
        ." ORDER BY `date` ASC";
               
    $statuses_result = $mysqli->query($statuses_q);
    
    $status_log_days = array();
    if ($statuses_result) {
        while ($r = $statuses_result->fetch_assoc()) {
            $status_log_days[] = $r;
        }
    } else {
        $sqlsuccess = false;
    }
    
    $first_day = $status_log_days[0];
    $count_day = $first_day;
    
    if (strtotime($first_day["date"]) > strtotime("$year-$month-01")) {
        $current_status_id = 1;
    } else {
        # find the day that counts during the start of the month
        $first_of_month_date = strtotime("$year-$month-01");
        foreach ($status_log_days as $day) {
            if (strtotime($day["date"]) > $first_of_month_date)
                break;
            $count_day = $day;
        }
        $current_status_id = $count_day["health_state_id"];
    }
	
	$days = array();
	$now = Time();
	for ($day = 1; $day <= cal_days_in_month(CAL_GREGORIAN, $month, $year) && strtotime("$year-$month-$day") <= $now; $day+=1) {
        $sqldate = Date("Y-m-d", strtotime("$year-$month-$day"));
        foreach ($status_log_days as $status_log_day) {
            if ($status_log_day["date"] == $sqldate) {
                $current_status_id = $status_log_day["health_state_id"];
                break;
            }
        }
        $doses = array();
        foreach ($rows as $dose) {
            if ($dose["day_date"] == $sqldate) {
                $doses[] = $dose;
            }
        }
        $days[] = array(
            'date' => $sqldate,
            'health_state_id' => $current_status_id,
            'doses' => $doses
        );
	}

	print json_encode(array(
		'sqlsuccess' => $sqlsuccess,
		'child_id' => $id,
		'year' => $year,
		'month' => $month,
		'query' => $q,
		'statuses_query' => $statuses_q,
		'days' => $days
	));
	$mysqli->close();
?>