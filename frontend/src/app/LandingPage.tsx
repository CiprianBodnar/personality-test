import React from 'react';
import UsernameSubmission from './UsernameSubmission';

function LandingPage() {
    const handleUsernameSubmit = (username: string) => {
        alert(`Submitted username: ${username}`);
    };

    return (
        <div className="landing-page">
            <h1>Welcome to the Landing Page</h1>
            <UsernameSubmission onSubmit={handleUsernameSubmit} />
        </div>
    );
}

export default LandingPage;
