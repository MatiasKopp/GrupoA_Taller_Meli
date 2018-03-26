import React from 'react';
//import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

import { userActions } from '../_actions';

class RegisterPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            user: {
                firstName: '',
                lastName: '',
                username: '',
                password: '',
                categoriasSeleccionadas: []
            },
            categories: [],
            submitted: false
        };

        fetch('https://api.mercadolibre.com/sites/MLA/categories')
        .then(res => { return res.json()
        })
        .then((category) => {
            this.setState({categories:category})
        });
        
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.onCheckClick = this.onCheckClick.bind(this);
    }

    handleChange(event) {
        const { name, value } = event.target;
        const { user } = this.state;

        this.setState({
            user: {
                ...user,
                [name]: value
            }
        });
    }

    onCheckClick(selected) {
        const index = this.state.user.categoriasSeleccionadas.indexOf(selected);
        if (index < 0) {
          this.state.user.categoriasSeleccionadas.push(selected);
        } else {
          this.state.user.categoriasSeleccionadas.splice(index, 1);
        }
        this.setState({ categoriasSeleccionadas: [...this.state.user.categoriasSeleccionadas] });
        console.log(this.state.user.categoriasSeleccionadas);
        //localStorage.setItem("categorias", this.state.user.categoriasSeleccionadas);
      }

    handleSubmit(event) {
        event.preventDefault();

        this.setState({ submitted: true });
        const { user } = this.state;
        const { dispatch } = this.props;
        
        if (user.firstName && user.lastName && user.username && user.password && user.categoriasSeleccionadas ) {
            dispatch(userActions.register(user));
            localStorage.setItem("categorias", user.categoriasSeleccionadas);
        }
    }    

    render() {
    var categ = this.state.categories;
        const estilo ={overflowY: 'scroll', height: '130px'}
        const { registering  } = this.props;
        const { user, submitted } = this.state;
        return (
            <div className="col-md-6 col-md-offset-3">
                <h2>Registro</h2>
                <form name="form" onSubmit={this.handleSubmit}>
                    <div className={'form-group' + (submitted && !user.firstName ? ' has-error' : '')}>
                        <label htmlFor="firstName">Nombre</label>
                        <input type="text" className="form-control" name="firstName" value={user.firstName} onChange={this.handleChange} />
                        {submitted && !user.firstName &&
                            <div className="help-block">Nombre es requerido</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !user.lastName ? ' has-error' : '')}>
                        <label htmlFor="lastName">Apellido</label>
                        <input type="text" className="form-control" name="lastName" value={user.lastName} onChange={this.handleChange} />
                        {submitted && !user.lastName &&
                            <div className="help-block">Apellido es requerido</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !user.username ? ' has-error' : '')}>
                        <label htmlFor="username">Email</label>
                        <input type="email" className="form-control" name="username" value={user.username} onChange={this.handleChange} />
                        {submitted && !user.username &&
                            <div className="help-block">Email es requerido</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !user.password ? ' has-error' : '')}>
                        <label htmlFor="password">Contraseña</label>
                        <input type="password" className="form-control" name="password" value={user.password} onChange={this.handleChange} />
                        {submitted && !user.password &&
                            <div className="help-block">Contraseña requerida</div>
                        }
                    </div>
                    <label>Cuales son tus categorias preferidas?</label>
                    <div style={estilo} className={'form-group' + (submitted && !user.categoriasSeleccionadas ? ' has-error' : '')}>
                    {categ.map((categoria, i) =>
                    <div key = {i}>
                        <label className="checkbox-inline" htmlFor="preferences">
                        <input type="checkbox" key={i} className="form-control"
                        onClick={() => this.onCheckClick(categoria.id)}
                        name={categoria.name} value={categoria.id}/>
                        {categoria.name}
                        </label>
                    </div>
                    )}
                    {submitted && !user.categoriasSeleccionadas &&
                            <div className="help-block">Seleccione al menos un elemento</div>
                        }
                    </div>
                    <div className="form-group">
                        <button className="btn btn-primary">Registrar</button>
                        {registering
                             }
                        
                        <a href="/login" className="btn btn-link">Cancelar</a>
                    </div>
                </form>
            </div>
        );
    }
}

function mapStateToProps(state) {
    const { registering } = state.registration;
    return {
        registering
    };
}

//export default RegisterPage

 export default connect(mapStateToProps)(RegisterPage);
// export { connectedRegisterPage as RegisterPage };
