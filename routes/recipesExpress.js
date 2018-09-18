var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  res.json([{
    name: 'Kadhai Paneer'
  },
  {
    name: 'Palak Paneer'
  },
  {
    name: 'Butter Paneer'
  },
  {
    name: 'Matar Paneer'
  },
  {
    name: 'Paneer do pyaza'
  }]);
});
router.get('/search/:searchString', function(req, res, next) {
  console.log("hit here")
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  
});
module.exports = router;
