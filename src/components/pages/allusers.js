import React, { Component } from 'react';
import { Panel } from "react-bootstrap";
import {withRouter} from "react-router-dom";
import UserProfile from './../../Assets/Profiles/userProfile';

function UserDetails(props){
    if(sessionStorage.getItem('allUserDetails') !== null){
        var retrieveduserDetails = JSON.parse(sessionStorage.getItem('allUserDetails')) ;
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
                if(j === 4){
                    var temptemp = JSON.parse(JSON.stringify(userValues[j]));
                    
                    if(temptemp.length !== 0){
                        console.log(temptemp.length);
                        let childrenchildren = []
                        for(let k = 0; k < temptemp.length; k++){
                            console.log(temptemp[k].id);
                            childrenchildren.push(<div key = {i + j + k + 250}> {temptemp[k].id}</div>) 
                        }
                        children.push(<Panel.Body key ={i+j+500}>{userKeys[j].toString().toLocaleUpperCase()} : {childrenchildren}</Panel.Body>)
                    }
                    else{
                        children.push(<Panel.Body key ={i+j+500}>{userKeys[j].toString().toLocaleUpperCase()} : No Recipies Added</Panel.Body>)
                    }
                }
                else{
                    children.push(<Panel.Body key ={i+j+1000}>{userKeys[j].toString().toLocaleUpperCase()} : {userValues[j]}</Panel.Body>)
                }
             }
            //Create the parent and add the children
            panel.push(<Panel key ={i+2000}><Panel.Heading key ={i+5000}>Username : {userValues[5]}</Panel.Heading>{children }</Panel>)
        }
        return panel
    }
    return null
}

class Allusers extends Component {
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
            <div className="Home">
                <UserDetails/>
            </div>
        </div>
        
    );
  }
}

export default withRouter(Allusers);
