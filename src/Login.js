import React from 'react';
//import logo from './logo.jpg';
//import RecipeList from './RecipeList';
//import { Button} from 'reactstrap';
import axios from 'axios';

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
        console.log(name)
	console.log(pass)
        axios.post('/expressLogin/',{
          username : name,
          passwd : pass
        }).then(res=>{
            console.log(res)
        });
      };
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
          <input type="text" placeholder="User name"   onChange={this.handleChangeUname.bind(this)}/>
          <input type="text" placeholder="Password"  onChange={this.handleChangePasswd.bind(this)}/>
          <input type="submit" />
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