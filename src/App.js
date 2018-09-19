import React, { Component } from 'react';
import logo from './logo.jpg';
import './App.css';
import axios from 'axios';
import {Router, Route, browserHistory, Link} from 'react-router';  
//import {Link} from 'react-router';

import RecipeList from './RecipeList';
import SignUp from './SignUp';


class App extends Component {
  constructor() {
    super();
    this.state = { recipes: [],
    uname:'',
    passwd:''
   };
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
    
    var name = this.state.uname;
    var pass = this.state.passwd;
    
    axios.post('/expressLogin/',{
      username : name,
      passwd : pass
    }).then(res=>{
        console.log(res)
    });
  };
  handleChangeUname(event){
    this.setState({uname: event.target.value})
    
  };
  handleChangePasswd(event){
    this.setState({passwd: event.target.value})
    
  };
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
          <input type="text" placeholder="User name"   onChange={this.handleChangeUname.bind(this)}/>
          <input type="text" placeholder="Password"  onChange={this.handleChangePasswd.bind(this)}/>
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
