import React from 'react';
import Background from './food.jpg';

var sectionStyle = {
    width: "100%",
    height: "400px",
    backgroundImage: "url(" +  Background  + ")"
  };
class Dashboard extends React.Component{
    render(){
        return (
            <div>
                <h1>Welcome to foodCode!</h1>
                <section style={ sectionStyle }>
                </section>
            </div>
        )
    }
}

export default Dashboard;