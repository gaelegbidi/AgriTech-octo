import React, {Component} from 'react';
import {apiRequest} from "../../../index";
import {Router} from 'react-router-dom'



class Login extends Component {

    state = {
        username: '',
        password: '',
    };

    onChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value,
        });
    };

    onSubmit = async (e) => {
        e.preventDefault();

        apiRequest.headers = {};
        apiRequest.post('/users/login',this.state)
            .then((response) => {
                console.log(response.status);
                console.log(response.data);
                localStorage.setItem('token', response.data);
                setTimeout(()=>{
                    this.props.history.push(`/`)
                },100);
            })
            .catch((error) => {
                if (error.response) {
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else if (error.request) {
                    console.log(error.request);
                } else {
                    console.log('Error', error.message);
                }
                console.log(error.config);
            })

    };

    render() {
        return (
            <div className="app flex-row align-items-center">
                <div className="container">
                    <div className="row justify-content-center">
                        <div className="col-md-8">
                            <div className="card-group mb-0">
                                <div className="card p-4">
                                    <div className="card-block">
                                        <h1>Login</h1>
                                        <p className="text-muted">Sign In to your account</p>
                                        <div className="input-group mb-3">
                                            <span className="input-group-addon"><i className="icon-user"></i></span>
                                            <input type="text" className="form-control" placeholder="Username"
                                                   name='username'
                                                   onChange={e => this.onChange(e)}
                                                   value={this.state.username}/>
                                        </div>
                                        <div className="input-group mb-4">
                                            <span className="input-group-addon"><i className="icon-lock"></i></span>
                                            <input type="password" className="form-control" placeholder="Password"
                                                   name='password'
                                                   onChange={e => this.onChange(e)}
                                                   value={this.state.password}/>
                                        </div>
                                        <div className="row">
                                            <div className="col-6">
                                                <button type="button" className="btn btn-primary px-4"
                                                        onClick={this.onSubmit}>Login
                                                </button>
                                            </div>
                                            <div className="col-6 text-right">
                                                <button type="button" className="btn btn-link px-0">Forgot password?
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="card card-inverse card-primary py-5 d-md-down-none"
                                     style={{width: 44 + '%'}}>
                                    <div className="card-block text-center">
                                        <div>
                                            <h2>Sign up</h2>
                                            <p>Voila comment nous aidons les agriculteurs avec la technologie à octo!!!.</p>
                                            <button type="button" className="btn btn-primary active mt-3">Register
                                                Now!
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Login;