var express = require('express');
var router = express.Router();
var request = require('request')
var bodyParser = require('body-parser')

router.use(bodyParser.json())
router.use(bodyParser.urlencoded({ extended: true }))
router.post('/', function(req, res, next) {
  console.log(req.body);
  console.log(req.body.passwd);
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  res.send(req.body)
  
})
module.exports = router;
