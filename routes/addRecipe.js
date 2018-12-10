var express = require('express');
var router = express.Router();
var request = require('request')
var bodyParser = require('body-parser')
var axios = require('axios')

router.use(bodyParser.json())
router.use(bodyParser.urlencoded({ extended: true }))
router.post('/', function(req, res, next) {

  // var reqBody=req.body.username
  // console.log(req.body.username)
  // console.log(req.body.password)
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

  var username = req.body.username;
  var madeby = req.body.madeby;
  var recipeName = req.body.name;
  var ingredients = req.body.ingredients;
  var description = req.body.description;
  var timeTaken = req.body.timeTaken;
  var genre = req.body.genre;
  var myToken = req.body.myToken;

  console.log(recipeName);
  console.log(description);
  console.log(madeby);
  console.log(genre);
  console.log(ingredients);
  console.log(timeTaken);
  console.log(" ");

var config = {
  headers: {'Authorization': myToken}
  };


console.log(JSON.parse(Object.keys(req.body)))
var jsonData = JSON.parse(Object.keys(req.body));

var body = {
  name: jsonData.name,
  description: jsonData.description,
  madeBy:jsonData.madeby,
  genre:jsonData.genre,
  ingredients:jsonData.ingredients,
  timeTaken:jsonData.timeTaken
}

// url: 'http://localhost:8000/recipes/addRecipe/',
// url: "http://localhost:8888/auth/users/create/"+madeby+"/save-posts",
  axios({
    method: 'post',
    url: 'http://service-search-server:8000/recipes/addRecipe/',
    data: body
  }).then(result=>{
      console.log("Adding Recipe success")
      var recipeId = result.data;


      axios({
        method: 'POST',
        url: "http://service-login:8002/auth/users/create/"+madeby+"/save-posts",
        headers: { Authorization: jsonData.myToken},
        data: { id:recipeId,
        user:{email:jsonData.username} }
      }).then(result=>{
          console.log("Login success")
          res.send(result.data)

      }).catch(error=>{
        console.log("Error here")
        console.log(error)
        res.status('404').send('not found')
      });
  }).catch(error=>{
    console.log("Error here")
    console.log(error)
    res.status('404').send('not found')
  });


})

module.exports = router;


// axios.post("http://localhost:8888/auth/users/create/"+madeby+"/save-posts",{headers: config},
// {
//
// })
