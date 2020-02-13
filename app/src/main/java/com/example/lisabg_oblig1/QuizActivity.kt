package com.example.lisabg_oblig1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private var quizPointsCounter = 0
    private var currentQuestion = 0
    private val quiz = mutableListOf<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        quiz.add(Question("There are more than 200 countries in the world", false))
        quiz.add(Question("Europe is the continent with the highest percentage of forest cover", true))
        quiz.add(Question("Asia is the continent with the most countries", false))
        quiz.add(Question("The oldest verifiable person was 122 years old", true))

        quiz_button_true.setOnClickListener {

            if (quiz[currentQuestion].answer) {
                quizPointsCounter++
            }
            quizAction()
        }

        quiz_button_false.setOnClickListener {

            if (!quiz[currentQuestion].answer) {
                quizPointsCounter++
            }
            quizAction()
        }

        quiz_button_reset.setOnClickListener {

            quiz_button_reset.isVisible = false
            quiz_button_true.isEnabled = true
            quiz_button_false.isEnabled = true
            quiz_question.text = quiz[currentQuestion].question
            quiz_num_points.text = getString(R.string.quiz_current_points_text, quizPointsCounter)
        }

    }

    private fun quizAction() {

        if (currentQuestion < quiz.size-1) {
            currentQuestion++
            quiz_question.text = quiz[currentQuestion].question
            quiz_num_points.text = getString(R.string.quiz_current_points_text, quizPointsCounter)

        } else {
            quiz_button_reset.isVisible = true
            quiz_button_true.isEnabled = false
            quiz_button_false.isEnabled = false
            quiz_question.text = getString(R.string.quiz_result_text, quizPointsCounter, quiz.size)
            quiz_num_points.text = ""

            quizPointsCounter = 0
            currentQuestion = 0
        }
    }



    override fun onStart() {
        super.onStart()

        val textViewValue = intent.extras?.getString("textEdit")
        quiz_previous_input.text = textViewValue

        quizPointsCounter = 0
        currentQuestion = 0

        quiz_question.text = quiz[currentQuestion].question
        quiz_num_points.text = getString(R.string.quiz_current_points_text, quizPointsCounter)
    }
}
