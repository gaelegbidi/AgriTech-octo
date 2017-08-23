import React, { Component } from 'react';
import {apiRequest} from "../../../index";
import {Router} from 'react-router-dom'

class Register extends Component {

    state = {
        username: '',
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        phone: '',
        address: '',
        city: '',
        country: '',
        function: '',
        soiety: '',
        roles: '' ,

    };

    onChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value,
        });
    };

    userRegister = async (e) => {
        e.preventDefault();

        apiRequest.headers = {};
        apiRequest.post('/users',this.state)
            .then((response) => {
            console.log(response.data)
                setTimeout(()=>{
                alert("user bien enregistrer")
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
            <div className="col-md-6">
              <div className="card mx-4">
                <div className="card-block p-4">
                  <h1>Register</h1>
                  <p className="text-muted">Create your account</p>
                  <div className="input-group mb-3">
                    <span className="input-group-addon"><i className="icon-user"></i></span>
                    <input type="text" className="form-control" placeholder="Username"
                           name="username"
                           onChange={e => this.onChange(e)}
                           value={this.state.username}/>
                  </div>
                    <div className="input-group mb-3">
                    <span className="input-group-addon"><i className="icon-user"></i></span>
                    <input type="text" className="form-control" placeholder="Firstname"
                           name="firstName"
                           onChange={e => this.onChange(e)}
                           value={this.state.firstName}/>
                  </div>
                    <div className="input-group mb-3">
                    <span className="input-group-addon"><i className="icon-user"></i></span>
                    <input type="text" className="form-control" placeholder="Lastname"
                           name="lastName"
                           onChange={e => this.onChange(e)}
                           value={this.state.lastName}/>
                  </div>
                  <div className="input-group mb-3">
                    <span className="input-group-addon">@</span>
                    <input type="text" className="form-control" placeholder="Email"
                    name="email"
                           onChange={e => this.onChange(e)}
                           value={this.state.email}/>
                  </div>
                  <div className="input-group mb-3">
                    <span className="input-group-addon"><i className="icon-lock"></i></span>
                    <input type="password" className="form-control" placeholder="Password"
                    name="password"
                           onChange={e => this.onChange(e)}
                           value={this.state.password}/>
                  </div>
                  <div className="input-group mb-4">
                    <span className="input-group-addon"><i className="icon-lock"></i></span>
                    <input type="password" className="form-control" placeholder="Repeat password"/>
                  </div>
                    <div className="input-group mb-3">
                        <span className="input-group-addon"><i className="icon-user"></i></span>
                        <input type="text" className="form-control" placeholder="Phone"
                               name="phone"
                               onChange={e => this.onChange(e)}
                               value={this.state.phone}/>
                    </div>
                    <div className="input-group mb-3">
                        <span className="input-group-addon"><i className="icon-user"></i></span>
                        <input type="text" className="form-control" placeholder="Adress"
                               name="address"
                               onChange={e => this.onChange(e)}
                               value={this.state.address}/>
                    </div>
                    <div className="input-group mb-3">
                        <span className="input-group-addon"><i className="icon-user"></i></span>
                        <input type="text" className="form-control" placeholder="City"
                               name="city"
                               onChange={e => this.onChange(e)}
                               value={this.state.city}/>
                    </div>
                    <div className="input-group mb-3">
                        <span className="input-group-addon"><i className="icon-user"></i></span>
                        <input type="text" className="form-control" placeholder="Country"
                               name="country"
                               onChange={e => this.onChange(e)}
                               value={this.state.country}/>
                    </div>
                    <div className="input-group mb-3">
                        <span className="input-group-addon"><i className="icon-user"></i></span>
                        <input type="text" className="form-control" placeholder="Function"
                               name="function"
                               onChange={e => this.onChange(e)}
                               value={this.state.function}/>
                    </div>
                    <div className="input-group mb-3">
                        <span className="input-group-addon"><i className="icon-user"></i></span>
                        <input type="text" className="form-control" placeholder="Society"
                               name="society"
                               onChange={e => this.onChange(e)}
                               value={this.state.society}/>
                    </div>
                    <div className="input-group mb-3">
                        <span className="input-group-addon"><i className="icon-user"></i></span>
                        <select name="roles" onChange = { e => this.onChange(e)} defaultValue={this.state.roles} >
                            <option value="Acheteur" >Agriculteur Acheteur</option>
                            <option value="Partenaire" >Partenaire</option>
                            <option value="Admin">Administrateur</option>
                            <option value="Public" >Pouvoirs Public</option>
                            <option value="ONG" >ONG</option>
                        </select>
                    </div>
                  <button type="button" className="btn btn-block btn-success"
                          onClick={this.userRegister}>Create Account</button>
                </div>
                {/*<div className="card-footer p-4">*/}
                  {/*<div className="row">*/}
                    {/*<div className="col-6">*/}
                      {/*<button className="btn btn-block btn-facebook" type="button"><span>facebook</span></button>*/}
                    {/*</div>*/}
                    {/*<div className="col-6">*/}
                      {/*<button className="btn btn-block btn-twitter" type="button"><span>twitter</span></button>*/}
                    {/*</div>*/}
                  {/*</div>*/}
                {/*</div>*/}
              </div>
            </div>
          </div>
        </div>
      </div>

         );

        }
      }

export default Register;
