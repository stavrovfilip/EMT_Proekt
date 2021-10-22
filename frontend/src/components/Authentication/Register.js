import React from "react";
import {Link, useHistory} from "react-router-dom";
import {userRepository} from "../../repository/userRepository";

const Register = (props) => {

    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        username:"",
        name: "",
        surname: "",
        password: "",
        repeatPassword: ""
    });

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const username = formData.username;
        const name = formData.name;
        const surname = formData.surname;
        const password = formData.password;
        const repeatPassword = formData.repeatPassword;

        register(username,name,surname,password,repeatPassword);
        history.push("/login")
    }

    const register = (username,name,surname,password,repeatPassword) => {
        userRepository.register(username, name, surname, password, repeatPassword);
    }


    return (
        <div className="d-flex pt-5 mt-5">
            <div className="d-flex flex-column shadow-lg p-3 mb-5 bg-body rounded w-25 m-auto ">
                <div className="border-bottom">
                    <h3>Register</h3>
                </div>
                <form className="d-flex flex-column justify-content-center" onSubmit={onFormSubmit}>
                    <input type="text"
                           id="username"
                           name={"username"}
                           required
                           className="form-control mt-3"
                           placeholder={"Enter your username"}
                           onChange={handleChange}/>

                    <input type="text"
                           id="name"
                           name={"name"}
                           required
                           className="form-control mt-3"
                           placeholder={"Enter your name"}
                           onChange={handleChange}/>

                    <input type="text"
                           id="surname"
                           name={"surname"}
                           required
                           className="form-control mt-3"
                           placeholder={"Enter your surname"}
                           onChange={handleChange}/>

                    <input type="password"
                           id="password"
                           name={"password"}
                           required
                           className="form-control mt-3"
                           placeholder={"Enter your password"}
                           onChange={handleChange}/>

                    <input type="password"
                           id="repeatPassword"
                           name={"repeatPassword"}
                           required
                           className="form-control mt-3"
                           placeholder={"Repeat your password"}
                           onChange={handleChange}/>

                    <button type="submit"
                            className="btn btn-danger w-100 mt-3">Sign up
                    </button>
                </form>
                <Link className="text-primary text-center mt-3" to={"/login"}>Already have an account?</Link>
            </div>
        </div>
    )

}
export default Register;