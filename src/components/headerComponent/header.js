import React, { Component } from 'react';
import {Button} from "react-bootstrap";
import {
    Link
  } from 'react-router-dom';
import Homepage from './../pages/homePage';

class Header extends Component {
  constructor(props){
    super(props);

    this.LogUserOut=this.LogUserOut.bind(this);
    this.checkLoggedStatus=this.checkLoggedStatus.bind(this);
    
    console.log('in constructor of header: '+sessionStorage.getItem('isLoggedIn'));
    if(sessionStorage.getItem('isLoggedIn') !== null && sessionStorage.getItem('isLoggedIn')){
      this.state = {
        loggedIn: true
      };
    } else {
      this.state = {
        loggedIn: false
      };
    }
  }

  LogUserOut(e) {
    this.setState({
      loggedIn: false,
    });
    if(sessionStorage.getItem('isLoggedIn') !== null && sessionStorage.getItem('isLoggedIn')){
      //console.log(sessionStorage.getItem('isLoggedIn'));
      sessionStorage.setItem('username','Guest User');
      sessionStorage.removeItem('userid');
      sessionStorage.setItem('isLoggedIn',false);
    }
    console.log('I am logged out');
    // this.state.Homepage.state.updateOnLogOut();
  }
  checkLoggedStatus() {
    if(sessionStorage.getItem('isLoggedIn') !== null && sessionStorage.getItem('isLoggedIn') === 'true'){
      //console.log('in checkLoggedStatus 1: '+this.state.loggedIn);
      return true;
    } else {
      //console.log('in checkLoggedStatus 2: '+this.state.loggedIn);
      return false;
    }
    // else {
    //   this.setState({
    //     loggedIn: false,
    //   });
    // }
    console.log('in checkLoggedStatus: '+this.state.loggedIn);
    // return this.state.loggedIn;
  }

  render() {
    return (
      <header>
          {/* Test Header */}
          <div className="logo">
            FoodCode
          </div>
          <nav>
              <ul>
                  <li className="first">
                        <Link to="/Homepage">Recipies</Link>
                        {/* will have home, list of all the recipes */}
                  </li>
                  <li>
                     <Link to="/Recipies">Search</Link>
                      {/* will have the main search functionality */}
                  </li>
                  <li>
                    <Link to="/About">About</Link>
                     {/* will have description about us */}
                  </li>
                  <li> 
                    {/* <div className={() => this.checkLoggedStatus() ? 'hidden' : ''}> */}
                    { this.checkLoggedStatus() ? null : <Link  to="/Login">Login</Link>}
                    
                      
                    {/* </div> */}
                    {/* user will login */}
                  </li>
                  <li className="last">
                   {/* <div >className={this.checkLoggedStatus ? 'hidden' : ''} */}
                   {this.checkLoggedStatus() ? null : <Link to="/Signup">Sign Up</Link>}
                    {/* <Link  to="/Signup">Sign Up</Link> */}
                   {/* </div> */}
                    {/* user will sign up */}
                  </li>
                  <li className="last">
                    {this.checkLoggedStatus() ? <Button
                    block
                    bsSize="large"
                    bsStyle="primary"
                    onClick={(e) => this.LogUserOut(e)}
                  >Sign Out</Button> : null}
                    
                   
                  </li>
              </ul>
          </nav>
      </header>
    );
  }
}

export default Header;
