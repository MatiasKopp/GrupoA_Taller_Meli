import React, {Component} from 'react';
import './LoadingSpinner.css';

class LoadingSpinner extends Component {
    render() {
        return(
            <div className="loadingContainer">
                <div className="sk-folding-cube">
                    <div className="sk-cube1 sk-cube"></div>
                    <div className="sk-cube2 sk-cube"></div>
                    <div className="sk-cube4 sk-cube"></div>
                    <div className="sk-cube3 sk-cube"></div>
                </div>
            </div>
        );
    }
}

export default LoadingSpinner;
