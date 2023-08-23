import React, { useState } from 'react';
import axios from "axios";
import { useNavigate } from 'react-router-dom';

interface UsernameSubmissionProps {
    onSubmit: (username: string) => void;
}

function UsernameSubmission({ onSubmit }: UsernameSubmissionProps) {
    const [username, setUsername] = useState<string>('');
    const [response, setResponse] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(event.target.value);
    };

    const handleSubmit = async () => {
        try {
            console.log(username)
            const response = await axios.post('http://localhost:8090/api/users/register', null,{
                params: {
                    username: username
                }
            });
            setResponse(response.data);
            navigate('/questions');
            console.log(response.data); // Response from the backend
        } catch (error) {
            console.error('Error submitting username:', error);
        }
    };

    return (
        <div className="username-submission">
            <input
                type="text"
                value={username}
                onChange={handleInputChange}
                placeholder="Enter a username"
            />
            <button onClick={handleSubmit}>Submit</button>
        </div>
    );
}

export default UsernameSubmission;
