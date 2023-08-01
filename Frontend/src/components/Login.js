import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useState } from 'react'
import Banner from './Register/Banner'
import axios from 'axios'
const Login = () => {

    let navigate = useNavigate();
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const [userId, setUserId] = useState('');
    const [role, setRole] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const userNameInputChangeHandler = event => {
        setName(event.target.value);
    }

    const passwordInputChangeHandler = event => {
        setPassword(event.target.value);
    };

    function login(logInDTO) {
        console.log(logInDTO);
        return axios.post('http://localhost:8092/login', logInDTO);
    }

    function goToUserDashboard(userId) {
        console.log(localStorage.getItem('token'));
        navigate('/userdashboard/' + userId);
    }

    function goToadminDashboard(userId) {
        console.log(localStorage.getItem('token'));
        navigate('/adminhome/' + userId);
    }

    async function formSubmissionHandler(event) {
        event.preventDefault();

        const logInDTO = { name, password }
        login(logInDTO).then((res) => {
            console.log(res.data);
            localStorage.setItem('token', res.data.token);
            if (res.data.user.role === 'user') {
                goToUserDashboard(res.data.user.userId);
            }
            else if (res.data.user.role === 'admin') {
                goToadminDashboard(res.data.user.userId);
            }
        }).catch(error => {
            alert("Invalid Credentials!! Try again!");
            console.log(error);
        })



    }



    return (
        <section id="register-page">

            <Banner />

            <div class="right">
                <div class="register-card">
                    <h2>Log In</h2>
                    <form action="">
                        <div>
                            <input type="text" placeholder="Username" value={name} onChange={userNameInputChangeHandler} />

                        </div>
                        <div>
                            <input type="password" placeholder="Password" value={password} onChange={passwordInputChangeHandler} />

                        </div>
                        <div>
                            <input type="submit" onClick={formSubmissionHandler} value="Login" />
                        </div>
                    </form>
                    <div class="form-redirects">
                        <Link to="/signup" className="redirect">New here? Create an account</Link>
                    </div>
                </div>
            </div>
        </section>
    )
}

export default Login