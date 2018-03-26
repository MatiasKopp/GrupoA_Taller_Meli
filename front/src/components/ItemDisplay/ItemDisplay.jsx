import './ItemDisplay.css';
import React, {Component}   from 'react';
import ItemGrid             from '../ItemGrid/ItemGrid';
import ItemList             from '../ItemList/ItemList';
import ItemCarrousel        from '../ItemCarrousel/ItemCarrousel';
import LoadingSpinner       from '../LoadingSpinner/LoadingSpinner';

class ItemDisplay extends Component {
    constructor() {
        super();

        this.state = {
            listing: 'grid'
        }

        this.handleListing = this.handleListing.bind(this);
    }

    componentWillMount() {
        //localStorage.setItem("user","JUAN PEREZ");
        //localStorage.removeItem("user");
        //localStorage.setItem("categories", "MLA1,MLA4");

        if(localStorage.getItem("user") != null){
            if(localStorage.getItem("categorias") != null){
                let urlItemsCategoriaApi = 'http://localhost:7070/items/q=' + localStorage.getItem("categorias");
                fetch(urlItemsCategoriaApi)
                    .then((response) => {
                        return response.json();
                    })
                    .then((items) => {
                        this.setState({ items: items })
                    })
            }
        } else {
            let urlItemsApi = "http://localhost:7070/items";
            fetch(urlItemsApi)
                .then((response) => {
                    return response.json()
                })
                .then((items) => {
                    this.setState({ items: items })
                })
        }

    }

    handleListing(e) {
        let target = e.target;
        let listing = target.getAttribute('listing');

        this.setState({ listing: listing });
    }

    render() {
        const buttonGroup = (
            <div className="col-xs-12 buttonGroup">
                <button className="btn btn-white glyphicon glyphicon-th"
                    onClick={this.handleListing} listing="grid"></button>
                <button className="btn btn-white glyphicon glyphicon-th-list"
                    onClick={this.handleListing} listing="list"></button>
                <button className="btn btn-white glyphicon glyphicon-stop"
                    onClick={this.handleListing} listing="carrousel"></button>
            </div>
        );

        if (!this.state.items) {
            return (
                <div style={{"marginTop": 200}}>
                    <LoadingSpinner />
                </div>
            );
        }

        let listing;

        switch(this.state.listing) {
            case 'grid':
                listing = ( <ItemGrid data={this.state.items}/> );
                break;
            case 'list':
                listing = ( <ItemList data={this.state.items}/> );
                break;
            case 'carrousel':
                listing = ( <ItemCarrousel data={this.state.items}/> );
                break;
            default:
                listing = ( <div><h1>Error!</h1></div> );
        }

        return (
            <div>
                {buttonGroup}
                <div className="itemListing">
                    {listing}
                </div>
            </div>
        );
    }
}

export default ItemDisplay;
