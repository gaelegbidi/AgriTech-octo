import React, {Component} from "react";
import {apiRequest} from "../../index";

class ConsultationTableItem extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            warning: false,

        };
        this.toggleWarning= this.toggleWarning.bind(this);
    }

    toggleWarning() {
        this.setState({
            warning: !this.state.warning
        });
    }

    editProfile = (e) =>{
        e.preventDefault();
        window.location.href="/editProfile/"+this.props.user.id

    }

    deleteUser = (e)=> {
        e.preventDefault();
            console.log(this.props.user.id);
            console.log(e.target);
        apiRequest.delete('/users/'+this.props.user.id)
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
                    <button className="btn-primary" onClick={this.toggleWarning} style={{marginRight:"15px"}}><i className="icon-eye">
                        <Modal isOpen={this.state.warning} toggle={this.toggleWarning} className={'modal-warning ' + this.props.className}>
                            <ModalHeader toggle={this.toggleWarning}>Modal title</ModalHeader>
                            <ModalBody>
                                {this.props.production.product.description}
                            </ModalBody>
                            <ModalFooter>
                                <Button color="primary" onClick={this.toggleWarning}>Tell me your Price</Button>{' '}
                                <Button color="secondary" onClick={this.toggleWarning}>Cancel</Button>
                            </ModalFooter>
                        </Modal>
                    </i></button>
                    <button className="btn-warning" style={{marginRight:"15px"}} onClick={this.editProfile}><i className="icon-note">

                    </i></button>
                    <button className="btn-danger" style={{marginRight:"15px"}} onClick={this.deleteUser}><i className="icon-trash">

                    </i></button>
                </td>
            </tr>
        );
    }

}

export default ConsultationTableItem;