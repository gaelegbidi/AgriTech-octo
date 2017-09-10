import React, { Component } from 'react';
import {apiRequest, apiStorageRequest} from "../../index";
import { ToastContainer,toast } from 'react-toastify';
import {Router} from 'react-router-dom'

class EditProduct extends Component {

    constructor(props) {
        super(props);
        this.state = {
                image:null,
                description:''
                    };
        console.log(props)
        apiRequest.get("/products/"+props.match.params.id).then((response) => {
            let product = response.data;
            this.setState(product);

        })


    }


    onChange = (e) => {
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

    editProduct = async (e) => {
        e.preventDefault();
        apiRequest.headers = {};
        apiRequest.put('/products/'+ this.state.id ,this.state)
            .then((response) => {
            console.log(response.data)
                setTimeout(()=>{
                alert(" bien modifier")
                     this.props.history.push(`/products`)
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
                  <p className="text-muted">Edit Product</p>
                    <div className="input-group mb-3">
                    <span className="input-group-addon"></span>
                    <textarea  className="form-control"
                           name="description"
                           onChange={e => this.onChange(e)}
                           value={this.state.description}/>
                  </div>
                    <div className="form-group">
                        <img src={this.state.image} alt="---" className="img-responsive" width={'80%'} style={{marginLeft:'10%'}}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="street">Product Image</label>
                        <input type="file" id="file-input" name="image" accept="image/*"
                               onChange={e => this.onImageChange(e)}/>
                    </div>
                  <button type="button" className="btn btn-block btn-success"
                          onClick={this.editProduct}>Edit Product</button>
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>

         );

        }
      }

export default EditProduct;
