import React, {Component} from "react";
import {apiRequest} from "../../index";

class NegociationTableItem extends React.Component {

    render() {
        return (
            <tr>
                <td>{this.props.negociation.user.username}</td>
                <td>{this.props.negociation.user.email}</td>
                <td>{this.props.negociation.price} MAD</td>
                <td>
                    <span style={{marginRight:50}}> {this.props.negociation.user.phone}</span>
                    <button onClick={(e)=>{e.preventDefault(); window.location.href="tel:"+this.props.negociation.user.phone}} className="btn-success"
                            style={{marginRight:"15px"}}><i className="icon-phone">
                    </i></button>
                </td>
            </tr>
        );
    }

}

export default NegociationTableItem;