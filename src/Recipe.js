import React from 'react';

class Recipe extends React.Component{
    render(){
        return (
            <li>
                {this.props.recipe.name}
            </li>
        )
    }
}

export default Recipe;