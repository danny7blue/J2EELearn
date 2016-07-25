var server = require('./Server');
var router = require("./Router");
var requestHandler = require("./requestHandler");

var handle = {};
handle["/"] = requestHandler.start;
handle["/start"] = requestHandler.start;
handle["/upload"] = requestHandler.start;
server.start(router.route, handle);