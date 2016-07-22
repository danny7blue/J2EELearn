'use strict'
var fs = require('fs');
fs.readFile('sample.txt', 'utf-8', function(err, data) {
	if(err) {
		console.log(err);
	} else {
		console.log(data);
	}
})

fs.readFile('testImage.jpg', function(err, data) {
	if (err) {
		console.log(err);
	} else {
		var text = data.toString();
//		console.log(text);
		var buf = new Buffer(text, 'utf-8');
		console.log(buf);
		console.log(data);
		console.log(data.length + ' bytes');
	}
});

try {
	var syncData = fs.readFileSync('sample1.txt', 'utf-8');
	console.log(syncData);
} catch(err) {
//	console.log(err);
	console.log('Sorry for your inconvience');
}

fs.stat('sample.txt', function(err, stat) {
	if (err) {
		console.log(err);
	} else {
		console.log('isFile: ' + stat.isFile());
		console.log('isDirectory: ' + stat.isDirectory());
		if (stat.isFile()) {
			console.log('size: ' + stat.size);
			console.log('birth time: ' + stat.birthtime);
			console.log('modified time: ' + stat.mtime);
		}
	}
}
)

var stat = fs.statSync('sample.txt');
console.log(stat.isFile);
