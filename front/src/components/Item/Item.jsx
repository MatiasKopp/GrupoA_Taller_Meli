import React, { Component } from 'react';
import './Item.css';

class Item extends Component {

    componentWillMount() {
        this.cargarItem();
    }

    cargarItem() {
        let idItem = this.props.match.params.itemID;
        //Item
        fetch("https://api.mercadolibre.com/items/" + idItem)
        .then((response) => {
            if (response.status == 404) {
                return null;
            }
            return response.json();
        })
        .then((data) => {
            if (!data) {
                return;
            }
            let pictures = [];
            data.pictures.forEach((picture) => {
                pictures.push(picture.url);
            });

            let obj = {
                "title": data.title,
                "price": data.price,
                "pictures": pictures,
                "dateCreated": data.date_created
            }
            this.setState({ item : obj });
            this.cargarItemsRelacionados();
        });

        //Descripcion
        fetch("https://api.mercadolibre.com/items/" + idItem + "/description")
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            let obj = {
                description: data.plain_text
            }
            this.setState({ data : obj });
        });
    }

    cargarItemsRelacionados() {
        fetch("https://api.mercadolibre.com/sites/MLA/search?category=" + this.state.item.categoriaItem)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            var finalData = [];

            data.results.forEach(function(element) {
                var obj = {
                    "title":element.title,
                    "price":element.price,
                    "image":element.thumbnail
                }
                if (element.thumbnail){
                    finalData.push(obj);
                }
            });
            this.setState({ itemsRelacionados : finalData });
        });
    }

    calcularTotal(e) {
        let target   = e.target;
        let value    = target.value;
        let total    = this.state.total ? this.state.total + value : value;

        this.setState({total: total})
    }

    render() {
        let item = this.state ? this.state.item : "";
        let description = this.state ? this.state.data : "";
        if (!item) {
            return(
                <div>
                    <h1>404</h1>
                    <h3>No hemos podido encontrar el articulo! :(</h3>
                </div>
            );
        }
        return (
            <div>
                <div className="col-xs-12 itemContainer">
                    <div className="itemHeader">
                        <Carrousel imagenes={item ? this.state.item.pictures : ""}/>
                        <div className="itemData">
                            <h3>{item ? item.title : ""}</h3>
                            <div className="itemSubData">
                                <h3>Price: ${item ? item.price : ""}</h3>
                                <h3>Shipping: $25</h3>
                                <h3>Quantity: <input type="number" min="1" className="quantitySelector" onChange={this.calcularTotal}/></h3>
                                <h2>Total: {}</h2>
                                <div className="itemButtons">
                                    <button className="btnBuy">Comprar</button>
                                    <button className="btnCart">Agregar a Carrito</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="itemBody">
                        <h1>Caracteristicas</h1>
                        <div className="container-fluid">
                            <div className="col-xs-12 col-sm-offset-2 col-sm-8">
                                <p>
                                    {description ? this.state.data.description : ""}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-xs-12 itemRelacionados">
                    <h1>Productos relacionados: </h1>
                    <div className="items">
                        <div className="itemBox"></div>
                        <div className="itemBox"></div>
                        <div className="itemBox"></div>
                        <div className="itemBox"></div>
                    </div>
                </div>
            </div>
        );
    }
}

class Carrousel extends Component {
    render() {
        let imagenes = this.props.imagenes;
        let style = {
            "height": 350
        }
        let imgDiv = {
            "height": "100%"
        }
        let img = {
            "objectFit": "contain",
            "height": "100%"
        }
        return (
            <div className="itemCarrousel">
                <div id="myCarousel" className="carousel slide" data-ride="carousel" style={style}>
                    <div className="carousel-inner" style={imgDiv}>
                        <div className="item active" style={imgDiv}>
                            <img src={imagenes[0]} alt="imagen1" style={img}/>
                        </div>

                        <div className="item" style={imgDiv}>
                            <img src={imagenes[1]} alt="imagen2" style={img} />
                        </div>

                        <div className="item" style={imgDiv}>
                            <img src={imagenes[2]} alt="imagen3" style={img} />
                        </div>
                    </div>

                    <a className="left carousel-control" href="#myCarousel" data-slide="prev">
                        <span className="glyphicon glyphicon-chevron-left"></span>
                        <span className="sr-only">Previous</span>
                    </a>
                    <a className="right carousel-control" href="#myCarousel" data-slide="next">
                        <span className="glyphicon glyphicon-chevron-right"></span>
                        <span className="sr-only">Next</span>
                    </a>
                </div>
            </div>
        );
    }
}

export default Item;
