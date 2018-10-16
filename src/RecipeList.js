import React from 'react';
import Recipe from './Recipe'
import { error } from 'util';

class RecipeList extends React.Component{
    constructor(){
        super();
        this.state={
            search: '',
            recipes:[]
        };
    };
    componentDidMount() {
        fetch('http://127.0.0.1:8000/recipes/pythonSearch/a/')
          .then(res => {
             res.json().then(resultArray => {
                this.setState({recipes:resultArray})
                console.log(resultArray)
             }).catch(error =>{
                console.log("error")
            })
        
          }).catch(error =>{
              console.log("error")
          })}
    updateSearch(event) {
        this.setState({search: event.target.value});
        
        const data = {
            searchString: event.target.value
        };

        fetch(`http://127.0.0.1:8000/recipes/pythonSearch/${encodeURIComponent(data.searchString)}`, {
            method: "GET"
        })
        .then(res => {
            res.json().then(resultArray => {
               this.setState({recipes:resultArray})
            }).catch(error =>{
                console.log("error")
            }) 
     
         }).catch(error =>{
            console.log("error")
        })
    }
    

    render(){
        // let filteredRecipes= this.props.recipes.filter(
        //     (recipe) => {
        //         return recipe.name.toLowerCase().indexOf(this.state.search.toLowerCase())!==-1;
        //     }
        // );
        
        return (
            <div>
                <div>
                    <input type = "text" value={this.state.search} onChange={this.updateSearch.bind(this)} >
                    </input>
                    { <ul>    
                        {this.state.recipes.map((recipe)=><Recipe recipe={recipe}/>)}
                    </ul>  }
                </div>
            </div>
        )
    }
}

export default RecipeList;