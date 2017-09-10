import React from "react";
import {apiRequest} from "../../index";
import {Button, Modal, ModalBody, ModalFooter, ModalHeader} from "reactstrap";

class ConsultationTableItem extends React.Component {

    constructor(props) {
        super(props);
        // noinspection JSAnnotator
        this.state = {
            warning: false,
            price: '',
            productionId: this.props.production.id

        };
        this.toggleWarning = this.toggleWarning.bind(this);

    }

    toggleWarning() {
        this.setState({
            warning: !this.state.warning
        });
    }

    onPriceChange = (e) => {
        this.setState({
            price: e.target.value,
        });
    };

    storeNegociation = (e) => {
        e.preventDefault();
        console.log("----");
        console.log(this.state);

        apiRequest.post('/negociations', this.state)
            .then((response) => {
                console.log(response.data);
                alert("price added")
            })
            .catch((error) => {
                if (error.response) {
                    console.log(error.response.data);
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
            <tr>
                <td>{this.props.production.product.name}</td>
                <td>{this.props.production.user.username}</td>
                <td>{this.props.production.compaign.name}</td>
                <td>{this.props.production.exploitation.name}</td>
                <td>{this.props.production.quantity}</td>
                <td>
                    <button className="btn-primary" onClick={this.toggleWarning} style={{marginRight: "15px"}}><i
                        className="icon-eye">
                        <Modal isOpen={this.state.warning} toggle={this.toggleWarning}
                               className={'modal-warning modal-lg' + this.props.className}>
                            <ModalHeader toggle={this.toggleWarning}>You can let your price After</ModalHeader>
                            <ModalBody >
                                <img src={this.props.production.product.image} className="img-responsive" alt="---"  width={'80%'} style={{marginLeft:'10%'}}/><br/>
                                {this.props.production.product.description}
                            </ModalBody>
                            <ModalFooter>
                                <input name="propose" placeholder="your price"
                                       onChange={e => this.onPriceChange(e)}
                                       value={this.state.price}/>
                                <Button color="primary" onClick={this.storeNegociation}>Propose</Button>{' '}
                                <Button color="secondary" onClick={this.toggleWarning}>Cancel</Button>
                            </ModalFooter>
                        </Modal>
                    </i></button>
                </td>
            </tr>
        );
    }

}

export default ConsultationTableItem;