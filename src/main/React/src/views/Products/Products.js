import React, {Component} from 'react';
import ProductTable from './ProductTable'
import ProductTableItem from './ProductTableItem'
import {apiRequest} from "../../index";

class Products extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            products: []
        };
        this.productsItems = [];
        this.loadAllProducts();
        this.reloadHandler = this.reloadHandler.bind(this);
    }

    // componentWillMount(){
    //     this.loadAllUsers();
    // }

    loadAllProducts() {

        apiRequest.headers = {};
        apiRequest.get('/products')
            .then((response) => {
                console.log(response.data);
                this.setState({products: response.data});
            })
    }

    reloadHandler = () => {
        this.forceUpdate();

    }

    render() {
        return (
            <div className="animated fadeIn">
                <ProductTable>
                    {this.state.products.map((p, i) => <ProductTableItem key={i} product={p}/>)}
                </ProductTable>

            </div>


        )
    }
}

export default Products;
