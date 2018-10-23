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
    console.log("This is receiver")
    amqp.connect('amqp://localhost', function (err, conn) {
        conn.createChannel(function (err, ch) {
            var q = 'hello';

            ch.assertQueue(q, {
                durable: false
            });
            ch.consume(q, function (msg) {
                console.log(" [x] Received %s", msg.content.toString());
                
            }, {
                noAck: true
            });
        });
    });
    
    
})
module.exports = router;