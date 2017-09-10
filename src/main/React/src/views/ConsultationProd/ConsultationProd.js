import React, {Component} from 'react';
import {apiRequest} from "../../index";
import { Bar, Line } from 'react-chartjs-2';
import { Dropdown, DropdownMenu, DropdownItem, Progress } from 'reactstrap';

const brandPrimary =  '#20a8d8';
const brandSuccess =  '#4dbd74';
const brandInfo =     '#63c2de';
const brandDanger =   '#f86c6b';
var data1 = [];
var data2 = [];
var data3 = [];
const mainChartOpts = {
    maintainAspectRatio: false,
    legend: {
        display: false
    },
    scales: {
        xAxes: [{
            gridLines: {
                drawOnChartArea: false,
            }
        }],
        yAxes: [{
            ticks: {
                beginAtZero: true,
                maxTicksLimit: 5,
                stepSize: Math.ceil(250 / 5),
                max: 250
            }
        }]
    },
    elements: {
        point: {
            radius: 0,
            hitRadius: 10,
            hoverRadius: 4,
            hoverBorderWidth: 3,
        }
    }
}

function convertHex(hex,opacity) {
    hex = hex.replace('#','');
    var r = parseInt(hex.substring(0,2), 16);
    var g = parseInt(hex.substring(2,4), 16);
    var b = parseInt(hex.substring(4,6), 16);

    var result = 'rgba('+r+','+g+','+b+','+opacity/100+')';
    return result;
}

const mainChart = {
    labels: ['M', 'T', 'W', 'T', 'F', 'S', 'S', 'M', 'T', 'W', 'T', 'F', 'S', 'S', 'M', 'T', 'W', 'T', 'F', 'S', 'S', 'M', 'T', 'W', 'T', 'F', 'S', 'S'],
    datasets: [
        {
            label: 'My First dataset',
            backgroundColor: convertHex(brandInfo,10),
            borderColor: brandInfo,
            pointHoverBackgroundColor: '#fff',
            borderWidth: 2,
            data: data1
        },
        {
            label: 'My Second dataset',
            backgroundColor: 'transparent',
            borderColor: brandSuccess,
            pointHoverBackgroundColor: '#fff',
            borderWidth: 2,
            data: data2
        },
        {
            label: 'My Third dataset',
            backgroundColor: 'transparent',
            borderColor: brandDanger,
            pointHoverBackgroundColor: '#fff',
            borderWidth: 1,
            borderDash: [8, 5],
            data: data3
        }
    ]
}
class ConsultationProd extends Component {

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
            <div className="card">
                <div className="card-block">
                    <div className="row">
                        <div className="col-sm-5">
                            <h4 className="card-title mb-0">Production</h4>
                            <div className="small text-muted">Statistics</div>
                        </div>
                        <div className="col-sm-7 hidden-sm-down">
                            <button type="button" className="btn btn-primary float-right"><i className="icon-cloud-download"></i></button>
                            <div className="btn-toolbar float-right" role="toolbar" aria-label="Toolbar with button groups">
                                <div className="btn-group mr-3" data-toggle="buttons" aria-label="First group">
                                    <label className="btn btn-outline-secondary">
                                        <input type="radio" name="options" id="option1"/> Country
                                    </label>
                                    <label className="btn btn-outline-secondary active">
                                        <input type="radio" name="options" id="option2" defaultChecked/> City
                                    </label>
                                    <label className="btn btn-outline-secondary">
                                        <input type="radio" name="options" id="option3"/> Type
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="chart-wrapper" style={{height: 300 + 'px', marginTop: 40 + 'px'}}>
                        <Line data={mainChart} options={mainChartOpts} height={300}/>
                    </div>
                </div>
                <div className="card-footer">

                </div>
            </div>
        )
    }
}

export default ConsultationProd;
