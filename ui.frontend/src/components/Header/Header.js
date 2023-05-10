//Header.js
import React, {Component} from 'react';
require('./Header.css');

export default class Header extends Component {

    render() {
        return (
                <header className="Header">
                    <div className="Header-container">
                        <h1>Prince</h1>
                    </div>
                </header>
        );
    }
}
