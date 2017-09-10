import React, { Component } from 'react';
import {apiRequest} from "../../index";
import {Router} from 'react-router-dom'

class EditUser extends Component {

    constructor(props) {
        super(props);
        this.state = {

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
                society: '',
                roles:[],
                roleRef: '' ,
                roleTabs:[],

                    };
        console.log(props)
        apiRequest.get("/users/"+props.match.params.id).then((response) => {
            console.log(response.data)
            let user = response.data;
            user.roleRef=user.roles.map(r=>r.ref).join('|')
            this.setState(user);

        })

        apiRequest.get("/roles").then((response) => {
            console.log(response.data)
            this.setState({
                roleTabs:response.data
            });

        })


    }

    onRolesChange = (e) => {
        console.log(e.target.name,e.target.value);
        console.log(document.getElementById("mySelect").length);
        let selects = document.getElementById("mySelect");
        var selected1 = [];
        for (var i = 0; i < selects.length; i++) {
            if (selects.options[i].selected) selected1.push(selects.options[i].value)
        }
        console.log(selected1.join("|"))
        this.setState({
            [e.target.name]:selected1.join("|"),
        });
    };

    onChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value,
        });
    };

    editUser = async (e) => {
        e.preventDefault();
        apiRequest.headers = {};
        apiRequest.put('/users/'+ this.state.id ,this.state)
            .then((response) => {
            console.log(response.data)
                setTimeout(()=>{
                alert("user bien modifier")
                     this.props.history.push(`/users`)
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
                  <h1>Edition</h1>
                  <p className="text-muted">Edit your account</p>
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
                  {/*<div className="input-group mb-3">*/}
                    {/*<span className="input-group-addon"><i className="icon-lock"></i></span>*/}
                    {/*<input type="password" className="form-control" placeholder="Password"*/}
                    {/*name="password"*/}
                           {/*onChange={e => this.onChange(e)}*/}
                           {/*value={this.state.password}/>*/}
                  {/*</div>*/}
                  {/*<div className="input-group mb-4">*/}
                    {/*<span className="input-group-addon"><i className="icon-lock"></i></span>*/}
                    {/*<input type="password" className="form-control" placeholder="Repeat password"/>*/}
                  {/*</div>*/}
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
                        <select multiple  className="form-control" id="mySelect" name="roleRef"
                                onChange={e => this.onRolesChange(e)}
                                defaultValue={this.state.roleRef}>
                            {this.state.roleTabs.map((r, i) => <option key={i}
                                                                    value={r.ref}>{r.name}</option>)}
                        </select>
                    </div>
                  <button type="button" className="btn btn-block btn-success"
                          onClick={this.editUser}>Edit User</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

         );

        }
      }

export default EditUser;
