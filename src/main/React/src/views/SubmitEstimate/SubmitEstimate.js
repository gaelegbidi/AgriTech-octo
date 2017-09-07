import React, {Component} from 'react';
import {apiRequest} from "../../index";

class SubmitEstimate extends Component {


    constructor(props) {
        super(props);
        this.state = {
            isMounted: false,
            name: '',
            quantity: '',
            exploitationRef: '',
            productRef: '',
            compaignRef: '',
            exploitations: [],
            compaigns: [],
            products: []

        };

        this.loadAllExploitation();
        this.loadAllCompaign();
        this.loadAllProduct();
    }

    componentDidMount() {
        this.setState({isMounted: true})
    }

    componentWillUnmount() {
        this.setState({isMounted: false})
    }

    onExploitationChange = (e) => {
        console.log(e.target.name);
        if (this.state.isMounted) {
            this.setState({
                exploitationRef: e.target.value,
            });
        }

    };
    onCompaignChange = (e) => {
        console.log(e.target.name);
        if (this.state.isMounted) {
            this.setState({
                compaignRef: e.target.value,
            });
        }
    };
    onProductChange = (e) => {
        console.log(e.target.name);
        if (this.state.isMounted) {
            this.setState({
                productRef: e.target.value,
            });
        }
    };
    onChange = (e) => {
        console.log(e.target.name);
        if (this.state.isMounted) {
            this.setState({
                [e.target.name]: e.target.value,
            });
        }
    };

    loadAllExploitation = () => {
        apiRequest.get('/exploitations')
            .then((response) => {
                console.log(response.data);

                let explo = [{
                    name: "Select Exploitation",
                    ref: ''
                }].concat(response.data);
                if (this.state.isMounted) {
                    this.setState({
                        exploitations: explo,
                    });
                }

            });
    };

    loadAllCompaign() {
        apiRequest.get('/compaigns')
            .then((response) => {
                console.log(response.data);
                let c = [{
                    name: "select compaign",
                    ref: ''
                }].concat(response.data);
                if (this.state.isMounted) {
                    this.setState({
                        compaigns: c,
                    });
                }

            });
    }

    loadAllProduct=()=> {
        console.log(localStorage.getItem('role'));
        apiRequest.get('/products')
            .then((response) => {
                console.log(response.data);
                let prod = [{
                    name: "Select Product",
                    ref: ''
                }].concat(response.data);
                if (this.state.isMounted) {
                    this.setState({
                        products: prod,
                    });
                }
            });
    }

    productionRegister = (e) => {
        e.preventDefault();

        console.log(this.state);
        apiRequest.post('/productions', this.state)
            .then((response) => {
                console.log(response.data)
                setTimeout(() => {
                    alert("prod bien enregistrer")
                    // this.props.history.push(`/`)
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
            <div className="row justify-content-center">
                <div className="col-sm-6">
                    <div className="card">
                        <div className="card-header">
                            <strong>Submit</strong>
                            <small>Estimate</small>
                        </div>
                        <div className="card-block">
                            <div className="form-group">
                                <label htmlFor="company">Agricol Compaign</label>
                                <div className="col-md-9">
                                    <select required className="form-control" id="select" name="compaignRef"
                                            onChange={e => this.onCompaignChange(e)}
                                            defaultValue={this.state.compaignRef}>
                                        {this.state.compaigns.map((com, i) => <option key={i}
                                                                                      value={com.ref}>{com.name}</option>)}
                                    </select>

                                </div>
                            </div>
                            <div className="form-group">
                                <label htmlFor="street">Product Name</label>
                                <div className="col-md-9">
                                    <select required className="form-control" id="select" name="productRef"
                                            onChange={e => this.onProductChange(e)}
                                            defaultValue={this.state.productRef}>
                                        {this.state.products.map((prod, i) => <option key={i}
                                                                                      value={prod.ref}>{prod.name}</option>)}
                                    </select>

                                </div>
                            </div>
                            <div className="form-group">
                                <label htmlFor="country">Quantity</label>
                                <input required type="text" className="form-control" id="country" name="quantity"
                                       placeholder="Quantity by Product unit"
                                       onChange={e => this.onChange(e)}/>
                            </div>
                            <div className="form-group row">
                                <label className="col-md-3 form-control-label" htmlFor="select">Select
                                    Exploitation</label>
                                <div className="col-md-9">
                                    <select required className="form-control" id="select" name="exploitationRef"
                                            onChange={e => this.onExploitationChange(e)}
                                            defaultValue={this.state.exploitationRef}>
                                        {this.state.exploitations.map((ex, i) => <option key={i}
                                                                                         value={ex.ref}>{ex.name}</option>)}
                                    </select>

                                </div>
                            </div>
                            <button type="button" className="btn btn-block btn-success"
                                    onClick={this.productionRegister}>Submit Estimation
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default SubmitEstimate;
