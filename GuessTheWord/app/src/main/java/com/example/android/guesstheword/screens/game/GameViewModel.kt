package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel: ViewModel() {

    companion object {
        private const val DONE = 0L

        private const val ONE_SECOND = 1000L

        private const val COUNTDOWN_TIME = 60000L
    }

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val timer: CountDownTimer

    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }


    private var _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score


    private val _eventGameFinish = MutableLiveData<Boolean>()

    val eventGameFinish: LiveData<Boolean>
      get() = _eventGameFinish

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
        _word.value = ""
        _score.value = 0

        Timber.i("GameViewModel Created!")

        resetList()
        nextWord()

        timer = object: CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }
        }
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("GameViewModel Called onCleared(), Destroyed!")

        timer.cancel()
    }

    fun onSkip() {
        if (!wordList.isEmpty()) {
            _score.value = (score.value)?.minus(1)
        }
        nextWord()
    }

    fun onCorrect() {
        if (!wordList.isEmpty()) {
            _score.value = (score.value)?.plus(1)
        }
        nextWord()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            // 単語リストが空ならリセット
            resetList()

        } else {
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }
    }

    /***
     *  ゲーム終了イベント
     */
    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }
}

