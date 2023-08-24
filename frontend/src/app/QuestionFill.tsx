import {Question} from "./model/Question";
import { useLocation } from 'react-router-dom';

interface QuestionFillProps {
    useQuestion: Question;
}
function QuestionFill(props: QuestionFillProps) {

    const { id, question, options } = props.useQuestion;

    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const userId = searchParams.get('userId');
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
