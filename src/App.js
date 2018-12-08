import React, { Component } from 'react';
import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom';

//components
import Header from './components/headerComponent/header';
import Footer from './components/footerComponent/footer';
import Homepage from './components/pages/homePage';
import Recipies from './components/pages/recipies';
import About from './components/pages/about';
import Login from './components/pages/login';
import Signup from './components/pages/signup';
import Addrecipe from './components/pages/addrecipe';
import Mydetails from './components/pages/mydetails';
import Allusers from './components/pages/allusers';



// include CSS
import './Assets/css/default.min.css';

class App extends Component {
  render() {
    return (

      <Router>
        <div className="App">
        <Header /> 
        <Route exact path='/' component={Homepage} />
        <Route exact path='/Homepage' component={Homepage} />
        <Route exact path='/Recipies' component={Recipies} />
        <Route exact path='/About' component={About} />
        <Route exact path='/Addrecipe' component={Addrecipe} />
        <Route exact path='/Mydetails' component={Mydetails} />
        <Route exact path='/Allusers' component={Allusers} />
        <Route exact path='/Login' component={Login} />
        <Route exact path='/Signup' component={Signup} />
        {/* <Footer /> */}
      </div>
      </Router>

      
    );
  }
}

export default App;
