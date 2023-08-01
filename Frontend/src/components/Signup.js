import React, { useEffect, useState } from 'react'
import Banner from './Register/Banner'
import { Link, useNavigate } from 'react-router-dom'
import axios from 'axios'
import Login from './Login'

const Signup = () => {

    let navigate = useNavigate();
    const [enteredUsername, setEnteredUsername] = useState('');
    const [enteredEmail, setEnteredEmail] = useState('');
    const [enteredPassword, setEnteredPassword] = useState('');
    const [enteredRole, setEnteredRole] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const userNameInputChangeHandler = event => {
        setEnteredUsername(event.target.value);
    }

    const emailInputChangeHandler = event => {
        setEnteredEmail(event.target.value);
    };

    const passwordInputChangeHandler = event => {
        setEnteredPassword(event.target.value);
    };

    useEffect(() => {
        setEnteredRole("user");
    })

    async function formSubmissionHandler(event) {
        event.preventDefault();

        if (enteredUsername.trim() === '') {
            setErrorMessage('Please enter a username!');
        } else if (enteredEmail.trim() === '') {
            setErrorMessage('Please enter an email address!');
        } else if (!isValidEmail(enteredEmail)) {
            setErrorMessage('Please enter a valid email address!');
        } else if (enteredPassword.trim() === '') {
            setErrorMessage('Please enter a password!');
        }

        else {
            try {
                await axios.post('http://localhost:8092/users/add',
                    {
                        name: enteredUsername,
                        password: enteredPassword,
                        email: enteredEmail,
                        role: enteredRole
                    });
                alert("You are successfully Registered.Click OK to continue");
                setEnteredEmail("");
                setEnteredPassword("");
                setEnteredUsername("");
                setErrorMessage("");
                navigate("/");
            }
            catch (err) {
                alert("Username Already Exists!");
            }

        }

        console.log(enteredEmail);
        console.log(enteredPassword);
        console.log(enteredUsername);
    };


    const isValidEmail = (email) => {
        const emailRegex = /^\S+@\S+\.\S+$/;
        return emailRegex.test(email);
    };

    return (
        <section id="register-page">

            <Banner />

            <div className="right">
                <div className="register-card">
                    <h2>Register Yourself</h2>
                    <form action="">
                        <div>
                            <input type="text" placeholder="Username" value={enteredUsername} onChange={userNameInputChangeHandler} />
                            {/* <span class="error">Invalid input</span> */}
                        </div>
                        <div>
                            <input type="email" placeholder="Email Id" value={enteredEmail} onChange={emailInputChangeHandler} />
                            {/* <span class="error">Invalid input</span> */}
                        </div>
                        <div>
                            <input type="password" placeholder="Password" value={enteredPassword} onChange={passwordInputChangeHandler} />
                            {/* <span class="error">Invalid input</span> */}
                        </div>
                        {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
                        <div>
                            <input type="submit" onClick={formSubmissionHandler} value="Register" />
                        </div>
                    </form>
                    <div class="form-redirects">
                        <Link to="/" className="redirect">Already have an account? Login</Link>
                    </div>
                </div>
            </div>
        </section>
    )
}

export default Signup