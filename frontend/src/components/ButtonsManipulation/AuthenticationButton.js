import React from "react";
import {Link} from "react-router-dom";

class AuthenticationButton extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            user:this.props.user,
            logoutUser:this.props.logout
        }
    }

    render() {
        console.log("render change");
        if (this.state.user) {
            return (
                <form className="form-inline mt-2 mt-md-0 ml-3" onSubmit={this.state.logoutUser}>
                    <button className="btn btn-success my-2 my-sm-0">Logout</button>
                </form>
            )
        } else {
            return (
                <form className="form-inline mt-2 mt-md-0 ml-3">
                    <Link className="btn btn-danger btn-outline-danger text-light my-2 my-sm-0" to={"/login"}>Login</Link>
                </form>
            )
        }
    }
}

export default AuthenticationButton;