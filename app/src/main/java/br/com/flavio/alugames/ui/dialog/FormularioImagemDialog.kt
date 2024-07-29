package br.com.flavio.alugames.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.flavio.alugames.databinding.ActivityFormularioImagemDialogBinding
import br.com.flavio.alugames.extensions.carregaImagemCoil

class FormularioImagemDialog(private val context: Context) {

    fun mostraDialog(
        urlPadrao: String? = null,
        quandoImagemCarregada: (urlImagem: String) -> Unit
    ) {

        ActivityFormularioImagemDialogBinding.inflate(LayoutInflater.from(context)).apply {

            urlPadrao.let {
                activityFormularioDialogTxtUrl.setText(it)
                activityFormularioDialogImageview.carregaImagemCoil(it)
            }

            activityFormularioDialogBtncarregar.setOnClickListener {
                val url = activityFormularioDialogTxtUrl.text.toString()
                activityFormularioDialogImageview.carregaImagemCoil(url)

            }
            AlertDialog.Builder(context)
                .setTitle("Inclusão de Imagem")
                .setMessage("Inclua a URL da Imagem:")
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = activityFormularioDialogTxtUrl.text.toString()
                    quandoImagemCarregada(url)

                }
                .setNegativeButton("Cancelar") { _, _ ->
                }
                .show()
        }
    }
}