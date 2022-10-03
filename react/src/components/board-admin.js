// import React, { Component } from "react";
// import UserService from "../services/user";
// import EventBus from "../common/EventBus";

// export default class BoardAdmin extends Component {
//   constructor(props) {
//     super(props);

//     this.state = {
//       content: ""
//     };
//   }

//   componentDidMount() {
//     UserService.getAdminBoard().then(
//       response => {
//         this.setState({
//           content: response.data
//         });
//       },
//       error => {
//         this.setState({
//           content:
//             (error.response &&
//               error.response.data &&
//               error.response.data.message) ||
//             error.message ||
//             error.toString()
//         });

//         if (error.response && error.response.status === 401) {
//           EventBus.dispatch("logout");
//         }
//       }
//     );
//   }

//   render() {
//     return (
//       <div className="container">
//         <header className="jumbotron">
//           <h3>{this.state.content}</h3>

//           <table class="table table-striped">
//             <thead class="thead-dark">
//               <tr>
//                 <th scope="col">#</th>
//                 <th scope="col">Name</th>
//                 <th scope="col">Content</th>
//                 <th scope="col">Price</th>
//                 <th scope="col">Handle</th>
//               </tr>
//             </thead>
//             <tbody>
//               <tr>
//                 <th scope="row">1</th>
//                 <td>Mark</td>
//                 <td>Otto</td>
//                 <td>999</td>
//                 <td>
//                   <button type="button" class="btn btn-info">Edit</button>
//                   <button type="button" class="btn btn-secondary">Delete</button>
//                 </td>
//               </tr>
//             </tbody>
//           </table>
          
//         </header>
//       </div>
//     );
//   }
// }

import { Table } from 'semantic-ui-react'
import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Admin() {
    const [APIData, setAPIData] = useState([]);
    useEffect(() => {
        axios.get(`http://localhost:8080/api/products`)
            .then((response) => {
                setAPIData(response.data);
            })
    }, [])
    return (
        <div>
            <Table singleLine>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Name</Table.HeaderCell>
                        <Table.HeaderCell>content</Table.HeaderCell>
                        <Table.HeaderCell>price</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>

                <Table.Body>
  {APIData.map((data) => {
     return (
       <Table.Row>
          <Table.Cell>{data.name}</Table.Cell>
           <Table.Cell>{data.content}</Table.Cell>
           <Table.Cell>{data.price}</Table.Cell>
        </Table.Row>
   )})}
</Table.Body>
            </Table>
        </div>
    )
}