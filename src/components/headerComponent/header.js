import React, { Component } from 'react';
import {Button} from "react-bootstrap";
import {
    Link
  } from 'react-router-dom';
import axios from 'axios';
import Homepage from './../pages/homePage';
import {withRouter} from "react-router-dom";

class Header extends Component {
  constructor(props,context){
    super(props,context);

    this.LogUserOut=this.LogUserOut.bind(this);
    this.checkLoggedStatus=this.checkLoggedStatus.bind(this);
    this.retieveAllUsers=this.retieveAllUsers.bind(this);
    this.retievemyDetails=this.retievemyDetails.bind(this);

    console.log('in constructor of header: '+sessionStorage.getItem('isLoggedIn'));
    if(sessionStorage.getItem('isLoggedIn') !== null && sessionStorage.getItem('isLoggedIn')){
      this.state = {
        loggedIn: true,
        uname: sessionStorage.getItem('username'),
        tk: sessionStorage.getItem('token'),
        uid: sessionStorage.getItem('userId'),
      };
    } else {
      this.state = {
        loggedIn: false,
        uname:"Guest user",
        tk: "",
        uid: ""
      };
    }
  }

  clearAllData(e){
    sessionStorage.setItem('hasSearched',false)
    sessionStorage.setItem('recipes','false')
    this.props.history.push({
      pathname: '/Homepage/'
    });
  }

  retieveAllUsers(e){
    // retrieve All users data.
    axios.get('/expressAllUserDetails/',{
    }).then(res=>{
      
      console.log(res.data);
      sessionStorage.setItem('allUserDetails',JSON.stringify(res.data));
      // sessionStorage.setItem('hasSearched',true);
      this.props.history.push({
        pathname: '/Allusers/'
      });
      console.log('redirecting to User details page');
    }).catch(error =>{
        alert("Search Failed")
    });
    e.preventDefault();
  }

  retievemyDetails(e){
    if(sessionStorage.getItem('isLoggedIn') !== null && sessionStorage.getItem('isLoggedIn') === 'true'){
      const userId = sessionStorage.getItem('userId');
      const userToken = sessionStorage.getItem('token');
      console.log(userId);
      // console.log(userToken);
      console.log("getting my details");
      var customConfig = {
        headers: {'Authorization': userToken}
      };
      axios.get('/expressMyDetails/'+userId,customConfig,{
        // id : userId
      }).then(res=>{
        // console.log(res.data);
        sessionStorage.setItem('myDetails',JSON.stringify(res.data));
        // sessionStorage.setItem('hasSearched',true);
        this.props.history.push({
          pathname: '/Mydetails/'
        });
        console.log(sessionStorage.getItem('myDetails'));
        console.log('redirecting to My details page');
      }).catch(error =>{
          alert("Search for My Details Failed")
      });
      e.preventDefault();
    }
  }

  LogUserOut(e) {
    //e.preventDefault();

    if(sessionStorage.getItem('isLoggedIn') !== null && sessionStorage.getItem('isLoggedIn')){
      // invalidate session at login auth service
      console.log('remove user session');
      // const uname2 = this.state.uname.slice();
      // console.log('about to send logout post request');
      // console.log("uame: "+uname2);

      // axios.post('http://localhost:8888/auth/users/logmeout',{
      //   username : uname2
      // }).then(res=>{
      //     //console.log(res.data.username)
      //     console.log("Logout Success..!");
      //     console.log('redirecting to home page');
      //     sessionStorage.setItem('username','Guest User');
      //     sessionStorage.removeItem('userid');
      //     sessionStorage.setItem('isLoggedIn',false);
      //     console.log('Sent logout post request');
      //     this.props.history.push({
      //       pathname: '/Homepage/'
      //     });
      // }).catch(error =>{
      //     alert("Logout Failed")
      // });
      // this.setState({
      //   loggedIn: false,
      //   uname: "Guest user"
      // });
      this.setState({
          loggedIn: false,
          uname: "Guest user",
          tk: "",
          uid: ""
        });
      sessionStorage.setItem('username','Guest User');
      sessionStorage.removeItem('userid');
      sessionStorage.removeItem('recipes');
      sessionStorage.setItem('token',"");
      sessionStorage.removeItem('hasSearched');
      sessionStorage.removeItem('myDetails');
      sessionStorage.setItem('hasSearched',false);
      sessionStorage.setItem('isLoggedIn',false);
      // this.props.history.push({
      //   pathname: '/Homepage/'
      // });
    }
    console.log('I am logged out');
    this.props.history.push('/Homepage/')

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
                <li>
                    {this.checkLoggedStatus() ? <Link to="/Mydetails" onClick={(e) => this.retievemyDetails(e)}>My Details</Link> : null}
                </li>
                <li className="first">
                    <Link to="/AllUsers" onClick={(e) => this.retieveAllUsers(e)} >All Users</Link>
                  </li>
                  <li>
                        <Link to="/Homepage" onClick={(e) => this.clearAllData(e)} >Homepage</Link>
                        {/* <Link to="/Homepage" >Homepage</Link> */}
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
                  <li>
                  {this.checkLoggedStatus() ? <Link to="/Addrecipe">Add Recipe</Link> : null}
                  </li>
                  <li className="last">
                    {this.checkLoggedStatus() ?
                    <Button
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

export default withRouter(Header);
