package com.example.gamekertasguntingbatu

import android.content.ContentValues.TAG
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.gamekertasguntingbatu.R.color.*
import com.example.gamekertasguntingbatu.databinding.ActivityMainBinding
import kotlin.random.Random

private lateinit var binding: ActivityMainBinding

var timer = 1
var playerChoice = 0
var computerChoice = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        hideSystemUI(MainActivity())

        setContentView(binding.root)

        whichButtonClick()


    }


    private fun whichButtonClick() {

        binding.ivPlayer1Rock.setOnClickListener {
            Log.d(TAG, "initButton: Pemain 1 Click BATU")


            playerChoice = 1

            showPicRockPlayer1()

            computerRandom()

//            playSound()


        }

        binding.ivPlayer1Paper.setOnClickListener {
            Log.d(TAG, "initButton: Pemain 1 Click KERTAS")


            playerChoice = 2

            showPicPaperPlayer1()

            computerRandom()

        }

        binding.ivPlayer1Scissor.setOnClickListener {
            Log.d(TAG, "initButton: Pemain 1 Click GUNTING")


            playerChoice = 3

            showPicScissorPlayer1()

            computerRandom()

        }

        binding.ivRefresh.setOnClickListener {

            Log.d(TAG, "initButton: Pemain 1 Click REFRESH->RESTART")

            setRestart()

            var clkRotate = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)

            binding.ivRefresh.startAnimation(clkRotate)
        }
    }



    private fun setTextDraw() {
        binding.tvResult.text = " D R A W !"
        with(binding) {
            tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50F)
            tvResult.setTextColor(Color.parseColor("#FFFFFFFF"))
            tvResult.setBackgroundColor(Color.parseColor("#0070c0"))
        }    }

    private fun setRestart() {
        timer = 1
        playerChoice = 0
        computerChoice = 0

        setTextVs()

        binding.ivPlayer1Paper.isVisible = true
        binding.ivPlayer1Rock.isVisible = true
        binding.ivPlayer1Scissor.isVisible = true
        binding.ivPlayer1Paper.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        binding.ivPlayer1Rock.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        binding.ivPlayer1Scissor.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        binding.ivPlayer2Rock.isVisible = true
        binding.ivPlayer2Paper.isVisible = true
        binding.ivPlayer2Scissor.isVisible = true
        binding.ivPlayer2Rock.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        binding.ivPlayer2Paper.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        binding.ivPlayer2Scissor.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
    }

    private fun setTextPlayerComWin() {
        binding.tvResult.text = "C O M \n Menang!"
        with(binding) {
            tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50F)
            tvResult.setTextColor(Color.parseColor("#FFFFFFFF"))
            tvResult.setBackgroundColor(Color.parseColor("#92d050"))
        }
    }

    private fun setTextPlayer1Win() {
        binding.tvResult.text = "Pemain 1 \n Menang!"
        with(binding) {
            tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50F)
            tvResult.setTextColor(Color.parseColor("#FFFFFFFF"))
            tvResult.setBackgroundColor(Color.parseColor("#92d050"))
        }
    }


    private fun setTextVs() {
        binding.tvResult.text = "V S"
        binding.tvResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50F)
        binding.tvResult.setTextColor(Color.parseColor("#FA0202"))
        binding.tvResult.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
    }

    private fun computerRandom() {

        if (timer == 1) {

            computerChoice = Random.nextInt(1, 4)



            when (computerChoice) {
                1 -> {
                    showPicRockPlayer2()
                    comparePlayerVsCom()
                }
                2 -> {
                    showPicPaperPlayer2()
                    comparePlayerVsCom()
                }
                3 -> {
                    showPicScissorPlayer2()
                    comparePlayerVsCom()

                }
                else -> Toast.makeText(this, "Timer = 0", Toast.LENGTH_LONG)
                    .show()
            }

        }


    }

    private fun comparePlayerVsCom() {
        if ((playerChoice == 3) && (computerChoice == 3)) {
            setTextDraw()

        } else if ((playerChoice == 2) && (computerChoice == 2)) {
            setTextDraw()

        } else if ((playerChoice == 1) && (computerChoice == 1)) {
            setTextDraw()
        }
  //         GUNTING VS KERTAS : GUNTING WIN
        else if ((playerChoice == 3) && (computerChoice == 2)) {

            setTextPlayer1Win()

        }
//        GUNTING VS BATU : BATU WIN

        else if ((playerChoice == 3) && (computerChoice == 1)) {

            setTextPlayerComWin()

        }
//        BATU VS GUNTING
        else if ((playerChoice == 1) && (computerChoice == 3)) {
            setTextPlayer1Win()

        }
//        BATU VS KERTAS : KERTAS MENANG
        else if ((playerChoice == 1) && (computerChoice == 2)) {
            setTextPlayerComWin()

        }
//        KERTAS VS GUNTING : GUNTING WIN
        else if ((playerChoice == 2) && (computerChoice == 3)) {
            setTextPlayerComWin()

        }
//        KERTAS VS BATU : kertas WIN
        else if ((playerChoice == 2) && (computerChoice == 1)) {

            setTextPlayer1Win()

        } else {
//            Log.d("OUTPUT", "PLAYER CHOICE : ${playerChoice} + COM = $computerChoice")
            Toast.makeText(
                this,
                "wow PLAYER CHOICE : ${playerChoice} + COM = $computerChoice",
                Toast.LENGTH_LONG
            )
                .show()

    }
    }
//    private fun playSound() {
//        val mediaPlayerRun : MediaPlayer = MediaPlayer.create(this, R.raw.sound_select)
//
//        mediaPlayerRun.start()
//    }


    private fun showPicScissorPlayer2() {
        binding.ivPlayer2Rock.isVisible = false
        binding.ivPlayer2Paper.isVisible = false
        binding.ivPlayer2Scissor.setBackgroundColor(Color.parseColor("#c3dae9"))
    }

    private fun showPicPaperPlayer2() {
        binding.ivPlayer2Rock.isVisible = false
        binding.ivPlayer2Scissor.isVisible = false
        binding.ivPlayer2Paper.setBackgroundColor(Color.parseColor("#c3dae9"))
    }

    private fun showPicRockPlayer2() {
        binding.ivPlayer2Paper.isVisible = false
        binding.ivPlayer2Scissor.isVisible = false
        binding.ivPlayer2Rock.setBackgroundColor(Color.parseColor("#c3dae9"))
    }

    private fun showPicRockPlayer1() {
        binding.ivPlayer1Scissor.isVisible = false
        binding.ivPlayer1Paper.isVisible = false
        binding.ivPlayer1Rock.setBackgroundColor(Color.parseColor("#c3dae9"))

    }

    private fun showPicPaperPlayer1() {
        binding.ivPlayer1Scissor.isVisible = false
        binding.ivPlayer1Rock.isVisible = false
        binding.ivPlayer1Paper.setBackgroundColor(Color.parseColor("#c3dae9"))
    }

    private fun showPicScissorPlayer1() {
        binding.ivPlayer1Paper.isVisible = false
        binding.ivPlayer1Rock.isVisible = false
        binding.ivPlayer1Scissor.setBackgroundColor(Color.parseColor("#c3dae9"))
    }

    //    FULL SCREEN SHOW
    private fun hideSystemUI(mainActivity: MainActivity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            mainActivity.window.insetsController?.let {
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                it.hide(WindowInsets.Type.systemBars())
            }
        } else {
            @Suppress("DEPRECATION")
            mainActivity.window?.apply { decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN }
        }

    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName


    }
}
