package com.example.android.guesstheword.screens.game

import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel: ViewModel() {

    var word = ""

    var score = 0

    private lateinit var wordList: MutableList<String>

    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }


    init {
        Timber.i("GameViewModel Created!")

        resetList()
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("GameViewModel Called onCleared(), Destroyed!")
    }


    fun onSkip() {
        if (!wordList.isEmpty()) {
            score--
        }
        nextWord()
    }

    fun onCorrect() {
        if (!wordList.isEmpty()) {
            score++
        }
        nextWord()
    }

    private fun nextWord() {
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            word = wordList.removeAt(0)
        }
    }
}
