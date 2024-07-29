package br.com.flavio.alugames.extensions

import android.widget.ImageView
import br.com.flavio.alugames.R
import coil.load

fun ImageView.carregaImagemCoil(url: String?= null){
    load(url){
        fallback(R.drawable.ic_launcher_foreground)
        error(R.drawable.ic_launcher_foreground)
        placeholder(R.drawable.ic_action_refresh_24)
    }
}

