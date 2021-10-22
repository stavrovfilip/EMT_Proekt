import React from 'react';
import {Link} from 'react-router-dom';

const articleTerm = (props) => {
    return (
        <tr>
            <td>{props.term.title}</td>
            <td>{props.term.description}</td>
            <td>{props.term.price.amount}</td>
            <td>{props.term.price.currency}</td>
            <td>{props.term.sales}</td>
            <td className={"text-center"}>
                <a title={"Delete"} className={"btn btn-danger ml-2"}
                   onClick={() => props.onDelete(props.term.id.id)}>
                    Delete
                </a>
                <span>   </span>
                <Link className={"btn btn-success ml-2"}
                      onClick={() => props.onEdit(props.term.id.id)}
                      to={`/articles/edit/${props.term.id.id}`}>
                    Edit
                </Link>
            </td>
        </tr>
    )
}

export default articleTerm;
