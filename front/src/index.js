import React from 'react';
import ReactDOM from 'react-dom';
import Navbar from './components/Navbar/Navbar'
import App from './components/App';
import Chat from './components/Chat/Chat';

ReactDOM.render(<Navbar />, document.getElementById('navbar'));
ReactDOM.render(<App/>, document.getElementById('app'));
ReactDOM.render(<Chat/>, document.getElementById('chat'));
//ReactDOM.render(<Categories/>, document.getElementById('categories'));
