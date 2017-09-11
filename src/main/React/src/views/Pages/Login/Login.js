import React, {Component} from 'react';
import {authRequest} from "../../../index";
import {apiRequest} from "../../../index";

class Login extends Component {

    state = {
        username: '',
        password: '',
        grant_type: 'password'
    };

    onChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value,
        });
    };

    onSubmit = async (e) => {
        e.preventDefault();
        authRequest.post('/oauth/token', 'grant_type=password&username=' + this.state.username +
            '&password=' + this.state.password)
            .then((response) => {
                localStorage.setItem('access_token', response.data.access_token);
                localStorage.setItem('refresh_token', response.data.refresh_token);
                localStorage.setItem('expired_in', response.data.expired_in);

                apiRequest.get("/users/info").then((res) => {
                    console.log(res.data);
                    localStorage.setItem('username', res.data.username);
                    localStorage.setItem('image', res.data.image);
                    localStorage.setItem('roles', res.data.roles.map(r=>r.ref).join('|'));
                });

                setTimeout(() => {
                    this.props.history.push(`/`)
                }, 100);
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
            <div className="app flex-row align-items-center"  style={{background: 'url(/img/login-bg.jpg) no-repeat', backgroundSize:' 100% 100%'}}>
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
                                        <img src="./img/logo.png" width="80%" alt="AgriTech-Octo"/>
                                        <div>
                                            <h2>---</h2>
                                            <p>Voila comment nous aidons les agriculteurs avec la technologie à
                                                octo!!!.</p>
                                            <button type="button" className="btn btn-primary active mt-3" onClick={()=>{window.location.href="https://agritech.github.io"}}>About Us
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
