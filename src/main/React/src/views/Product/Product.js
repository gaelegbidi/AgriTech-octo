import React, {Component} from 'react';
import {apiRequest, apiStorageRequest} from "../../index";
import update from 'react-addons-update';

class Product extends Component {

    constructor(props, country = null) {
        super(props);
        this.state = {
            name:'',
            ref:'',
            description:'',
            image:null
        };

    }

    onChange = (e) => {
        console.log(e.target.name);
        console.log(e.target.value);
        this.setState({
            [e.target.name]: e.target.value,

        });
    };

    onImageChange = (e) => {
        console.log(e.target.name,e.target.value);
        console.log(e.target.name,e.target.files);
        this.fileUpload(e.target.files[0]);

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

    productRegister = async (e) => {
        e.preventDefault();

        console.log("----");
        apiRequest.post('/products',this.state)
            .then((response) => {
                console.log(response);
                setTimeout(()=>{
                    alert("product bien enregistrer")
                    this.props.history.push('/products');
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
            <div className="row justify-content-center">
                <div className="col-sm-6">
                    <div className="card">
                        <div className="card-header">
                            <strong>Add</strong>
                            <small>Product</small>
                        </div>
                        <div className="card-block">
                            <div className="form-group">
                            <label htmlFor="street">Product Reference</label>
                            <input type="text" className="form-control" id="ref"
                                   placeholder="Reference of exploitation" name="ref"
                                   onChange={e => this.onChange(e)}
                                   value={this.state.ref}/>
                            </div>
                            <div className="form-group">
                                <label htmlFor="street">Product Name</label>
                                <input type="text" className="form-control" id="name"
                                       placeholder="Name of exploitation" name="name"
                                       onChange={e => this.onChange(e)}
                                       value={this.state.name}/>
                            </div>
                            <div className="form-group">
                                <label htmlFor="street">Product Description</label>
                                <input type="text" className="form-control" id="description"
                                       placeholder="product describe" name="description"
                                       onChange={e => this.onChange(e)}
                                       value={this.state.description}/>
                            </div>
                            <div className="form-group">
                                <label htmlFor="street">Product Image</label>
                            <input type="file" id="file-input" name="image" accept="image/*"
                                   onChange={e => this.onImageChange(e)}/>
                            </div>


                            <button type="button" className="btn btn-block btn-success"
                                    onClick={this.productRegister}>Add Product
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        )
    }


}

export default Product;
