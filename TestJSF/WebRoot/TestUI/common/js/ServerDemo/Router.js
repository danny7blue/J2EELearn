/**
 * Created by Danny on 2016/7/25.
 */
function route(handle, pathname) {
    console.log("About to route a request for " + pathname);
    if(typeof handle[pathname] === "function") {
        handle[pathname]();
    } else {
        console.log("No request handler found for " + pathname);
    }
}

exports.route = route;