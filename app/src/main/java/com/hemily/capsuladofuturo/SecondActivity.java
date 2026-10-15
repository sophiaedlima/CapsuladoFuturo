package com.hemily.capsuladofuturo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hemily.capsuladofuturo.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding binding;
    String autor, titulo, mensagem;
    int ano, importancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent recebendoDados = getIntent();
        autor = recebendoDados.getStringExtra("autor");
        titulo = recebendoDados.getStringExtra("titulo");
        mensagem = recebendoDados.getStringExtra("mensagem");
        ano = recebendoDados.getIntExtra("ano", 0);
        importancia = recebendoDados.getIntExtra("importancia", 0);

        binding.txtTituloCapsula.setText(titulo);
        binding.txtCriadaPor.setText(getString(R.string.texto_criada_por) + " " + autor + " " + getString(R.string.texto_aberta_em) + " " + ano + ".");
        binding.txtMensagemFutura.setText(mensagem);
        binding.txtImportanciaFinal.setText(getString(R.string.texto_importancia) + " " + importancia + "%");

        binding.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoCompartilhado = getString(R.string.texto_minha_capsula) + " " + ano + ": " + titulo + ". " + mensagem;

                Intent compartilhar = new Intent(Intent.ACTION_SEND);
                compartilhar.setType("text/plain");
                compartilhar.putExtra(Intent.EXTRA_TEXT, textoCompartilhado);
                startActivity(Intent.createChooser(compartilhar, getString(R.string.compartilhar_via)));
            }
        });
    }
}