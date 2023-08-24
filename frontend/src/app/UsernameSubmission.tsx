import React, { useState } from 'react';
import axios from "axios";
import { useNavigate } from 'react-router-dom';
import {UserResponse} from "./model/UserResponse";

interface UsernameSubmissionProps {
    onSubmit: (username: string) => void;
}

function UsernameSubmission({ onSubmit }: UsernameSubmissionProps) {
    const [username, setUsername] = useState<string>('');
    const [responseApi, setResponse] = useState<UserResponse>();
    const navigate = useNavigate();

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(event.target.value);
    };

    const handleSubmit = async () => {
        try {
            const response = await axios.post('http://localhost:8090/api/users/register', null,{
                params: {
                    username: username
                }
            });
            setResponse(response.data);

            const userData = response.data;
            const mappedUserProfile: UserResponse = {
                id: userData.id,
                username: userData.username
            };

            const additionalData = {userId: mappedUserProfile.id, username: mappedUserProfile.username};
            navigate('/questions', {state: additionalData });

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
