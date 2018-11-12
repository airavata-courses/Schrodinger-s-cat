import React from 'react';

class Recipe extends React.Component{
    render(){
        return (
            <div>
                <li>
                    <details>
                        <summary>{this.props.recipe.name}</summary>
                        <p>Name of the recipe : {this.props.recipe.name}</p>
                        <p>Genre: {this.props.recipe.genre}</p>
                        <p>Ingredients:</p>
                        <p>{ <ul>    
                            {this.props.recipe.ingredients.map((recipe)=><li>{recipe}</li>)}
                        </ul>  }
                        </p>
                    </details>
                </li>
            </div>
        )
    }
}

export default Recipe;