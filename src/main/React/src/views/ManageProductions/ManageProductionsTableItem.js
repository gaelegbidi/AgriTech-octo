import React, {Component} from "react";
import {apiRequest} from "../../index";
import {Button, Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";
import NegociationTable from "../Negociation/NegociationTable";
import NegociationTableItem from "../Negociation/NegociationTableItem";

class ManageProductionsTableItem extends React.Component {

    constructor(props) {
        super(props);
        // noinspection JSAnnotator
        this.state = {
            warning: false,
            negociations:[],
        };
        this.toggleWarning = this.toggleWarning.bind(this);

    }

    loadAllNegociations() {
        apiRequest.headers = {};
        apiRequest.get('productions/'+this.props.production.id+'/negociations')
            .then((response) => {
                console.log(response.data);
                this.setState({negociations: response.data});
            })
    }


    toggleWarning() {
        this.setState({
            warning: !this.state.warning
        });
        this.loadAllNegociations();
    }


    editProduction = (e) =>{
        e.preventDefault();
        window.location.href="/submitEstimate/"+this.props.production.id

    }

    deleteProduction = (e)=> {
        e.preventDefault();
        apiRequest.delete('/productions/'+this.props.production.id)
            .then((response) => {
                console.log(response.status);
                console.log(response.data);
                setTimeout(()=>{
                    window.location.reload();
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


    }

    render() {
        return (
            <tr>
                <td>{this.props.production.product.name}</td>
                <td>{this.props.production.user.username}</td>
                <td>{this.props.production.compaign.name}</td>
                <td>{this.props.production.exploitation.name}</td>
                <td>{this.props.production.quantity}</td>
                <td>
                    <button className="btn-primary" style={{marginRight:"15px"}}><i className="icon-eye">

                    </i></button>
                    <button className="btn-warning" style={{marginRight:"15px"}} onClick={this.editProduction}><i className="icon-note">

                    </i></button>
                    <button className="btn-danger" style={{marginRight:"15px"}} onClick={this.deleteProduction}><i className="icon-trash">

                    </i></button>
                </td>
                <td>
                    <button className="btn-primary" onClick={this.toggleWarning} style={{marginRight: "15px"}}><i
                        className="icon-anchor">
                        <Modal isOpen={this.state.warning} toggle={this.toggleWarning}
                               className={'modal-warning modal-lg ' + this.props.className}>
                            <ModalHeader toggle={this.toggleWarning}>My Price Panel</ModalHeader>
                            <ModalBody>
                                <NegociationTable>
                                    {this.state.negociations.map((n, i) => <NegociationTableItem key={i} negociation={n}/>)}
                                </NegociationTable>
                            </ModalBody>
                            <ModalFooter>
                                <Button color="secondary" onClick={this.toggleWarning}>Cancel</Button>
                            </ModalFooter>
                        </Modal>
                    </i></button>
                </td>
            </tr>
        );
    }

}

export default ManageProductionsTableItem;