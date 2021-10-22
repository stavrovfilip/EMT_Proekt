import React from "react";
import {userRepository} from "../../repository/userRepository";
import {Link, useHistory} from "react-router-dom";


const Login = () => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        username: "",
        password: "",
    });

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = async (e) => {
        e.preventDefault();
        const username = formData.username;
        const password = formData.password;
        await authenticate(username, password);
        await getUser();
        history.push("/articles");
    }

    const authenticate = async (username, password)  => {
        await userRepository.authenticate(username, password)
            .then(t => {
                let token = t.data;
                console.log(token);
                localStorage.setItem("token", token);
            }).catch(error => console.log(error));
    }

    const getUser = async () => {
        let token = localStorage.getItem("token");
        if(token){
            await userRepository.getUserByToken(token)
                .then(data => {
                    console.log(data);
                    let userStringify = JSON.stringify(data.data);
                    localStorage.setItem("user",userStringify);
                }).catch(error => console.log(error));
        }
    }

    return (
        <div className="d-flex pt-5 mt-5">
            <div className="d-flex flex-column shadow-lg p-3 mb-5 bg-body rounded w-25 m-auto ">
                <div className="border-bottom">
                    <h3>Login</h3>
                </div>
                <form className="d-flex flex-column justify-content-center" onSubmit={onFormSubmit}>
                    <input type="text"
                           id="username"
                           name={"username"}
                           required
                           className="form-control mt-3"
                           placeholder={"Enter your username"}
                           onChange={handleChange}/>

                    <input type="password"
                           id="password"
                           name={"password"}
                           required
                           className="form-control mt-3"
                           placeholder={"Enter your password"}
                           onChange={handleChange}/>

                    <button type="submit"
                            className="btn btn-success w-100 mt-3">Sign in
                    </button>
                </form>
                <Link className="btn btn-danger w-100 mt-3" to={"/register"}>Sign up</Link>
            </div>
        </div>
    )
}

export default Login;