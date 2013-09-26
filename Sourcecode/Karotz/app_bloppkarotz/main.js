//Main file for the Karotz program

include('lib/underscore.js');
include('util.js')
include('src/Blopp.js');
include('src/Repository.js');
include('src/Medication.js');
include('src/Notification.js');

var karotz_ip = '192.168.1.4';

// load config and data
var configPath = 'res/config.json';
var childDataPath = 'data/child.json';

var exitFunction = function(event) {
    if ((event == 'CANCELLED') || (event == 'TERMINATED')) {
        exit();
    }
    return true;
}

var onKarotzConnect = function(data) {
    // create main object
    var blopp = new Blopp(file.read(configPath), file.read(childDataPath));
    blopp.init();
}

karotz.connectAndStart(karotz_ip, 9123, onKarotzConnect, {});