util = {};
//karotz.buttonListenerId = 0;

// Make a C#-esque .format function (similar to C sprintf) to get cleaner
// string formating, and make loaded strings behave better
String.prototype.format = function() {
    var string = new String(this.toString());
    for (var i=0; i<arguments.length; i++) {
        string = string.replace("{"+i+"}", arguments[i]);
    }
    return string;
};

karotz.enablePlayer = function(callback) {
    karotz.multimedia.play("lock::no", callback);
};

karotz.playSound = function(path, callback) {
    karotz.multimedia.play(path, function(event) {
        if (event == "TERMINATED" || event == "CANCELLED")
            callback();
    });
};

karotz.connectAndStart = function(host, port, callback, data){	
    try{
        karotz.connect(host, port);
    	log("connected");
    	karotz.start(callback, data);
    }catch(err){
    	log(err);
    }
};

karotz.addButtonListener = (function() {
    var i = 0;
    return function(callback) {
        var id = ++i;
        log("Adding button listener with id {0}.".format(id));
        karotz.button.addListener(function(event) {
            if (i == id) {
                i++;
                if (typeof callback === "function")
                    callback(event);
            }
        });
    };
})();

karotz.addRfidListener = (function() {
    var i = 0;
    return function(callback) {
        var id = ++i;
        log("Adding RFID listener with id {0}.".format(id));
        karotz.rfid.addListener(function(event) {
            if (i == id) {
                i++;
                if (typeof callback === "function")
                    callback(event);
            }
        });
    };
})();

util.medColorToKey = function(mc) {
    return {
        "BLUE": "b",
        "PURPLE": "p",
        "ORANGE": "o"
    }[mc];
};

util.stringify = function(obj, depth) {
    var depth = depth || 0;
    if (typeof obj === "object") {
        if (_(obj).all(function(v, k) { return !isNaN(k); })) {
            return "[" + _(obj).map(function(v) {
                return util.stringify(v, depth + 1);
            }).join(", ")+ "]";
        }
        var spacesOuter = (function() {
            var str = "";
            for (var i = 0; i < depth; i++) {
                str += "    ";
            }
            return str;
        })();
        var spacesInner = spacesOuter + "    ";
        return "{\n{0}\n{1}}".format(_(obj).map(function(v, k) {
            return "{0}{1}: {2}".format(spacesInner, k, util.stringify(v, depth+1));
        }).join(",\n"), spacesOuter);
    } else if (typeof obj === "string") {
        return '"{0}"'.format(obj);
    } else if (typeof obj === "function") {
        return "[Function]";
    }
    return obj;
};

util.doseListToManuscript = function(doses) {
    var manuscript = ["1s", "2s"];
    log("{0} = {1}".format("doses", util.stringify(doses)));
    if (_(doses).any(function(d) { return d.medicine_color != _(doses).first().medicine_color; })) {
        manuscript.push("3s");
    }
    for (var i = 0; i < doses.length; i++) {
        var d = doses[i];
        manuscript.push("1{0}".format(util.medColorToKey(d.medicine_color)));
        manuscript.push("2{0}".format(util.medColorToKey(d.medicine_color)));
        if (i + 1 < doses.length && doses[i + 1].medicine_color == d.medicine_color) { // next is the same
            manuscript.push("3{0}2".format(util.medColorToKey(d.medicine_color)));
            manuscript.push("4s");
            manuscript.push("5s");
            i++;
        } else {
            manuscript.push("3{0}1".format(util.medColorToKey(d.medicine_color)));
            if (i + 1 < doses.length) { //there is a next and it is different
                manuscript.push("4s");
                manuscript.push("8s");
            }
        }
    }
    manuscript.push("4s");
    manuscript.push("6s");
    manuscript.push("9s");
    manuscript.push("7s{0}".format(doses.length));
    return manuscript;
};

util.interpretAction = function(action, callback) {
    log("Recieved action = {0}".format(util.stringify(action)));
    var notificationMode = action.hasOwnProperty("action") && action.action == "notification";
        
    /*return function(callback) {*/
    if (action.hasOwnProperty("light")) {
        karotz.led.light(action.light);
    }
    if (notificationMode) {
        karotz.ears.moveRelative(1000, -1000);
        var notifying = true;
        var colorCycle = {
            colors: ["0000FF", "00FF00", "FF0000", "00FF9F", "FFA500", "FFCFAF", "9F00FF", "75FF00", "4FFF68"],
            i: 0,
            next: function() {
                var s = this;
                if (s.i >= s.colors.length) s.i = 0;
                return s.colors[s.i++];
            }
        };
        var pulse = function(event) {
            if (event != "TERMINATED" && event != "CANCELLED") return;
            if (notifying) {
                karotz.led.fade(colorCycle.next(), 2500, pulse);
            }
        };
        pulse("TERMINATED");
    }
    var endFunction = function() {
        log("ending action for sound {0}.".format(action.sound));
        if (notificationMode) {
            karotz.ears.reset();
            notifying = false;
        }
        util.resetLED();
        callback();
    };
    karotz.playSound(action.sound, function() {
        log("done playing {0}".format(action.sound));
        if (action.activator.type == "button") {
            log("adding button listener");
            karotz.addButtonListener(endFunction);
        } else if (action.activator.type == "nanoz") {
            log("adding rfid listener");
            var nanozListener = function(data) {
                log("felt something; data = {0}".format(util.stringify(data)));
                if (data.color == action.activator.color) {
                    endFunction();
                } else {
                    karotz.addRfidListener(nanozListener);
                }
            };
            karotz.addRfidListener(nanozListener);
            //karotz.rfid.addListener(nanozListener);
        } else if (action.activator.type == "wait") {
            log("adding timeout");
            setTimeout(action.activator.duration , endFunction);
        }
    });
};

util.SQLTimeToDate = function(sqltime) {
    var match = sqltime.match(/(\d\d)\:(\d\d)\:(\d\d)/); 
    var time = {
        'hours': match[1], 
        'minutes': match[2], 
        'seconds': match[3]
    };
    var d = new Date();
    d.setSeconds(time.seconds);
    d.setMinutes(time.minutes);
    d.setHours(time.hours);
    return d;
};

util.dateToSQLDay = function(date) {
    return "{0}-{1}-{2}".format(date.getFullYear(), "0{0}".format(date.getMonth() + 1).slice(-2), "0{0}".format(date.getDate()).slice(-2));
};
    
util.resetLED = function() {
    karotz.led.light("4FFF68");
};