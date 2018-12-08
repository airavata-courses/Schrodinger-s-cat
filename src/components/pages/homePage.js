import React, { Component } from 'react';
import { Panel } from "react-bootstrap";
import {withRouter} from "react-router-dom";
import UserProfile from './../../Assets/Profiles/userProfile';


function Hello(props){
    if(sessionStorage.getItem('isLoggedIn') !== null && sessionStorage.getItem('isLoggedIn') === 'true'){
        //console.log('in checkLoggedStatusHome 1: '+this.state.isLogin);
        return <h1> Hello {sessionStorage.getItem('username')}</h1> ;
      } else {
        //console.log('in checkLoggedStatusHome 2: '+this.state.isLogin);
        return <h1> Hello Guest User</h1> ;
      }
}

class Homepage extends Component {
    constructor(props,context){
        super(props,context);
        
        //this.updateOnLogOut=this.updateOnLogOut.bind(this);
        
        if(sessionStorage.getItem('isLoggedIn') !== null && sessionStorage.getItem('isLoggedIn') === 'true'){
            console.log('in constructor of Homepage: '+sessionStorage.getItem('username'));
            console.log("Logged in user? "+sessionStorage.getItem('isLoggedIn'));
            if(sessionStorage.getItem('hasSearched') !== null && sessionStorage.getItem('hasSearched') === 'true'){
                console.log("Has searched? "+sessionStorage.getItem('hasSearched'));
                this.state = {
                userId: sessionStorage.getItem('userId'),
                userName: sessionStorage.getItem('username'),
                isSearched: sessionStorage.getItem('hasSearched'),
                isLogin: sessionStorage.getItem('isLoggedIn'),
                showDefault: true,
                recipes: sessionStorage.getItem('recipes'),
                };
            } else {
                console.log("Has searched? "+false);
                this.state = {
                userId: sessionStorage.getItem('userId'),
                userName: sessionStorage.getItem('username'),
                isSearched: false,
                isLogin: sessionStorage.getItem('isLoggedIn'),
                showDefault: true,
                recipes:[],
                };
            }            
        } else {
            console.log("Logged in user? "+false);
            this.state = {
            userId: '',
            userName: 'Guest User',
            isSearched: false,
            isLogin:false,
            showDefault: true, // if isSearched is true, showDefault is false. vice versa
            recipes:[]
            };
        }       
    }

   

  render() {
    return (
        <div>
            <Panel>
                <Panel.Heading>
                    <Panel.Title>
                        <Hello/>
                    </Panel.Title>
                </Panel.Heading>
                <Panel.Body>
                {/* <RenderWelcomeUser value={[this.state.userName,this.state.test]}/> */}
                    <div className="Home">
                    {/* <RenderRecipies value={this.state.isSearched}/> */}
                    </div>
                 </Panel.Body>
            </Panel>
        </div>
    );
  }
}

export default withRouter(Homepage);
