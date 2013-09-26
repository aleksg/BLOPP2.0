//Distraction:
//wave ears, flash lights and make sound

var Medication = function(blopp) {
    this.blopp = blopp;
    
    this.init = function() {
        var s = this;
    };
    
    this.startMedication = function(medicines) {
        var s = this;
        
        var manuscript = util.doseListToManuscript(medicines);
        
        var executeAction = function() {
            var action = s.blopp.config.actions[manuscript.splice(0,1)[0]];
            if (action !== undefined) {
                util.interpretAction(action, executeAction);
            } else {
                s.endMedication(medicines);
            }
        };
        executeAction();
    };

    // fire the distraction event.
    // temp behavior: count down 10 to 1
    this.fireDistraction = function(callback) {
        var s = this;

        karotz.ears.moveRelative(1000, 1000);
        karotz.playSound(s.blopp.config.soundFiles.countdown, function() {
            s.endDistraction(callback);
        });
    };
    
    // cleanup distraction,
    // save things,
    // fire events that are waiting for distraction to finish
    this.endDistraction = function(callback) {
        var s = this;
        
        karotz.ears.reset();
        karotz.playSound(s.blopp.config.soundFiles.medicationDone, function() {
            callback({ok: true});
        });
    };
    
    this.endMedication = function(medicines/*, callback*/) {
        var s = this;
        
        _(medicines).each(function(m) { s.blopp.repository.logMedicineTaken(m); });
        
        /*util.resetLED();
        s.blopp.repository.logMedicineTaken(medicine, callback);*/
    };
}