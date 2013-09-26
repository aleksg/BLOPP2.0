//Notification:
//wave ears, flash lights and make sound


var Notification = function(blopp) {
    this.blopp = blopp;

    this.init = function() {
        var s = this;
        s.currentTimeoutId = 0;
        s.notifying = false;
        s.notificationDoses = [];
    };
    
    // create a notification event,
    // args: timems (date expressed as ms from the epoch)
    this.makeNotification = function(datems, doses) {
        var s = this;
        
        var timeoutId = ++s.currentTimeoutId;
        
        var notificationTimeout = datems - (new Date()).getTime();
        
        if (notificationTimeout > 0) {
            setTimeout(notificationTimeout, function() {
                if (s.currentTimeoutId != timeoutId) return;
                s.notificationDoses = doses;
                s.blopp.medication.startMedication(s.notificationDoses);
            });
        }
    };
}