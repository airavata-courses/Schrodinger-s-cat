import React from 'react';
//import logo from './logo.jpg';
//import {Form, Input, FormGroup, Container, Label} from 'reactstrap';
//import {SingleDatePicker} from 'react-dates';
//import Icon from './icon';
import axios from 'axios';
import {ToastContainer,toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'
class SignUp extends React.Component {
    handleSubmit(e) {
        e.preventDefault();
        console.log("here")
        var firstname = this.state.firstname;
        var lastname = this.state.lastname;
        var email = this.state.email;
        var username = this.state.username;
        var password = this.state.password;
        var description = this.state.description;
        axios.post('/expressSignup',{
            firstName : firstname,
            lastName : lastname,
            email:  email,
            username : email,
            password : password,
            selfDescription : description,
            recipeId : []
        }).then(res=>{
            this.notify("Signup Successful")
            console.log(res)
        }).catch(error => {
            this.notify("Sign up failed")
        });
      };
      handleChangefname(event){
        this.setState({firstname: event.target.value})
      };
      handleChangelname(event){
        this.setState({lastname: event.target.value})      
      };
      handleChangeemail(event){
        this.setState({email: event.target.value})
      };
      handleChangedescription(event){
        this.setState({description: event.target.value})
      };
      handleChangepassword(event){
        this.setState({password: event.target.value})      
      };
      notify = (message) => toast(message)
    render() {
        return (
            <div>
            {/* <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo" />
                    <h1 className="App-title">foodCode</h1>
                </header>
                <p className="App-intro">
                    All the recipes at one place
                </p>
        </div> */}

                <form onSubmit={this.handleSubmit.bind(this)}>
                <div className="firstname">
                    <label htmlfor="firstname">First Name :</label>
                    <input type="text" placeholder="firstname"   onChange={this.handleChangefname.bind(this)}/>
                </div>
                <div className="lastname">
                    <label htmlfor="lastname">Last Name :</label>
                    <input type="text" placeholder="lastname"  onChange={this.handleChangelname.bind(this)}/>
                </div>

                <div className="description">
                    <label htmlfor="description">Self description (atleast 5 characters):</label>
                    <input type="text" placeholder="description"  onChange={this.handleChangedescription.bind(this)}/>
                </div>
                <div className="email">
                    <label htmlfor="email">Email :</label>
                    <input type="text" placeholder="email"  onChange={this.handleChangeemail.bind(this)}/>
                </div>
                
                <div className="password">
                    <label htmlfor="password">password :</label>
                    <input type="password" placeholder="password"  onChange={this.handleChangepassword.bind(this)}/>
                </div>
                    <input type="submit" />
                    <ToastContainer/>
                </form>
                {/* <div>

                        <SingleDatePicker
                          // showClearDate={true}
                          customInputIcon={
                            <svg className="icon icon-small">
                              <Icon
                                icon="ICON_CALENDER"
                                className="icon icon-large"
                              />
                            </svg>
                          }
                          inputIconPosition="after"
                          small={true}
                          block={false}
                          numberOfMonths={1}
                          date={this.state.date}
                          onDateChange={date => this.handleDateChange(date)}
                          focused={this.state.focused}
                          onFocusChange={({ focused }) =>
                            this.setState({ focused })
                          }
                          openDirection="up"
                          hideKeyboardShortcutsPanel={true}
                        />



 */}
            </div>
        )
    }
  }

  export default SignUp;