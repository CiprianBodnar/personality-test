import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {Question} from "../model/Question";
import './QuestionSeries.css';
import {useLocation} from "react-router-dom"; // Import the CSS file


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
        if (currentIndex < questions.length - 1) {

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
    }, []); // Empty dependency array ensures this effect runs once on component mount

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
            <ul>
                {
                    //<QuestionFill useQuestion={questions[currentIndex]}/>
                    <li>{questions[currentIndex]?.question}
                        <ul>
                            {
                                questions[currentIndex]?.options.map((option: string, index: number) => (
                                    <li
                                        key={index}
                                        className={selectedItem === option ? 'selected' : ''}
                                        onClick={() => handleItemClick(option)}
                                    >
                                        {option}
                                    </li>
                                ))
                            }
                        </ul>
                    </li>
                }
            </ul>
            <p>Selected item: {selectedItem || 'None'}</p>
            {showNextButton && <button onClick={handleNextClick}>Next</button>}
            {result}
        </div>
    );

}

export default QuestionSeries;
