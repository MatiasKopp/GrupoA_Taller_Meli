import React from 'react';
import ReactDOM from 'react-dom';
import Navbar from './components/Navbar/Navbar'
import App from './components/App';

ReactDOM.render(<Navbar />, document.getElementById('navbar'));
ReactDOM.render(<App/>, document.getElementById('app'));
//ReactDOM.render(<Categories/>, document.getElementById('categories'));
