/**
 * Created by Danny on 2016/7/25.
 */
'use strict'
const crypto = require('crypto');
const hash = crypto.createHash('md5');
hash.update('Hello, world');
hash.update('Hello, nodeJs');
console.log(hash.digest('hex'));
