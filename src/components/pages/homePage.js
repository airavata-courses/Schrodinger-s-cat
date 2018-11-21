import React, { Component } from 'react';
import { Panel,Jumbotron } from "react-bootstrap";
import {withRouter} from "react-router-dom";
import UserProfile from './../../Assets/Profiles/userProfile';
// function RenderWelcomeUser(props) {
//     return (
//         <Jumbotron>
//             <h1>Hello {props.value[0]}</h1>
//             {/* <p>
//                 <Button bsStyle="primary">Learn more</Button>
//             </p> */}
//         </Jumbotron>
//     );
// }

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
    constructor(props){
        super(props);
        
        //this.updateOnLogOut=this.updateOnLogOut.bind(this);

        console.log(sessionStorage.getItem('isLoggedIn'));
        if(sessionStorage.getItem('isLoggedIn') !== null && sessionStorage.getItem('isLoggedIn') === 'true'){
            console.log('in constructor of Homepage: '+sessionStorage.getItem('username'));
            this.state = {
                userId: sessionStorage.getItem('userId'),
                userName: sessionStorage.getItem('username'),
                isSearched: false,
                isLogin: sessionStorage.getItem('isLoggedIn'),
                showDefault: true,
            };
        } else {
            this.state = {
                userId: '',
                userName: 'Guest User',
                isSearched: false,
                isLogin:false,
                showDefault: true, // if isSearched is true, showDefault is false. vice versa
            };
        }       
    }

    // updateOnLogOut() {
    //     console.log('in constructor of updateOnLogOut: '+sessionStorage.getItem('isLoggedIn'));
    //     this.setState=({
    //         userId: '',
    //         userName: 'Guest User',
    //         isSearched: false,
    //         isLogin:false,
    //         showDefault: true,
    //     });
    // }
    // componentDidUpdate(){
    //     console.log('in componentDidUpdate');
    //     if(sessionStorage.getItem('isLoggedIn') !== null && sessionStorage.getItem('isLoggedIn') === 'true'){
    //         console.log('in constructor of Homepage: '+sessionStorage.getItem('username'));
    //         this.setState = {
    //             userId: sessionStorage.getItem('userId'),
    //             userName: sessionStorage.getItem('username'),
    //             isSearched: false,
    //             isLogin: sessionStorage.getItem('isLoggedIn'),
    //             showDefault: true,
    //         };
    //     } else {
    //         this.setState = {
    //             userId: '',
    //             userName: 'Guest User',
    //             isSearched: false,
    //             isLogin:false,
    //             showDefault: true, // if isSearched is true, showDefault is false. vice versa
    //         };
    //     } 
    // }

    // checkLoggedStatusHome() {
    //     if(sessionStorage.getItem('isLoggedIn') !== null && sessionStorage.getItem('isLoggedIn') === 'true'){
    //       //console.log('in checkLoggedStatusHome 1: '+this.state.isLogin);
    //       return sessionStorage.getItem('username');
    //     } else {
    //       //console.log('in checkLoggedStatusHome 2: '+this.state.isLogin);
    //       return 'Guest User';
    //     }
    //     // else {
    //     //   this.setState({
    //     //     loggedIn: false,
    //     //   });
    //     // }
    //     console.log('in checkLoggedStatusHome of : '+this.state.isLogin);
    //     // return this.state.loggedIn;
    //   }

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
                        
                    </div>
                 </Panel.Body>
            </Panel>
        </div>
    );
  }
}

export default withRouter(Homepage);
