import React, {Component} from 'react';
import {apiRequest, apiStorageRequest} from "../../index";


class Register extends Component {

    constructor(props){
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
            roles: [],
            roleRef:[],
            image: null
        };
        this.loadAllRoles();
    }



    onChange = (e) => {
        console.log(e.target.name,e.target.value);
        this.setState({
            [e.target.name]: e.target.value,
        });
    };

    onImageChange = (e) => {
        console.log(e.target.name,e.target.value);
        console.log(e.target.name,e.target.files);
        this.fileUpload(e.target.files[0]);

    };

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

    fileUpload=(file)=>{
        const formData = new FormData();
        formData.append('file',file);
        apiStorageRequest.post('/storeImages',formData)
            .then((response)=>{
                console.log(response.data);
                this.setState({
                   image: response.data,
                });
            });
    };

    userRegister = async (e) => {
        e.preventDefault();

        apiRequest.headers = {};
        apiRequest.post('/users', this.state)
            .then((response) => {
                console.log(response.data)
                setTimeout(() => {
                    alert("user bien enregistrer")
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

    loadAllRoles = () => {
        apiRequest.get('/roles')
            .then((response) => {
                console.log(response.data);
                let rols = [{
                    name: "Select Role",
                    ref: ''
                }].concat(response.data);
                this.setState({
                    roles: rols
                })
            })

    }


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
                                            <select multiple  className="form-control" id="mySelect" name="roleRef"
                                                    onChange={e => this.onRolesChange(e)}
                                                    defaultValue={this.state.roleRef}>
                                                {this.state.roles.map((r, i) => <option key={i}
                                                                                              value={r.ref}>{r.name}</option>)}
                                            </select>

                                    </div>
                                    <div className="form-group">
                                        <img src={this.state.image} alt="---" className="img-responsive" width={'80%'} style={{marginLeft:'10%'}}/>
                                    </div>
                                    <div className="form-group row">
                                        <label className="col-md-3 form-control-label" htmlFor="file-input">Profil
                                            Photo</label>
                                        <div className="col-md-9">
                                            <input type="file" id="file-input" name="image" accept="image/*"
                                                   onChange={e => this.onImageChange(e)}/>
                                        </div>
                                    </div>
                                    <button type="button" className="btn btn-block btn-success"
                                            onClick={this.userRegister}>Create Account
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        );

    }
}

export default Register;
