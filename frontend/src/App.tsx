import React from 'react';
import LandingPage from './app/LandingPage';
import QuestionSeries from './app/question-series/QuestionSeries';

import {BrowserRouter, Route, Routes} from "react-router-dom";


function App() {
    // @ts-ignore
    return (

        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LandingPage/>}/>
                <Route path="/questions" element={<QuestionSeries/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
