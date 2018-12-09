var express = require('express');
var router = express.Router();
var request = require('request')
var bodyParser = require('body-parser')
var axios = require('axios')

router.get('/', function(req, res, next) {
  console.log("here")
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

  // axios.get('http://localhost:8888/jpa/users/get/all-attributes',
  axios.get('http://service-login:8002/jpa/users/get/all-attributes',
  {
  }).then(result=>{
      console.log("Retrieved Data success")
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
