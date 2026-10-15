package com.hemily.capsuladofuturo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.hemily.capsuladofuturo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String autor, titulo, anoTexto, mensagem;
    int ano, importancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        importancia = binding.seekImportancia.getProgress();

        binding.seekImportancia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                importancia = i;
                binding.txtImportancia.setText(getString(R.string.texto_importancia) + " " + importancia + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.btnSelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autor = binding.edtAutor.getText().toString().trim();
                titulo = binding.edtTitulo.getText().toString().trim();
                anoTexto = binding.edtAno.getText().toString().trim();
                mensagem = binding.edtMensagem.getText().toString().trim();

                if(autor.isEmpty()){
                    binding.edtAutor.setError(getString(R.string.erro_autor));
                    binding.edtAutor.requestFocus();
                } else if(titulo.isEmpty()){
                    binding.edtTitulo.setError(getString(R.string.erro_titulo));
                    binding.edtTitulo.requestFocus();
                } else if(anoTexto.isEmpty()){
                    binding.edtAno.setError(getString(R.string.erro_ano));
                    binding.edtAno.requestFocus();
                } else if(Integer.parseInt(anoTexto) <= 2026){
                    binding.edtAno.setError(getString(R.string.erro_ano_invalido));
                    binding.edtAno.requestFocus();
                } else if(mensagem.isEmpty()){
                    binding.edtMensagem.setError(getString(R.string.erro_mensagem));
                    binding.edtMensagem.requestFocus();
                } else if(mensagem.length() < 20){
                    binding.edtMensagem.setError(getString(R.string.erro_mensagem_curta));
                    binding.edtMensagem.requestFocus();
                } else{
                    ano = Integer.parseInt(anoTexto);
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    i.putExtra("autor", autor);
                    i.putExtra("titulo", titulo);
                    i.putExtra("ano", ano);
                    i.putExtra("mensagem", mensagem);
                    i.putExtra("importancia", importancia);
                    startActivity(i);                }
            }
        });
    }
}