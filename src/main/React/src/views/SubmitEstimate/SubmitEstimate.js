import React, { Component } from 'react';
import {apiRequest} from "../../index";

class SubmitEstimate extends Component{


    constructor(props, country = null) {
        super(props);
        this.state = {

                name: '',
                quantity: '',
                exploitationRef:'',
                productRef:'',
                compaignRef:'',
                exploitations:[],
                compaigns:[],
                products:[]

        };

       this.loadAllExploitation();
       this.loadAllCompaign();
       this.loadAllProduct();
    }

    onExploitationChange =(e) =>{
        console.log(e.target.name);
        this.setState({
            exploitation: e.target.value,
        });
    }
    onCompaignChange =(e) =>{
        console.log(e.target.name);
        this.setState({
            compaign: e.target.value,
        });
    }
    onProductChange =(e) =>{
        console.log(e.target.name);
        this.setState({
            product: e.target.value,
        });
    }

    loadAllExploitation(){
        apiRequest.headers = {};
        apiRequest.get('/exploitations')
            .then((response) => {
                console.log(response.status);
                console.log(response.data);
                this.setState({
                    exploitations: response.data._embedded.exploitations,
                });
                console.log(this.state);


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
    }
    loadAllCompaign(){
        apiRequest.headers = {};
        apiRequest.get('/compaigns')
            .then((response) => {
                console.log(response.status);
                console.log(response.data);
                let c = [{
                    name:"select compaign",
                    ref:''
                }].concat(response.data._embedded.compaigns);
                this.setState({
                    compaigns: c,
                });
                console.log(this.state);


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
    }
    loadAllProduct(){
        apiRequest.headers = {};
        apiRequest.get('/products')
            .then((response) => {
                console.log(response.status);
                console.log(response.data);
                this.setState({
                    products: response.data._embedded.products,
                });
                console.log(this.state);


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
    }
    productionRegister = async (e) => {
        e.preventDefault();

        apiRequest.headers = {};
        apiRequest.post('/productions/store',this.state)
            .then((response) => {
                console.log(response.data)
                setTimeout(()=>{
                    alert("prod bien enregistrer")
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
render()
    {
    return(
        <div className="row justify-content-center">
    <div className="col-sm-6">
    <div className="card">
        <div className="card-header">
            <strong>Submit</strong> <small>Estimate</small>
        </div>
        <div className="card-block">
            <div className="form-group">
                <label htmlFor="company">Agricol Compaign</label>
                <div className="col-md-9">
                    <select className="form-control" id="select" name="compaignRef"
                            onChange={e => this.onCompaignChange(e)} defaultValue={this.state.compaignRef}>
                        {this.state.compaigns.map((com, i) => <option key={i}
                                                                         value={com.ref}>{com.name}</option>)}
                    </select>

                </div>
            </div>
            <div className="form-group">
                <label htmlFor="street">Product Name</label>
                <div className="col-md-9">
                    <select className="form-control" id="select" name="productRef"
                            onChange={e => this.onProductChange(e)} defaultValue={this.state.productRef}>
                        {this.state.products.map((prod, i) => <option key={i}
                                                                      value={prod.ref}>{prod.name}</option>)}
                    </select>

                </div>
             </div>
            <div className="form-group">
                <label htmlFor="country">Quantity</label>
                <input type="text" className="form-control" id="country" name="quantity" placeholder="Quantity by Product unit"/>
            </div>
            <div className="form-group row">
                <label className="col-md-3 form-control-label" htmlFor="select">Select Exploitation</label>
                <div className="col-md-9">
                    <select className="form-control" id="select" name="exploitationRef"
                            onChange={e => this.onExploitationChange(e)} defaultValue={this.state.exploitationRef}>
                        {this.state.exploitations.map((ex, i) => <option key={i}
                                                                     value={ex.ref}>{ex.name}</option>)}
                    </select>

                </div>
            </div>
            <button type="button" className="btn btn-block btn-success"
                    onClick={this.productionRegister}>Submit Estimation</button>
        </div>
    </div>
</div>
        </div>
)
}
}
export default SubmitEstimate;
