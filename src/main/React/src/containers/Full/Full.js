import React, { Component } from 'react';
import { Link, Switch, Route, Redirect } from 'react-router-dom'
import Header from '../../components/Header/';
import Sidebar from '../../components/Sidebar/';
import Breadcrumb from '../../components/Breadcrumb/';
import Aside from '../../components/Aside/';
import Footer from '../../components/Footer/';

import Dashboard from '../../views/Dashboard/';
import Users from '../../views/Users/';
import ConsultationProd from '../../views/ConsultationProd/';
import SubmitEstimate from '../../views/SubmitEstimate/';
import EditProfile from '../../views/EditProfile/';
import {AdminRoute} from '../../index';
import Exploitation from "../../views/Exploitation";
import Compaign from "../../views/Compaign";
import Product from "../../views/Product/Product";
import Consultation from "../../views/Consultation/Consultation";

class Full extends Component {
  render() {
    return (
      <div className="app">
        <Header history={this.props.history} />
        <div className="app-body">
          <Sidebar {...this.props}/>
          <main className="main">
            <Breadcrumb />
            <div className="container-fluid">
              <Switch>
                <Route path="/dashboard" name="Dashboard" component={Dashboard}/>
                <AdminRoute path="/users" name="Users" component={Users}/>
                <Route path="/consultationProd" name="Consulation Production" component={ConsultationProd}/>
                <Route path="/exploitation" name="Exploitation" component={Exploitation}/>
                <Route path="/submitEstimate" name="Submit Estimate" component={SubmitEstimate}/>
                <Route path="/compaign" name="Compaign" component={Compaign}/>
                <Route path="/product" name="Product" component={Product}/>
                <Route path="/consultation" name="Consultation" component={Consultation}/>
                <Route path="/editProfile/:id" name="EditProfile" component={EditProfile}/>
                <Redirect from="/" to="/dashboard"/>
              </Switch>
            </div>
          </main>
          <Aside />
        </div>
        <Footer />
      </div>
    );
  }
}

export default Full;
