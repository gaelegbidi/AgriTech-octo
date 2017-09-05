import React, {Component} from 'react';
import {apiRequest} from "../../index";
import ManageProductionsTable from "./ManageProductionsTable";
import ManageProductionsTableItem from "./ManageProductionsTableItem";

class ManageProductions extends Component {

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

        apiRequest.get('/users/productions')
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
                <ManageProductionsTable>
                    {this.state.productions ? this.state.productions.map((p, i) =>
                        <ManageProductionsTableItem key={i}
                                                    production={p}/>) : null}
                </ManageProductionsTable>

            </div>


        )
    }
}

export default ManageProductions;
