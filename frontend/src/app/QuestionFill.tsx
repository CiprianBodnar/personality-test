import {Question} from "./model/Question";
import {useState} from "react";

interface QuestionFillProps {
    useQuestion: Question;
}
function QuestionFill(props: QuestionFillProps) {

    const { id, question, options } = props.useQuestion;



    return (
        <div>
            <li key={id}>{question}
                <ul>
                    {
                        options.map((option: string, index: number) => (
                            <li key={index}>{option}
                            </li>
                        ))
                    }
                </ul>
            </li>

        </div>

    )
}

export default QuestionFill;
