var express = require('express');
var router = express.Router();
var request = require('request')
var bodyParser = require('body-parser')
var axios = require('axios')
router.use(bodyParser.json())
router.use(bodyParser.urlencoded({ extended: true }))

router.post('/', function(req, res, next) {
    console.log(req.body)
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

    // service-login:8002
    // axios.post('http://localhost:8888/jpa/users/register',
    axios.post('http://service-login:8002/jpa/users/register',

    req.body).then(result=>{
      console.log("result success for signup")
        console.log(result.data)
        res.send(result.data[0])
    }).catch(error=>{
      console.log(error)
      res.status('404').send('not found')
    });
  })
  module.exports = router;
