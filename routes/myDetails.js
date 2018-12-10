var express = require('express');
var router = express.Router();
var request = require('request')
var bodyParser = require('body-parser')
var axios = require('axios')

router.get('/:userId', function(req, res, next) {
  console.log("my details here")
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,X-Auth-Token,Authorization");
  console.log(req.params.userId);
  console.log(req.headers.authorization);

  var config = {
  headers: {'Authorization': req.headers.authorization}
};
  // service-login:8002
  // axios.get('http://localhost:8888/auth/users/get/all-attributes/'+req.params.userId,config,
  axios.get('http://service-login:8002/auth/users/get/all-attributes/'+req.params.userId,config,
  {

  }).then(result=>{
      console.log("Retrieved Data success")
      console.log(res.data)
      // console.log(res.data.token)
      res.send(result.data)
  }).catch(error=>{
    console.log("Error in Data Retrieval")
    console.log(error)
    res.status('404').send('not found')
  });
})

module.exports = router;
