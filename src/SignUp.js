import React from 'react';
//import logo from './logo.jpg';
//import {Form, Input, FormGroup, Container, Label} from 'reactstrap';
//import {SingleDatePicker} from 'react-dates';
//import Icon from './icon';
import axios from 'axios';

class SignUp extends React.Component {
    handleSubmit(e) {
        e.preventDefault();
        
        var firstname = this.state.firstname;
        var lastname = this.state.lastname;
        var phoneno = this.state.phoneno;
        var dob = this.state.dob;
        var foodPreference = this.state.foodPreference;
        var displayPicture = this.state.displayPicture;

        axios.post('/expressSignUp/',{
            firstname : firstname,
            lastname : lastname,
            phoneno : phoneno,
            dob : dob,
            foodPreference : foodPreference,
            displayPicture : displayPicture

        }).then(res=>{
            console.log(res)
        });
      };
      handleChangefname(event){
        this.setState({firstname: event.target.value})
      };
      handleChangelname(event){
        this.setState({lastname: event.target.value})      
      };
      handleChangephoneno(event){
        this.setState({phoneno: event.target.value})
      };
      handleChangedob(event){
        this.setState({dob: event.target.value})
      };
      handleChangefoodPreference(event){
        this.setState({foodPreference: event.target.value})
      };
      handleChangedisplayPicture(event){
        this.setState({displayPicture: event.target.value})
      };
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

                <form onSubmit={this.onSubmit}>
                <div className="firstname">
                    <label htmlfor="firstname">First Name :</label>
                    <input type="text" placeholder="firstname"   onChange={this.handleChangefname.bind(this)}/>
                </div>
                <div className="lastname">
                    <label htmlfor="lastname">Last Name :</label>
                    <input type="text" placeholder="lastname"  onChange={this.handleChangelname.bind(this)}/>
                </div>
                <div className="phoneno">
                    <label htmlfor="phoneno">Phone number :</label>
                    <input type="text" placeholder="phoneno"  onChange={this.handleChangephoneno.bind(this)}/>
                </div>
                <div className="dob">
                    <label htmlfor="dob">Date of birth :</label>
                    <input type="text" placeholder="dob"  onChange={this.handleChangedob.bind(this)}/>
                </div>
                <div className="foodPreference">
                    <label htmlfor="foodPreference">Type of food Preferred :</label>
                    <input type="text" placeholder="foodPreference"  onChange={this.handleChangefoodPreference.bind(this)}/>
                </div>
                <div className="displayPicture">
                    <label htmlfor="displayPicture">Upload Display Picture :</label>
                    <input type="text" placeholder="displayPicture"  onChange={this.handleChangedisplayPicture.bind(this)}/>
                </div>
                    <input type="submit" />
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