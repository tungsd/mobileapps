package com.petamind.ping.components

import android.content.Context

class AIPlayer(context: Context, shapeId: Int?, game: Game?) :
    Player(context, shapeId) {
    var game: Game?
    enum class TYPE {
        DUMP, NORMAL, SMART
    }

    var type = TYPE.SMART

    init {
        when (type) {
            TYPE.DUMP -> movVec.x = 5f
            TYPE.NORMAL -> movVec.x = 10f
            TYPE.SMART -> movVec.x = 25f
        }
        this.game = game
    }

    override fun update() {
         if (shouldUpdate) {
            super.update()
            location.offset(movVec.x, movVec.y)
            if(game?.ball?.location!!.centerX() > location.right ||
                game?.ball?.location!!.centerX() < location.left && game!!.ball.movVec.y < 0) {
                if((location.centerX() - game?.ball?.location!!.centerX()) * movVec.x > 0){
                    movVec.x = -movVec.x
                }
            } else if(location.left <= 0 || location.right >= game!!.bounds.width()){
                movVec.x = -movVec.x
            }
        }
    }
}
