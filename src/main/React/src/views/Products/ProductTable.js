import React,{Component} from 'react'
import {apiRequest} from "../../index";
import ProductTableItem from './ProductTableItem'
import {Button, DropdownItem} from "reactstrap";



class ProductTable extends React.Component{
    render(){
        return(
            <div className="row">
                <div className="col-lg-12">
                    <div className="card">
                        <div className="card-header">
                            <i className="fa fa-align-justify"></i> <span>Our Users</span>
                            <button className="btn-info pull-right" onClick={()=>{window.location.href='/product'}}> <i className="icon-plus"></i> Add Product</button>
                        </div>

                        <div className="card-block">
                            <table className="table table-bordered table-striped table-sm">
                                <thead>
                                <tr>
                                    <th>Product Reference</th>
                                    <th>Product Name</th>
                                    <th>Status</th>
                                </tr>
                                </thead>
                                <tbody>
                                {this.props.children}
                                </tbody>
                            </table>
                            <nav>
                                <ul className="pagination">
                                    <li className="page-item"><a className="page-link" href="#">Prev</a></li>
                                    <li className="page-item active">
                                        <a className="page-link" href="#">1</a>
                                    </li>
                                    <li className="page-item"><a className="page-link" href="#">2</a></li>
                                    <li className="page-item"><a className="page-link" href="#">3</a></li>
                                    <li className="page-item"><a className="page-link" href="#">4</a></li>
                                    <li className="page-item"><a className="page-link" href="#">Next</a></li>
                                    {/*<li><Button type="button" className="page-item" onClick={()=>{this.props.history.push('/register')}}>Add</Button></li>*/}
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}
export default ProductTable;