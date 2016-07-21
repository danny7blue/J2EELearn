'use strict'
var s = 'hello';
var b = "Bye";
function greet(name) {
	console.log(s + ',' + name + '!');
}

function bye(name) {
	console.log(b + ',' + name + '!');
}

module.exports = {
	bye: bye,
	greet: greet
};
