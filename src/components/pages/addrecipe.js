import React, { Component } from 'react';
import { Panel,Button, ButtonToolbar,DropdownButton, FormGroup, MenuItem, ControlLabel,HelpBlock,FormControl } from "react-bootstrap";
import axios from 'axios';
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

class Addrecipe extends Component {

  constructor(props, context) {
    super(props, context);

    this.handleChangeTitle = this.handleChangeTitle.bind(this);
    this.handleChangeIngridents = this.handleChangeIngridents.bind(this);
    this.handleChangeDesc = this.handleChangeDesc.bind(this);
    this.handleReturn = this.handleReturn.bind(this);
    this.handleChangeTime = this.handleChangeTime.bind(this);
    this.bindTimeValue = this.bindTimeValue.bind(this);
    this.handleChangeGenre = this.handleChangeGenre.bind(this);

    if(sessionStorage.getItem('isLoggedIn') !== null && sessionStorage.getItem('isLoggedIn')){
      this.state = {
        username: sessionStorage.getItem('username'),
        userId: sessionStorage.getItem('userId'),
        recipeTitle: '',
        recipeIngridents: '',
        recipeDesc: '',
        defaultTimeSelect: '2',
        genre: '',
        myToken: sessionStorage.getItem('token'),
      };
    } else {
      this.state = {
        username: 'Guest User',
        userId: '0',
        recipeTitle: '',
        recipeIngridents: '',
        recipeDesc: '',
        defaultTimeSelect: '2',
        genre: '',
        myToken: ''
      };
    }
  }
  
  getValidationState() {
    const recipeTitle_length = this.state.recipeTitle.length;
    const recipeIngridents_length = this.state.recipeIngridents.length;
    const recipeDesc_length = this.state.recipeDesc.length;
    const defaultTimeSelect_length = this.state.defaultTimeSelect.length;

    if (recipeTitle_length > 3 && recipeIngridents_length > 3 && recipeDesc_length > 3  && defaultTimeSelect_length > 0) return 'success';
    else if (recipeTitle_length > 0 || recipeIngridents_length > 0 || recipeDesc_length > 0 || defaultTimeSelect_length <= 0) return 'error';
    return null;
  }

  validateForm() {
    console.log('value of validation state is: '+this.getValidationState());
    // console.log(this.getValidationState() === 'success' ? true : false);
    return this.getValidationState() === 'success' ? true : false;
  }

  handleChangeTitle(e) {
    this.setState({ recipeTitle: e.target.value });
  }
  handleChangeIngridents(e) {
    this.setState({ recipeIngridents: e.target.value });
  }
  handleChangeDesc(e) {
    this.setState({ recipeDesc: e.target.value });
  }
  handleChangeGenre(e) {
    this.setState({ genre: e.target.value });
  }
  handleChangeTime(e) {
    this.setState({ defaultTimeSelect: e.target.value });
  }

  handleReturn = event => { //send this detail to the server
    // console.log('Enter pressed with the following details '+ this.state.value + ' and the time is ' +this.state.defaultTimeSelect );
    event.preventDefault();
    // username: 'Guest User',
    // userId: '0',
    // recipeTitle: '',
    // recipeIngridents: '',
    // recipeDesc: '',
    // defaultTimeSelect: '2',

    const username = this.state.username.slice();
    const userId = this.state.userId.slice();
    const recipeTitle = this.state.recipeTitle.slice();
    const recipeIngridents = this.state.recipeIngridents.slice();
    const recipeDesc = this.state.recipeDesc.slice();
    var defaultTimet = this.state.defaultTimeSelect.slice();
    var defaultTimeSelect=1; 
    if(defaultTimet < 16){
      defaultTimeSelect = 1;
    }else if(defaultTimet > 16 && defaultTimet < 45 ){
      defaultTimeSelect = 2;
    }else{
      defaultTimeSelect = 3;
    }
    const genre = this.state.genre.slice();
    const myToken = this.state.myToken.slice();

    axios.post('/expressAddRecipe/',{
      username : username,
      madeby : userId,
      name : recipeTitle,
      ingredients : recipeIngridents,
      description : recipeDesc,
      timeTaken : defaultTimeSelect,
      genre : genre,
      myToken: myToken,
    }).then(res=>{
      //console.log(res.data.username)
      console.log("Adding recipe successfull ..!");
      console.log('redirecting to home page');
      // sessionStorage.setItem('username',res.data.username);
      // sessionStorage.setItem('userId',res.data.id);
      // sessionStorage.setItem('isLoggedIn',true);
      this.props.history.push({
        pathname: '/Homepage/'
      });
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
        <Panel.Title componentClass="h1">Add Recipes Here</Panel.Title>
        </Panel.Heading>
        <Panel.Body>
        <div className="Addrecipe">   
        <form onSubmit={this.handleReturn}>
          <FormGroup
            controlId="formBasicText"
            validationState={this.getValidationState()}>
            <ControlLabel>Recipe Title</ControlLabel>
            <FormControl
              type="text"
              value={this.state.recipeTitle}
              placeholder="Minimum 5 characters"
              onChange={this.handleChangeTitle}/>
            <FormControl.Feedback />
            <br/>
            <ControlLabel>Ingirdents</ControlLabel>
            <FormControl
              type="text"
              value={this.state.recipeIngridents}
              placeholder="Minimum 5 characters"
              onChange={this.handleChangeIngridents}/>
            <FormControl.Feedback />
            <br/>
            <ControlLabel>Description</ControlLabel>
            <FormControl
              type="text"
              value={this.state.recipeDesc}
              placeholder="Minimum 5 characters"
              onChange={this.handleChangeDesc}/>
            <FormControl.Feedback />
            <br/>
            <ControlLabel>Genre</ControlLabel>
            <FormControl
              type="text"
              value={this.state.genre}
              placeholder="Minimum 5 characters"
              onChange={this.handleChangeGenre}/>
            <FormControl.Feedback />
            <br/>
            <ControlLabel>Time to cook</ControlLabel>
            <FormControl
              type="text"
              value={this.state.defaultTimeSelect}
              placeholder="In mins ex: '15'"
              onChange={this.handleChangeTime}/>
            <FormControl.Feedback />
            <br/>
            {/* <RenderDropDownButton value="Time to Cook" onSelect={(e) => this.bindTimeValue(e)}/> */}
            <center>
            <Button
              bsSize="large"
              bsStyle="primary"
              disabled={!this.validateForm()}
              type="submit">
              Add Post
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

export default Addrecipe;
