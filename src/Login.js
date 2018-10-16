import React from 'react';
//import logo from './logo.jpg';
//import RecipeList from './RecipeList';
//import { Button} from 'reactstrap';
import axios from 'axios';
import {ToastContainer,toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'
class Login extends React.Component{
    constructor() {
      super();
      this.state = { recipes: [],
      uname:'',
      passwd:''
    };
      this.onSubmit = this.handleSubmit.bind(this);
    //  this.routeChange = this.routeChange.bind(this);
    }
    handleSubmit(e) {
        e.preventDefault();
        
        var name = this.state.uname;
        var pass = this.state.passwd;
       
        axios.post('/expressLogin/',{
          username : name,
          password : pass
        }).then(res=>{
            console.log(res.data.username)
            this.notify("Login Success..! Dashboard and other functionality are WIP")
        }).catch(error =>{
            this.notify("Login Failed")
        });
      };
      notify = (message) => toast(message)
      handleChangeUname(event){
        this.setState({uname: event.target.value})
        
      };
      handleChangePasswd(event){
        this.setState({passwd: event.target.value})
        
      };
    render(){
        return (
            <div className="Home">
             {/* <header className="App-header">
              <img src={logo} className="App-logo" alt="logo" />
              <h1 className="App-title">foodCode</h1>
            </header>
            <p className="App-intro">
              All the recipes at one place
            </p>  */}
          <form onSubmit={this.onSubmit}>
          <input type="text" placeholder="Email Id"   onChange={this.handleChangeUname.bind(this)}/>
          <input type="password" placeholder="Password"  onChange={this.handleChangePasswd.bind(this)}/>
          <input type="submit" />
          <ToastContainer/>
        </form>
        {/* <div className="component-wrapper">
            <div>
              <Button color="primary" className="px-4" onClick={this.handlePageChange}>
                  Sign Up!
              </Button> 
            </div>
         </div> */}
        {/* // <div>
        //   <h1>List</h1> 
        //   <RecipeList recipes={recipes}/>
        // </div> */}
        </div>
        )
    }
}

export default Login;