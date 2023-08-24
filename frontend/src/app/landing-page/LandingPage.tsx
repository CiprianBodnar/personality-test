import React from 'react';
import UsernameSubmission from '../username-submition/UsernameSubmission';
import './LandingPage.css';

function LandingPage() {

    return (
        <div className="landing-container">
            <div className="landing-content">
                <h1 className="landing-title">Welcome to the Landing Page</h1>
                    <label className="username-label">Enter Your Username</label>
                    <UsernameSubmission />

            </div>
        </div>
    );
}
export default LandingPage;
