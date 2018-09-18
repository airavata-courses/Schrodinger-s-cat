import React from 'react';
import Recipe from './Recipe'

class RecipeList extends React.Component{
    constructor(){
        super();
        this.state={
            search: ''
        };
    };
    updateSearch(event){
        this.setState({search: event.target.value});
    }
    render(){
        let filteredRecipes= this.props.recipes.filter(
            (recipe) => {
                return recipe.name.toLowerCase().indexOf(this.state.search.toLowerCase())!==-1;
            }
        );
        return (
            <div>
                <input type = "text" value={this.state.search} onChange={this.updateSearch.bind(this)} >
                </input>
                { <ul>    
                    {filteredRecipes.map((recipe)=><Recipe recipe={recipe}/>)}
                </ul>  }
            </div>
        )
    }
}

export default RecipeList;