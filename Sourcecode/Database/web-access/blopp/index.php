<!DOCTYPE html>
<html>
<head>
    <title>BLOPP</title>
</head>
<body>
    <?php
        include 'dbconfig.php';
        
        $mysqli = new mysqli($dburl, $dbuser, $dbpwd, $dbname);
        $q = 'SELECT id, label FROM HEALTH_STATES';
        
        $result = $mysqli->query($q);
        $states = array();
        if ($result) {
            while ($r = $result->fetch_assoc()) {
                $states[] = $r;
            }
        }
        
        $children_q = "SELECT id, name, pers_num FROM `CHILDREN` LIMIT 100";
        
        $result = $mysqli->query($children_q);
        $children = array();
        if ($result) {
            while ($r = $result->fetch_assoc()) {
                $children[] = $r;
            }
        }
    ?>
    <form method="post" action="add_child.php">
        <fieldset>
            <legend>Add child</legend>
            <p>
                Name<br />
                <input type="text" id="name-input" name="name" />
            </p>
            <p>
                Pers. num<br />
                <input type="text" id="persnum-input" name="persnum" />
            </p>
            <p>
                Available States<br />
                <?php
                    foreach ($states as $state) {
                        print ' <input '
                            .'type="checkbox" '
                            .'name="states[]" '
                            .'class="state-checkbox" '
                            .'value="'.$state['id'].'" /> '
                            .$state['label']
                            .'<br />';
                    }
                ?>
            </p>
            <input type="submit" value="Create" />
        </fieldset>
    </form>
    
    <fieldset>
        <legend>Get Doses For Current Child</legend>
        <p>
            Choose child<br />
            <select id="getDosesChildren">
                <?php
                    foreach ($children as $child) {
                        print '<option '
                                .'value="'.$child['id'].'" >'
                                .$child['name'].' ('.$child['pers_num'].')'
                                .'</option>';
                    }
                ?>
            </select>
        </p>
        <button id="getDosesBtn">Fetch</button>
        <p>
            Doses<br />
            <div id="getDosesField"></div>
        </p>
    </fieldset>
    
    <fieldset>
        <legend>Get Child State</legend>
        <p>
            Choose child<br />
            <select id="getStateChildren">
                <?php
                    foreach ($children as $child) {
                        print '<option '
                                .'value="'.$child['id'].'" >'
                                .$child['name'].' ('.$child['pers_num'].')'
                                .'</option>';
                    }
                ?>
            </select>
        </p>
        <button id="getStateBtn">Fetch</button>
        <p>
            State</br>
            <div id="getStateField"></div>
        <p>
    </fieldset>
    
    
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script src="underscore-min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            // helper function for listing all props in an object
            var listProps = function(obj, divider) {
                var divider = divider || '<br />';
                return _(obj).map(function(v, k) {
                        return '<em>' + k + ':</em> ' + v;
                    }).join(divider);
            };
                
            // add listener to get doses btn
            $('#getDosesBtn').click(function() {
                var id = $('#getDosesChildren').val();
                var url='get_doses_for_current_state.php?child_id=' + id;
                
                $.get(url, function(json) {
                    var $field = $('#getDosesField');
                    $field.empty();
                    _(json.rows).each(function(d) {
                        $field.append('<div><hr />' + listProps(d) + '</div>');
                    });
                });
            });
            
            // add listener to get child state
            $('#getStateBtn').click(function() {
                var id = $('#getStateChildren').val();
                var url = 'get_child_state.php?child_id=' + id;
                
                $.get(url, function(json) {
                    var $field = $('#getStateField');
                    $field.empty();
                    $field.append('<div><hr />' + listProps(json.state) + '</div>');
                });
            });
        });
    </script>
</body>
</html>