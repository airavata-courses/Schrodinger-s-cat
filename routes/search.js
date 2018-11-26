var express = require('express');
var router = express.Router();
var request = require('request')

// /:searchString/:timeString'
router.get('/:searchString/:timeString', function(req, res, next) {

  console.log("hi "+req.params.searchString)
  console.log("http://localhost:8000/recipes/pythonSearch/"+req.params.searchString+"/"+req.params.timeString)
  request("http://localhost:8000/recipes/pythonSearch/"+req.params.searchString+"/"+req.params.timeString, function (error, response, body) {
    if (!error && response.statusCode == 200) {
      res.header("Access-Control-Allow-Origin", "*");
      res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
      // console.log
      res.send(body);
    }
  })
})
module.exports = router;
