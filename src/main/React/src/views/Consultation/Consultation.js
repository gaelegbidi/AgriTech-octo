import React, { Component } from 'react';
import {apiRequest} from "../../index";
import ConsultationTable from "./ConsultationTable";
import ConsultationTableItem from "./ConsultationTableItem";

class Consultation extends Component{

    constructor(props) {
        super(props);
        this.state = {
            productions:[]
        };
        this.consultationItems = [];
        this.loadAllProductions();
        this.reloadHandler=this.reloadHandler.bind(this);
    }

    // componentWillMount(){
    //     this.loadAllUsers();
    // }

    loadAllProductions(){

        apiRequest.headers = {};
        apiRequest.get('/productions')
            .then((response) => {
                console.log(response.status);
                console.log(response.data);
                this.setState({productions: response.data._embedded.productions});
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

    reloadHandler = ()=>{
        this.forceUpdate();

    }

    render() {
        return (
            <div className="animated fadeIn">
                <ConsultationTable>
                    {this.state.productions.map( (p,i) => <ConsultationTableItem key={i} production={p}  />)}
                </ConsultationTable>

            </div>


        )
    }
}
export default Consultation;
