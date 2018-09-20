import React, { Component } from 'react';
import './App.css';
import {Router, Route, browserHistory,withRouter} from 'react-router';  
import logo from './logo.jpg';
import {Button} from 'reactstrap';
import SignUp from './SignUp';
import Login from './Login';
import RecipeList from './RecipeList';


class App extends Component {
  constructor() {
    super();
    this.state = {recipes:[]};
  }
handlePageChange_SignUP() {
    window.location = "signup";
  }

  handlePageChange_Login() {
    window.location = "login";
  }
  handlePageChange_Home() {
    window.location = "App";
  }
  render() {
    let alpha = this.state.recipes;
    return (
      /*https://code.visualstudio.com/docs/nodejs/reactjs-tutorial*/
        <div className="Body">
          <div className="App">
          <header className="App-header">
              <img src={logo} className="App-logo" alt="logo" />
              <h1 className="App-title">foodCode</h1>
            </header>
            <p className="App-intro">
              All the recipes at one place
            </p>
          </div>
          {/* <Tabs tabActive={2} onBeforeChange={this.onBeforeChange} onAfterChange={this.onAfterChange} onMount={this.onMount}>
              <Tabs.Panel title='Tab #1'>
                <h2>Content #1</h2>
              </Tabs.Panel>
              <Tabs.Panel title='Tab #2'>
                <h2>Content #2</h2>
              </Tabs.Panel>
              <Tabs.Panel title='Tab #3'>
                <h2>Content #3</h2>
              </Tabs.Panel>
          </Tabs> */}

          <div>
              <Button color="primary" className="px-4" onClick={this.handlePageChange_Home}>
                  Home
              </Button>
              <Button color="primary" className="px-4" onClick={this.handlePageChange_SignUP}>
                  Sign Up!
              </Button>
              <Button color="primary" className="px-4" onClick={this.handlePageChange_Login}>
                  Login
              </Button> 
          </div>
          {/* <Tabs active={this.state.active} onchange={active=> this.setState({active})}>
              <div key={aTab} title="Tab 1">
                Tab 1 content
              </div>
              <div key={bTab} title="Tab 2">
                Tab 2 content
              </div>
          </Tabs>
          <h2>Content</h2>
          <p>{content[this.state.active]}</p> */}
          <div>
          <Router history={browserHistory}>
                <Route exact path="signup" component={SignUp}/>
          </Router>
          </div>
          <div>
          <Router history={browserHistory}>
                <Route exact path="login" component={Login}/>
          </Router>
          </div>
          <div>
              <h1>List</h1> 
              <RecipeList />
          </div>
        </div>
      );
    } 
  }
        /* <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <h1 className="App-title">foodCode</h1>
          </header>
          <p className="App-intro">
            All the recipes at one place
          </p>
        </div> 
         https://stackoverflow.com/questions/41332643/sending-data-to-database-in-react-js-web-application 
        
         <nav className="navbar navbar-default">
            <div className="container">
                <div className="navbar-header">
                    <ul className= "nav navbar-nav">
                        <li>Home</li>
                        <li>SignUp</li>    
                    </ul>
                </div>
            </div>
        </nav> 
        
         <form onSubmit={this.onSubmit}>
          <input type="text" placeholder="User name" ref="Username"/>
          <input type="text" placeholder="Password" ref="Password"/>
          <input type="submit" />
        </form>
        <div className="component-wrapper">
            <div>
              <Button color="primary" className="px-4" onClick={this.handlePageChange}>
                  Sign Up!
              </Button> 
            </div>
         </div>
        <div>
          <h1>List</h1> 
          <RecipeList recipes={recipes}/>
        </div> */

//export default App;
//React.renderComponent(<App />, mountNode);
export default withRouter(App);