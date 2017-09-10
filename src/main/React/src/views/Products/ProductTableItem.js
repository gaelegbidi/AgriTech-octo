import React, {Component} from "react";
import {apiRequest} from "../../index";
import {Button, Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";

class ProductTableItem extends React.Component {

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


    editProduct = (e) =>{
        e.preventDefault();
        window.location.href="/editProduct/"+this.props.product.id

    }

    deleteProduct = (e)=> {
        e.preventDefault();
        apiRequest.delete('/products/'+this.props.product.id)
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
                <td>{this.props.product.ref}</td>
                <td>{this.props.product.name}</td>
                <td>
                    <button className="btn-primary" onClick={this.toggleWarning} style={{marginRight: "15px"}}><i
                        className="icon-eye">
                        <Modal isOpen={this.state.warning} toggle={this.toggleWarning}
                               className={'modal-warning modal-lg' + this.props.className}>
                            <ModalHeader toggle={this.toggleWarning}>More about Product</ModalHeader>
                            <ModalBody style={{padding:'10%'}}>
                                <img src={this.props.product.image} width={'50%'}  className="img-responsive" style={{marginLeft:'25%',borderRadius:'20%'}} alt="---"/><br/>
                                <label><b>Product Description</b>:</label><span className="pull-right" >{this.props.product.description}</span><br/>
                            </ModalBody>
                            <ModalFooter>
                                <button className="btn-warning" style={{marginRight:"15px"}} onClick={this.editProduct}><i className="icon-note">

                                </i>Edit Product</button>
                                <button className="btn-danger" style={{marginRight:"15px"}} onClick={this.deleteProduct}><i className="icon-trash">

                                </i>Delete Product</button>
                            </ModalFooter>
                        </Modal>
                    </i></button>


                </td>
            </tr>
        );
    }

}

export default ProductTableItem;