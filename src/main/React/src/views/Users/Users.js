import React, {Component} from 'react';
import UserTable from './UsersTable'
import UsersTableItem from './UsersTableItem'
import {apiRequest} from "../../index";

class Users extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            users:[]
        };
        this.usersItems = [];
        this.loadAllUsers();
        this.reloadHandler=this.reloadHandler.bind(this);
    }

     // componentWillMount(){
     //     this.loadAllUsers();
     // }

    loadAllUsers(){

        apiRequest.headers = {};
        apiRequest.get('/users')
            .then((response) => {
                console.log(response.status);
                console.log(response.data);
                console.log(response.data._embedded.users);
                this.setState({users: response.data._embedded.users});
                console.log(this.state);


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

    reloadHandler = ()=>{
        this.forceUpdate();

    }

    render() {
        return (
            <div className="animated fadeIn">
                <UserTable>
                    {this.state.users.map( (u,i) => <UsersTableItem key={i} user={u}  />)}
                </UserTable>

            </div>


        )
    }
}

export default Users;
