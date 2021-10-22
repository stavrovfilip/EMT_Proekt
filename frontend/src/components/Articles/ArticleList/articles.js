import React from "react";
import ArticleTerm from "../ArticleTerm/articleTerm"
import {Link} from 'react-router-dom';

const articles = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <br/>
                    <h4 className={"text-center"}>Here you can find the newest researches in the field of Artificial Intelligence</h4>
                    <br/>
                    <table className={"table table-striped border"}>
                        <thead className={"table-dark"}>
                        <tr>
                            <th scope={"col"}>Title</th>
                            <th scope={"col"}>Description</th>
                            <th scope={"col"}>Price-amount</th>
                            <th scope={"col"}>Price-currency</th>
                            <th scope={"col"}>Sales</th>
                            <th scope={"col"}>Buttons</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.articles.map((term) => {
                            return (
                                <ArticleTerm term={term} onDelete={props.onDelete} onEdit={props.onEdit}/>
                            );
                        })}


                        </tbody>
                    </table>
                </div>
                <div className="col mb-3">
                    <div className="row">
                        <div className="col-sm-12 col-md-12">
                            <Link className={"btn btn-block btn-dark"} to={"/articles/add"}>Add new article</Link>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    );
}

export default articles;
