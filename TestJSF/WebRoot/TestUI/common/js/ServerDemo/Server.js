/**
 * Created by Danny on 2016/7/25.
 */
var http = require("http");
var url = require("url"
);
function start(route, handle) {
    var server = http.createServer(function (request, response) {
        var pathname = url.parse(request.url).pathname;
        console.log("Request for" + pathname + " received") ;

        route(handle, pathname);

        response.writeHead(200, {"Content-Type": "text/plain"});
        response.write("Hello World");
        response.end();
    });
    server.listen(8889);
    console.log("Server is running at http://127.0.0.1:8889");
};

exports.start = start;