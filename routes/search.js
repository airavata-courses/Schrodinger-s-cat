var express = require('express');
var router = express.Router();
var request = require('request')
var bodyParser = require('body-parser')
var axios = require('axios')

// /:searchString/:timeString'
router.get('/:searchString/:timeString', function(req, res, next) {
  console.log("this is fun :"+req.params)
  // console.log("hi "+req.params.searchString+" "+timeString)
  console.log("http://service-search-server:8000/recipes/pythonSearch/"+req.params.searchString+"/"+req.params.timeString)

axios.get("http://service-search-server:8000/recipes/pythonSearch/"+req.params.searchString+"/"+req.params.timeString,{

  }).then(result=>{
    console.log("Retrieved Data success")
    console.log(result.data)
    // console.log(res.data.id)
    // console.log(res.data.token)
    res.send(result.data)
  }).catch(error=>{
    console.log("Error in Data Retrieval")
    console.log(error)
    res.status('404').send('not found')
  });
})
module.exports = router;
