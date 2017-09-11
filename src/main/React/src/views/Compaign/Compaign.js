import React, {Component} from 'react';
import {apiRequest} from "../../index";
import Notifications, {notify} from 'react-notify-toast';
import update from 'react-addons-update';

class Compaign extends Component {

    constructor(props, country = null) {
        super(props);
        this.state = {
            beginDate:'',
            endDate:'',
            name:'',
            ref:''
        };
        this.loadAllCountries();

    }

    onChange = (e) => {
        console.log(e.target.name);
        console.log(e.target.value);
        this.setState({
            [e.target.name]: e.target.value,

        });
    };
    onCountryChange = (e) => {
        console.log(e.target.name);
        this.loadAllCities(e.target.value)
    };
    onCityChange = (e) => {
        console.log(e.target.name);
        this.loadAllVillages(e.target.value)
    };

    loadAllCountries() {

        apiRequest.headers = {};
        apiRequest.get('/countries')
            .then((response) => {
                console.log(response.status);
                console.log(response.data);
                console.log(response.data._embedded.countries);
                this.setState({
                    countries: response.data._embedded.countries,
                    cities: [],
                    villages: []
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
     loadAllCities(country) {

        apiRequest.headers = {};
        apiRequest.get('/countries/'+country+'/cities')
            .then((response) => {
                console.log(response.status);
                console.log(response.data);
                this.setState({
                    country:country,
                    cities: response.data,
                    villages: []
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
    loadAllVillages(city) {

        apiRequest.headers = {};
        apiRequest.get('/cities/'+city+'/villages')
            .then((response) => {
                console.log(response.status);
                console.log(response.data);
                this.setState({
                    city:city,
                    villages: response.data
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

    compaignRegister = async (e) => {
        e.preventDefault();

        apiRequest.headers = {};
        console.log("----");
        console.dir(this);
        apiRequest.post('/compaigns',this.state)
            .then((response) => {
                console.log(response);
                setTimeout(()=>{
                    alert("compaign bien enregistrer")
                    // notify.show('Toasty!');
                    // this.props.history.push(`/`)
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
                <Notifications/>
                <div className="col-sm-6">
                    <div className="card">
                        <div className="card-header">
                            <strong>Add</strong>
                            <small>Compaign</small>
                        </div>
                        <div className="card-block">
                            <div className="form-group">
                                <label htmlFor="reference">Begin Date</label>
                                <input type="date" className="form-control" id="beginDate"
                                       placeholder="your compain begin date" name="beginDate"
                                       onChange={e => this.onChange(e)}
                                       value={this.state.beginDate}/>
                            </div>
                            <div className="form-group">
                                <label htmlFor="reference">End Date</label>
                                <input type="date" className="form-control" id="endDate"
                                       placeholder="your compain end date" name="endDate"
                                       onChange={e => this.onChange(e)}
                                       value={this.state.endDate}/>
                            </div>

                            <div className="form-group">
                            <label htmlFor="street">Compaign Reference</label>
                            <input type="text" className="form-control" id="ref"
                                   placeholder="Reference of exploitation" name="ref"
                                   onChange={e => this.onChange(e)}
                                   value={this.state.ref}/>
                            </div>
                            <div className="form-group">
                                <label htmlFor="street">Compaign Name</label>
                                <input type="text" className="form-control" id="name"
                                       placeholder="Name of exploitation" name="name"
                                       onChange={e => this.onChange(e)}
                                       value={this.state.name}/>
                            </div>


                            <button type="button" className="btn btn-block btn-success"
                                    onClick={this.compaignRegister}>Add Compaign
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        )
    }


}

export default Compaign;
