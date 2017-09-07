import React,{Component} from 'react'

class NegociationTable extends React.Component{
    render(){
        return(
            <div className="row">
                <div className="col-lg-12">
                    <div className="card">
                        <div className="card-block">
                            <table className="table table-bordered table-striped table-sm">
                                <thead>
                                <tr>
                                    <th>User who are Interest</th>
                                    <th>Email</th>
                                    <th>Price</th>
                                    <th>Call him</th>
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
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}
export default NegociationTable;