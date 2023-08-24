import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {Question} from "../model/Question";
import './QuestionSeries.css';
import {useLocation} from "react-router-dom";

function QuestionSeries() {

    const [questions, setQuestions] = useState<Question[]>([]);
    const [currentIndex, setCurrentIndex] = useState(0);
    const [result, setResult] = useState('');
    const [showNextButton, setShowNextButton] = useState(true);
    const [selectedItem, setSelectedItem] = useState<string | null>(null);
    const location = useLocation();
    const userIdFromObject = location.state?.userId;
    const username = location.state?.username;

    const handleItemClick = (item: string) => {
        setSelectedItem(item);
    };
    const handleNextClick = async () => {
        if (currentIndex < questions.length) {

            let questionId = questions[currentIndex].id;
            await axios.post('http://localhost:8090/api/answers/create', null, {
                params: {
                    questionId: questionId,
                    answer: selectedItem,
                    userId: userIdFromObject
                }
            });

            setCurrentIndex(currentIndex + 1);
        } else {
            setShowNextButton(false);
            const response = await axios.get('http://localhost:8090/api/users/result', {
                params: {
                    userId: userIdFromObject
                }
            });
            setResult("Result for the user: " + username + " is: " + response.data)
        }
    };


    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8090/api/questions/all');
            setQuestions(response.data);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    return (
        <div className="object-list">
            <h1>Are you an introvert or an extrovert?</h1>
            <div className="question-container">
                <p className="question">{questions[currentIndex]?.question}</p>
                <ul className="options-list">
                    {questions[currentIndex]?.options.map((option: string, index: number) => (
                        <li
                            key={index}
                            className={`option-item ${selectedItem === option ? 'selected' : ''}`}
                            onClick={() => handleItemClick(option)}
                        >
                            {option}
                        </li>
                    ))}
                </ul>
            </div>

            {currentIndex < questions.length && (
                <p>Selected item: {selectedItem || 'None'}</p>
            )}
            {showNextButton && (
                <button className="next-button" onClick={handleNextClick}>
                    {currentIndex > questions.length - 1 ? 'Submit' : 'Next'}
                </button>
            )}
            {result}
        </div>
    );
}

export default QuestionSeries;
