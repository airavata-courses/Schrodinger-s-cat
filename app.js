var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/recipesExpress');
var searchRouter = require('./routes/search');
var loginRouter = require('./routes/login');
var logoutRouter = require('./routes/logout');
var myDetailsRouter = require('./routes/myDetails');
var allUserDetailsRouter = require('./routes/allUserDetails');
var addRecipeRouter = require('./routes/addRecipe');
var app = express();
var signupRouter = require('./routes/signup')
var receiveAmqp = require('./routes/receive')
var sendAmqp = require('./routes/send')
// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/recipesExpress', usersRouter);
app.use('/search/',searchRouter);
app.use('/expressLogin/', loginRouter);
app.use('/expressLogout/', logoutRouter);
app.use('/expressAllUserDetails/', allUserDetailsRouter);
app.use('/expressMyDetails/', myDetailsRouter);
app.use('/expressAddRecipe/', addRecipeRouter);
app.use('/expressSignup/', signupRouter);
app.use('/startreceive/',receiveAmqp);
app.use('/startsend/',sendAmqp);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// Constants
const PORT = 4000;
const HOST = '0.0.0.0';
app.listen(PORT, HOST);
// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
