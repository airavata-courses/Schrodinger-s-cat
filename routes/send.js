var express = require('express');
var router = express.Router();
var request = require('request');
var bodyParser = require('body-parser');
var amqp = require('amqplib/callback_api');
// var axios = require('axios')
// router.use(bodyParser.json())
// router.use(bodyParser.urlencoded({
//     extended: true
// }))

router.get('/', function (req, res, next) {
    console.log("This is Sender")
    amqp.connect('amqp://localhost', function(err, conn) {
        conn.createChannel(function(err, ch) {
          var q = 'hello';
      
          ch.assertQueue(q, {durable: false});
          // Note: on Node 6 Buffer.from(msg) should be used
          ch.sendToQueue(q, new Buffer('Hello World!'));
          console.log(" [x] Sent 'Hello World!'");
          setTimeout(function() { conn.close();}, 500);
          return;
        });
        
      });
      
})
module.exports = router;