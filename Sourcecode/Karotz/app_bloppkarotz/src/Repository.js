//Updater:
//fetch updates from the database

function Repository(blopp) {
    this.blopp = blopp;
    this.updaterAlive = true;
    
    this.init = function() {
        var s = this;
        s.medicationPlan = [];
        s.updateTimeout = s.blopp.config.updater.timeout;
        
        s.update();
    };
    
    this.handleMedicalPlan = function(medicalPlan) {
        var s = this;
        var now = new Date();
        
        var dosesToday = _.chain(medicalPlan)
            .map(function(med) { 
                return {
                    time: med.time, 
                    date: util.SQLTimeToDate(med.time)
                }; 
            }).filter(function(med) {
                return med.date - now > 0; //filter out all dates that have been today
            }).value();
        
        if (dosesToday.length > 0) {
            var soonestTime = _(dosesToday).reduce(function(med, memo) {
                return (med.date < memo.date ? med : memo); //find the soonest time
            });
                
            var doses = _(medicalPlan).filter(function(med) {
                return med.time == soonestTime.time;
            });
            
            var date = soonestTime.date;
            
            s.blopp.notification.makeNotification(date, doses);
            
            log("creating notification from update... should overwrite");
            log("notification date: " + date);
            log("now: " + now);
        }
    };
    
    this.logMedicineTaken = function(dose, callback) {
        var s = this;
        
        var url = s.blopp.config.database.accessUrls.registerMedicineTaken;
        
        var postData = {
            "child_id": s.blopp.data.child.id.toString(),
            "medicine_id": dose.id.toString(),
            "time": dose.time,
            "day_date": util.dateToSQLDay(new Date()),
            "health_state_id": dose.health_state_id.toString(),
            "medical_plan_dose_id": dose.id.toString()
        };
        
        log("logging medicine " + dose.medicine_name);
        log("var postData = {0}".format(util.stringify(postData)));
        
        log("url: " + url);
        
        try {        
            var resultstr = http.post(url, postData);
            
            var result = JSON.parse(resultstr);
            
            log("result = {0}".format(util.stringify(result)));
            
            if (result.unique) {
                if (typeof callback === "function") {
                    callback({ok: true, reward: result.reward});
                }
            }
        }
        catch (ex) {
            log("Could not connect to host. Server or internet connection is down.");
            log("Error message: " + ex.message);
        }
    };
    
    this.update = function() {
        var s = this;
        if (!s.updaterAlive) return;
        var id = s.blopp.data.child.id;
        var url = s.blopp.config.database.accessUrls.getDoses.format(id);
        try {
            var before = new Date();
            log("getting url: " + url);
            var datastr = http.get(url);
            var after = new Date();
            
            log("http get took {0} milliseconds!".format(after - before));
            
            log("datastring: " + datastr);
            var medicationPlanData = JSON.parse(datastr);
            
            log("fetched medication plan");
            if (!medicationPlanData.sqlsuccess)
                log("SQL failed!");
            
            s.medicationPlan = medicationPlanData.rows;
            
            s.handleMedicalPlan(s.medicationPlan);
        } catch (ex) {
            log("Could not connect to host. Server or internet connection is down.");
            log("Error message: " + ex.message);
        }
        
        /*
        if (s.blopp.notification.notifying) {
            _(s.blopp.notification.notifyingDoses).each(function(d, i) {
                var id = d.id;
                var url = s.blopp.config.database.accessUrls.doseIsTaken.format(id);
                try {
                    var before = new Date();
                    log("getting url: {0}".format(url))
                    var datastr = http.get(url);
                    var after = new Date();
                    log("http get took {0} milliseconds!".format(after-before));
                    
                    log("datastring: {0}".format(datastr))
                    var doseTakenData = JSON.parse(datastr);
                    
                    if (!doseTakenData.sqlsuccess)
                        log("SQL failed!");
                        
                    var doseTaken = doseTakenData.result;
                    if (doseTaken) {
                        delete s.blopp.notification.notificationDoses[i];
                        if (_(s.blopp.notification.notificationDoses).all(function(e) { return e === undefined; })) {
                            s.blopp.notification.notifying = false;
                        }
                    }
                    
                } catch (ex) {
                    log("Could not connect to host. Server or internet connection is down.");
                    log("Error message: " + ex.message);
                }
            });
        }
        */
        setTimeout(s.updateTimeout * 1000, function() {
            s.update();
        });
    };
}