import React, { Component } from 'react';
import { Panel,Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import {withRouter} from "react-router-dom";
import './../../Assets/css/login.min.css';
import axios from 'axios';
import UserProfile from './../../Assets/Profiles/userProfile';

class Login extends Component {
    constructor(props) {
        super(props);


        this.handleChange = this.handleChange.bind(this);
        this.handleReturn = this.handleReturn.bind(this);

        this.state = {
          email: "",
          password: ""
        };
      }
    
      validateForm() {
        return this.state.email.length > 5 && 
        this.state.password.length > 5;
      }
    
      handleChange = event => {
        this.setState({
          [event.target.id]: event.target.value
        });
      }
    
      handleReturn = event => {
        console.log('Enter pressed with the following details '+ this.state.email);
        console.log('Enter pressed with the following details '+ this.state.password);
        event.preventDefault();

        const uname = this.state.email.slice();
        const pass = this.state.password.slice();
        
        axios.post('/expressLogin/',{
          username : uname,
          password : pass
        }).then(res=>{
            //console.log(res.data.username)
            console.log("Login Success..!");
            console.log('redirecting to home page');
            sessionStorage.setItem('username',res.data.username);
            sessionStorage.setItem('userId',res.data.id);
            sessionStorage.setItem('isLoggedIn',true);
            this.props.history.push({
              pathname: '/Homepage/'
            });
            //UserProfile.setUserId(res.data.id);
            //UserProfile.setUserName(res.data.username);
            // this.props.history.push({
            //   pathname: '/',
            //   state: { uname: uname }
            // });
        }).catch(error =>{
            alert("Login Failed")
        });

      }

  render() {
    return (
      <div>
      <Panel>
      <Panel.Heading>
      <Panel.Title componentClass="h1">Login</Panel.Title>
      </Panel.Heading>
        <Panel.Body>
          <div className="Login">
            <form onSubmit={this.handleReturn}>
              <FormGroup controlId="email" bsSize="large">
                <ControlLabel>Email</ControlLabel>
                <FormControl
                  autoFocus
                  type="email"
                  value={this.state.email}
                  onChange={this.handleChange}
                />
              </FormGroup>
              <FormGroup controlId="password" bsSize="large">
                <ControlLabel>Password</ControlLabel>
                <FormControl
                  value={this.state.password}
                  onChange={this.handleChange}
                  type="password"
                />
              </FormGroup>
              <Button
                block
                bsSize="large"
                bsStyle="primary"
                disabled={!this.validateForm()}
                type="submit"
              >
                Login
              </Button>
            </form>
          </div>
        </Panel.Body>
      </Panel>
      </div>
    );
  }
}

export default withRouter(Login);
