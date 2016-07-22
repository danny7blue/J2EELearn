var fs = require('fs');
var data = 'Hello, Node.js';
fs.writeFile('output.txt', data, function (err) { 
	if (err) {
		console.log(err);
	} else {
		console.log('ok');
	}
});

var imageData = fs.readFileSync('testImage.jpg');
console.log(imageData);

fs.writeFileSync('outputImage.jpg', imageData);