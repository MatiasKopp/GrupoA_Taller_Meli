import React, {Component} from 'react';
import ItemDisplay from './ItemDisplay/ItemDisplay';
import Item from './Item/Item';
import Trend from './Trends';
import {
    BrowserRouter as Router,
    Route,
    Link
} from 'react-router-dom';

class App extends Component {
    constructor() {
        super();
    }

    render() {
        return (
            <Router>
                <div>
                    <Route exact path="/" component={ItemDisplay} />
                    <Route path="/items/:itemID" component={Item}/>
                </div>
            </Router>
        );
    }
}

export default App;
