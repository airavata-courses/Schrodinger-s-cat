import React, { Component } from 'react';
import { Panel } from "react-bootstrap";
import {withRouter} from "react-router-dom";
import UserProfile from './../../Assets/Profiles/userProfile';

function UserDetails2(props){
    console.log("strange "+sessionStorage.getItem('recipes') )
    if(sessionStorage.getItem('recipes') !== null || sessionStorage.getItem('recipes') != null || sessionStorage.getItem('recipes')){
        var retrieveduserDetails = JSON.parse(sessionStorage.getItem('recipes')) ;
        // console.log("something 0 "+retrieveduserDetails);
        // console.log("something 1 "+retrieveduserDetails.length);
        // console.log("something 2 "+Object.keys(retrieveduserDetails[0]).length);
        // console.log("something 3 "+Object.values(retrieveduserDetails[0]));
        let panel = [];

        // Outer loop to create parent
        for (let i = 0; i < Object.keys(retrieveduserDetails).length; i++) {
            var userKeys = Object.keys(retrieveduserDetails[i]);
            var userValues = Object.values(retrieveduserDetails[i])
            let children = []

            //Inner loop to create children
            for (let j = 0; j < 6 ; j++) {
                if(userKeys[j] === 'ingredients'){
                    var temptemp = JSON.parse(JSON.stringify(userValues[j]));
                    
                    if(temptemp.length !== 0){
                        console.log("hellooooo"+temptemp.length);
                        let childrenchildren = []
                        for(let k = 0; k < temptemp.length; k++){
                            console.log(temptemp[k]);
                            childrenchildren.push(<div key = {i + j + k + 250}> {temptemp[k]}</div>) 
                        }
                        children.push(<Panel.Body key ={i+j+500}>{userKeys[j].toString().toLocaleUpperCase()} : {childrenchildren}</Panel.Body>)
                    }
                    else{
                        children.push(<Panel.Body key ={i+j+500}>{userKeys[j].toString().toLocaleUpperCase()} : No Recipies Added</Panel.Body>)
                    }
                }
                else if(userKeys[j] === 'timeTaken'){
                    if(userValues[j]=== 1){
                        children.push(<Panel.Body key ={i+j+1010}>{userKeys[j].toString().toLocaleUpperCase()} : 15 mins</Panel.Body>)
                    }else if(userValues[j]=== 2){
                        children.push(<Panel.Body key ={i+j+1020}>{userKeys[j].toString().toLocaleUpperCase()} : 30 mins</Panel.Body>)
                    }else{
                        children.push(<Panel.Body key ={i+j+1030}>{userKeys[j].toString().toLocaleUpperCase()} : 45 mins</Panel.Body>)
                    }
                    
                }else{
                    children.push(<Panel.Body key ={i+j+1000}>{userKeys[j]} : {userValues[j]}</Panel.Body>)
                }
             }
            //Create the parent and add the children
            panel.push(<Panel key ={i+2000}><Panel.Heading key ={i+5000}>Recipe Name : {userValues[5]}</Panel.Heading>{children }</Panel>)
        }
        return panel
    }
    return null
}


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
                <Panel.Body><small>Click Home Page to clear the results</small></Panel.Body>
            </Panel>
            <div className="Home">
                <UserDetails2/>
            </div>
        </div>
    );
  }
}

export default withRouter(Homepage);
