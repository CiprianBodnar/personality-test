import React, { useState } from 'react';

interface Question {
    question: string;
    options: string[];
}

const questions: Question[] = [
    {
        question: 'Question 1: ...',
        options: ['Option A', 'Option B', 'Option C'],
    },
    {
        question: 'Question 2: ...',
        options: ['Option A', 'Option B', 'Option C'],
    },
    // Add more questions as needed
];

const QuizComponent: React.FC = () => {
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    const [selectedOptions, setSelectedOptions] = useState<string[]>([]);

    const handleOptionSelect = (option: string) => {
        const updatedSelectedOptions = [...selectedOptions];
        updatedSelectedOptions[currentQuestionIndex] = option;
        setSelectedOptions(updatedSelectedOptions);
    };

    const handleNextQuestion = () => {
        if (currentQuestionIndex < questions.length - 1) {
            setCurrentQuestionIndex(currentQuestionIndex + 1);
        }
    };

    return (
        <div>
            <h1>Introvert or Extrovert Quiz</h1>
            {currentQuestionIndex < questions.length ? (
                <div>
                    <h2>{questions[currentQuestionIndex].question}</h2>
                    <ul>
                        {questions[currentQuestionIndex].options.map((option, index) => (
                            <li key={index}>
                                <button onClick={() => handleOptionSelect(option)}>{option}</button>
                            </li>
                        ))}
                    </ul>
                    <button onClick={handleNextQuestion}>Next Question</button>
                </div>
            ) : (
                <div>
                    <h2>Quiz Completed!</h2>
                    <p>Results: {selectedOptions.join(', ')}</p>
                </div>
            )}
        </div>
    );
};

export default QuizComponent;
