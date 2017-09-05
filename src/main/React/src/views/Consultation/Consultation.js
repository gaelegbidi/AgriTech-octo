import React, {Component} from 'react';
import {apiRequest} from "../../index";
import ConsultationTable from "./ConsultationTable";
import ConsultationTableItem from "./ConsultationTableItem";

class Consultation extends Component {

    constructor(props) {
        super(props);
        this.state = {
            productions: []
        };
        this.consultationItems = [];
        this.loadAllProductions();
        this.reloadHandler = this.reloadHandler.bind(this);
    }

    // componentWillMount(){
    //     this.loadAllUsers();
    // }

    loadAllProductions = () => {

        apiRequest.get('/productions')
            .then((response) => {
                console.log(response.data);
                this.setState({
                    productions: response.data
                });
                console.log(this.state);
            });
    };

    reloadHandler = () => {
        this.forceUpdate();

    };

    render() {
        return (
            <div className="animated fadeIn">
                <ConsultationTable>
                    {this.state.productions.map((p, i) => <ConsultationTableItem key={i} production={p}/>)}
                </ConsultationTable>

            </div>


        )
    }
}

export default Consultation;
