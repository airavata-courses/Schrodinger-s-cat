import React, { Component } from 'react';
import logo from './logo.jpg';
import './App.css';
import {Router, Route, browserHistory, Link} from 'react-router';  
//import {Link} from 'react-router';

import RecipeList from './RecipeList';
import SignUp from './SignUp';


class App extends Component {
  constructor() {
    super();
    this.state = { recipes: [] };
    this.onSubmit = this.handleSubmit.bind(this);
  //  this.routeChange = this.routeChange.bind(this);
  }

  

  handleSignUp(e){
      return(
          <ul>    
              <li>
              </li>
          </ul>
      );
  }
  // routeChange(){
  //   let path = "/SignUp";
  //   this.props.history.push(path);
  //   }
  handleSubmit(e) {
    e.preventDefault();
    var self = this;
    // On submit of the form, send a POST request with the data to the server.
    fetch('/users', { 
        method: 'POST',
        data: {
          name: self.refs.Username,
          job: self.refs.Password
        }
      })
      .then(function(response) {
        return response.json()
      }).then(function(body) {
        console.log(body);
      });
  }

  render() {
    let alpha = this.state.recipes;
    return (
      /*https://code.visualstudio.com/docs/nodejs/reactjs-tutorial*/
      <div className="Header">
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <h1 className="App-title">foodCode</h1>
          </header>
          <p className="App-intro">
            All the recipes at one place
          </p>
        </div>
        <div className="Login">
        </div>
        <div className="Signup">
        </div>
        {/* https://stackoverflow.com/questions/41332643/sending-data-to-database-in-react-js-web-application */}
        
        {/* <nav className="navbar navbar-default">
            <div className="container">
                <div className="navbar-header">
                    <ul className= "nav navbar-nav">
                        <li>Home</li>
                        <li>SignUp</li>    
                    </ul>
                </div>
            </div>
        </nav> */}
        
        <form onSubmit={this.onSubmit}>
          <input type="text" placeholder="User name" ref="Username"/>
          <input type="text" placeholder="Password" ref="Password"/>
          <input type="submit" />
        </form>
        <div className="component-wrapper">
            {/* <button onClick={this.handleSignUp()} className="button">
            Sign Up!
            </button> */}
            {/* <button className="button" onClick={this.routeChange()}>Sign Up!</button> */}
            <button className="button"><Link to ={"/SignUP"}>Sign Up!</Link></button>
         </div>
        <Router history={browserHistory}>
          <Route path={"SignUP"} component={SignUp}/>
        </Router> 
        <div>
          <h1>List</h1> 
          <RecipeList />
        </div>
      </div>
      
    );
  }

}

export default App;
