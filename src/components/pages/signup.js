import React, { Component } from 'react';
import { Panel, Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import './../../Assets/css/login.min.css';
import axios from 'axios';
import {withRouter} from "react-router-dom";
class Signup extends Component {

    constructor(props) {
        super(props);
    
        this.state = {
          fname: "",
          lname: "",
          desc: "", 
          email: "",
          password: ""
        };
      }
    
      validateForm() {
        return this.state.email.length > 5 && 
        this.state.password.length > 5 && 
        this.state.fname.length > 5 &&
        this.state.lname.length > 5 &&
        this.state.desc.length > 10;
      }
    
      handleChange = event => {
        this.setState({
          [event.target.id]: event.target.value
        });
      }
    
      handleSubmit = event => {
        event.preventDefault();
        
        const firstName = this.state.fname.slice();
        const lastName = this.state.lname.slice();
        const email = this.state.email.slice();
        const password = this.state.password.slice();
        const selfDescription = this.state.desc.slice();
        //const lastName = this.state.password.slice();
        axios.post('http://service-node-server:4000/expressSignup',{
          firstName : firstName,
          lastName : lastName,
          email:  email,
          username : email,
          password : password,
          selfDescription : selfDescription,
          recipeId : []
        }).then(res=>{
            //console.log(res.data.username)
            console.log("Sign Up Success..!");
            console.log('redirecting to login page');
            this.props.history.push('/Login/');
        }).catch(error =>{
            alert("Sign Up Failed")
        });
      }

  render() {
    return (
        <div>
          <Panel>
          <Panel.Heading>
          <Panel.Title componentClass="h1">Sign Up</Panel.Title>
          </Panel.Heading>
          <Panel.Body>
            <div className="Signup">            
            <form onSubmit={this.handleSubmit}>
            <FormGroup controlId="fname" bsSize="large">
                <ControlLabel>First Name</ControlLabel>
                
                <FormControl
                  autoFocus
                  type="text"
                  value={this.state.fname}
                  onChange={this.handleChange}
                  placeholder="Min 5 characters"
                />
                  
              </FormGroup>
              <FormGroup controlId="lname" bsSize="large">
                <ControlLabel>Last Name</ControlLabel>
                <FormControl
                  autoFocus
                  type="text"
                  value={this.state.lname}
                  onChange={this.handleChange}
                  placeholder="Min 5 characters"
                />
              </FormGroup>
              <FormGroup controlId="desc" bsSize="large">
                <ControlLabel>Description</ControlLabel>
                <FormControl
                  autoFocus
                  type="text"
                  value={this.state.desc}
                  onChange={this.handleChange}
                  placeholder="Min 10 characters"
                />
              </FormGroup>
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
                Sign Up!
              </Button>
            </form>
          </div>
          </Panel.Body>
        </Panel>
      </div>
    );
  }
}

export default withRouter(Signup);
