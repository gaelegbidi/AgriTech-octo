import React, {Component} from 'react';
import UserTable from './UsersTable'
import UsersTableItem from './UsersTableItem'
import {apiRequest} from "../../index";

class Users extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            users: []
        };
        this.usersItems = [];
        this.loadAllUsers();
        this.reloadHandler = this.reloadHandler.bind(this);
    }

    loadAllUsers() {

        apiRequest.headers = {};
        apiRequest.get('/users')
            .then((response) => {
                console.log(response.data);
                this.setState({users: response.data});
            })
    }
    reloadHandler = () => {
        this.forceUpdate();

    }

    render() {
        return (
            <div className="animated fadeIn">
                <UserTable>
                    {this.state.users.map((u, i) => <UsersTableItem key={i} user={u}/>)}
                </UserTable>

            </div>


        )
    }
}

export default Users;
