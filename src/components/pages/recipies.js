import React, { Component } from 'react';
import { Panel,Button, ButtonToolbar,DropdownButton, FormGroup, MenuItem, ControlLabel,HelpBlock,FormControl } from "react-bootstrap";
import axios from 'axios';
import homePage from './homePage';
function RenderDropDownButton(props) {
  // const BUTTONS = 'Primary';
  //alert('props.value: '+props.value);
  return (
    <div>
      <ButtonToolbar>
        <DropdownButton
          bsStyle="primary"
          title={props.value}
          noCaret
          id="dropdown-no-caret"
          onSelect={props.onSelect}
        >
        <MenuItem eventKey="1" >15 mins</MenuItem>
        <MenuItem eventKey="2" >30 - 45 mins</MenuItem>
        <MenuItem eventKey="3" >Over 45 mins</MenuItem>
        </DropdownButton>
      </ButtonToolbar>
    </div>
  );
}

class Recipies extends Component {

  constructor(props, context) {
    super(props, context);

    this.handleChange = this.handleChange.bind(this);
    this.handleReturn = this.handleReturn.bind(this);
    this.bindTimeValue = this.bindTimeValue.bind(this);
    this.navigateToHomepage = this.navigateToHomepage.bind(this);

    this.state = {
      value: '',
      defaultTimeSelect: '2'
    };
  }
  
  getValidationState() {
    const length = this.state.value.length;
    if (length > 1) return 'success';
    else if (length > 0) return 'error';
    return null;
  }

  validateForm() {
    console.log('value of validation state is: '+this.getValidationState());
    // console.log(this.getValidationState() === 'success' ? true : false);
    return this.getValidationState() === 'success' ? true : false;
  }

  handleChange(e) {
    this.setState({ value: e.target.value });
  }
  navigateToHomepage() {
    this.props.history.push('/Homepage');
  }
  handleReturn(e){ //send this detail to the server
    e.preventDefault();
    console.log('Enter pressed with the following details '+ this.state.value + ' and the time is ' +this.state.defaultTimeSelect );

    const items = this.state.value.slice();
    const time = this.state.defaultTimeSelect.slice();

    axios({
      method: 'get',
      url: 'http://129.114.16.182:31003/search/'+items+'/'+time,
      headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
    }).then(res=>{
      console.log('redirecting to home page');
      console.log(res.data);
      sessionStorage.setItem('recipes',JSON.stringify(res.data));
      sessionStorage.setItem('hasSearched',true);
      console.log(res.data);
      this.navigateToHomepage();
    }).catch(error =>{
        alert("Search Failed")
    });
    
  }
  bindTimeValue(e){
    console.log('Entered time value is: '+e);
    this.setState({defaultTimeSelect:e});
    console.log('Entered time value of state is: '+this.state.defaultTimeSelect);
  }
  
  render() {
    return (
      <div>
        <Panel>
        <Panel.Heading>
        <Panel.Title componentClass="h1">Search Recipes Here</Panel.Title>
        </Panel.Heading>
        <Panel.Body>
        <div className="Search">   
        <form onSubmit={(e) => this.handleReturn(e)}>
          <FormGroup
            controlId="formBasicText"
            validationState={this.getValidationState()}>
            <ControlLabel>Enter Recipe Name (with spaces) or Ingridents with ' , '. Then hit enter</ControlLabel>
            <FormControl
              type="text"
              value={this.state.value}
              placeholder="Minimum 5 characters"
              onChange={this.handleChange}/>
            <FormControl.Feedback />
            <HelpBlock>The more the ingridents, the better the search result.</HelpBlock>
            <RenderDropDownButton value="Time to Cook" onSelect={(e) => this.bindTimeValue(e)}/>
            <center>
            <Button
              bsSize="large"
              bsStyle="primary"
              disabled={!this.validateForm()}
              type="submit">
              Search
            </Button>
            </center>
            </FormGroup>
          </form>
          </div>
          </Panel.Body>
        </Panel>
      </div>
    );
  }
  
}

export default Recipies;
