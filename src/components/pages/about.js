import React, { Component } from 'react';

class About extends Component {
  render() {
    return (
        <div className="container-fluid">
            <h1>
            About Content
            </h1>
            <br/>
            <br/>
            <br/>
           <p>
           This is a project made by students of Indiana University Bloomington, School of Informatics, Computing and Engineering as a part of the course CSCI-B659 Science Gateway Architecture. 
           </p><p>
             This applicaiton is deployed on kubernetes and is running on TACC's jetstream-cloud infrastructure. We have implemented High availability, Scalibility, fault tolerance and rolling updates with zero downtime.
           </p>
           <p>Visit our profiles on linkedin</p>
           <p><a href="https://www.linkedin.com/in/nawazhk/">Nawaz Hussain K</a></p>
           <p><a href="hhttps://www.linkedin.com/in/jlsoumya/">Laxmi Soumya Josyula</a></p>       
           <p><a href="hhttps://www.linkedin.com/in/prashanth-swargam-pswargam/">Prashanth Swargam</a></p>   
        </div>
    );
  }
}

export default About;
