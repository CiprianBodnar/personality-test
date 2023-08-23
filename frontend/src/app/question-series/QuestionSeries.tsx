import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {Question} from "../model/Question";
import QuestionFill from "../QuestionFill";
import './QuestionSeries.css'; // Import the CSS file


function QuestionSeries() {

    const [questions, setQuestions] = useState<Question[]>([]);
    const [currentIndex, setCurrentIndex] = useState(0);
    const [showNextButton, setShowNextButton] = useState(true);
    const [selectedItem, setSelectedItem] = useState<string | null>(null);
    const handleItemClick = (item: string) => {
        setSelectedItem(item);
    };
    const handleNextClick = async () => {
        if (currentIndex < questions.length - 1) {

            let questionId = questions[currentIndex].id;
            let answer = selectedItem;
            let userId = 1;

            const response = await axios.post('http://localhost:8090/api/answers/create', null,{
                params: {
                    questionId: questionId,
                    answer: answer,
                    userId: userId
                }
            });



            setCurrentIndex(currentIndex + 1);
        } else {
            setShowNextButton(false);
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
                    <li >{questions[currentIndex]?.question}
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
        </div>
    );

}

export default QuestionSeries;
