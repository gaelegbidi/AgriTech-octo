import React, {Component} from 'react';
import {apiRequest} from "../../index";

class Dashboard extends Component {

    constructor(props) {
        super(props);
        this.state = {
            userStats: {count: 0, acheteurCount: 0, partenaireCount: 0, publicCount: 0, ongCount: 0}
        };
        this.loadUsersStats();
    }

    loadUsersStats(){

        apiRequest.get('/users/stats')
            .then((response) => {
                this.setState({userStats: response.data});
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
    render(){
        return (
            <div className="animated fadeIn">
                <div className="row">
                    <div className="col-md-12">
                        <div className="card">
                            <div className="card-header">
                                Traffic &amp; Statistques
                            </div>
                            <div className="card-block">
                                <div className="row">
                                    <div className="col-sm-12 col-lg-4">
                                        <div className="row">
                                            <div className="col-sm-6">
                                                <div className="callout callout-info">
                                                    <small className="text-muted">Users</small><br/>
                                                    <strong className="h4">{this.state.userStats.count}</strong>
                                                    <div className="chart-wrapper">
                                                        <canvas id="sparkline-chart-1" width="100" height="30"></canvas>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="col-sm-6">
                                                <div className="callout callout-danger">
                                                    <small className="text-muted">Acheteurs</small><br/>
                                                    <strong className="h4">{this.state.userStats.acheteurCount}</strong>
                                                    <div className="chart-wrapper">
                                                        <canvas id="sparkline-chart-2" width="100" height="30"></canvas>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <hr className="mt-0"/>

                                    </div>
                                    <div className="col-sm-6 col-lg-4">
                                        <div className="row">
                                            <div className="col-sm-6">
                                                <div className="callout callout-warning">
                                                    <small className="text-muted">Partenaires</small><br/>
                                                    <strong className="h4">{this.state.userStats.partenaireCount}</strong>
                                                    <div className="chart-wrapper">
                                                        <canvas id="sparkline-chart-3" width="100" height="30"></canvas>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="col-sm-6">
                                                <div className="callout callout-success">
                                                    <small className="text-muted">Publics</small><br/>
                                                    <strong className="h4">{this.state.userStats.publicCount}</strong>
                                                    <div className="chart-wrapper">
                                                        <canvas id="sparkline-chart-4" width="100" height="30"></canvas>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <hr className="mt-0"/>

                                    </div>
                                    <div className="col-sm-6 col-lg-4">
                                        <div className="row">
                                            <div className="col-sm-6">
                                                <div className="callout">
                                                    <small className="text-muted">ONG</small><br/>
                                                    <strong className="h4">{this.state.userStats.ongCount}</strong>
                                                    <div className="chart-wrapper">
                                                        <canvas id="sparkline-chart-5" width="100" height="30"></canvas>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <hr className="mt-0"/>

                                    </div>
                                </div>
                                <br/>


                            </div>

                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default Dashboard;
