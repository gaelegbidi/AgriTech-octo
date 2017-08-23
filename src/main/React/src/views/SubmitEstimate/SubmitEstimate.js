import React, { Component } from 'react';
import {apiRequest} from "../../index";

class SubmitEstimate extends Component{

    prodRegister = async (e) => {
        e.preventDefault();

        apiRequest.headers = {};
        apiRequest.post('/productions',this.state)
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
                <input type="text" className="form-control" id="company" placeholder="the current agricol compaign"/>
            </div>
            <div className="form-group">
                <label htmlFor="vat">Farmer Name</label>
                <input type="text" className="form-control" id="vat" placeholder="farmer name"/>
            </div>
            <div className="form-group">
                <label htmlFor="street">Product Name</label>
                <input type="text" className="form-control" id="street" placeholder="your product name"/>
            </div>
            {/*<div className="row">*/}
                {/*<div className="form-group col-sm-8">*/}
                    {/*<label htmlFor="city">Quantity</label>*/}
                    {/*<input type="text" className="form-control" id="city" placeholder="Enter your city"/>*/}
                {/*</div>*/}
                {/*<div className="form-group col-sm-4">*/}
                    {/*<label htmlFor="postal-code">Postal Code</label>*/}
                    {/*<input type="text" className="form-control" id="postal-code" placeholder="Postal Code"/>*/}
                {/*</div>*/}
            {/*</div>*/}
            <div className="form-group">
                <label htmlFor="country">Quantity</label>
                <input type="text" className="form-control" id="country" placeholder="Quantity by Product unit"/>
            </div>
            <button type="button" className="btn btn-block btn-success"
                    onClick={this.prodRegister}>Submit Estimation</button>
        </div>
    </div>
</div>
        </div>
)
}
}
export default SubmitEstimate;
