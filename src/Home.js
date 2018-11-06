import React from 'react';
import RecipeList from './RecipeList';

class Home extends React.Component{
    constructor() {
        super();
        this.state = {recipes:[]};
      }
    render(){
        //let alpha = this.state.recipes;
        return (
        <div>
            <div>
                <p>
                    <h2>Search recipes!!! </h2>
                </p>
            </div>
              <RecipeList />
          </div>
        )
    }
}

export default Home;