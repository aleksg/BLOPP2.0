//Blopp:
//main object,
//various help functions

function Blopp(configFile, childDataFile) {
    this.configFile = configFile;
    this.childDataFile = childDataFile;
    
    this.init = function () {
        var s = this;
        
        util.resetLED();
        log("date: {0}".format(new Date().toString()));
        s.config = s.parseConfigFile(s.configFile);
        s.data = s.parseDataFiles({child: childDataFile});
        log("loaded config and data");
        
        s.notification = new Notification(s);
        s.notification.init();
        log("inited notification");
        
        s.medication = new Medication(s);
        s.medication.init();
        log("initied distraction");
        
        s.repository = new Repository(s);
        s.repository.init();
        log("inited repo");
        
        /*karotz.tts.start("B L O P P start", "en", function(event) {
            if (event != "TERMINATED") return;
            
            s.notification = new Notification(s);
            s.notification.init();
            log("inited notification");
            
            s.medication = new Medication(s);
            s.medication.init();
            log("initied distraction");
            
            s.repository = new Repository(s);
            s.repository.init();
            log("inited repo");
        });*/
    };
    
    this.parseConfigFile = function(configFile) {
        var config = JSON.parse(configFile.text);
        return config;
    };
    
    this.parseDataFiles = function(files) {
        var childData = JSON.parse(files.child.text);
        return {child: childData};
    };
}