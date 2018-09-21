import React from 'react';
import RecipeList from './RecipeList';

class Home extends React.Component{
    constructor() {
        super();
        this.state = {recipes:[]};
      }
    render(){
        let alpha = this.state.recipes;
        return (
        <div>
              <h1>List</h1> 
              <RecipeList />
          </div>
        )
    }
}

export default Home;