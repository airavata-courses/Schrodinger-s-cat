import React from 'react';
import Recipe from './Recipe'

class RecipeList extends React.Component{
    constructor(){
        super();
        this.state={
            search: '',
            recipes:[]
        };
    };
    componentDidMount() {
        fetch('http://localhost:4000/recipesExpress')
          .then(res => {
             res.json().then(resultArray => {
                this.setState({recipes:resultArray})
             }) 
      
          })}
    updateSearch(event){
        this.setState({search: event.target.value});
        
        let searchString=this.state.search
        console.log(searchString);
        fetch('http://localhost:4000/recipesExpress/search/?param1=${searchString}')
    }
    

    render(){
        // let filteredRecipes= this.props.recipes.filter(
        //     (recipe) => {
        //         return recipe.name.toLowerCase().indexOf(this.state.search.toLowerCase())!==-1;
        //     }
        // );
        
        return (
            <div>
                <input type = "text" value={this.state.search} onChange={this.updateSearch.bind(this)} >
                </input>
                { <ul>    
                    {this.state.recipes.map((recipe)=><Recipe recipe={recipe}/>)}
                </ul>  }
            </div>
        )
    }
}

export default RecipeList;