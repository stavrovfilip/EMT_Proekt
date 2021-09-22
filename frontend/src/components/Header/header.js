import React from 'react';
import {Link} from 'react-router-dom';

const header = (props) => {
    return (
        <header>
            <nav className="navbar navbar-expand-md navbar-dark navbar-fixed bg-dark">
                <a className="navbar-brand" href="/articles">AI Researches E-Shop</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                        aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarCollapse">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/articles"}>Articles</Link>
                        </li>
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/orders"}>Orders</Link>
                        </li>
                    </ul>
                    <form className="form-inline mr-sm-2">
                        <Link className="btn btn-outline-primary btn-primary text-light my-2 my-sm-0" to={"/login"}>Login</Link>
                    </form>
                </div>
            </nav>
        </header>
    )
}

export default header;
