import React, {Component} from 'react';
import ItemDisplay from './ItemDisplay/ItemDisplay';
import Item from './Item/Item';
import {
    BrowserRouter as Router,
    Route
} from 'react-router-dom';

class App extends Component {
    render() {
        return (
            <Router>
                <div>
                    <Route exact path="/" component={ItemDisplay} />
                    <Route exact path="/items/:itemID" component={Item}/>
                </div>
            </Router>
        );
    }
}

export default App;
