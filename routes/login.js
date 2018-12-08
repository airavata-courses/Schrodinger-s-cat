var express = require('express');
var router = express.Router();
var request = require('request')
var bodyParser = require('body-parser')
var axios = require('axios')
router.use(bodyParser.json())
router.use(bodyParser.urlencoded({ extended: true }))
router.post('/', function(req, res, next) {
  console.log("here")
  var reqBody=req.body.username
  console.log(req.body.username)
  console.log(req.body.password)
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

  axios.post('http://localhost:8888/auth/users/logmein',
  {
    username : req.body.username,
    password : req.body.password
  }).then(result=>{
      console.log("Login success")
      // console.log(res.data.id)
      // console.log(res.data.token)
      res.send(result.data)
  }).catch(error=>{
    console.log("Error here")
    console.log(error)
    res.status('404').send('not found')
  });
  // request.post({
  //   headers: {'content-type' : 'application/x-www-form-urlencoded'},
  //   url:     'http://192.168.0.12/auth/users/logmein',
  //   body: {"username":"pswargam",
  //           "password":"asdfjkl;'"}
  // }, function(error, response, body){
  //   console.log(body);
  // });


})

module.exports = router;
