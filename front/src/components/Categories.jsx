import React from 'react'

class CategoriesApp extends React.Component {

    constructor(props) {
        super(props);
        this.state = { categories: [] }
    }

    componentWillMount() {
        fetch('http://localhost:7070/categories')
            .then((response) => {
                return response.json()
            })
            .then((categ) => {
                this.setState({ categories: categ })
            })
    }

    render() {
        if (this.state.categories.length > 0) {
            return (
                <div className="col-md-12">
                    {
                        this.state.categories.map((categorie) => {
                            return <Cell key={ categorie.id }
                                         name={categorie.name}
                                         url_image={categorie.url_image}/>
                        })
                    }
                </div>
            )
        } else {
            return <p className="text-center">Cargando categorias...</p>
        }
    }

}

class Cell extends React.Component {
    render() {
        return(
            <div className="Cell col-xs-12 col-sm-6 col-md-4 col-lg-3"><a href="http://www.google.com" target="_blank" rel="noopener noreferrer">{this.props.name}</a></div>
        )
    }
}

export default CategoriesApp
