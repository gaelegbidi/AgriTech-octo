import React, {Component} from "react";
import {apiRequest} from "../../index";
import {Button, Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";

class UsersTableItem extends React.Component {

    constructor(props) {
        super(props);
        // noinspection JSAnnotator
        this.state = {
            warning: false
        };
        this.toggleWarning = this.toggleWarning.bind(this);

    }



    toggleWarning() {
        this.setState({
            warning: !this.state.warning
        });
    }


    editUser = (e) =>{
        e.preventDefault();
        window.location.href="/editUser/"+this.props.user.id

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
                <td>{this.props.user.username}</td>
                <td>{this.props.user.email}</td>
                <td>{this.props.user.roles.map(r=>r.ref).join('|')}</td>
                <td>
                    <button className="btn-primary" onClick={this.toggleWarning} style={{marginRight: "15px"}}><i
                        className="icon-eye">
                        <Modal isOpen={this.state.warning} toggle={this.toggleWarning}
                               className={'modal-warning modal-lg' + this.props.className}>
                            <ModalHeader toggle={this.toggleWarning}>More about user</ModalHeader>
                            <ModalBody style={{padding:'10%'}}>
                                <img src={this.props.user.image} width={'50%'}  className="img-responsive" style={{marginLeft:'25%',borderRadius:'20%'}} alt="---"/><br/>
                                <label><b>Email</b>:</label><span className="pull-right" >{this.props.user.email}</span><br/>
                                <label><b>Function</b>:</label><span className="pull-right" >{this.props.user.function}</span><br/>
                                <label><b>Society</b>:</label><span className="pull-right" >{this.props.user.society}</span><br/>
                                <label><b>Telephone</b>:</label><span className="pull-right" >{this.props.user.phone}</span><br/>
                                <label><b>Adress</b>:</label><span className="pull-right" >{this.props.user.address}</span><br/>
                                <label><b>Country</b>:</label><span className="pull-right" >{this.props.user.country}</span><br/>
                                <label><b>City</b>:</label><span className="pull-right" >{this.props.user.city}</span><br/>


                            </ModalBody>
                            <ModalFooter>
                                <button className="btn-warning" style={{marginRight:"15px"}} onClick={this.editUser}><i className="icon-note">

                                </i>Edit User</button>
                                <button className="btn-danger" style={{marginRight:"15px"}} onClick={this.deleteUser}><i className="icon-trash">

                                </i>Delete User</button>
                            </ModalFooter>
                        </Modal>
                    </i></button>


                </td>
            </tr>
        );
    }

}

export default UsersTableItem;