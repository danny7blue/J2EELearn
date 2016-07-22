'use strict'
var fs = require('fs');
var rs = fs.createReadStream('demo.txt');
var ws = fs.createWriteStream('demo_copy.txt');

rs.pipe(ws);