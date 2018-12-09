var express = require('express');
var router = express.Router();
var request = require('request')
/* GET users listing. */
router.get('/', function(req, res, next) {
  console.log("Making request to python");
  request('http://service-search-server:8000/recipes/pythonSearch/a', function (error, response, body) {
  if (!error && response.statusCode == 200) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    res.send(body);
  }
  
})
  
})
module.exports = router;
