import React, { Component } from 'react';
import './Navbar.css';

class Navbar extends Component {
    render() {
        return(
            <nav className="navbar-melu">
                <div className="nav-container">
                    <a href="/" className="navbarTitle">Kwik-E-Mart</a>
                    <form className="searchBar">
                        <input type="text" name="searchText" id="searchText" maxLength="40"/>
                        <button className="glyphicon glyphicon-search btnSearch" type="button"></button>
                    </form>
                    <div className="nav-right hidden-xs">
                        <button className="btn btn-nav">Login</button>
                        <button className="btn btn-nav glyphicon glyphicon-shopping-cart"></button>
                    </div>
                </div>
            </nav>
        );
    }
}

export default Navbar;
