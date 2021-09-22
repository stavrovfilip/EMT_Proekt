import React from "react";

const articles = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <h4 class={"text-center"}>Here you can find the newest researches in the field of Artificial Intelligence</h4>
                    <br/>
                    <table className={"table table-striped border"}>
                        <thead className={"table-dark"}>
                        <tr>
                            <th scope={"col"}>Title</th>
                            <th scope={"col"}>Description</th>
                            <th scope={"col"}>Price-amount</th>
                            <th scope={"col"}>Price-currency</th>
                            <th scope={"col"}>Sales</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.articles.map((term) => {
                            return (
                                <tr>
                                    <td>{term.title}</td>
                                    <td>{term.description}</td>
                                    <td>{term.price.amount}</td>
                                    <td>{term.price.currency}</td>
                                    <td>{term.sales}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default articles;
