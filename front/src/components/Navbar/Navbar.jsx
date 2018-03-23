import React, { Component } from 'react';
import './Navbar.css';

class Navbar extends Component {
    render() {
        return(
            <nav className="navbar-melu">
                <div className="nav-container">
                    <h2>Kwik-E-Mart</h2>
                    <div className="nav-right">
                        <button className="btn btn-nav">Login</button>
                        <button className="btn btn-nav glyphicon glyphicon-shopping-cart"></button>
                    </div>
                </div>
            </nav>
        );
    }
}

export default Navbar;
