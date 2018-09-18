import React from 'react';
//import {Form, Input, FormGroup, Container, Label} from 'reactstrap';
//import {SingleDatePicker} from 'react-dates';
//import Icon from './icon';

class SignUp extends React.Component {
    render() {
        return (
            <div className={SignUp}>
               <form onSubmit={this.onSubmit}>
                <div>
                    <label htmlfor="firstName">First Name :</label>
                    <input type="text" id="firstName" />
                </div>
                <div>
                    <label htmlfor="lastName">Last Name :</label>
                    <input type="text" id="lastName" />
                </div>
                <div>
                    <label htmlfor="phoneno">Phone number :</label>
                    <input type="text" id="phoneno" />
                </div>
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
                <div>

                    <label htmlfor="dob">Date of birth :</label>
                    <input type="text" id="dob" />
                </div>
                <div>
                    <label htmlfor="foodPreference">Type of food Preferred :</label>
                    <input type="text" id="dob" />
                </div>
                <div>
                    <label htmlfor="displayPicture">Upload Display Picture :</label>
                    <input type="text" id="displayPicture" />
                </div>
                <div>
                    <label htmlfor="firstName">First Name</label>
                    <input type="text" id="firstName" />
                </div>
            </form>
            </div>
        )
    }
  }

  export default SignUp;